package nlp.yuqing.dz.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import nlp.yuqing.dz.configure.DBConfigure;
import nlp.yuqing.dz.model.Subject;
import nlp.yuqing.dz.util.DBUtil;

public class SubjectDAO {
	
	public void insertSubject(Subject subject){
		DBConfigure dbc = new DBConfigure();
		Connection conn = DBUtil.getAppConn();
		Statement stmt = DBUtil.createStmt(conn);
		try{
			subject.showData();
			String sql ="insert into %s(userId,type,state,name,regionId,createTime,update_time) values(%d,%d,%d,'%s',%d,'%s','%s')";
			sql = String.format(sql, dbc.getString("SubjectTable"),subject.getUserId(),subject.getType(),subject.getState(),subject.getName(),subject.getRegionId(),subject.getCreateTime(),subject.getUpdate_time());
			System.out.println(sql);
			stmt.execute(sql);
			
			int maxId = getSubjectMaxId();
			subject.setId(maxId);
			insertSubjectKeywords(subject);
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {  
                stmt.close();  
                conn.close();  
            } catch (SQLException e) {  
                e.printStackTrace();  
            }  
		}
	}
	
	public void insertSubjectList(List<Subject> list){
		DBConfigure dbc = new DBConfigure();
		Connection conn = DBUtil.getAppConn();
		Statement stmt = DBUtil.createStmt(conn);
		try{
			for(Subject subject:list){
				subject.showData();
				String sql ="insert into %s(userId,type,state,name,regionId,createTime,update_time) values(%d,%d,%d,'%s',%d,'%s','%s')";
				sql = String.format(sql, dbc.getString("SubjectTable"),subject.getUserId(),subject.getType(),subject.getState(),subject.getName(),subject.getRegionId(),subject.getCreateTime(),subject.getUpdate_time());
				System.out.println(sql);
				stmt.execute(sql);
				
				int maxId = getSubjectMaxId();
				subject.setId(maxId);
				insertSubjectKeywords(subject);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {  
                stmt.close();  
                conn.close();  
            } catch (SQLException e) {  
                e.printStackTrace();  
            }  
		}
	}
	
	/**
	 * 函数作用：获取领导专题的最大id值
	 */
	public int getSubjectMaxId(){
		int MaxId=-1;
		DBConfigure dbc = new DBConfigure();
		String sql = "select max(id) as maxSubjectId from %s";
		sql = String.format(sql, dbc.getString("SubjectTable"));
		
		System.out.println(sql);
		Connection conn = DBUtil.getAppConn();
		
		Statement stmt = DBUtil.createStmt(conn);
		ResultSet rs = DBUtil.getRs(stmt, sql);
		try{
			if(rs.next()){
				MaxId = rs.getInt("maxSubjectId");
			}
		}catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}finally{
			try {  
                rs.close();  
                stmt.close();  
                conn.close();  
            } catch (SQLException e) {  
                e.printStackTrace();  
            }  
		}
		return MaxId;
	}
	public void insertSubjectKeywords(Subject subject){
		DBConfigure dbc = new DBConfigure();
		String talbeName = dbc.getString("SubjectKeywordsTable");
		String eventKeywordSql="insert into %s(subjectId,name,keywords,rejectFlag,regionId,mainbodyId,eventId) values(%d,'%s','%s','%s',%d,%d,%d)";
		String peopleKeywordSql="insert into %s(subjectId,name,keywords,rejectFlag,regionId,mainbodyId,eventId) values(%d,'%s','%s','%s',%d,%d,%d)";
		String placeKeywordSql="insert into %s(subjectId,name,keywords,rejectFlag,regionId,mainbodyId,eventId) values(%d,'%s','%s','%s',%d,%d,%d)";
		String rejectKeywordSql="insert into %s(subjectId,name,keywords,rejectFlag,regionId,mainbodyId,eventId) values(%d,'%s','%s','%s',%d,%d,%d)";
		
		eventKeywordSql = String.format(eventKeywordSql, talbeName,subject.getId(),"eventKeywords",subject.getEventKeywords(),"0",0,0,0);
		peopleKeywordSql = String.format(peopleKeywordSql, talbeName,subject.getId(),"peopleKeywords",subject.getPeopleKeywords(),"0",0,0,0);
		placeKeywordSql = String.format(placeKeywordSql, talbeName,subject.getId(),"placeKeywords",subject.getPlaceKeywords(),"0",0,0,0);
		rejectKeywordSql = String.format(rejectKeywordSql, talbeName,subject.getId(),"rejectKeywords",subject.getRejectKeywords(),"1",0,0,0);
	
		Connection conn = DBUtil.getAppConn();
		Statement stmt = DBUtil.createStmt(conn);
		try {
			if(subject.getEventKeywords()!=null && !subject.getEventKeywords().trim().isEmpty()){
				System.out.println(eventKeywordSql);
				stmt.execute(eventKeywordSql);
			}
			if(subject.getPeopleKeywords()!=null && !subject.getPeopleKeywords().trim().isEmpty()){
				System.out.println(peopleKeywordSql);
				stmt.execute(peopleKeywordSql);
			}
			if(subject.getPlaceKeywords()!=null && !subject.getPlaceKeywords().trim().isEmpty()){
				System.out.println(placeKeywordSql);
				stmt.execute(placeKeywordSql);
			}
			if(subject.getRejectKeywords()!=null && !subject.getRejectKeywords().trim().isEmpty()){
				System.out.println(rejectKeywordSql);
				stmt.execute(rejectKeywordSql);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {  
                stmt.close();  
                conn.close();  
            } catch (SQLException e) {  
                e.printStackTrace();  
            }  
		}
	}
	
}
