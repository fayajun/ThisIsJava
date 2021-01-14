package ex04;

import java.util.List;
import java.util.Scanner;



public class GetZipcodeTest {

	public static void main(String[] args) {
		
		DBHandler  db       = new DBHandler();
		
		Scanner in = new Scanner(System.in);
		System.out.println("찾고싶은 동명입력(읍,면,동,리,건물명): ");
		
		
		
		String     dong     = in.nextLine();//"롯데백화점";
		List<Post> postList = db.findZipcodeList(dong);

		int cnt = 0;
		for (Post post : postList) {
			System.out.println(post);
			cnt++;
		}
		System.out.println("총" + cnt + "건");
		db.close();
	}

}
