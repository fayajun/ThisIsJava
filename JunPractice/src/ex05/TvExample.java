package ex05;

class Tv{
	static String company = "Samsung";
	static String model = "LCD";
	static String info;
	
	static {
		info = company + "-" + model;
	}
}

public class TvExample {

	public static void main(String[] args) {
		System.out.println(Tv.info);

	}

}
