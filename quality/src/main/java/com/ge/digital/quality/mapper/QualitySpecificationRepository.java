package com.ge.digital.quality.mapper;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ge.digital.quality.entity.QualityItem;
import com.ge.digital.quality.entity.QualitySpecification;

@Repository
public interface QualitySpecificationRepository extends JpaRepository<QualitySpecification, Long> {
	List<QualitySpecification> findByPartNumberAndItemCode(String partNumber, String itemCode);
	@Query(value = "select max(LastUpdateOn)  from pr_quality.ma_qualityitem ", nativeQuery = true)
	Date findLastestUpdate();
}
