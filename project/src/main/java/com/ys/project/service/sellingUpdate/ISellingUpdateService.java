package com.ys.project.service.sellingUpdate;

import java.util.List;

import com.ys.project.projectVO.PartnerVO;
import com.ys.project.projectVO.ProductionVO;

public interface ISellingUpdateService {

	// ��Ʈ�� ����
	public List<PartnerVO> directPickList();

	// ��ǰ ���
	public int insert(ProductionVO productionVO);

}
