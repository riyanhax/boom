package com.ys.project.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ys.project.dao.PartnerDao;
import com.ys.project.memberVO.MemberVO;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PartnerService {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(PartnerService.class);
	
	private PartnerDao dao;
	
	//��Ʈ�� �������丮
	public void partnerRegister(Map<String, Object> partner, int mnum) {
		// TODO Auto-generated method stub
		dao.partnerRegister(partner, mnum);
	}
	
	public Map selectnumber(String nickname) {
		System.out.println("��Ʈ�ʼ��񽺿��� �ҷ��� " + dao.selectnumber(nickname).toString());
		return dao.selectnumber(nickname);
	}
	
	public void partnersignalup(int mnum) {
		System.out.println("(Service) mnum �Է¹޾� Dao ���� ��" + mnum);
		dao.partnerSignalUp(mnum);
	}
	

}
