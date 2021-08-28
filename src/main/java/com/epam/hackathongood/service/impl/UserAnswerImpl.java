package com.epam.hackathongood.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.epam.hackathongood.mapper.UserAnswerMapper;
import com.epam.hackathongood.model.UserAnswer;
import com.epam.hackathongood.model.UserInfo;

@Repository
public class UserAnswerImpl implements UserAnswerMapper{
	@Autowired
    private JdbcTemplate jdbcTemplate;
	@Autowired
	private DataSource dataSource;
	private final DataSourceTransactionManager manager = new DataSourceTransactionManager();
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Override
	public List<UserAnswer> answerQuestion(List<UserAnswer> userAnswer) {
		TransactionDefinition definition = new DefaultTransactionDefinition();
		manager.setDataSource(dataSource);
        TransactionStatus status = manager.getTransaction(definition);
        Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strTime = formatter.format(currentTime);
		String uuid = UUID.randomUUID().toString();
		List<UserInfo> list = new ArrayList<UserInfo>();
		UserInfo user = new UserInfo();
		user.setUserId(uuid);
		user.setRegisterTime(strTime);
		list.add(user);
		for(int i=0;i<userAnswer.size();i++) {
			userAnswer.get(i).setUserId(uuid);
			userAnswer.get(i).setAnswerTime(strTime);
			userAnswer.get(i).setAnswerId(UUID.randomUUID().toString());
		}
		SqlParameterSource[] userBeanSources  = SqlParameterSourceUtils.createBatch(list.toArray());
		String userSql = "INSERT INTO hackforgood_development.user(userId,registerTime) VALUES (:userId,:registerTime)";
		SqlParameterSource[] userAnswerBeanSources  = SqlParameterSourceUtils.createBatch(userAnswer.toArray());
		String userAnswerSql = "INSERT INTO hackforgood_development.userAnswer(answerId,userId,questionId,optionId,answerTime) VALUES (:answerId,:userId,:questionId,:optionId,:answerTime)";		
		
		try {
			namedParameterJdbcTemplate.batchUpdate(userSql, userBeanSources);
			namedParameterJdbcTemplate.batchUpdate(userAnswerSql, userAnswerBeanSources);
			manager.commit(status);

		}catch(Exception e) {
			e.printStackTrace();
			manager.rollback(status);
			return null;
		}
		return userAnswer;
	}
//	@Override
//	public UserAnswer answerQuestion(UserAnswer userAnswer) {
//		UUID uuid  =  UUID.randomUUID();
//		String userId = userAnswer.getUserId();
//		String questionId = userAnswer.getQuestionId();
//		String optionId = userAnswer.getOptionId();
//		Date currentTime = new Date();
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String answerTime = formatter.format(currentTime);
//		
//		try {
//			jdbcTemplate.update("INSERT INTO hackforgood_development.userAnswer(answerId,userId,questionId,optionId,answerTime) VALUES (?,?,?,?,?)",uuid.toString(),userId,questionId,optionId,answerTime);
//		}catch(Exception e) {
//			return null;
//		}
//		userAnswer.setAnswerId(uuid.toString());
//		userAnswer.setAnswerTime(answerTime);
//		return userAnswer;
//	}

	@Override
	public Map<String,Object> getUserProfiles(String userId) {
		String conditions = "";
		if(userId!=null && userId.length()>0) {
			conditions = " and ua.userId=" + "\"" + userId + "\" ";
		}
		String sqlStr = "select ua.userId,q.questionId,q.questionText,opt.optionId,opt.content,p.profileId,p.name,p.title,p.description,p.personality1,p.personality2,p.personality3 from userAnswer ua \r\n" + 
				"left join questions q on q.questionId = ua.questionId \r\n" + 
				"left join options opt on opt.optionId = ua.optionId\r\n" + 
				"left join profiles p on p.type=opt.optionId\r\n" + 
				"where 1=1 "+ conditions + "\r\n" + 
				"group by ua.userId,q.questionId,opt.optionId;";
		List<Map<String, Object>> sqlRtn = jdbcTemplate.queryForList(sqlStr);
		Map<String,Integer> statisticProfiles = new HashMap<String,Integer>();
		Map<String,Object> mapProfile = new TreeMap<String,Object>();
		if(sqlRtn.size() == 0) {
			return mapProfile;
		}
		for(int i=0;i<sqlRtn.size();i++) {
			String option = sqlRtn.get(i).get("optionId").toString();
			Integer value = statisticProfiles.get(option);
			statisticProfiles.put(option, (value==null?0:value) + 1);
		}
		int max = 0;
		for(String str:statisticProfiles.keySet()) {
			if(statisticProfiles.get(str) > max) {
				max = statisticProfiles.get(str);
			}
		}
		String profile = "";
		for(String str:statisticProfiles.keySet()) {
			if(statisticProfiles.get(str) == max) {
				profile = str;
				break;
			}
		}
		
		for(int i=0;i<sqlRtn.size();i++) {
			if(sqlRtn.get(i).get("optionId").equals(profile)) {
				mapProfile.put("optionId", profile);
				mapProfile.put("number", max);
				mapProfile.put("profileId", sqlRtn.get(i).get("profileId"));
				mapProfile.put("name", sqlRtn.get(i).get("name"));
				
				mapProfile.put("description", sqlRtn.get(i).get("description"));
				mapProfile.put("personality1", sqlRtn.get(i).get("personality1"));
				mapProfile.put("personality2", sqlRtn.get(i).get("personality2"));
				mapProfile.put("personality3", sqlRtn.get(i).get("personality3"));
				mapProfile.put("title", sqlRtn.get(i).get("title"));
			}
		}
		return mapProfile;
	}
}
