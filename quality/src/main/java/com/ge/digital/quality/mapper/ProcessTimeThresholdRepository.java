package com.ge.digital.quality.mapper;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ge.digital.quality.entity.AlarmMessage;
import com.ge.digital.quality.entity.ProcessTimeThreshold;

@Repository
public interface ProcessTimeThresholdRepository extends JpaRepository<ProcessTimeThreshold, Long> {
	List<ProcessTimeThreshold> findByPartNumber(String partNumber);
	@Query(value = "select max(LastUpdateOn)  from pr_quality.ma_processparamthreshold ", nativeQuery = true)
	Date findLastestUpdate();
}
