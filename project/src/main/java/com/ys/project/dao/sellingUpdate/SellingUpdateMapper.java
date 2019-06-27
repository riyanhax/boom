package com.ys.project.dao.sellingUpdate;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ys.project.projectDTO.MemberProductionList;
import com.ys.project.projectVO.PartnerVO;
import com.ys.project.projectVO.ProductionVO;

public interface SellingUpdateMapper {

	// ��Ʈ�� ����
	public List<PartnerVO> directPickList();

	// ��Ʈ��â���� �˻�
	public List<PartnerVO> directPickListSearch(@Param("choose") String choose, @Param("keyword") String keyword);

	// ��ǰ ���
	public int insert(ProductionVO productionVO);

	// �ɹ��� ���� ��ǰ ����
	public List<MemberProductionList> getMemberProductionList(Map map);

	// �ɹ��� ���� �� ��ǰ ����
	public int getMemberProductionTotalCount(int m_num);

}
