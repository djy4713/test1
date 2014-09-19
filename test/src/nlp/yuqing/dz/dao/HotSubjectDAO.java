package nlp.yuqing.dz.dao;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nlp.yuqing.dz.configure.DBConfigure;
import nlp.yuqing.dz.model.HotSubject;
import nlp.yuqing.dz.model.LeaderSubject;
import nlp.yuqing.dz.model.Page;
import nlp.yuqing.dz.util.DBUtil;
import nlp.yuqing.dz.util.TimeUtil;

public class HotSubjectDAO {
	
	/** 
	 * 函数作用：根据区域号，获取该区域里的热点专题列表
	 */
	public List<HotSubject> getHotSubjectList(){
		List<HotSubject> hotSubjectList = new ArrayList<HotSubject>();
		DBConfigure dbc = new DBConfigure();
		String HotSubjectTable = dbc.getString("HotSubjectTable");
		String sql = "select * from %s order by id asc";
		sql = String.format(sql, HotSubjectTable);
		System.out.println("查询热点专题列表：\n"+sql);
		Connection conn = DBUtil.getAppConn();
		Statement stmt = null;
		ResultSet rs = null;
		try{
			stmt = DBUtil.createStmt(conn);
			rs = DBUtil.getRs(stmt, sql);
			while(rs.next()){
				HotSubject subject = new HotSubject();
				fillResultSetToHotSubject(subject,rs);
				hotSubjectList.add(subject);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {  
                rs.close();  
                stmt.close();  
                conn.close();  
            } catch (SQLException e) {  
                e.printStackTrace();  
            }  
		}
		if(hotSubjectList.size()==0)
			return null;
		else
			return hotSubjectList;
	}
	
	
	
	/**
	 * 函数作用：插入新专题
	 */
	public String insertHotSubject(HotSubject hotSubject){
		int state=1;
		DBConfigure dbc = new DBConfigure();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String createTime = sdf.format(date);
		String sql ="insert into %s(adminId,state,name,regionId,eventKeywords,peopleKeywords,placeKeywords,rejectKeywords,createTime) values(%d,%d,'%s',%d,'%s','%s','%s','%s','%s')";
		sql = String.format(sql, dbc.getString("HotSubjectTable"),hotSubject.getAdminId(),state,hotSubject.getName(),hotSubject.getRegionId(),hotSubject.getEventKeywords(),hotSubject.getPeopleKeywords(),hotSubject.getPlaceKeywords(),hotSubject.getRejectKeywords(),createTime);
		System.out.println(String.format("插入新的领导专题：\n %s",sql));
		Connection conn = DBUtil.getAppConn();
		Statement stmt = DBUtil.createStmt(conn);
		try{
			
			stmt.execute(sql);
		}catch (SQLException e) {
			e.printStackTrace();
			return "后台由于网络或者程序的故障，无法插入此专题";
		}finally{
			try {  
                stmt.close();  
                conn.close();  
            } catch (SQLException e) {  
                e.printStackTrace();  
            }  
		}
		return "新建成功";
	}
	
	
	
	/**
	 * 函数作用：根据专题ID，获取该专题相关的文档列表
	 */
	public List<Page> getHotSubjectPageList(String subjectId){
		List<Page> pagesList = new ArrayList<Page>();
		DBConfigure dbc = new DBConfigure();
		/*String LeaderSubjectTable = dbc.getString("HotSubjectTable");
		String sql = "select * from %s where regionId=%s order by ordinal asc, id desc";
		sql = String.format(sql, LeaderSubjectTable,regionId);
		System.out.println("查询热点专题列表：\n"+sql);*/
		String sql = "select * from %s as pages,%s as subjectPage where subjectPage.subId=%s and subjectPage.pageId=pages.id";	
		sql = String.format(sql,dbc.getString("wdyq_pagesTable"), dbc.getString("HotSubjectPageTable"),subjectId);
		System.out.println(String.format("查询id为%s的热点专题相关的页面列表：\n %s", subjectId,sql));
		Connection conn = DBUtil.getAppConn();
		Statement stmt = DBUtil.createStmt(conn);
		ResultSet rs = DBUtil.getRs(stmt, sql);
		try{
			while(rs.next()){
				Page page = new Page();
				fillResultSetToPage(page, rs);
				pagesList.add(page);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {  
                rs.close();  
                stmt.close();  
                conn.close();  
            } catch (SQLException e) {  
                e.printStackTrace();  
            }  
		}
		if(pagesList.size()==0)
			return null;
		else
			return pagesList;
	}
	
	
	
	public void fillResultSetToPage(Page page,ResultSet rs){
		try {
			page.setId(rs.getInt("id"));
			page.setUrl(rs.getString("url"));
			page.setFn(rs.getString("fn"));
			page.setWebSite(rs.getString("webSite"));
			page.setDownloadDate(rs.getTimestamp("downloadDate"));
			page.setTitle(rs.getString("title"));
			page.setClickCount(rs.getInt("clickCount"));
			page.setReplyCount(rs.getInt("replyCount"));
			page.setForwardCount(rs.getInt("forwardCount"));
			page.setType(rs.getInt("type"));
			page.setPublishDate(rs.getTimestamp("publishDate"));
			page.setNewsLevel(rs.getInt("newsLevel"));
			page.setSummary(rs.getString("summary"));
			
		} catch (SQLException e) {
			System.out.println("将Page的查询结果封装到类对象的时候出错了");
			e.printStackTrace();
		}
		
	}
	public void fillResultSetToHotSubject(HotSubject subject,ResultSet rs){
		try {
			subject.setId(rs.getInt("id"));
			subject.setAdminId(rs.getInt("adminId"));
			subject.setRegionId(rs.getInt("regionId"));
			subject.setState(rs.getInt("state"));
			String name=rs.getString("name");
			String eventKeywords=rs.getString("eventKeywords");
			String placeKeywords=rs.getString("placeKeywords");
			String peopleKeywords=rs.getString("peopleKeywords");
			String rejectKeywords=rs.getString("rejectKeywords");
			/*try {
				if(name!=null)
					name = new String(name.getBytes("ISO-8859-1"), "UTF-8");
				if(eventKeywords!=null)
					eventKeywords = new String(eventKeywords.getBytes("ISO-8859-1"), "UTF-8");
				if(placeKeywords!=null)
					placeKeywords = new String(placeKeywords.getBytes("ISO-8859-1"), "UTF-8");
				if(peopleKeywords!=null)
					peopleKeywords = new String(peopleKeywords.getBytes("ISO-8859-1"), "UTF-8");
				if(rejectKeywords!=null)
					rejectKeywords = new String(rejectKeywords.getBytes("ISO-8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}*/
			subject.setName(name);
			subject.setEventKeywords(eventKeywords);
			subject.setPlaceKeywords(placeKeywords);
			subject.setPeopleKeywords(peopleKeywords);
			subject.setRejectKeywords(rejectKeywords);
		} catch (SQLException e) {
			System.out.println("将HotSubject的查询结果封装到类对象的时候出错了");
			e.printStackTrace();
		}
		
	}
}
