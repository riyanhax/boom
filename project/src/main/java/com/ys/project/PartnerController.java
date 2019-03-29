package com.ys.project;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ys.project.memberVO.MemberVO;
import com.ys.project.memberVO.Partner;
import com.ys.project.service.PartnerService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping(value = "partner")
public class PartnerController {

	private static final Logger logger = LoggerFactory.getLogger(PartnerController.class);
	@Autowired
	private PartnerService service;

	// ��Ʈ�� ������ �̵�
	@RequestMapping(value = "partnerPage", method = RequestMethod.GET)
	public void partnerRegisterGet(Model model) throws Exception {

		logger.info("��Ʈ�� �������� ���� �̵�...");
	}

	// ��Ʈ�� ����
	@RequestMapping(value = "partnerRegister", method = RequestMethod.POST)
	public String partnerRegisterPost(Model model, Partner partner, HttpServletRequest request,
			RedirectAttributes rttr) throws Exception {
		HttpSession session = request.getSession();
		String loginSession = (String) session.getAttribute("loginSession"); // �α��ε� ������ �г���
		String tempPartner_signal_fuck = (String) session.getAttribute("partner_signal");// �α��ε� ������ ��Ʈ�ʽ�ȣ(��� ���÷��̽� ������
		int tempPartner_signal = Integer.parseInt(tempPartner_signal_fuck);
		int update_siganl = tempPartner_signal + 1 ; // update �ߴ�

		logger.info("��Ʈ�� �������� :" + partner.toString());
	
		// Map partner �� �Էµ� form�±��� ����.
//		logger.info("����" + service.selectnumber(loginSession).toString());
		int m_num;
		m_num = service.selectnumber(loginSession); // ȸ���� ��ȣ ���ö�� ��ġ
		logger.info("mNum : " + m_num);

		update_siganl = tempPartner_signal + 1; // �����ְ� �־��ٲ�

		session.removeAttribute(loginSession);
		session.removeAttribute(tempPartner_signal_fuck);
		
		partner.setM_num(m_num);
		logger.info("���ض� ~~~ ����Ű" + partner);
		service.partnerRegister(partner);
		
		// ������Ʈ �־��ٲ� �ù�
		MemberVO member = new MemberVO();
		member.setPartner_signal(update_siganl);
		member.setNickname(loginSession);
		service.partnerUpdate(member);
		logger.info("member : " + member);
		//������Ʈ ����
		session.setAttribute("loginSession", loginSession);
		session.setAttribute("partner_signal", Integer.toString(update_siganl));
		
		return "redirect:/";// ��Ʈ�� ������ �Ϸ�Ǿ� ���������� �̵�
	}

}
