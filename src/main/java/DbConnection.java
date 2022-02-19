
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnection {

	public static User getPasswordByUser(String userName) {

		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;

		String url = "jdbc:postgresql://localhost:5432/lighthouse";
		String user = "postgres";
		String password = "123456";

		User userFromDB = null;
		
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(url, user, password);
			System.out.println("数据库链接成功");

			statement = connection.createStatement();
			String sql = "select * from public.user where name = \'" + userName + "\'";
			System.out.println(sql);
			result = statement.executeQuery(sql);
			System.out.println("数据库查询成功");

			while (result.next()) {
				userFromDB = new User(result.getString(1), result.getString(2));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (connection != null)connection.close();
				if (statement != null)statement.close();
				if (result != null)result.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		return userFromDB;
	}

}

class User {

	public String name;
	public String password;
	
	public  User() {
		
	}
	public User(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}

}
