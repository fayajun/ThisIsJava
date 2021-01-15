package callablestatment1;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import oracle.jdbc.OracleTypes;

// DAO : Data Access Object (DBHandler) 
public class StudentDao {
	//field
	private String driver = "oracle.jdbc.OracleDriver";
	private String url    = "jdbc:oracle:thin:@localhost:1521:xe";
	private String dbuid  = "exam03";
	private String dbpw   = "1234";

	private Connection        conn  = null;
	private CallableStatement cstmt = null; //oracle에 stored procedure를 호출하는 
	private ResultSet         rs    = null;

	//constructor
	public StudentDao() {
		open();

	}

	//method
	private void open() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,dbuid, dbpw);

		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Student getStudent(int stid) {
		Student s = null;

		return s;
	}

	public void close() {

		try {
			if(rs != null) rs.close();
		} catch (SQLException e) {				
		}
	}

	//학생추가
	public void addStudent(String name, String tel) {
		try {
			String sql = "{CALL ADD_STUDENT(?, ?)}";
			cstmt      = conn.prepareCall(sql);

			cstmt .setString(1, name);
			cstmt .setString(2, tel);

			int result = cstmt.executeUpdate();
			System.out.println(result);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//점수입력
	public void addScore(String subject, int score, int stid) {
		try {
			cstmt = conn.prepareCall("{CALL ADDSCORE(?,?,?)}");

			cstmt .setString(1, subject);
			cstmt .setInt(2, score);
			cstmt .setInt(3, stid);

			cstmt.executeUpdate();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
	}

	//점수조회 : get_stud_score
	//NUMBER    --> (parameter, Types.NUMERIC)
	//VARCHAR2  --> (parameter, Types.VARCHAR)
	//(6, :V_STID, :V_STNAME, :V_KOR, :V_ENG, :V_MAT, :V_TOTSCORE, :V_AVGSCORE)
	public StudentScoreVo getStudScore(int stud_id) {
		StudentScoreVo stsc = null;
		try {
			String sql = "{call get_stud_score(?,?,?,?,?,?,?,?)}";
			cstmt      = conn.prepareCall(sql);
			//파라미터 setting
			// in 파라미터 값 지정
			cstmt .setInt(1, stud_id); //입력 in

			//OUT 파라미터 등록(여기서부터 출력)`
			cstmt.registerOutParameter(2, Types.NUMERIC);
			cstmt.registerOutParameter(3, Types.VARCHAR);
			cstmt.registerOutParameter(4, Types.NUMERIC);
			cstmt.registerOutParameter(5, Types.NUMERIC);
			cstmt.registerOutParameter(6, Types.NUMERIC);
			cstmt.registerOutParameter(7, Types.NUMERIC);
			cstmt.registerOutParameter(8, Types.NUMERIC);

			cstmt.executeUpdate();// SP 실행

			//out 파라미터 사용: 결과를 받아오려면
			int    stid   = cstmt.getInt(2);
			String stname = cstmt.getString(3);
			int    kor    = cstmt.getInt(4);
			int    eng    = cstmt.getInt(5);
			int    mat    = cstmt.getInt(6);
			int    tot    = cstmt.getInt(7);
			int    avg    = cstmt.getInt(8);

			stsc = new StudentScoreVo(stid, stname, kor, eng, mat, tot, avg);

		} catch (SQLException e) {			
			e.printStackTrace();
		}
		return stsc;
	}


	// 학생정보 수정
	//EXEC UPDATESTUDENT(10, '이효리', '010-8888-8888', '2021-01-15');
	public void updateStudent(int stidUp, String stname, String telUp, String indate) {
		try {
			cstmt = conn.prepareCall("{CALL UPDATESTUDENT(?,?,?,?)}");

			cstmt.setInt(1, stidUp);
			cstmt.setString(2, stname);
			cstmt.setString(3, telUp);
			cstmt.setString(4, indate);


			cstmt.executeUpdate();
		} catch (SQLException e) {			
			e.printStackTrace();
		}

	}


	// 학생명단 조회 : 목록조회
	//EXEC GETSTUDENTLIST(:O_CUR);
	public List<Student> getStudentList(){
		List<Student> list = new ArrayList<>();

		try {
			String sql = "{call GETSTUDENTLIST(?)}";
			cstmt      = conn.prepareCall(sql);

			//			cstmt.registerOutParameter(1, Types.REF_CURSOR); //oracle cursor	   : ojdbc7.jar이상		

			cstmt.registerOutParameter(1, oracle.jdbc.internal.OracleTypes.CURSOR); //oracle cursor : ojdbc6.jar
			cstmt.executeQuery(); //조회

			//조회된 결과 처리
			//RS = cstmt.getResultSet();//작동X
			rs = (ResultSet) cstmt.getObject(1); //cursor에서 조회된 값을 가져온다

			while (rs.next()) {
				int    stid   = rs.getInt("stid");
				String stname = rs.getString("stname"); 
				String tel    = rs.getString("tel"); 
				String indate = rs.getString("indate"); 

				Student s = new Student(stid, stname, tel, indate);
				list.add(s);
			}

		} catch (SQLException e) {			
			e.printStackTrace();
		}

		return list;

	}

	// 전체 학생의 성적 목록 출력
	//EXEC GETSTUDENTSCORESLIST(:O_CUR);
	//stid, stname, kor, eng, mat, tot, avg, rank, grade

	public List<StudentScoreVo> getStudentScoreList(){
		List<StudentScoreVo> list = new Vector<>();

		try {
			String sql = "{call GETSTUDENTSCORESLIST(?)}";
			cstmt      = conn.prepareCall(sql);

		
			cstmt.registerOutParameter(1, OracleTypes.CURSOR); 
			cstmt.executeQuery(); 


			rs = (ResultSet) cstmt.getObject(1); 

			while( rs.next() ) {
				int    stid   = rs.getInt("stid");
				String stname = rs.getString("stname");
				int    kor    = rs.getInt("kor");
				int    eng    = rs.getInt("eng");
				int    mat    = rs.getInt("mat");
				int    tot    = rs.getInt("totscore");
				int    avg    = rs.getInt("avgscore");

				StudentScoreVo vo = new StudentScoreVo(stid, stname, kor, eng, mat, tot, avg);
				list.add(vo);				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}


}










