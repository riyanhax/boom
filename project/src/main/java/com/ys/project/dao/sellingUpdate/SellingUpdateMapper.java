package com.ys.project.dao.sellingUpdate;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ys.project.projectVO.PartnerVO;

public interface SellingUpdateMapper {
	
	// ��Ʈ�� ����
	public List<PartnerVO> directPickList();

}
