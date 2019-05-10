package com.ys.project.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ys.project.projectVO.MemberVO;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping(value = "chatting")
public class ChattingListController {
	@Autowired
	private RedisTemplate< String , String > redisTemplate;
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	//ä�� ���
	@RequestMapping(value = "chatting", method = RequestMethod.GET)
	public String chatGet(Model model, HttpSession session) {
		MemberVO user2 = (MemberVO) session.getAttribute("loginSession");
		String user = user2.getNickname();
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
	
	// �����ϱ�
	@RequestMapping(value = "doChat*", method = RequestMethod.GET)
	public String doChat(Model model, HttpSession session, HttpServletRequest request) {
		MemberVO user2 = (MemberVO) session.getAttribute("loginSession");
		String user = user2.getNickname();
		String m_num = request.getParameter("m_num");
		String pro_num = request.getParameter("pro_num");
		request.setAttribute("m_num", m_num);
		request.setAttribute("pro_num", pro_num);
		System.out.println(">>>>> redis test ȸ����ȣ" + m_num+" ++ ��ǰ��ȣ" + pro_num);
		  try {
			 
			 redisTemplate.delete("user");//�����ʱ�ȭ
			 
		   System.out.println(">>>>> has : " + redisTemplate.hasKey("user"));// key �����ϸ� TRUE ������FALSE 
		   redisTemplate.opsForValue().set("user", user);    // "user"��� Ű��,user.name�̶�� valuer��
		   String value = redisTemplate.opsForValue().get("user");   
		   
		   System.out.println(">>>>> redis value :" + value);
		logger.info("Ŭ���� ��ǰ������ ä�ù� �����ϰ� �ٷ� ä�ù�����");} 
		  catch(Exception ex) {
			   ex.printStackTrace();
			  } 
		//return "chat/moveChat?m_num="+m_num+"&pro_num="+pro_num;
		  return "chat/moveChat";
	}
	
	

}
