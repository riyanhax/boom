package com.ys.project.controller.member;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ys.project.projectVO.MemberVO;
import com.ys.project.projectVO.PartnerVO;
import com.ys.project.projectVO.ProductionVO;
import com.ys.project.service.sellingUpdate.ISellingUpdateService;

import lombok.AllArgsConstructor;

/*
 * �������� ��ǰ ���� ���� Ȯ��
 */

@Controller
@AllArgsConstructor
@RequestMapping(value = "selling")
public class SellingController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	private ISellingUpdateService service;

	// �Ǹ��ϱ�� �̵�
	@RequestMapping(value = "selling", method = RequestMethod.GET)
	public String sellGet(Model model) {

		logger.info("�Ǹ��ϱ�� �̵� �Ѵ�.");
		return "sell/selling";

	}

	// ��ǰ ������ �̵�
	@RequestMapping(value = "sell_productManage", method = RequestMethod.GET)
	public String sell_productManage(Model model, HttpServletRequest request) {
		JSONObject object ;
		JSONArray array;
		MemberVO memberVO = new MemberVO();
		HttpSession session = request.getSession();
		memberVO = (MemberVO) session.getAttribute("loginSession");
		int m_num = memberVO.getM_num();
		logger.info("�Ŵ����� �̵�" + m_num);
		
		
		logger.info("��ǰ���� : " + service.getMemberProductionList(m_num));
		
		model.addAttribute("productList", service.getMemberProductionList(m_num));
		
		
		
		return "/sell/sell-ProductManage";

	}

	// �� ����Ʈ
	@RequestMapping(value = "chatList", method = RequestMethod.GET)
	public String chatList(Model model) {

		logger.info("ä�ø�� �̵�");

		return "sell/chatList";

	}

	// ���ų���
	@RequestMapping(value = "purchaseList", method = RequestMethod.GET)
	public String purchaseList(Model model) {

		logger.info("���� ���� �̵�");

		return "sell/purchaseList";

	}

	// �Ǹų���
	@RequestMapping(value = "sellList", method = RequestMethod.GET)
	public String sellList(Model model) {

		logger.info("�Ǹ� ���� �̵�");

		return "sell/sellList";

	}

	// Ż���ϱ�
	@RequestMapping(value = "memberOut", method = RequestMethod.GET)
	public String memberOut(Model model) {

		logger.info("ȸ��Ż��");

		return "sell/memberOut";

	}

	// ���÷��̽� �����ϱ�
	@GetMapping("directPick")
	public String directPick(Model model) {
		List<PartnerVO> list = service.directPickList();
		model.addAttribute("partnerList", list);
		return "/pick/directPick";

	}

	// ��ǰ ����ϱ�
	@PostMapping("uploadProduct")
	public String uploadProduct(ProductionVO productionVO, Model model, RedirectAttributes rttr) {

		if (productionVO.getUploadVOList() != null) {
			productionVO.getUploadVOList().forEach(attach -> logger.info("" + attach));
		}
		logger.info("�� ���� �� �� ��??" + productionVO);

		service.insert(productionVO);

		logger.info("========================================");

		return "redirect:/selling/selling";
	}

}
