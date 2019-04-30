package com.ys.project.controller.member;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ys.project.projectVO.MemberVO;
import com.ys.project.service.member.IMemberService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping(value = "member")
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	@Autowired
	private IMemberService service;

	// �α��� ó��
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(Model model, @RequestParam Map map) throws Exception {

		return "login/login";

	}

	@ResponseBody
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String loginPost(Model model, @RequestBody MemberVO vo, HttpServletRequest request) throws Exception {
		logger.info("===================================================> loginPost �ѿ��� �� : " + vo.toString());
		System.out.println("���� �Ѿ� �Գ� ? " + vo);

		HttpSession session = request.getSession();
		
		JSONObject object = new JSONObject();
		vo = service.loginMember(vo);
		if (vo != null) {
			session.setAttribute("loginSession", vo);
			object.put("signal", "success");
			object.put("nickname",vo.getNickname());
			return object.toString();
		} else {
			System.out.println("========================��߶�� ���========");
			object.put("signal","fail");
			return object.toString();
		}

	}

	// �α׾ƿ�
	@RequestMapping("logout")
	public String memberLogout(HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/";
	}

	// ȸ�� ����

	@RequestMapping(value = "memberRegister", method = RequestMethod.GET)
	public String memberRegister(Model model) throws Exception {

		return "login/memberRegister";

	}

	@RequestMapping(value = "memberRegister", method = RequestMethod.POST)
	public String memberRegisterPost(Model model, @RequestParam Map map, RedirectAttributes ra) throws Exception {
		logger.info("�ɹ� �������� : " + map);
		ra.addFlashAttribute("msg", "SUCCESS");
		service.registerMember(map);
		return "redirect:/";

	}

	// �г��� üũ
	@RequestMapping(value = "nickNameCheck", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> nickNameCheck(Model model, @RequestBody MemberVO nickName, RedirectAttributes ra)
			throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		logger.info("�г��� üũ : " + nickName);
		nickName = service.nickNameCheck(nickName.getNickname());
		if (nickName == null) {
			map.put("signal", "SUCCESS");
		} else
			map.put("signal", "fail");
		return map;

	}

	// ���������� , // �ش� ������ �г��� ���� ��ȸ
	@RequestMapping(value = "myPage", method = RequestMethod.GET)
	public String myPage(Model model, HttpServletRequest request, RedirectAttributes rttr) throws Exception {

		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("loginSession"); // �α��ε� ������ �г���
		System.out.println("�����Ӿ������" + session.getAttribute("loginSession"));
		logger.info("���� ������ ���� : " + member);

		model.addAttribute("member", member);

		System.out.println("���� ������ : member/myPage");

		return "myPage/myPage";
	}

	
	// post ������ ���� 
	@RequestMapping(value = "myPage", method = RequestMethod.POST)
	@ResponseBody
	public MemberVO memberUpdate(Model model, @RequestBody MemberVO vo, HttpServletRequest request,
			RedirectAttributes rttr) throws Exception {

		HttpSession session = request.getSession();
		MemberVO before = (MemberVO) session.getAttribute("loginSession"); // �α��ε� ������ �г���

		System.out.println("������Ʈ �Ǳ��� : " + before);

		service.memberUpdate(vo);
		System.out.println("������Ʈ ���� : " + vo);

		session.setAttribute("loginSession", vo);

		return vo;
	}
	
	// Ÿ�� ������
	@RequestMapping(value = "other/"+"{data}", method = RequestMethod.GET)
	public String otherGET(Model model , @PathVariable String data) throws Exception {
		
		MemberVO member = service.nickNameCheck(data);

		model.addAttribute("other",member);
		 
		return "/myPage/other"; 

	}

	@CrossOrigin(maxAge = 3600)
	   @ResponseBody
	   @RequestMapping(value = "mlogin", method = RequestMethod.POST)
	   public String mlogin(Model model, @RequestBody Map<String, String> map) throws Exception {
	      //JSONParser parser;
	      //parser=new JSONParser();
//	      Object a =parser.parse(param);
//	      JSONObject jsonobj=(JSONObject) a;
//	      String nickname=(String)jsonobj.get("nickname");
//	      String m_password=(String)jsonobj.get("m_password");
		
		
//	      System.out.println(nickname + m_password);
//	      System.out.println("mlogin");
	      // Map<String, String> map = new HashMap<String, String>();
	      //JSONObject hh = (JSONObject) parser.parse(param);
	      //logger.info("�г��� : " + param);
		logger.info("�г��� : sdsdddddddddddddddddddddddddddddddddddddddddddd" + map);
	      String name = (String) map.get("nickname");
	      MemberVO member = service.nickNameCheck(name);
	      logger.info("�г��� : sdsdddddddddddddddddddddddddddddddddddddddddddd" + member);
	      /*
	       * if (nickName == null) { map.put("signal", "SUCCESS"); } else
	       * map.put("signal", "fail");
	       */
	      return "ok";
	   }
	
	

}
