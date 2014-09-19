package nlp.yuqing.dz.model;



/**
 * 热点专题对象，包含热点专题的某些属性
 */
public class Subject {
	
	private int id;
	private int userId;
	public int type;
	public int state;
	private int regionId;
	private String name;
	public String createTime;
	public String update_time;
	private String eventKeywords;
	private String placeKeywords;
	private String peopleKeywords;
	private String rejectKeywords;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getRegionId() {
		return regionId;
	}
	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
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

	public void showData(){
		String str = "subjectId:%d , name:%s \n eventKeywords:%s \n peopleKeywords:%s \n placeKeywords:%s \n rejectKeywords:%s";
		str = String.format(str, id,name,eventKeywords,peopleKeywords,placeKeywords,rejectKeywords);
		System.out.println(str);
	}
	
}
