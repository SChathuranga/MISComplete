package entities;

import java.sql.Date;

public class Teachers {

	private String RegNo;
	private String Name;
	private String Adress;
	private Date DOB;
	private String NIC;
	private String Sex;
	private int ContactNo;
	private String TGrade;
	private String Type;
	private String WNOP;
	private String PrefSub;
	private int Distance;
	private Date DateofFirstApp;
	private Date DateofCurrentApp;
	private String Nationality;
	private String SpecialSkills;
	private byte[] ProfilePicture;
	
	public String getRegNo() {
		return RegNo;
	}
	public void setRegNo(String regNo) {
		RegNo = regNo;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getAdress() {
		return Adress;
	}
	public void setAdress(String adress) {
		Adress = adress;
	}
	public Date getDOB() {
		return DOB;
	}
	public void setDOB(Date dOB) {
		DOB = dOB;
	}
	public String getNIC() {
		return NIC;
	}
	public void setNIC(String nIC) {
		NIC = nIC;
	}
	public String getSex() {
		return Sex;
	}
	public void setSex(String sex) {
		Sex = sex;
	}
	public int getContactNo() {
		return ContactNo;
	}
	public void setContactNo(int contactNo) {
		ContactNo = contactNo;
	}
	public String getTGrade() {
		return TGrade;
	}
	public void setTGrade(String tGrade) {
		TGrade = tGrade;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public String getWNOP() {
		return WNOP;
	}
	public void setWNOP(String wNOP) {
		WNOP = wNOP;
	}
	public String getPrefSub() {
		return PrefSub;
	}
	public void setPrefSub(String prefSub) {
		PrefSub = prefSub;
	}
	public int getDistance() {
		return Distance;
	}
	public void setDistance(int distance) {
		Distance = distance;
	}
	public Date getDateofFirstApp() {
		return DateofFirstApp;
	}
	public void setDateofFirstApp(Date dateofFirstApp) {
		DateofFirstApp = dateofFirstApp;
	}
	public Date getDateofCurrentApp() {
		return DateofCurrentApp;
	}
	public void setDateofCurrentApp(Date dateofCurrentApp) {
		DateofCurrentApp = dateofCurrentApp;
	}
	public String getNationality() {
		return Nationality;
	}
	public void setNationality(String nationality) {
		Nationality = nationality;
	}
	public String getSpecialSkills() {
		return SpecialSkills;
	}
	public void setSpecialSkills(String specialSkills) {
		SpecialSkills = specialSkills;
	}
	public byte[] getProfilePicture() {
		return ProfilePicture;
	}
	public void setProfilePicture(byte[] profilePicture) {
		ProfilePicture = profilePicture;
	}
	
	public Teachers(String regNo, String name, String adress, Date dOB,
			String nIC, String sex, int contactNo, String tGrade, String type,
			String wNOP, String prefSub, int distance, Date dateofFirstApp,
			Date dateofCurrentApp, String nationality, String specialSkills,
			byte[] profilePicture) {
		super();
		RegNo = regNo;
		Name = name;
		Adress = adress;
		DOB = dOB;
		NIC = nIC;
		Sex = sex;
		ContactNo = contactNo;
		TGrade = tGrade;
		Type = type;
		WNOP = wNOP;
		PrefSub = prefSub;
		Distance = distance;
		DateofFirstApp = dateofFirstApp;
		DateofCurrentApp = dateofCurrentApp;
		Nationality = nationality;
		SpecialSkills = specialSkills;
		ProfilePicture = profilePicture;
	}
	public Teachers() {
		super();
	}
	
	
	
}
