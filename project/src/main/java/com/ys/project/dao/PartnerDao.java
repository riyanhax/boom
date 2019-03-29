package com.ys.project.dao;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.ys.project.memberVO.MemberVO;
import com.ys.project.memberVO.Partner;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class PartnerDao {
	private static final Logger logger = LoggerFactory.getLogger(PartnerDao.class);

	@Autowired
	private SqlSession session;

	public void partnerRegister(Partner partner) {
		// TODO Auto-generated method stub
		logger.info("���̿� ��Ʈ�� " + partner);
//		partner.setLag("123.456");
//		partner.setLng("456.789");
		// ��Ʈ��mapper ��ü�� mnum �������� ��� �����ұ�
		session.insert("partner.partnerRegister", partner); // ��Ʈ�� �μ�Ʈ

	}

	public int selectnumber(@RequestParam String nickname) {
		logger.info("�г��� ã�� : " + nickname);
		return session.selectOne("partner.selectnumber", nickname);
	}
	
	public void partnerUpdate(MemberVO memervo) {
		session.update("partner.updateNumber",memervo);
	}


}
