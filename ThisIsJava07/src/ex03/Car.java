package ex03;

public class Car {
	//field
	// 4개의 타이어
	Tire frontLeftTire = new Tire("앞왼쪽 ", 6);
	Tire frontRightTire = new Tire("앞오른쪽 ", 2);
	Tire backLeftTire = new Tire("뒤왼쪽 ", 3);
	Tire backRightTire = new Tire("뒤오른쪽 ", 4);
	
	//constructor
	
	//method
	// Tire 객체의 roll() 메소드 호출. false리턴시 해당 타이어 번호로 리턴 (switch, case)
	int run() {
		System.out.println("[자동차가 달립니다.]");
		if(frontLeftTire.roll() == false){
			stop();
			return 1;
		}
		if(frontRightTire.roll() == false) {
			stop();
			return 2;
		}
		if(backLeftTire.roll() == false) {
			stop();
			return 3;
		}
		if(backRightTire.roll() == false) {
			stop();
			return 4;
		}
		return 0;	
	}

	void stop() {
		System.out.println("[자동차가 멈춥니다.]");
		
	}
}
