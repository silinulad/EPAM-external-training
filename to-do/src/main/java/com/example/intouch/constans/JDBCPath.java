package com.example.intouch.constans;

import java.io.File;

public class JDBCPath {

	private static final String PROPERTIES_FOLDER = "WEB-INF" + File.separator + "classes";
	private static String pathProperties;
	private static JDBCPath instance;

	private JDBCPath() {
	}

	public static void initialize(String realPath) {
		if (!Constants.EMPTY_STRING.equals(realPath)) {
			synchronized (JDBCPath.class) {
				if (instance == null) {
					pathProperties = realPath + File.separator + PROPERTIES_FOLDER + File.separator;
					instance = new JDBCPath();
				}
			}
		}
	}

	public static String getPathJDBC() {
		if (pathProperties == null) {
			throw new RuntimeException(ErrorMessage.ERROR_INIT_PATH);
		}
		return pathProperties;
	}
}
