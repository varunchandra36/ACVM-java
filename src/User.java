
public class User {
	private String firstname,lastname,mailid,mobileno,userid,password;

	public User(String firstname, String lastname, String mailid, String mobileno, String userid, String password) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.mailid = mailid;
		this.mobileno = mobileno;
		this.userid = userid;
		this.password = password;
	}
	
	public User(){
		
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getMailid() {
		return mailid;
	}

	public void setMailid(String mailid) {
		this.mailid = mailid;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [firstname=" + firstname + ", lastname=" + lastname + ", mailid=" + mailid + ", mobileno="
				+ mobileno + ", userid=" + userid + ", password=" + password + "]";
	}
}
