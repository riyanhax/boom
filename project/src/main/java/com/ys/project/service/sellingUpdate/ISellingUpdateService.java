package com.ys.project.service.sellingUpdate;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ys.project.projectDTO.MemberProductionList;
import com.ys.project.projectVO.PartnerVO;
import com.ys.project.projectVO.PaymentVO;
import com.ys.project.projectVO.ProductionVO;

public interface ISellingUpdateService {

	// ��Ʈ�� ����
	public List<PartnerVO> directPickList();

	// ��Ʈ��â���� �˻�
	public List<PartnerVO> directPickListSearch(@Param("choose") String choose, @Param("keyword") String keyword);

	// ��ǰ ���
	public void insert(ProductionVO productionVO);

	// �ɹ��� ���� ��ǰ ����
	public List<MemberProductionList> getMemberProductionList(Map map);

	// �ɹ��� ���� �� ��ǰ ����
	public int getMemberProductionTotalCount(int m_num);

	// ��ǰ ������ ���� ����Ʈ �ҷ�����
	public List<PaymentVO> getMemberPayment(@Param("nickname") String nickname);

	// ��ǰ ���� ��ҿ� ���� ó��
	public int refuseDelete(@Param("imp_uid") String imp_uid);

}
