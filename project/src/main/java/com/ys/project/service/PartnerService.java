package com.ys.project.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ys.project.dao.PartnerDao;
import com.ys.project.memberVO.Partner;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PartnerService {

	private static final Logger logger = LoggerFactory.getLogger(PartnerService.class);
	
	private PartnerDao dao;
	
	//��Ʈ�� �������丮
	public void partnerRegister(Partner partner) {
		// TODO Auto-generated method stub
		dao.partnerRegister(partner);

	}

}
