package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnection {
	
	private static Connection connect = null;
    private static final String url = "jdbc:mysql://localhost:3306/locationenligne?serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "root";

    private SingletonConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(url, user, password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
	}
	
	public static Connection getInstance(){
		if(connect == null) {
			new SingletonConnection();
		}
		return connect;
		
	}
	

}
