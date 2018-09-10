package com.example.intouch.constans;

import java.io.File;

public class UploadPath {

	private static final String UPLOAD_FOLDER = "upload";
	private static String pathUpload;
	private static UploadPath instance;

	private UploadPath() {
	}

	public static void initialize(String realPath) {
		if (!Constants.EMPTY_STRING.equals(realPath)) {
			synchronized (UploadPath.class) {
				if (instance == null) {
					pathUpload = realPath + File.separator + UPLOAD_FOLDER + File.separator;
					instance = new UploadPath();
				}
			}
		}
	}

	public static String getPathUpload() {
		if (pathUpload == null) {
			throw new RuntimeException(ErrorMessage.ERROR_INIT_PATH);
		}
		return pathUpload;
	}
}
