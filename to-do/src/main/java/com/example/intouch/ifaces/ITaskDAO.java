package com.example.intouch.ifaces;

import java.util.Date;
import java.util.List;

import com.example.intouch.enums.ActionType;
import com.example.intouch.enums.SectionType;
import com.example.intouch.exceptions.DAOException;
import com.example.intouch.model.beans.Task;
import com.example.intouch.model.beans.User;

public interface ITaskDAO {

	public Task getTask(User user, int id) throws DAOException;

	public List<Task> getTasks(User user, SectionType section) throws DAOException;

	public void addTask(String content, Date date, User user) throws DAOException;

	public void displaceTasks(List<Task> selectedTasks, ActionType action) throws DAOException;

}
