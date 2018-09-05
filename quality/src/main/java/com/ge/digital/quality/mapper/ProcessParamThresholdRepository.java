package com.ge.digital.quality.mapper;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ge.digital.quality.entity.ProcessParamThreshold;

@Repository
public interface ProcessParamThresholdRepository extends JpaRepository<ProcessParamThreshold, Long> {
	List<ProcessParamThreshold> findByPartNumberAndLineAndItemAndSequenceNo(String partNumber, String line, String item,
			Integer sequenceNo);
	
	@Query(value = "select max(LastUpdateOn)  from pr_quality.ma_qualityspecification ", nativeQuery = true)
	Date findLastestUpdate();

}
