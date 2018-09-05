package com.ge.digital.quality.mapper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ge.digital.quality.entity.WipProcessCardInfo;

@Repository
public interface WipProcessCardInfoRepository extends JpaRepository<WipProcessCardInfo, Integer> {

	WipProcessCardInfo findByProcessCardNumber(String processCardNumber);

}
