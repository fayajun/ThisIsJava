package ex03;

public class Tire {
	//field
	public int maxRotation;
	public int accumulatedRotation;
	public String location;

	//constructor
	public Tire(String location, int maxRotation) {
		this.location = location;
		this.maxRotation = maxRotation;
		
	}

	//method
	public boolean roll() {
		++accumulatedRotation;
		if(accumulatedRotation < maxRotation) {
			//정상 회전( 누적 < 최대 ) 일 경우 실행
			System.out.println(location + "Tire 수명: " + 
					(maxRotation - accumulatedRotation) + "회");
			return true;
		} else {
			//펑크 ( 누적 == 최대 ) 일 경우 실행
			System.out.println("+++ " + location + " Tire 펑크 +++");
			return false;
		}
		
	}

}
