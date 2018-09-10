package com.example.intouch.model.impls;

import java.util.ArrayList;
import java.util.List;

import com.example.intouch.constans.Constants;
import com.example.intouch.constans.ErrorMessage;
import com.example.intouch.exceptions.DAOException;
import com.example.intouch.ifaces.IUserDAO;
import com.example.intouch.model.beans.User;

public class MemoryUserImpl implements IUserDAO {

	private static int userId = 0;
	private static List<User> users = new ArrayList<>();

	static {
		users.add(new User(userId++, "admin", "admin", "admin@example.com"));
		users.add(new User(userId++, "user", "user", "user@example.com"));
		users.add(new User(userId++, "anotherUser", "anotherUser", "anotherUser@example.com"));
	}

	@Override
	public User getUser(String login, String password) throws DAOException {
		User resultUser = null;
		for (User currentUser : users) {
			if (currentUser.getLogin().equals(login)) {
				if (currentUser.getPassword().equals(password)) {
					resultUser = currentUser;
					break;
				}
			}
		}
		return resultUser;
	}

	@Override
	public User setUser(String login, String password, String email) throws DAOException {
		if (Constants.USER_GUEST.toLowerCase().equals(login.toLowerCase())) {
			throw new DAOException(ErrorMessage.ERROR_LOGIN);
		}
		User resultUser = null;
		synchronized (MemoryUserImpl.class) {

			for (User currentUser : users) {
				if (currentUser.getLogin().equals(login)) {
					throw new DAOException(ErrorMessage.ERROR_LOGIN);
				}
			}
			resultUser = new User(userId++, login, password, email);
			users.add(resultUser);
		}

		return resultUser;
	}
}
