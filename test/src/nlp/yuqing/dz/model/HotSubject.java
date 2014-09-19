package nlp.yuqing.dz.model;



/**
 * 热点专题对象，包含热点专题的某些属性
 */
public class HotSubject {
	
	private int id;
	private int adminId;
	private int regionId;
	private int state ;
	private String name;
	private String eventKeywords;
	private String placeKeywords;
	private String peopleKeywords;
	private String rejectKeywords;

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}


	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}

	public int getRegionId() {
		return regionId;
	}

	public String getEventKeywords() {
		return eventKeywords;
	}

	public void setEventKeywords(String eventKeywords) {
		this.eventKeywords = eventKeywords;
	}

	public String getPlaceKeywords() {
		return placeKeywords;
	}

	public void setPlaceKeywords(String placeKeywords) {
		this.placeKeywords = placeKeywords;
	}

	public String getPeopleKeywords() {
		return peopleKeywords;
	}

	public void setPeopleKeywords(String peopleKeywords) {
		this.peopleKeywords = peopleKeywords;
	}

	public String getRejectKeywords() {
		return rejectKeywords;
	}

	public void setRejectKeywords(String rejectKeywords) {
		this.rejectKeywords = rejectKeywords;
	}
	
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public void showData(){
		String str = "subjectId:%d , name:%s \n eventKeywords:%s \n peopleKeywords:%s \n placeKeywords:%s \n rejectKeywords:%s";
		str = String.format(str, id,name,eventKeywords,peopleKeywords,placeKeywords,rejectKeywords);
		System.out.println(str);
	}
}
