package com.ys.project.projectDTO;

import java.sql.Timestamp;

import lombok.Data;

//�ε����� �� ������ ���� �ѷ��ֱ� ���� ���

@Data
public class IndexProductionDTO {

	private int pro_num;
	private String title;
	private int price;
	private String cate_code;
	private int place_signal; // ���� ��� ����
	private String state_msg; // ���� �޽���
	private String uuid;
	private String uploadPath;
	private String fileName;
	private int rep;
	
	private Timestamp create_date; //��ǰ ����Ʈ���� �߰��� ����
	
	private String addr;
	private String place_pick;

}
