package com.ge.digital.quality.mapper;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ge.digital.quality.entity.ProcessTimeThreshold;
import com.ge.digital.quality.entity.QualityItem;

@Repository
public interface QualityItemRepository extends JpaRepository<QualityItem, Long> {


	QualityItem findByItemCode(String itemCode);


	//List<QualityItem> findByItemCode(String lineCode);
	@Query(value = "select max(LastUpdateOn)  from pr_quality.ma_processtimethreshold ", nativeQuery = true)
	Date findLastestUpdate();

}
