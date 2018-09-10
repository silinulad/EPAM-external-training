package by.gsu.epamlab.database.dataloaders;

import by.gsu.epamlab.beans.Result;
import by.gsu.epamlab.database.jdbc.ConnectorDB;
import by.gsu.epamlab.interfaces.IResultCreatable;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

public class DatabaseLoader {
	public static ConnectorDB INSTANCE_CONN = ConnectorDB.INSTANCE;

	private static enum InsertEnum {
		FIRST_POSITION(1), SECOND_POSITION(2), THIRD_POSITION(3), FOURTH_POSITION(4);
		private int index;

		private InsertEnum(int index) {
			this.index = index;
		}
	}

	public static void loadResultsIntoDatabase(IResultCreatable<Result> reader)
			throws IOException, SQLException, ParseException {

		final String DELETE_LOGINS = "DELETE FROM logins";
		final String DELETE_TESTS = "DELETE FROM tests";
		final String DELETE_RESULTS = "DELETE FROM results";
		final String RESET_AUTOINCREMENT_LOGINS = "ALTER TABLE logins AUTO_INCREMENT = 1";
		final String RESET_AUTOINCREMENT_TESTS = "ALTER TABLE tests AUTO_INCREMENT = 1";

		final String INSERT_INTO_LOGINS = "INSERT INTO logins (name) VALUES(?)";
		final String INSERT_INTO_TESTS = "INSERT INTO tests (name) VALUES(?)";
		final String INSERT_INTO_RESULTS = "INSERT INTO results SET loginId = (SELECT idLogin FROM logins WHERE name = ?), testId = (SELECT idTest FROM tests WHERE name = ?), dat = ?, mark = ?";

		final String SELECT_LOGIN_ID = "SELECT name FROM logins WHERE name = ?";
		final String SELECT_TEST_ID = "SELECT name FROM tests WHERE name = ?";

		Connection connect = null;
		Statement st = null;
		PreparedStatement results = null;
		PreparedStatement tests = null;
		PreparedStatement logins = null;
		PreparedStatement idLogin = null;
		PreparedStatement idTest = null;

		try {
			connect = INSTANCE_CONN.getConnection();
			st = connect.createStatement();
			st.executeUpdate(DELETE_LOGINS);
			st.executeUpdate(DELETE_TESTS);
			st.executeUpdate(DELETE_RESULTS);
			st.executeUpdate(RESET_AUTOINCREMENT_LOGINS);
			st.executeUpdate(RESET_AUTOINCREMENT_TESTS);
			while (reader.hasLine()) {

				Result result = reader.getResult();
				String login = result.getLogin();
				String test = result.getTest();
				Date date = result.getDate();
				int mark = result.getMark().getMark();

				executeSatement(connect, login, SELECT_LOGIN_ID, INSERT_INTO_LOGINS, logins, idLogin);
				executeSatement(connect, test, SELECT_TEST_ID, INSERT_INTO_TESTS, tests, idTest);

				results = connect.prepareStatement(INSERT_INTO_RESULTS);
				results.setString(InsertEnum.FIRST_POSITION.index, login);
				results.setString(InsertEnum.SECOND_POSITION.index, test);
				results.setDate(InsertEnum.THIRD_POSITION.index, date);
				results.setInt(InsertEnum.FOURTH_POSITION.index, mark);
				results.executeUpdate();

			}
		} catch (SQLException e) {
			throw new IllegalStateException(e);
		} finally {
			reader.closeFileSourse();
			INSTANCE_CONN.closeStatement(results, logins, tests, idLogin, idTest, st);
			INSTANCE_CONN.closeConnection();
		}
	}

	private static void executeSatement(Connection cn, final String name, final String SELECT_SQL,
			final String INSERT_SQL, PreparedStatement ps, PreparedStatement psId) throws SQLException {
		ps = cn.prepareStatement(SELECT_SQL);
		ps.setString(InsertEnum.FIRST_POSITION.index, name);
		psId = cn.prepareStatement(INSERT_SQL);
		ResultSet resultSetLogin = ps.executeQuery();
		boolean loginExists = resultSetLogin.next();
		if (!loginExists) {
			psId.setString(InsertEnum.FIRST_POSITION.index, name);
			psId.executeUpdate();
		}

	}

}
