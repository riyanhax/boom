package com.ys.project.controller.member;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping(value = "chatting")
public class ChattingListController {
	@Autowired
	private RedisTemplate< String , String > redisTemplate;
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@RequestMapping(value = "chatting", method = RequestMethod.GET)
	public String chatGet(Model model, HttpSession session) {
		String user = (String) session.getAttribute("loginSession");
		System.out.println(">>>>> redis test ");
		  try {
			 
			 redisTemplate.delete("user");//�����ʱ�ȭ
			 
		   System.out.println(">>>>> has : " + redisTemplate.hasKey("user"));// key �����ϸ� TRUE ������FALSE 
		   redisTemplate.opsForValue().set("user", user);    // "user"��� Ű��,user.name�̶�� valuer��
		   String value = redisTemplate.opsForValue( ).get("user");   
		   
		   System.out.println(">>>>> redis value :" + value);
		logger.info("ü�� ������� �̵�");}
		  catch(Exception ex) {
			   ex.printStackTrace();
			  } 
		return "chat/chatList";

	}

}
