package com.ys.project.productionVO;

import lombok.Data;

@Data
public class Production_uploadVO {

	private int upl_num; // pk
	private String path; // ���
	private String original; // ���� ���� �̸�
	private String thumnail; // ����� ���� �̸�
	private int pro_num; // ��ǰ��ȣ ����Ű

}
