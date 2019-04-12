package com.ys.project.projectVO;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class ProductionVO {

	private int pro_num;
	private String title;
	private int price;
	private String content;
	private Date create_date;
	private String addr;
	private String place_pick; // ���� ���̽� ���� default null
	private int chat_room_count; // ü�ù� ��û�� ���� �Ѱ��� ����
	private String p_quality; // ��ǰ�� ���� ����Ƽ
	private String state_msg; // ���� �޼���
	private int place_signal; // ���÷��̽� ��ȣ ��ȣ�� 1.0 ���� �Ǵ� 1�̸� ���÷��̽� �ŷ��� ���

	private String cate_code; // ��ǰ�� ���� ī�װ�
	private int m_num; // ȸ���� ���� ����Ű

	// ���ε� �� ��ü������ ������ ��Ƶδ� ��� ����Ʈ
	private List<Production_uploadVO> uploadVOList;

}
