package com.ge.digital.quality.mapper;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ge.digital.quality.entity.InspectionDetail;

@Repository
public interface InspectionDetailRepository extends JpaRepository<InspectionDetail, Long> {

	List<InspectionDetail> findByInspectionNo(String inspectionNo);

	List<InspectionDetail> findByInspectionNoIn(List<String> inspectionNos);

}
