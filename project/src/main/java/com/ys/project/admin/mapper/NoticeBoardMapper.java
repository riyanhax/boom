package com.ys.project.admin.mapper;

import java.util.List;

import com.ys.project.memberVO.Criteria;
import com.ys.project.memberVO.NoticeBoard;

public interface NoticeBoardMapper {
	public List<NoticeBoard> getList();
	
	public void insert(NoticeBoard board);

	public void insertSelectKey(NoticeBoard board);

	public NoticeBoard read(long bno);

	// ��ȯ ���� int�� ó���ϸ� �����ϸ� 1 , �ƴ� 0�� ��µȴ�.
	public int delete(long bno);

	public int update(NoticeBoard board);
	
	public List<NoticeBoard> getListWithPagin(Criteria cri);
	
	// ��Ż ������
	public int getTotalCount();
}
