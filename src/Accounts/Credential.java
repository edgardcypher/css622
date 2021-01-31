package Accounts;

public class Credential {
	private String usrname;
	private String memberId;
	private String pwd;
	
	public Credential() {}
	/**
	 * @param memberId
	 * @param pwd
	 */
	public Credential(String memberId, String pwd, String usrname) {
		super();
		this.memberId = memberId;
		this.pwd = pwd;
		this.usrname = usrname;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getUsrname() {
		return usrname;
	}
	public void setUsrname(String usrname) {
		this.usrname = usrname;
	}
	
	

}
