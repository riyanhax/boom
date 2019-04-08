package com.ys.project.service.member;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ys.project.dao.member.IMemberDao;
import com.ys.project.projectVO.MemberVO;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MemberService implements IMemberService {

	@Autowired
	private IMemberDao dao;

	@Override
	public void registerMember(Map map) throws Exception {
		dao.registerMember(map);
	}

	@Override
	public MemberVO loginMember(MemberVO memberVO) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("�������Ƥӷ��ƤӤ����Ӥ�����");
		return dao.loginMember(memberVO);
	}

	@Override
	public MemberVO nickNameCheck(String nickName) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("�г��� Ȯ�� : " + nickName);

		return dao.memberCheck(nickName);
	}

}
