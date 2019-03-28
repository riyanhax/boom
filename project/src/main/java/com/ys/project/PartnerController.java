package com.ys.project;

import java.math.BigDecimal;
import java.util.Map;

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
	public String partnerRegisterPost(Model model, @RequestParam Map<String, Object> partner,
			HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		HttpSession session = request.getSession();
		String loginSession = (String) session.getAttribute("loginSession"); // �α��ε� ������ �г���
		String partner_signal = (String) session.getAttribute("partner_signal");// �α��ε� ������ ��Ʈ�ʽ�ȣ(��� ���÷��̽� ������ ������)
		logger.info("��Ʈ�� �������� :" + partner.toString());
		// Map partner �� �Էµ� form�±��� ����.
		logger.info("����" + service.selectnumber(loginSession).toString());
		Map mvo = service.selectnumber(loginSession);
		logger.info(mvo.toString());
		int mnum = ((BigDecimal) mvo.get("M_NUM")).intValue();
//		int mnum = (int) mvo.get("M_NUM"); BigDecimal�� int�� Ÿ��ĳ���� �Ҽ����ٴ� �����߻�
		logger.info("��Ʈ�� ��������  ��ȣ�˻�:");
		logger.info("��Ʈ�� ��������  VO���� ��ȣ������:" + mnum);
		// �г������� DB�� m_num�� ã�Ƽ� �μ�Ʈ�Ҷ� ���� �־��ش�.
		logger.info("��Ʈ�� �������� :" + partner.toString() + partner_signal);
		service.partnerRegister(partner, mnum);
		logger.info("��Ʈ�� ���� �Ϸ�");
		
		rttr.addFlashAttribute("msg", "SUCCESSPARTNER");

		service.partnersignalup(mnum);
		logger.info("��Ʈ�� ������Ʈ �Ϸ�");

		
		session.removeAttribute("partner_signal");
		logger.info("������ partner_signal ������ ���ȴ�!");
		// ���ǿ� DB�� ������Ʈ�� ���� �ҷ��Ȱ� ���ÿ�
		Map mvo2 = service.selectnumber(loginSession); // ������Ʈ�� ������ ã�´�.
		logger.info("������Ʈ������ ������ ã�´�" + mvo2.get("NICKNAME") + " /// " + mvo2.get("PARTNER_SIGNAL"));
		
		
		session.setAttribute("partner_signal", mvo2.get("PARTNER_SIGNAL"));
		logger.info("������ partner_signal��Ʈ����Ʈ ���� ���ְ� ������Ʈ�� ��� �۾��Ϸ�");

		return "redirect:/";// ��Ʈ�� ������ �Ϸ�Ǿ� ���������� �̵�
	}

}
