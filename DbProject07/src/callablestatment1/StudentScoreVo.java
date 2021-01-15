package callablestatment1;

public class StudentScoreVo {
	//field
	private int    stid;
	private String stname;
	private int    kor;
	private int    eng;
	private int    mat;
	private int    tot;
	private int    avg;
	
	//constructor
	public StudentScoreVo() {}
	
	public StudentScoreVo(int stid, String stname, int kor, int eng, int mat, int tot, int avg) {		
		this.stid = stid;
		this.stname = stname;
		this.kor = kor;
		this.eng = eng;
		this.mat = mat;
		this.tot = tot;
		this.avg = avg;
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

	public int getKor() {
		return kor;
	}
	public void setKor(int kor) {
		this.kor = kor;
	}

	public int getEng() {
		return eng;
	}
	public void setEng(int eng) {
		this.eng = eng;
	}

	public int getMat() {
		return mat;
	}
	public void setMat(int mat) {
		this.mat = mat;
	}

	public int getTot() {
		return tot;
	}
	public void setTot(int tot) {
		this.tot = tot;
	}

	public int getAvg() {
		return avg;
	}
	public void setAvg(int avg) {
		this.avg = avg;
	}

	//toString
	@Override
	public String toString() {
		
		String fmt = "학번:%d 이름:%s 국어:%d 영어:%d 수학:%d 총점:%d 평균:%d";
		String msg = String.format(fmt, 
				this.stid, this.stname, this.kor, this.eng, this.mat, this.tot, this.avg);
		return msg;
		
		
//		return "StudentScoreVo [stid=" + stid + ", stname=" + stname + ", kor=" + kor + ", eng=" + eng + ", mat=" + mat
//				+ ", tot=" + tot + ", avg=" + avg + "]";
	}
	
	
}
