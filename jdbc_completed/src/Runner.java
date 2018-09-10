
import java.sql.*;
import java.util.*;

import by.gsu.epamlab.beans.NumLen;

public class Runner {
	private static final String LENGTH_COLUMN = "len";
	private static final String NUMBER_COLUMN = "num";
	private static final String DELIMETER = ";";

	private static void printTable(ResultSet rs, final String message) throws SQLException {
		System.out.println(message);
		while (rs.next()) {
			System.out.println(rs.getInt(LENGTH_COLUMN) + DELIMETER + rs.getInt(NUMBER_COLUMN));
		}
	}

	public static void main(String[] args) {
		final String DB_URL = "jdbc:mysql://localhost/segments";
		final String USER = "epamlab";
		final String PASSWORD = "111";
		final String SELECT_COORDINATES = "SELECT round(abs(x1-x2)) as len, count(*) as num FROM coordinates group by len order by len";
		final String DELETE_FREQUENCIES = "DELETE FROM frequencies";
		final String INSERT_INTO_FREQUENCIES = "INSERT INTO frequencies values(?,?)";
		final String SELECT_FREQUENCIES_WHERE = "SELECT * FROM frequencies WHERE len > num";
		final String SELECT_FREQUENCIES = "SELECT * FROM frequencies";

		// load a driver and connect to a database
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			List<NumLen> arraySegments = new ArrayList<>();
			try (Connection cn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
					Statement st = cn.createStatement();
					PreparedStatement ps = cn.prepareStatement(INSERT_INTO_FREQUENCIES)) {
				
				ResultSet rs = st.executeQuery(SELECT_COORDINATES);
				// 1 - form a list of len;num from the coordinate table
				while (rs.next()) {
					final int LEN = rs.getInt(LENGTH_COLUMN);
					final int NUM = rs.getInt(NUMBER_COLUMN);
					arraySegments.add(new NumLen(LEN, NUM));
				}
				final String MESSAGE_1 = "the list after cerated";
				System.out.println(MESSAGE_1);
				for (NumLen numLen : arraySegments) {
					System.out.println(numLen);
				}
				// 2 - delete all records from the frequencies table
				st.execute(DELETE_FREQUENCIES);

				// 3 - upload the list into the frequencies table
				for (NumLen numLen : arraySegments) {
					final int LEN_INDEX = 1;
					final int NUM_INDEX = 2;
					ps.setInt(LEN_INDEX, numLen.getLenth());
					ps.setInt(NUM_INDEX, numLen.getNumber());
					ps.executeUpdate();
				}

				ResultSet resultFrequencies = st.executeQuery(SELECT_FREQUENCIES);
				final String MESSAGE_2 = "the frequencies table after saved";
				printTable(resultFrequencies, MESSAGE_2);

				// 4 - find records where length greater than number
				ResultSet resultFrequenciesWhere = st.executeQuery(SELECT_FREQUENCIES_WHERE);
				final String MESSAGE_3 = "the frequencies table after found";
				printTable(resultFrequenciesWhere, MESSAGE_3);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
