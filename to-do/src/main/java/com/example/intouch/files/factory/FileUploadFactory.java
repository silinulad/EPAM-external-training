package com.example.intouch.files.factory;

import javax.servlet.http.HttpServletRequest;

import com.example.intouch.files.utils.AbstractFileHandler;
import com.example.intouch.files.utils.FileUploadHandlerNew;
import com.example.intouch.files.utils.FileUploadHandlerOld;
import com.example.intouch.model.beans.User;

public class FileUploadFactory {
	private static WayUpload wayUpload;

	public static void setWayUpload(String way) {
		wayUpload = WayUpload.valueOf(way.toUpperCase());
	}

	public static String getWayUpload() {
		return wayUpload.name();
	}

	public static AbstractFileHandler getClassFromFactory(User user, HttpServletRequest request) {
		return wayUpload.getFileUploader(user, request);

	}

	private enum WayUpload {
		// for Servlet API before version 2.5
		OLD {
			@Override
			AbstractFileHandler getFileUploader(User user, HttpServletRequest request) {
				return new FileUploadHandlerOld(user, request);
			}
		},
		// for Servlet API since version 3.0
		NEW {
			@Override
			AbstractFileHandler getFileUploader(User user, HttpServletRequest request) {
				return new FileUploadHandlerNew(user, request);
			}
		};
		abstract AbstractFileHandler getFileUploader(User user, HttpServletRequest request);
	}
}
