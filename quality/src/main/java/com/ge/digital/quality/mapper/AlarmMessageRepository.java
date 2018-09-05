package com.ge.digital.quality.mapper;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ge.digital.quality.entity.AlarmMessage;

@Repository
public interface AlarmMessageRepository extends JpaRepository<AlarmMessage, Long> {

	List<AlarmMessage> findByLineAndAlarmCode(String line,String alarmCode);
	@Query(value = "select max(LastUpdateOn)  from pr_quality.ma_alarmblacklist ", nativeQuery = true)
	Date findLastestUpdate();
}
