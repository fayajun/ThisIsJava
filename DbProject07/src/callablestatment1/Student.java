package callablestatment1;

// DTO : Data Transfer Object
// VO  : Value Object
public class Student {
	//filed
	private int    stid;
	private String stname;
	private String tel;
	private String indate;

	//constructor
	public Student() {}
	
	public Student(int stid, String stname, String tel, String indate) {
		this.stid   = stid;
		this.stname = stname;
		this.tel    = tel;
		this.indate = indate;
	}

	//getter & setter
	public int getStid() {
		return stid;
	}
	public void setStid(int stid) {
		this.stid = stid;
	}

	public String getStname() {
		return stname;
	}
	public void setStname(String stname) {
		this.stname = stname;
	}

	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getIndate() {
		return indate;
	}
	public void setIndate(String indate) {
		this.indate = indate;
	}

	//toString
	@Override
	public String toString() {
		return "Student [stid=" + stid + ", stname=" + stname + ", tel=" + tel + ", indate=" + indate + "]";
	}

	
	
	
}