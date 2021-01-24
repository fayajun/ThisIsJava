package ex01;

public class Member {

	// field
	private int id;
	private String userid;
	private String uername;
	private String userpass;
	private String email;
	private String regdate;

	// Constructor
	public Member() {
	}

	public Member(int id, String userid, String uername, String userpass, String email, String regdate) {
		this.id = id;
		this.userid = userid;
		this.uername = uername;
		this.userpass = userpass;
		this.email = email;
		this.regdate = regdate;
	}

	// Getter & Setter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUername() {
		return uername;
	}

	public void setUername(String uername) {
		this.uername = uername;
	}

	public String getUserpass() {
		return userpass;
	}

	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	// toString
	@Override
	public String toString() {
		String fmt = "%d, %s, %s, %s, %s";
		String msg = String.format(fmt, id, userid, uername, userpass, email, regdate);
		return msg;
//		 return "Member [id=" + id + ", userid=" + userid + ", uername=" + uername +
//		 ", userpass=" + userpass + ", email=" + email + ", regdate=" + regdate + "]";
		 
	}

}
