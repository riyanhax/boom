package com.ys.project.projectVO;

import java.util.Date;

import lombok.Data;

@Data
public class WarningBoardVO {

	private int wa_num;
	private String title;
	private String content;
	private Date create_date;
	private Date update_date;
	private String wa_type; //�Ű� �з�
	private int m_num; //ȸ�� ����Ű

}
