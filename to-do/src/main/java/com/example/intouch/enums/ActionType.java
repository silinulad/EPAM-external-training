package com.example.intouch.enums;

import com.example.intouch.constans.ConstantsSQL;
import com.example.intouch.constans.Controllers;

public enum ActionType {

	FIX {
		@Override
		public String getController() {
			return Controllers.DISPLACE_CONTROLLER;
		}

		@Override
		public String getActionQuery() {
			return ConstantsSQL.PS_FIX_TASK;
		}
	},
	RECYCLE {
		@Override
		public String getController() {
			return Controllers.DISPLACE_CONTROLLER;
		}

		@Override
		public String getActionQuery() {
			return ConstantsSQL.PS_RECYCLED_TASK;
		}
	},
	RESTORE {
		@Override
		public String getController() {
			return Controllers.DISPLACE_CONTROLLER;
		}

		@Override
		public String getActionQuery() {
			return ConstantsSQL.PS_RESTORE_TASK;
		}
	},
	REMOVE {
		@Override
		public String getController() {
			return Controllers.DISPLACE_CONTROLLER;
		}

		@Override
		public String getActionQuery() {
			return ConstantsSQL.PS_REMOVE_TASK;
		}
	};

	public abstract String getController();

	public abstract String getActionQuery();

}
