package com.ys.project.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class PartnerDao {
	private static final Logger logger = LoggerFactory.getLogger(PartnerDao.class);

	@Autowired
	private SqlSession session;
	
	public void partnerRegister(Map<String, Object> partner, int mnum) {
		// TODO Auto-generated method stub
	
		logger.info("��Ʈ�� DAO: Map ��ü  :" + partner.toString());
		logger.info("��Ʈ�� DAO: ���� Mnum :" + mnum);
		partner.put("m_num", mnum);
		logger.info((String) partner.get("p_start"));
		String a = "2000-01-01 "+(String)partner.get("p_start") + ":00";
		String b = "2000-01-01 "+(String)partner.get("p_end") + ":00";
		partner.put("p_start", a);
		partner.put("p_end", b);
		logger.info("��Ʈ�� DAO: �ٳ־���!" + partner.toString());
		// ��Ʈ��mapper ��ü�� mnum �������� ��� �����ұ�
		session.insert("partner.partnerRegister", partner); // ��Ʈ�� �μ�Ʈ
		logger.info("��Ʈ�� DAO: �μ�Ʈ�Ϸ� " + partner.toString());
		
		
		
		
	}
	
	public Map selectnumber(String nickname) {
		logger.info("�г��� ã�� : " + nickname);
		return session.selectOne("partner.selectnumber", nickname);
	}
	
	public void partnerSignalUp(int mnum) {
		logger.info("(DAO)������Ʈ�� mnum : "+mnum);
		session.update("partner.partnersignalup", mnum);
		// �����߻� ### Cause: java.sql.SQLSyntaxErrorException: ORA-00911: ���ڰ� �������մϴ� update member set partner_signal = partner_signal + 1 where m_num = ?
		// update member set partner_signal = partner_signal + 1 where m_num = ? �̶�µ� ?�� ���ڷ� ��ü�ؼ� OracleDeveloper���� �̻���� �����.
		// 
		logger.info("(DAO)���� ������Ʈ �޼ҵ� �Ϸ� ");
	}
	
}
