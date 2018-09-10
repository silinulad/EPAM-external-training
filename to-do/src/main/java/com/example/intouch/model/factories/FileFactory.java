package com.example.intouch.model.factories;

import com.example.intouch.ifaces.IFileDAO;
import com.example.intouch.model.impls.DBFileImpl;

public class FileFactory {
    public static IFileDAO getClassFromFactory() {
    	return new DBFileImpl();
    }
}
