package com.ys.project.service.sellingUpdate;

import java.util.List;
import java.util.Map;

import com.ys.project.projectDTO.MemberProductionList;
import com.ys.project.projectVO.PartnerVO;
import com.ys.project.projectVO.ProductionVO;

public interface ISellingUpdateService {

	// ��Ʈ�� ����
	public List<PartnerVO> directPickList();

	// ��ǰ ���
	public void insert(ProductionVO productionVO);
	
	// �ɹ��� ���� ��ǰ ����
	public List<MemberProductionList> getMemberProductionList(Map map);
	
	// �ɹ��� ���� �� ��ǰ ����
	public int getMemberProductionTotalCount(int m_num);
	

}
