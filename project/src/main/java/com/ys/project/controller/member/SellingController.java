package com.ys.project.controller.member;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping(value = "selling")
public class SellingController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	// �Ǹ��ϱ�� �̵�
	@RequestMapping(value = "selling", method = RequestMethod.GET)
	public String sellGet(Model model) {

		logger.info("�Ǹ��ϱ�� �̵� �Ѵ�.");
		return "sell/selling";

	}

	// ��ǰ ������ �̵�
	@RequestMapping(value = "sell_productManage", method = RequestMethod.GET)
	public String sell_productManage(Model model) {

		logger.info("�Ŵ����� �̵�");

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

}
