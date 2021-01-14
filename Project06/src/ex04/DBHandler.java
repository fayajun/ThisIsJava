package ex04;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBHandler {
	
	private String driver = "oracle.jdbc.OracleDriver";
	private String url    = "jdbc:oracle:thin:@localhost:1521:xe";
	private String uid = "exam01";
	private String pwd = "1234";
	
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	

	public DBHandler() {
		open();	
	}
	
	private void open() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, uid, pwd);
			

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void close() {
		try {
			if(rs   != null) rs.close();
			if(stmt != null) stmt.close();
			if(conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public List<Post> findZipcodeList(String sdong) {
		List<Post> postList = new ArrayList<>();
		
		try {
			stmt       = conn.createStatement();
			String sql = "SELECT ZIPCODE, SIDO, GUGUN, ";
			sql       += " DONG, ";
			sql       += " NVL(BUNJI,' ') BUNJI, SEQ";  
			sql       += " FROM POST";
			sql		  += " WHERE DONG LIKE '%" + sdong + "%'";
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String zipcode = rs.getString("zipcode");
				String sido    = rs.getString("sido");
				String gugun   = rs.getString("gugun");
				String dong    = rs.getString("dong");
				String bunji   = rs.getString("bunji");
				int    seq     = rs.getInt("seq");
				
				Post post = new Post(zipcode, sido, 
						gugun, dong, bunji, seq);
				
				postList.add(post);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return postList;
	}

}
