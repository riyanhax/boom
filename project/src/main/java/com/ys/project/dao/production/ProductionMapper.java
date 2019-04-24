package com.ys.project.dao.production;

import java.util.List;

import com.ys.project.projectDTO.IndexProductionDTO;
import com.ys.project.projectDTO.ProMemberJoinDTO;
import com.ys.project.projectVO.Production_uploadVO;

public interface ProductionMapper {

	// �ε��� ��ǥ���� ǥ�� !!!
	public List<IndexProductionDTO> productionJoin();

	// ��ǰ�� ���� ���ε� ���� ������
	public List<Production_uploadVO> fineByPro(int pro_num);
	
	// ��ǰ�� ���� �ɹ� ����
	public ProMemberJoinDTO proMemberJoin(int pro_num);
}
