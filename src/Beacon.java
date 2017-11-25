
public class Beacon {

	private String uid;
	private int major, minor;
	
	public Beacon(String uid, int major, int minor){
		this.uid = uid;
		this.major = major;
		this.minor = minor;
	}
	
	public Beacon(){
		
	}
	
	public String getUid() {
		return uid;
	}
	public int getMajor() {
		return major;
	}
	public int getMinor() {
		return minor;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public void setMajor(int major) {
		this.major = major;
	}
	public void setMinor(int minor) {
		this.minor = minor;
	}
	
	
}
