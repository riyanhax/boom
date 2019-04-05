package com.ys.project.dao.member;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ys.project.projectVO.MemberVO;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class MemberDao implements IMemberDao {

	private static final Logger logger = LoggerFactory.getLogger(MemberDao.class);
	@Autowired
	private SqlSession session;

	//ȸ�� ���� map ó��
	@Override
	public void registerMember(Map map) throws Exception {
		// TODO Auto-generated method stub

		logger.info("�������� dao: " + map.toString());
		session.insert("member.memberRegister", map);

	}

	@Override
	public MemberVO loginMember(MemberVO memberVO) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("�������Ƥӷ��ƤӤ����Ӥ�����");
//		logger.info(session.selectOne("member.memberLogin", memberVO).toString());
		return session.selectOne("member.memberLogin", memberVO);
	}

	@Override
	public MemberVO memberCheck(String nickName) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("�ɹ� üũ" + nickName);
		System.out.println("oo : " + session.selectOne("nicknameCheck", nickName));
		return session.selectOne("member.nicknameCheck", nickName);
	}

	@Override
	public void memberDelete(String nickname) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("ȸ�� Ż�� ");
		session.delete("member.memberDelete",nickname);
	}

}
