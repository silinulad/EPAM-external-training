package by.gsu.epamlab.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.sql.Date;
import java.text.ParseException;
import java.util.*;

import org.xml.sax.SAXException;

import by.gsu.epamlab.beans.Result;
import by.gsu.epamlab.database.dataloaders.DatabaseLoader;
import by.gsu.epamlab.database.jdbc.ConnectorDB;
import by.gsu.epamlab.factories.ResultFactory;
import by.gsu.epamlab.interfaces.IResultCreatable;

public class TaskExecution {

	private enum FieldEnum {
		LOGIN(1), TEST(2), DATE(3), MARK(4);
		private int index;

		private FieldEnum(int index) {
			this.index = index;
		}
	}

	private static MarkInt getMarkFromInt(int mark, ResultFactory resultFactory) {
		final MarkInt MARK;
		if (mark % 10 != 0) {
			MARK = resultFactory.getMarkFromFactory(mark);
		} else {
			MARK = new MarkInt(mark);
		}
		return MARK;
	}

	public static void executeTask(ResultFactory resultFactory, String fileName) {
		ConnectorDB instanceConn = ConnectorDB.INSTANCE;
		final int AVG_MARK_INDEX = 2;
		final String DELIMETER = ";";
		final String SELECT_NAME_MARK = "SELECT logins.name AS login, ROUND( AVG( results.mark/10 ) , 2 ) AS mark FROM logins, tests, results WHERE loginId = idLogin GROUP BY logins.name ORDER BY mark DESC";
		final String SELECT_RESULTS = "SELECT logins.name AS login, tests.name AS test, dat AS date, mark FROM results, logins, tests WHERE loginId=idLogin AND idTest=testId ORDER BY dat";
		Connection connect = null;
		Statement st = null;

		try {
			connect = instanceConn.getConnection();
			IResultCreatable<Result> reader = resultFactory.getResultDAOFromFactory(fileName);
			DatabaseLoader.loadResultsIntoDatabase(reader);

			// 3
			st = connect.createStatement();
			ResultSet rs = st.executeQuery(SELECT_NAME_MARK);
			ResultSetMetaData rsmd = rs.getMetaData();
			final String LOGIN_NAME = rsmd.getColumnName(FieldEnum.LOGIN.index);
			final String AVERAGE_MARK = rsmd.getColumnName(AVG_MARK_INDEX);
			System.out.println(LOGIN_NAME + DELIMETER + AVERAGE_MARK);
			while (rs.next()) {
				System.out.println(rs.getString(FieldEnum.LOGIN.toString().toLowerCase()) + DELIMETER
						+ rs.getString(FieldEnum.MARK.toString().toLowerCase()));
			}

			System.out.println("--------------------");

			// 4
			final List<Result> listResults = new LinkedList<>();

			rs = st.executeQuery(SELECT_RESULTS);

			Calendar calendar = Calendar.getInstance();
			int currentMonth = calendar.get(Calendar.MONTH);

			while (rs.next()) {
				calendar.setTime(rs.getDate(FieldEnum.DATE.index));
				final int month = calendar.get(Calendar.MONTH);
				if (currentMonth == month) {
					final String LOGIN = rs.getString(FieldEnum.LOGIN.toString().toLowerCase());
					final String TEST = rs.getString(FieldEnum.TEST.toString().toLowerCase());
					final Date DATE = rs.getDate(FieldEnum.DATE.toString().toLowerCase());
					final int MARK_INT = rs.getInt(FieldEnum.MARK.toString().toLowerCase());
					final MarkInt MARK = getMarkFromInt(MARK_INT, resultFactory);

					Result result = resultFactory.getResultFromFactory(LOGIN, TEST, DATE, MARK);

					listResults.add(result);
				}
			}

			for (Result result : listResults) {
				System.out.println(result);
			}

			System.out.println("--------------------");
			// 5
			if (!listResults.isEmpty()) {
				ListIterator<Result> iterator = listResults.listIterator(listResults.size() - 1);
				Date lastDate = iterator.next().getDate();
				while (iterator.hasPrevious()) {
					final Result latestDate = iterator.previous();
					if (latestDate.getDate().equals(lastDate)) {
						System.out.println(latestDate);
					} else {
						break;
					}
				}
			}

		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (IOException | SQLException | SAXException | ParseException e) {
			System.err.println(e);
		}
	}

}
