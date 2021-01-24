package ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class DBHandler {
	private String driver = "com.mysql.jdbc.Driver";
	private String url    = "jdbc:mysql://localhost:3306/testdb";
	private String dbUid  = "test";
	private String dbPwd  = "1234";

	private Connection        conn;
	private PreparedStatement pstmt;
	private ResultSet         rs;

	public DBHandler() {
		open();
	}

	private void open() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, dbUid, dbPwd);

		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
		} catch (SQLException e) {			
			e.printStackTrace();
		}

	}
	public void close() {
		try {				
			if(rs    != null)    rs.close();
			if(pstmt != null) pstmt.close();
			if(conn  != null)  conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Member> getMemberList() {
		ArrayList<Member> list = new ArrayList<Member>();

		String sql = "";
		try {
			sql   = "select id, userid, uername, userpass, email, regdate from member"; 
			pstmt = conn.prepareStatement(sql);
			rs    = pstmt.executeQuery();
			while(rs.next()) {
				int    id        = rs.getInt("id");
				String userid    = rs.getString("userid");
				String uername   = rs.getString("uername");
				String userpass  = rs.getString("userpass");
				String email     = rs.getString("email");
				String regdate   = rs.getString("regdate");

				Member m = new Member(id, userid, uername, userpass, email, regdate);
				list.add(m);

			}
		} catch (SQLException e) {			
			e.printStackTrace();
		}

		return list;
	}

}

public class TestMySql {

	public static void main(String[] args) {
		DBHandler          db = new DBHandler();
		ArrayList<Member>  list = db.getMemberList();
		for (Member member : list) {
			System.out.println(member);
		}


		db.close();


	}

}
