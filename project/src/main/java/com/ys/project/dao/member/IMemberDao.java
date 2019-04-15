package com.ys.project.dao.member;

import java.util.Map;

import com.ys.project.projectVO.MemberVO;

public interface IMemberDao {
	
	//ȸ�� ����
	public void registerMember(Map map) throws Exception;
	
	//�α���
	public MemberVO loginMember(MemberVO memberVO) throws Exception;
	
	//�г���üũ
	public MemberVO memberCheck(String nickName) throws Exception;
	
	// ȸ�� Ż��
	public void memberDelete(String nickname) throws Exception;

}