package com.ys.project.dao.member;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ys.project.projectVO.MemberVO;
import com.ys.project.projectVO.ProductionReviewVO;

public interface IMemberDao {
	
	//ȸ�� ����
	public void registerMember(Map map) throws Exception;
	
	//�α���
	public MemberVO loginMember(MemberVO memberVO) throws Exception;
	
	//�г���üũ
	public MemberVO memberCheck(String nickName) throws Exception;
	
	//ȸ�� Ż��
	public void memberDelete(String nickname) throws Exception;

	//ȸ������
	public void memberUpdate(MemberVO vo) throws Exception;

	//�����ı� ������
	public List<ProductionReviewVO> getReviewData(MemberVO member) throws Exception;

	public List<ProductionReviewVO> getReviewData2(String data) throws Exception;

	public List<ProductionReviewVO> getPagingList(ProductionReviewVO pv) throws Exception;

	public List<ProductionReviewVO> scrollPaging(String nickname) throws Exception;

	public List<ProductionReviewVO> infiniteScrollDown(Map map) throws Exception;

	int getPagingListCount(int i) throws Exception;

	public int usingData(String data) throws Exception;
}
