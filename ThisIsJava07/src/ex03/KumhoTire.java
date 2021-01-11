package ex03;

public class KumhoTire extends Tire{
	//fields
	
	//constructor
	public KumhoTire(String locatoin, int maxRotatoin) {
		super(locatoin, maxRotatoin);
	}
	
	//method
	@Override
	public boolean roll() {
		++accumulatedRotation;
		if( accumulatedRotation < maxRotation) {
			System.out.println(location + "KumhoTire 수명: " + 
		(maxRotation - accumulatedRotation) + "회");
		return true;
		} else {
			System.out.println("+++ " + location + " KumhoTire 펑크 +++");
			return false;
		}
	}
}
