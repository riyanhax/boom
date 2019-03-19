package com.ys.project;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ys.project.memberVO.MemberVO;
import com.ys.project.service.IMemberService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping(value = "member")
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	@Autowired
	private IMemberService service;

	//�α��� ó�� 
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(Model model, @RequestParam Map map) throws Exception {

		return "login/login";

	}

	@ResponseBody
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String loginPost(Model model, @RequestBody MemberVO vo, HttpServletRequest request) throws Exception {
		logger.info("===================================================> loginPost �ѿ��� �� : " + vo.toString());
		System.out.println("���� �Ѿ� �Գ� ? " + vo);
		Map<String, Object> map = new HashMap<String, Object>();

		// �ٲ��� ~~!!
		vo = service.loginMember(vo);
		if (vo == null) {
			map.put("error", "fail");
		} else {
			logger.info(
					"===================================================>�ٲ��� :  loginPost �ѿ��� �� : " + vo.toString());

			map.put("nickname", vo.getNickname());
			map.put("partner_signal", vo.getPartner_signal());
			HttpSession session = request.getSession();
			String loginSession = map.get("nickname").toString();
			String partner_signal = map.get("partner_signal").toString();
			session.setAttribute("loginSession", loginSession);
			session.setAttribute("partner_signal", partner_signal);
			System.out.println("session�� ���� ���� : " + loginSession + "," + partner_signal);

		}
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(map));
		return mapper.writeValueAsString(map);
	}
	
	//�α׾ƿ�
	@RequestMapping("logout")
	public String memberLogout(HttpServletRequest request )throws Exception {
		
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/";
	}

	// ȸ�� ����

	@RequestMapping(value = "memberRegister", method = RequestMethod.GET)
	public String memberRegister(Model model, @RequestParam Map map) throws Exception {

		return "login/memberRegister";

	}

	@RequestMapping(value = "memberRegister", method = RequestMethod.POST)
	public String memberRegisterPost(Model model, @RequestParam Map map) throws Exception {

		return "login/memberRegister";

	}

}
