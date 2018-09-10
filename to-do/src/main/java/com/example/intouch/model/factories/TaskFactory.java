package com.example.intouch.model.factories;

import com.example.intouch.ifaces.ITaskDAO;
import com.example.intouch.model.impls.DBTaskImpl;

public class TaskFactory {
    public static ITaskDAO getClassFromFactory() {
    	return new DBTaskImpl();
    }
}
