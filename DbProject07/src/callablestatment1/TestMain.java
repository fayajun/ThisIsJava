package callablestatment1;

import java.util.Iterator;
import java.util.List;

public class TestMain {

	public static void main(String[] args) {
		
		StudentDao db       = new StudentDao();
		
		//학생추가
		String name = "혜선신";
		String tel  = "010-4862-4864";
		//db.addStudent(name, tel);
		
		//학생조회
		int       stid     = 1;
		Student   student1 = db.getStudent( stid );
		System.out.println(student1);
		
		//학생 성적조회
		StudentScoreVo stsc = db.getStudScore(3);
		System.out.println(stsc.toString());
		
		//학생정보 수정
		int    stidUp   = 1;
		String stname   = "이효리";
		String telUp    = "010-9999-9999";
		String indate   = "2021/01/15";
		db.updateStudent(stidUp, stname, telUp, indate);
		System.out.println("수정되었습니다.");
		System.out.println();
		
		
		//학생목록 조회
		List<Student> list = db.getStudentList();
		for (Student student : list) {
			System.out.println(student);
		}
		System.out.println();
		
		//점수 목록조회
		List<StudentScoreVo> scoreList = db.getStudentScoreList();
		for (StudentScoreVo studentScoreVo : scoreList) {
			System.out.println(studentScoreVo.toString());
		}
		System.out.println();
		db.close();

	}

	
	
	
	
	
	
	
	
	
	
}
