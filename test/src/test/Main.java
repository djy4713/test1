package test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import nlp.yuqing.dz.dao.HotSubjectDAO;
import nlp.yuqing.dz.dao.LeaderSubjectDAO;
import nlp.yuqing.dz.dao.SubjectDAO;
import nlp.yuqing.dz.model.HotSubject;
import nlp.yuqing.dz.model.LeaderSubject;
import nlp.yuqing.dz.model.Subject;
import nlp.yuqing.dz.util.TimeUtil;


public class Main {
	
	public static void main(String[] args) {
		LeaderSubjectDAO ldao = new LeaderSubjectDAO();
		HotSubjectDAO hdao = new HotSubjectDAO();
		SubjectDAO dao = new SubjectDAO();
		
		List<LeaderSubject> leaderList = ldao.getLeaderSubjectList();
		List<HotSubject> hotList = hdao.getHotSubjectList();
		
		System.out.println("--------------插入领导专题------------------");
		List<Subject> subjectList = parseLeaderSubjectList(leaderList);
		dao.insertSubjectList(subjectList);
		
		System.out.println("--------------插入热点专题------------------");
		subjectList = parseHotSubjectList(hotList);
		dao.insertSubjectList(subjectList);
		
		
	}
	
	public static List<Subject> parseLeaderSubjectList(List<LeaderSubject> leaderList){
		List<Subject> subjectList = new ArrayList<Subject>();
		for(LeaderSubject leader:leaderList){
			
			Subject subject = new Subject();
			subject.setId(leader.getId());
			subject.setUserId(leader.getAdminId());
			subject.setState(leader.getState());
			subject.setType(0);
			subject.setRegionId(leader.getRegionId());
			subject.setName(leader.getName());
			subject.setEventKeywords(leader.getEventKeywords());
			subject.setPeopleKeywords(leader.getPeopleKeywords());
			subject.setPlaceKeywords(leader.getPlaceKeywords());
			subject.setRejectKeywords(leader.getRejectKeywords());
			
			subject.setCreateTime(TimeUtil.getDateString(new Date()));
			subject.setUpdate_time(TimeUtil.getDateString(new Date()));
			
			subjectList.add(subject);
		}
		return subjectList;
	}
	
	public static List<Subject> parseHotSubjectList(List<HotSubject> leaderList){
		List<Subject> subjectList = new ArrayList<Subject>();
		for(HotSubject hot:leaderList){
			Subject subject = new Subject();
			subject.setId(hot.getId());
			subject.setUserId(hot.getAdminId());
			subject.setState(hot.getState());
			subject.setType(1);
			subject.setRegionId(hot.getRegionId());
			subject.setName(hot.getName());
			subject.setEventKeywords(hot.getEventKeywords());
			subject.setPeopleKeywords(hot.getPeopleKeywords());
			subject.setPlaceKeywords(hot.getPlaceKeywords());
			subject.setRejectKeywords(hot.getRejectKeywords());
			
			subject.setCreateTime(TimeUtil.getDateString(new Date()));
			subject.setUpdate_time(TimeUtil.getDateString(new Date()));
			
			subjectList.add(subject);
		}
		return subjectList;
	}

}
