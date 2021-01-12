package ex05Answer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBHandler {

	//fields
	private String driver = "oracle.jdbc.OracleDriver";
	private String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	private String dbUid = "exam01";
	private String dbPwd = "1234";

	private Connection conn = null;
	private Statement  stmt = null;
	private ResultSet  rs   = null;

	//constructor
	public DBHandler() {
		open();
	}

	//methods
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
			if(rs   != null)rs.close();
			if(stmt != null)stmt.close();
			if(conn != null)conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void createTable() {
		try {
			stmt	   = conn.createStatement();

			String sql = "";
			sql        ="DROP TABLE POST2";
			stmt.executeUpdate(sql);

			sql  = "CREATE TABLE POST2";
			sql += " ( ";
			sql += " ZIPCODE    VARCHAR2(7)";
			sql += " ,SIDO      VARCHAR2(6)";
			sql += " ,GUGUN     VARCHAR2(34)";
			sql += " ,DONG      VARCHAR2(104)";
			sql += " ,BUNJI     VARCHAR2(34)";
			sql += " ,SEQ       NUMBER(5) PRIMARY KEY";
			sql += " )";

			stmt.executeUpdate(sql);
	

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//zipcode.csv -->  oracle post2 table에 insert
	public void text2DB() {
		//파일 준비

		try {
			File file = new File("c:\\imsi\\zipcode.csv");
			if(!file.exists()) {
				System.out.println("파일이 없어요");
				System.exit(-1);
			}
			FileReader     fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			String line = "";
			br.readLine(); // <-제목줄 skip

			int ln = 1;
			while( (line = br.readLine()) != null ) {
				String msg = String.format("%05d.%s", ln, line);
				System.out.println( msg );
				String [] li = line.trim().split(",");

				String zipcode = li[0].trim();
				String sido    = li[1].trim();
				String gugun   = li[2].trim();
				String dong    = li[3].trim();
				String bunji   = li[4].trim();
				int    seq     = Integer.parseInt(li[5]);

				//db추가: insert
				addPost(zipcode, sido, gugun, dong, bunji, seq);
				ln++;

			} //while end
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void addPost(String zipcode, String sido, String gugun, String dong, String bunji, int seq) {

		try {
			String sql = "INSERT INTO post2";
			sql += "( zipcode, sido, gugun, dong, bunji, seq )";
			sql += " VALUES (";
			sql += " '" + zipcode + "',";
			sql += " '" + sido    + "',";
			sql += " '" + gugun   + "',";
			sql += " '" + dong    + "',";
			sql += " '" + bunji   + "',";
			sql +=      + seq     + " )";

			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public List<Post> getList(String dong) {
		List<Post> postList = new ArrayList<Post>();
		
		try {
			stmt = conn.createStatement();
			String sql = "select zipcode, sido, gugun, dong, ";
			sql       += " nvl(bunji, ' ') bunji, seq";
			sql       += " from post2";
			sql       += " where dong like '%" + dong + "%'";
			
			System.out.println(sql);
			
			rs   = stmt.executeQuery(sql);
			while( rs.next() ) {
				String zipcode = rs.getString("zipcode");
				String sido    = rs.getString("sido");
				String gugun   = rs.getString("gugun");
				String dong2    = rs.getString("dong");
				String bunji   = rs.getString("bunji");
				int    seq     = rs.getInt("seq");		
				
				Post post = new Post(zipcode, sido, gugun, dong2, bunji, seq);
				postList.add( post );
			}
			
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
		
		return postList;
	}




}























