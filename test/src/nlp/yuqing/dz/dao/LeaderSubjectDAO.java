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
import nlp.yuqing.dz.model.LeaderSubject;
import nlp.yuqing.dz.model.Page;
import nlp.yuqing.dz.util.DBUtil;
import nlp.yuqing.dz.util.TimeUtil;

public class LeaderSubjectDAO {
	
	/**
	 * 函数作用：根据区域号，获取该区域里的领导专题列表 
	 */
	public List<LeaderSubject> getLeaderSubjectList(){
		List<LeaderSubject> leaderSubjectList = new ArrayList<LeaderSubject>();
		DBConfigure dbc = new DBConfigure();
		String LeaderSubjectTable = dbc.getString("LeaderSubjectTable");
		String sql = "select * from %s order by regionId asc, id asc";
		sql = String.format(sql, LeaderSubjectTable);
		System.out.println("查询领导专题列表：\n"+sql);
		Connection conn = DBUtil.getAppConn();
		Statement stmt = DBUtil.createStmt(conn);
		ResultSet rs = DBUtil.getRs(stmt, sql);
		try{
			while(rs.next()){
				LeaderSubject subject = new LeaderSubject();
				fillResultSetToLeaderSubject(subject,rs);
				leaderSubjectList.add(subject);
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
		if(leaderSubjectList.size()==0)
			return null;
		else
			return leaderSubjectList;
	}
	
	
	/**
	 * 函数作用：插入新专题
	 */
	public String insertLeaderSubject(LeaderSubject leaderSubject){
		int state=1;
		DBConfigure dbc = new DBConfigure();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String createTime = sdf.format(date);
		String sql ="insert into %s(adminId,state,name,regionId,eventKeywords,peopleKeywords,placeKeywords,rejectKeywords,createTime) values(%d,%d,'%s',%d,'%s','%s','%s','%s','%s')";
		sql = String.format(sql, dbc.getString("LeaderSubjectTable"),leaderSubject.getAdminId(),state,leaderSubject.getName(),leaderSubject.getRegionId(),leaderSubject.getEventKeywords(),leaderSubject.getPeopleKeywords(),leaderSubject.getPlaceKeywords(),leaderSubject.getRejectKeywords(),createTime);
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
	 * 函数作用：根据专题ID，获取该专题对象
	 */
	public LeaderSubject getLeaderSubject(String subjectId){
		LeaderSubject leaderSubject = new LeaderSubject();
		DBConfigure dbc = new DBConfigure();
		String LeaderSubjectTable = dbc.getString("LeaderSubjectTable");
		String sql = "select * from %s where id=%s";
		sql = String.format(sql, LeaderSubjectTable,subjectId);
		System.out.println(String.format("查询id为%s的领导专题：\n %s",subjectId,sql));
		Connection conn = DBUtil.getAppConn();
		Statement stmt = DBUtil.createStmt(conn);
		ResultSet rs = DBUtil.getRs(stmt, sql);
		try{
			if(rs.next()){
				fillResultSetToLeaderSubject(leaderSubject,rs);
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
		
		return leaderSubject;
	}
	
	/**
	 * 函数作用：根据专题ID，获取该专题相关的文档列表
	 */
	public List<Page> getLeaderSubjectPageList(String subjectId){
		List<Page> pagesList = new ArrayList<Page>();
		DBConfigure dbc = new DBConfigure();
		/*String LeaderSubjectTable = dbc.getString("HotSubjectTable");
		String sql = "select * from %s where regionId=%s order by ordinal asc, id desc";
		sql = String.format(sql, LeaderSubjectTable,regionId);
		System.out.println("查询热点专题列表：\n"+sql);*/
		String sql = "select * from %s as pages,%s as subjectPage where subjectPage.subId=%s and subjectPage.pageId=pages.id";	
		sql = String.format(sql,dbc.getString("wdyq_pagesTable"), dbc.getString("LeaderSubjectPageTable"),subjectId);
		System.out.println(String.format("查询id为%s的领导专题相关的页面列表：\n %s", subjectId,sql));
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
			page.setTitle(rs.getString("title"));
			page.setDownloadDate(rs.getTimestamp("downloadDate"));
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
	public void fillResultSetToLeaderSubject(LeaderSubject subject,ResultSet rs){
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
			System.out.println("将LeaderSubject的查询结果封装到类对象的时候出错了");
			e.printStackTrace();
		}
		
	}
}
