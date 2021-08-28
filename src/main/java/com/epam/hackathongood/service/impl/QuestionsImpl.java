package com.epam.hackathongood.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.epam.hackathongood.mapper.QuestionsMapper;
import com.epam.hackathongood.model.Options;
import com.epam.hackathongood.model.Questions;
import com.epam.hackathongood.model.QuestionsWrapper;

@Repository
public class QuestionsImpl implements QuestionsMapper{
    
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<QuestionsWrapper> getAllQuestions() {
		
		StringBuilder sqlStr = new StringBuilder("select quest.questionId,quest.questionText,quest.selectedOption,quest.selectedTime,opt.optionId,opt.content from questions quest\r\n" + 
				"    left join options opt on quest.questionId = opt.questionId\r\n" + 
				"	where 1=1\r\n" + 
				"	group by quest.questionId,opt.optionId order by quest.questionId,opt.optionId asc;");
		List<QuestionsWrapper> questionsList = new ArrayList<QuestionsWrapper>();
		List<Map<String, Object>> sqlRtn = jdbcTemplate.queryForList(sqlStr.toString());
        //List<UserInfo> userList = jdbcTemplate.query("select userId,userName,department,email,phone,password,registerTime FROM hackforgood_development.user WHERE userName = ? and email = ?",new Object[]{user.getUserName(),user.getEmail()},new BeanPropertyRowMapper(UserInfo.class));
		Map<String,List<Options>> rtn = new HashMap<String,List<Options>>();
		Map<String,Questions> mapQuestions = new HashMap<String,Questions>();
		for(int i=0;i<sqlRtn.size();i++) {
			Map<String, Object> map = sqlRtn.get(i);
			String questionId = map.get("questionId").toString();
			String questionText = map.get("questionText").toString();
			String selectedOption = map.get("selectedOption").toString();
			String selectedTime = map.get("selectedOption").toString();
			String optionId = map.get("optionId").toString();
			String content = map.get("content").toString();
			Questions questions = new Questions();
			questions.setQuestionId(questionId);
			questions.setQuestionText(questionText);
			questions.setSelectedOption(selectedOption);
			questions.setSelectedTime(selectedTime);
			mapQuestions.put(questionId, questions);
			Options options = new Options();
			options.setOptionId(optionId);
			options.setContent(content);
			options.setQuestionId(questionId);
			List<Options> listOptions = new ArrayList<Options>();
			if(rtn.isEmpty() || (!rtn.isEmpty() && !rtn.containsKey(questionId))) {
				listOptions.add(options);
				rtn.put(questionId, listOptions);
			}else {
				System.out.println(options.getContent());
				for (Map.Entry<String,List<Options>> entry : rtn.entrySet()) {
					if(questionId.equals(entry.getKey())) {
						listOptions.clear();
						listOptions = entry.getValue();
						listOptions.add(options);
						rtn.put(entry.getKey(), listOptions);
						//rtn.replace(questionId, entry.getValue(), listOptions);
					}
				}
			}
			
		}
		Map<Questions,List<Options>> mapQueOpt = new HashMap<Questions,List<Options>>();
		for(String rtnQuestionId : rtn.keySet()) {
			mapQueOpt.put(mapQuestions.get(rtnQuestionId), rtn.get(rtnQuestionId));
		}
		for(Questions questions : mapQueOpt.keySet()) {
			QuestionsWrapper questionsWrapper = new QuestionsWrapper();
			questionsWrapper.setQuestions(questions);
			questionsWrapper.setListOptions(mapQueOpt.get(questions));
			questionsList.add(questionsWrapper);
		}
        return questionsList;
	}

}
