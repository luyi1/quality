package com.ge.digital.quality.mapper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ge.digital.quality.entity.GasUsage;

@Repository
public interface GasUsageRepository extends JpaRepository<GasUsage, Long> {

}
