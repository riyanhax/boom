package com.ys.project.dao.production;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ys.project.projectDTO.Criteria3;
import com.ys.project.projectDTO.IndexProductionDTO;
import com.ys.project.projectDTO.ProMemberJoinDTO;
import com.ys.project.projectVO.PaymentVO;
import com.ys.project.projectVO.Production_uploadVO;

public interface ProductionMapper {

	// 인덱스 대표사진 표기 !!!
	public List<IndexProductionDTO> productionJoin();

	// 상품에 대한 업로드 정보 들고오기
	public List<Production_uploadVO> fineByPro(int pro_num);

	// 상품에 대한 맴버 조인
	public ProMemberJoinDTO proMemberJoin(int pro_num);

	// 인덱스 상품에서 주석 처리 대상
	public List<IndexProductionDTO> productionListJoin(String cate_code);

	// 상품 리스트 정렬
	public List<IndexProductionDTO> sort(@Param("order") String order,
			@org.apache.ibatis.annotations.Param("cate_code") String cate_code,
			@org.apache.ibatis.annotations.Param("pageNum") int pageNum,
			@org.apache.ibatis.annotations.Param("amount") int amount);

	// 상품 갯수 들고오기
	public int getTotalCount(@Param("cate_code") String cate_code);

	// 앱 상품 서비스 맵퍼
	public List<IndexProductionDTO> appSort(@Param("order") String order, @Param("cate_code") String cate_code);
	
	// 앱 상품 검색 맵퍼

	public List<IndexProductionDTO> appSearch(@Param("keyword") String keyword);
	
	// 검색 상품 리스트 맵퍼
	public List<IndexProductionDTO> searchSort(Criteria3 cri3);

	// 검색 상품 리스트 토탈 카운트
	public int searchGetTotalCount(@Param("type") String type, @Param("keyword") String keyword);
	
	// 상품 결제에 대한 리스트 불러오기 
	public List<PaymentVO> getMemberPayment(@Param("nickname") String nickname);
	
	// 상품 결제 취소에 대한 처리
	public int refuseDelete(@Param("imp_uid") String imp_uid);

	public List<Map<String, Integer>> cateCount();
	

}