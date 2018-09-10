package com.example.intouch.files.utils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.intouch.constans.Constants;
import com.example.intouch.constans.ErrorMessage;
import com.example.intouch.constans.UploadPath;
import com.example.intouch.exceptions.UploadException;
import com.example.intouch.files.utils.beans.AddressPartHeader;
import com.example.intouch.files.utils.beans.FileInfo;
import com.example.intouch.model.beans.User;

public class FileUploadHandlerOld extends AbstractFileHandler {

	private static final int DATA_EMPTY = -1;
	private static final String HEADER_DATA_DELIMETER = "\r\n\r\n";
	private static final int OFFSET_HEADER_DATA_DELIMETER = HEADER_DATA_DELIMETER.length();
	private static final int START_INDEX = 0;

	private final User user;
	private final HttpServletRequest request;

	public FileUploadHandlerOld(User user, HttpServletRequest request) {
		this.user = user;
		this.request = request;
	}

	@Override
	public void upload() throws IOException, UploadException {
		final String CONTENT_TYPE_REQUEST = "Content-Type";

		// Storing the contents of the request
		byte[] realRequestData;

		// Maximum amount of information stored
		final int MAX_DATA_SIZE = 1024 * 1024 * 30; // ~31 mb

		InputStream inputStream = request.getInputStream();
		BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, MAX_DATA_SIZE);

		// temporary storage of the contents of the request
		byte[] maxRequestData = new byte[MAX_DATA_SIZE];
		int buffer = 0;
		int realDataSize = 0;
		while ((buffer = bufferedInputStream.read()) != DATA_EMPTY) {
			maxRequestData[realDataSize] = (byte) buffer;
			realDataSize++;
		}
		realRequestData = new byte[realDataSize];
		System.arraycopy(maxRequestData, 0, realRequestData, 0, realDataSize);

		String contentTypeHeader = request.getHeader(CONTENT_TYPE_REQUEST);
		String boundary = getBoundary(contentTypeHeader);

		List<AddressPartHeader> parts = getPartsHeader(realRequestData, boundary);

		idTask = getHiddenFieldIdTask(parts, realRequestData, Constants.ID_TASK_UPLOAD);
		if (idTask == Constants.ID_EMPTY) {
			throw new UploadException(ErrorMessage.NO_SELECTED_TASK);
		}
		FileInfo fileAddress = getFileInfo(parts, realRequestData, idTask);
		if (fileAddress == null) {
			throw new UploadException(ErrorMessage.NO_SELECTED_FILE);
		}

		String fileCatalog = getCurrentFileCatalog(UploadPath.getPathUpload(), user.getId(), idTask);
		fileAddress.saveFile(realRequestData, fileCatalog);
		fileName = fileAddress.getFileName();
	}

	private FileInfo getFileInfo(List<AddressPartHeader> parts, byte[] requestData, int idUploadTask)
			throws UnsupportedEncodingException {

		final char QUOTE = '"';
		final int FIRST_QUOTE = 1;

		final String LABEL_ID_TASK = " name=";
		final String LABEL_FILENAME = "filename=";
		final int OFFSET_ID_TASK = LABEL_ID_TASK.length() + FIRST_QUOTE;
		final int OFFSET_LABEL_FILENAME = LABEL_FILENAME.length() + FIRST_QUOTE;

		FileInfo resultFile = null;
		for (AddressPartHeader currentPart : parts) {
			String currentDataPart = new String(requestData, currentPart.getStartIndex(),
					currentPart.getLastIndex() - currentPart.getStartIndex(), "UTF-8");
			int indexStartDelim = currentDataPart.indexOf(HEADER_DATA_DELIMETER, START_INDEX);
			String header = currentDataPart.substring(START_INDEX, indexStartDelim);

			int indexLabelId;
			if ((indexLabelId = header.indexOf(LABEL_ID_TASK)) != DATA_EMPTY) {
				int indexLabelFileName;
				if ((indexLabelFileName = header.indexOf(LABEL_FILENAME)) != DATA_EMPTY) {

					int endIdIndex = header.indexOf((int) QUOTE, indexLabelId + OFFSET_ID_TASK);
					String rawIdTask = header.substring(indexLabelId + OFFSET_ID_TASK, endIdIndex);

					int endFilenameIndex = header.indexOf((int) QUOTE, indexLabelFileName + OFFSET_LABEL_FILENAME);
					String rawFilename = header.substring(indexLabelFileName + OFFSET_LABEL_FILENAME, endFilenameIndex);

					int curentIdTask = Integer.parseInt(rawIdTask);
					if (curentIdTask == idUploadTask) {
						if (!rawFilename.isEmpty()) {
							int offsetStartFile = currentPart.getStartIndex() + indexStartDelim
									+ OFFSET_HEADER_DATA_DELIMETER;
							// 2 unnecessary character before the border
							int offsetEndFile = currentPart.getLastIndex() - 2;
							resultFile = new FileInfo(offsetStartFile, offsetEndFile, rawFilename);
						}
						break;
					}
				}
			}
		}
		return resultFile;
	}

	private int getHiddenFieldIdTask(List<AddressPartHeader> parts, byte[] requestData, String fieldUploadIdTask) {

		final String HEADER_FIELD_UPLOAD = "name=\"" + fieldUploadIdTask + "\"";
		final int OFFSET_FIELD_NAME = HEADER_FIELD_UPLOAD.length();

		for (AddressPartHeader currentPart : parts) {

			String currentDataPart = new String(requestData, currentPart.getStartIndex(),
					currentPart.getLastIndex() - currentPart.getStartIndex());
			int indexStartDelim = currentDataPart.indexOf(HEADER_DATA_DELIMETER, START_INDEX);
			String header = currentDataPart.substring(START_INDEX, indexStartDelim);

			int indexStartName;
			if ((indexStartName = header.indexOf(HEADER_FIELD_UPLOAD)) != DATA_EMPTY) {
				int startData = indexStartName + OFFSET_FIELD_NAME + OFFSET_HEADER_DATA_DELIMETER;
				String rawId = currentDataPart.substring(startData, currentDataPart.length());
				return Integer.parseInt(rawId.trim());
			}
		}

		return Constants.ID_EMPTY;
	}

	private List<AddressPartHeader> getPartsHeader(byte[] requestData, String boundary) {

		final int INITIAL_VALUE = 0;

		String requestString = new String(requestData);

		List<AddressPartHeader> parts = new ArrayList<>();
		int i = INITIAL_VALUE;
		int prevIndex = INITIAL_VALUE;
		int tmpIndex = INITIAL_VALUE;
		while ((tmpIndex = requestString.indexOf(boundary, tmpIndex)) != DATA_EMPTY) {
			if (i != INITIAL_VALUE) {
				AddressPartHeader currentPart = new AddressPartHeader(prevIndex, tmpIndex);
				parts.add(currentPart);
			}
			tmpIndex += boundary.length();
			prevIndex = tmpIndex;
			i++;
		}
		return parts;
	}

	private String getBoundary(String contentTypeHeader) {
		// real boundary on the two characters '-' long.
		final String EXTRA_STRING_BOUNDARY = "--";
		final String LABLE_BOUNDARY = "boundary=";
		final int OFFESET = LABLE_BOUNDARY.length();
		final int START_INDEX = contentTypeHeader.lastIndexOf(LABLE_BOUNDARY);
		String rawBoundary = contentTypeHeader.substring(START_INDEX + OFFESET);
		return EXTRA_STRING_BOUNDARY + rawBoundary;
	}
}
