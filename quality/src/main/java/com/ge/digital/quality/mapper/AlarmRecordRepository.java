package com.ge.digital.quality.mapper;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ge.digital.quality.entity.AlarmRecord;
import com.ge.digital.quality.vo.AlarmVO;

@Repository
public interface AlarmRecordRepository extends JpaRepository<AlarmRecord, Long> {

	@Query(value = "SELECT new com.ge.digital.quality.vo.AlarmVO(ar.alarmCode,am.alarmContentCn,am.alarmLevel)  "
			+ "FROM AlarmRecord as ar,AlarmMessage as am  WHERE "
			+ "ar.line=?1 and ar.loadNumber=?2 and  am.alarmLevel in (2,3) and ar.alarmCode=am.alarmCode")
	List<AlarmVO> findMechanicalPhase(String line, String loadNumber);

}
