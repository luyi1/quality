package com.ge.digital.quality.mapper;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ge.digital.quality.entity.WipTask;

@Repository
public interface WipTaskRepository extends JpaRepository<WipTask, Integer> {

	@Query(value = "select count(*) from dbo.udtwip_task where taskEndDate>=?1 and taskEndDate<=?2 and line=?3 and partNumber=?4", nativeQuery = true)
	int findDaliyCount(String beginTime, String endTime, String line, String partNumber);

	@Query(value = "select count(*) from dbo.udtwip_task where line=?1 and partNumber=?2", nativeQuery = true)
	int findTotalCount(String line, String partNumber);

}
