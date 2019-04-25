package com.ys.project.service.production;

import java.util.List;
import java.util.Map;

import com.ys.project.projectDTO.IndexProductionDTO;

public interface IProductionService {

	// �ε��� ��ǥ���� ǥ�� !!!
	public List<IndexProductionDTO> productionJoin();
	
	
	// ��ǰ �� ���� mapper�� �ΰ��� �޼ҵ带 �ϳ��� ����
	public List<Map<String,Object>> totalFineByPro(int pro_num);	
	
//	// ��ǰ�� ���� ���ε� ���� ������
//	public List<Production_uploadVO> fineByPro(int pro_num);
//
//	// ��ǰ�� ���� �ɹ� ����
//	public ProMemberJoinDTO proMemberJoin(int pro_num);

}
