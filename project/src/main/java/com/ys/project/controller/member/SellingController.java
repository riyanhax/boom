package com.ys.project.controller.member;

import java.util.ArrayList;
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

import com.ys.project.projectDTO.MemberProductionList;
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
		JSONObject object;
		JSONArray array = new JSONArray();
		MemberVO memberVO = null;
//		MemberProductionList memberProductionList = null;
		List<MemberProductionList> list = null;

		HttpSession session = request.getSession();
		memberVO = (MemberVO) session.getAttribute("loginSession");
		int m_num = memberVO.getM_num();
		logger.info("�Ŵ����� �̵�" + m_num);

		int pro_num = 0;
		String title;
		String state_msg;
		int price;
		String cate_code;
		String path;

		list = service.getMemberProductionList(m_num);

		for (int i = 0; i < list.size(); i++) {
			object = new JSONObject();

			path = list.get(i).getUploadPath() + "\\s_" + list.get(i).getUuid() + "_" + list.get(i).getFileName();
			pro_num = list.get(i).getPro_num();
			title = list.get(i).getTitle();
			state_msg = list.get(i).getState_msg();
			price = list.get(i).getPrice();
			cate_code = list.get(i).getCate_code();

			object.put("path", path);
			object.put("pro_num", pro_num);
			object.put("title", title);
			object.put("state_msg", state_msg);
			object.put("price", price);
			object.put("cate_code", cate_code);

			array.add(object);

		}

		logger.info("��ǰ���� : " + array);

		model.addAttribute("productList", array.toString());

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
