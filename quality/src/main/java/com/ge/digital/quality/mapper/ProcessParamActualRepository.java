package com.ge.digital.quality.mapper;

import java.util.List;

import java.util.Date;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ge.digital.quality.entity.ProcessParamActual;

@Repository
public interface ProcessParamActualRepository extends JpaRepository<ProcessParamActual, Long> {

	List<ProcessParamActual> findByLineAndLoadNumber(String line, String loadNumber);

}
