package com.ys.project.service.production;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ys.project.dao.production.ProductionMapper;
import com.ys.project.projectDTO.IndexProductionDTO;
import com.ys.project.projectDTO.ProMemberJoinDTO;
import com.ys.project.projectVO.Production_uploadVO;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductionService implements IProductionService {

	private static final Logger log = LoggerFactory.getLogger(ProductionService.class);

	private ProductionMapper mapper;

	@Override
	public List<IndexProductionDTO> productionJoin() {
		// TODO Auto-generated method stub
		return mapper.productionJoin();
	}

	@Transactional
	@Override
	// fineBYPro , proMemberJoin 통합 매서드 처리
	public List<Map<String, Object>> totalFineByPro(int pro_num) {
		// TODO Auto-generated method stub
		List<Production_uploadVO> fineByPro = mapper.fineByPro(pro_num);

		ProMemberJoinDTO proMemberJoin = mapper.proMemberJoin(pro_num);

		Map<String, Object> map = new HashMap<String, Object>();

		
		map.put("Production_uploadVO", fineByPro);

		map.put("ProMemberJoinDTO", proMemberJoin);

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list.add(map);

		log.info("너는 무엇 이니  ? " + list);

		return list;
	}

}
