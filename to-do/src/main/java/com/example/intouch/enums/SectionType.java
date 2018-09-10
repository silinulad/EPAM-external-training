package com.example.intouch.enums;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.example.intouch.constans.Constants;
import com.example.intouch.constans.ConstantsSQL;
import com.example.intouch.constans.ErrorMessage;
import com.example.intouch.exceptions.ValidationException;

public enum SectionType {

	TODAY {
		@Override
		public String getSelectTaskQuery() {
			return ConstantsSQL.SELECT_TODAY_TASK;
		}

		@Override
		public Date getDate(String date) throws ValidationException {
			date = Constants.EMPTY_STRING;
			Date todayDate = new Date();
			return todayDate;
		}
	},
	TOMORROW {
		@Override
		public String getSelectTaskQuery() {
			return ConstantsSQL.SELECT_TOMORROW_TASK;
		}

		@Override
		public Date getDate(String date) throws ValidationException {
			date = Constants.EMPTY_STRING;
			final int DAY_OFFSET = 1;
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DATE, DAY_OFFSET);
			Date tomorrowDate = calendar.getTime();
			return tomorrowDate;
		}
	},
	SOMEDAY {
		@Override
		public String getSelectTaskQuery() {
			return ConstantsSQL.SELECT_SOMEDAY_TASK;
		}

		@Override
		public Date getDate(String date) throws ValidationException {
			final String FORMAT_DATE = "yyyy-MM-dd";

			date = date.trim();
			if (Constants.EMPTY_STRING.equals(date)) {
				throw new ValidationException(ErrorMessage.ERROR_DATE_EMPTY);
			}
			try {
				DateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
				formatter.setLenient(false);
				return formatter.parse(date);
			} catch (ParseException e) {
				throw new ValidationException(e.getMessage());
			}
		}
	},
	FIXED {
		@Override
		public String getSelectTaskQuery() {
			return ConstantsSQL.SELECT_FIXED_TASK;
		}

		@Override
		public Date getDate(String date) throws ValidationException {
			throw new UnsupportedOperationException();
		}
	},
	RECYCLE {
		@Override
		public String getSelectTaskQuery() {
			return ConstantsSQL.SELECT_RECYCLE_TASK;
		}

		@Override
		public Date getDate(String date) throws ValidationException {
			throw new UnsupportedOperationException();
		}
	};

	public abstract String getSelectTaskQuery();

	public abstract Date getDate(String date) throws ValidationException;

}
