package com.ys.project.dao.productionUpload;

import java.util.List;

import com.ys.project.projectVO.ProductionVO;
import com.ys.project.projectVO.Production_uploadVO;

public interface ProductionUploadMapper {

	// ���� ���ε�
	public void insertUpload(Production_uploadVO upload);
	
	// ���ε� �� ����
	public void delete(String uuid);
	
	// �Խù������� ���ε� ���� ã��
	public List<ProductionVO> fineByPro(int pro_num);
	
	// �̹��� �ѷ��ֱ� ���� 
	/* public List<ProductionVO> indexProduct */
	
	
}
