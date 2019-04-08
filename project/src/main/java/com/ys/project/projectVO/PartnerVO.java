package com.ys.project.projectVO;

import lombok.Data;

@Data
public class PartnerVO {

	private String company_number;
	private String part_name;
	private String boss_name;
	private String part_phone;
	private String zip_code; // ?��?��???
	private String road_name; // ??�?�? 주�??
	private String addr; // �?�? 주�??
	private String detail_addr; // ???? 주�??
	private java.util.Date reg_date;
	private String lag; // ????
	private String lng; // 경�??
	private int m_num; // ?�린??
}
