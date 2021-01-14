package ex04;

import java.text.MessageFormat;

//zipcode, sido, gugun, dong, bunji, seq  
public class Post {
	
	//fields
	private String zipcode;
	private String sido; 
	private String gugun;  
	private String dong;
	private String bunji;  
	private int    seq;
	
	// constructor
	public Post (){}
	
	public Post(String zipcode, String sido, String gugun, String dong, String bunji, int seq) {
		this.zipcode = zipcode;
		this.sido = sido;
		this.gugun = gugun;
		this.dong = dong;
		this.bunji = bunji;
		this.seq = seq;
	}

	// getter & setter
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getSido() {
		return sido;
	}
	public void setSido(String sido) {
		this.sido = sido;
	}

	public String getGugun() {
		return gugun;
	}
	public void setGugun(String gugun) {
		this.gugun = gugun;
	}

	public String getDong() {
		return dong;
	}
	public void setDong(String dong) {
		this.dong = dong;
	}

	public String getBunji() {
		return bunji;
	}
	public void setBunji(String bunji) {
		this.bunji = bunji;
	}

	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}

	//toString
	@Override
	public String toString() {
		String fmt = "[{0}] {1} {2} {3} {4} {5}";
		String msg = MessageFormat.format(fmt, 
				zipcode, sido, gugun, dong, bunji, seq);
		return msg;
//		return "Post [zipcode=" + zipcode + ", sido=" + sido + ", gugun=" + gugun + ", dong=" + dong + ", bunji="
//				+ bunji + ", seq=" + seq + "]";
	}  

	
	
}
















