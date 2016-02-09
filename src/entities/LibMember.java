package entities;

public class LibMember {

	private String MemID;
	private String Password;
	
	public String getMemID() {
		return MemID;
	}
	public void setMemID(String memID) {
		MemID = memID;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public LibMember(String memID, String password) {
		super();
		MemID = memID;
		Password = password;
	}
	public LibMember() {
		super();
	}
	
}
