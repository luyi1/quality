package com.ge.digital.quality.mapper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ge.digital.quality.entity.InspectionSummary;

@Repository
public interface InspectionSummaryRepository extends JpaRepository<InspectionSummary, Long> {

	InspectionSummary findByInspectionNo(String inspectionNo);

}
