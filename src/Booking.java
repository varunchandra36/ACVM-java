
public class Booking {
	
	private int branchid, zipcode, representatives;
	private String branchname, addrline1, addrline2, city, state;
	
	public Booking(int branchid, String branchname, String addrline1, String addrline2, String city, String state, int zipcode, int representatives ){
		this.branchid = branchid;
		this.branchname = branchname;
		this.addrline1 = addrline1;
		this.addrline2 = addrline2;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.representatives = representatives;
	}
	
	public Booking(){
		
	}
	public void setBranchid(int branchid) {
		this.branchid = branchid;
	}
	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}
	public void setRepresentatives(int representatives) {
		this.representatives = representatives;
	}
	public void setBranchname(String branchname) {
		this.branchname = branchname;
	}
	public void setAddrline1(String addrline1) {
		this.addrline1 = addrline1;
	}
	public void setAddrline2(String addrline2) {
		this.addrline2 = addrline2;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getBranchid() {
		return branchid;
	}
	public int getZipcode() {
		return zipcode;
	}
	public int getRepresentatives() {
		return representatives;
	}
	public String getBranchname() {
		return branchname;
	}
	public String getAddrline1() {
		return addrline1;
	}
	public String getAddrline2() {
		return addrline2;
	}
	public String getCity() {
		return city;
	}
	public String getState() {
		return state;
	}


}
