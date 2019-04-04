package com.ys.project.productionVO;

import java.util.Date;

import lombok.Data;

@Data
public class ProductionVO {

	private int pro_num;
	private String title;
	private String content;
	private int price;
	private Date create_date;
	private String addr;
	private int chat_room_count;
	private String p_quality;
	private String state_msg;
	private int place_signal;
	private String cate_code; // ī�װ� ����Ű
	private int m_num; // ȸ�� ��ǰ ����Ű

}
