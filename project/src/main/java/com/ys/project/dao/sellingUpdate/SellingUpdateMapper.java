package com.ys.project.dao.sellingUpdate;

import java.util.List;

import com.ys.project.projectVO.PartnerVO;
import com.ys.project.projectVO.ProductionVO;

public interface SellingUpdateMapper {

	// ��Ʈ�� ����
	public List<PartnerVO> directPickList();

	// ��ǰ ���
	public int insert(ProductionVO productionVO);

	

}
