package com.ys.project.controller.member;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ys.project.projectVO.MemberVO;
import com.ys.project.projectVO.PartnerVO;
import com.ys.project.service.member.PartnerService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping(value = "/partner/**")
public class PartnerController {

	private static final Logger logger = LoggerFactory.getLogger(PartnerController.class);
	@Autowired
	private PartnerService service;

	// ��Ʈ�� ������ �̵�
	@RequestMapping(value = "partnerPage", method = RequestMethod.GET)
	public void partnerRegisterGet(Model model) throws Exception {

		logger.info("��Ʈ�� �������� ���� �̵�...");
	}
	 @RequestMapping(value = "/partnerRegister", method = RequestMethod.POST)
	   public String partnerRegisterPost(Model model, PartnerVO partner, HttpServletRequest request,
	         RedirectAttributes rttr, @RequestParam("uploadFile") MultipartFile[] uploadFile) throws Exception {
	      
	      logger.info(">>>>>"+ uploadFile[0].getOriginalFilename());
	      //=============================================================
	      PartnerVO tempVO =  uploadMethod(uploadFile);
	      logger.info(">>>>>"+tempVO);
	      String uuid = tempVO.getUuid();
	      String fileName = tempVO.getFileName();
	      String uploadPath = tempVO.getUploadPath();
	      //=============================================================
	      
	      
	      HttpSession session = request.getSession();
	      MemberVO vo = (MemberVO) session.getAttribute("loginSession");
	      String nickname = vo.getNickname(); // �α��ε� ������ �г���
	      String tempPartner_signal_fuck = Integer.toString(vo.getPartner_signal()); // �α��ε� ������ ��Ʈ�ʽ�ȣ(��� ���÷��̽� ������
	      int tempPartner_signal = Integer.parseInt(tempPartner_signal_fuck);
	      int update_siganl = tempPartner_signal + 1; // update �ߴ�

	      logger.info("��Ʈ�� �������� :" + partner.toString());

	      int m_num;
	      m_num = service.selectnumber(nickname); // ȸ���� ��ȣ ���ö�� ��ġ
	      logger.info("mNum : " + m_num);

	      update_siganl = tempPartner_signal + 1; // �����ְ� �־��ٲ�

	      // ���� �����
	      session.removeAttribute("loginSession");

	      partner.setM_num(m_num);
	      logger.info("���ض� ~~~ ����Ű" + partner);
	      partner.setUuid(uuid);
	      partner.setUploadPath(uploadPath);
	      partner.setFileName(fileName);
	      // ��Ʈ�� �����ϴ� �κ� ~~~========================��======================
	      logger.info("������ ~~~~~~~~~~~" + partner);
	      service.partnerRegister(partner);
	      

	      // ������Ʈ �־��ٲ� �ù�
	      MemberVO member = vo;
	      member.setPartner_signal(update_siganl);
	      member.setNickname(nickname);
	      service.partnerUpdate(member);
	      logger.info("�ٲ� �ɹ� Ȯ�� ==== > : " + member);
	      // ������Ʈ ����
	      session.setAttribute("loginSession", member);
	      
	      model.addAttribute("msg","SUCCESS");
	      rttr.addAttribute("msg","SUCCESS");
	      rttr.addFlashAttribute("msg","SUCCESSPARTNER");
	      return "redirect:/";
	   }


	// �˻� ������ �̵�
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "placeSearch", method = RequestMethod.GET)
	public String GetPartner(Model model) throws Exception {
		/* model.addAttribute("list" , service.getList()); */
		JSONArray array = new JSONArray();
		JSONObject object = null;

		PartnerVO partner;
		List<PartnerVO> partnerList = service.getList();

		int length = partnerList.size();
		for (int i = 0; i < length; i++) {
			object = new JSONObject();

			partner = partnerList.get(i);
			object.put("company_number", partner.getCompany_number());
			object.put("part_name", partner.getPart_name());
			object.put("boss_name", partner.getBoss_name());
			object.put("part_phone", partner.getPart_phone());
			object.put("zip_code", partner.getZip_code());
			object.put("road_name", partner.getRoad_name());
			object.put("addr", partner.getAddr());
			object.put("detail_addr", partner.getDetail_addr());
			object.put("reg_date", partner.getReg_date().toString());
			object.put("lag", partner.getLag());
			object.put("lng", partner.getLng());
			object.put("uuid", partner.getUuid());
			object.put("uploadPath", partner.getUploadPath());
			object.put("fileName", partner.getFileName());
			object.put("m_num", partner.getM_num());

			array.add(object);

		}

		logger.info("fuck : " + array.toString());
		/*
		 * object3 = new JSONObject(); object3.put("fucking",object2);
		 */
		model.addAttribute("list", array);
		logger.info("JSON���� �Ѿ�� LIST : " + array);
		System.out.println();
//			return "partner/placeSearch";

		return "/partner/placeSearch";
	}

//	@RequestMapping(value = "placeSearch", method = RequestMethod.GET)
//	public void placeSearch(Model model) throws Exception {
//
//		logger.info("���÷��̽� �˻� ������  �̵�...");
//	}

	// ���������� �����÷��̽� ������ �̵�
	@RequestMapping(value = "InfoPlace", method = RequestMethod.GET)
	public void InfoPlaceGet(Model model) throws Exception {

		logger.info("���������� �����÷��̽� �̵�...");
	}

	// ���� �̹��� ���ε� �ϴ� �żҵ�
	private PartnerVO uploadMethod(MultipartFile[] uploadFile) {
		// TODO Auto-generated method stub

		HttpServletRequest request = this.getRequest(); // HttpServletRequest ��ü ��� ���ؼ�

		String uploadFolder = request.getServletContext().getRealPath("/resources");

		File RealuploadPath = new File(uploadFolder, this.getFolder()); // ���� ������ ����

		PartnerVO partnerVO = new PartnerVO();

		for (MultipartFile multipartFile : uploadFile) {

			multipartFile.getOriginalFilename();
			String uploadFileName = multipartFile.getOriginalFilename();
			partnerVO = new PartnerVO();

			partnerVO.setFileName(uploadFileName); // ���� �̸� ����

			UUID uuid = UUID.randomUUID();

			uploadFileName = uuid.toString() + "_" + uploadFileName;

			try {
				File saveFile = new File(RealuploadPath, uploadFileName); // ���� ��ο� ���ߵ� ���� �̸�
				multipartFile.transferTo(saveFile);
				partnerVO.setUuid(uuid.toString());
				partnerVO.setUploadPath(this.getFolder());
			} catch (Exception e) {
				logger.error(e.getMessage());
			}

		}

		return partnerVO;

	}

	// ���� ����� �޼���
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String addPartner = "partner/";
		Date date = new Date();
		String str = sdf.format(date);
		System.out.println("���� ��ȯ�� : " + str); // 2019-03-31
		System.out.println("���� ��ȯ�� : " + str.replace("-", File.separator)); // 2019/03/31 "/" �̰ɷ� ��ȯ
		return addPartner + str.replace("-", File.separator);

	}

	// HttpServletRequest �������̽� Ÿ�Կ� request��ä ���
	public static HttpServletRequest getRequest() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		return attr.getRequest();
	}

}
