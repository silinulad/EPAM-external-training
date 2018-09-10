package com.example.intouch.files.utils.beans;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileInfo {

	private String fileName;
	private int indexStartData;
	private int indexLastData;

	public FileInfo() {
	}

	public FileInfo(int indexStartData, int indexLastData, String fileName) {
		this.indexStartData = indexStartData;
		this.indexLastData = indexLastData;
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}

	public int getIndexStartData() {
		return indexStartData;
	}

	public int getIndexLastData() {
		return indexLastData;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setIndexStartData(int indexStartData) {
		this.indexStartData = indexStartData;
	}

	public void setIndexLastData(int indexLastData) {
		this.indexLastData = indexLastData;
	}

	public void saveFile(byte[] data, String directory) throws IOException {
		FileOutputStream outputStream = null;
		try {
			int length = indexLastData - indexStartData;
			creatDirectory(directory);
			File file = new File(directory + fileName);
			outputStream = new FileOutputStream(file);
			outputStream.write(data, indexStartData, length);
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException ex) {
					throw ex;
				}
			}
		}
	}

	private void creatDirectory(String pathDir) throws IOException {
		Path path = Paths.get(pathDir);
		Files.createDirectories(path);
	}
}
