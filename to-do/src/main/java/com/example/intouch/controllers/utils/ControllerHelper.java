package com.example.intouch.controllers.utils;

import javax.servlet.http.HttpServletRequest;

import com.example.intouch.enums.ActionType;

public class ControllerHelper {

	private ControllerHelper() {
	}

	public static final ActionType getActionType(HttpServletRequest request) {
		for (ActionType currentAction : ActionType.values()) {
			if (request.getParameter(currentAction.toString().toLowerCase()) != null) {
				return currentAction;
			}
		}
		return null;
	}

	

}
