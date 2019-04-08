package com.ys.project.controller.member;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.AllArgsConstructor;


@Controller
@AllArgsConstructor
@RequestMapping(value = "/")
public class ProductController {
		// ��ǰ������� �̵�
		@RequestMapping(value = "index_productList", method = RequestMethod.GET)
		public String productList(Model model) {

//			logger.info("��ǰ��Ϸ� �̵� �Ѵ�.");
			return "mainIndex/index-productList";

		}
		
		// ��ǰ�󼼺���� �̵�
		@RequestMapping(value = "index_productView", method = RequestMethod.GET)
		public String productView(Model model) {

//			logger.info("��ǰ��Ϸ� �̵� �Ѵ�.");
			return "mainIndex/index-productView";

		}
}
