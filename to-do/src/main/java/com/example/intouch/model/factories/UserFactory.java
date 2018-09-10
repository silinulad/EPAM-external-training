package com.example.intouch.model.factories;

import com.example.intouch.ifaces.IUserDAO;
import com.example.intouch.model.impls.DBUserImpl;

public class UserFactory {

	public static IUserDAO getClassFromFactory() {
		return new DBUserImpl();
		// return new MemoryUserImpl();
	}
}
