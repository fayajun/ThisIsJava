package ex05Answer;

import java.util.Iterator;
import java.util.List;

public class TestText2DB {

	public static void main(String[] args) {
		DBHandler db = new DBHandler();
		
		db.createTable();
		db.text2DB();
		System.out.println("저장완료");
		
		
		String dong = "부산대학교";
		List<Post> postList = db.getList(dong);
		
		for (Post post : postList) {
			System.out.println( post );
		}
		
		
		db.close();
		
	}

}

























