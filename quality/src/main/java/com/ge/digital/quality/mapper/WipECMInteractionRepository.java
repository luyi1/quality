package com.ge.digital.quality.mapper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ge.digital.quality.entity.QualityItem;
import com.ge.digital.quality.entity.WipECMInteraction;

@Repository
public interface WipECMInteractionRepository extends JpaRepository<WipECMInteraction, Integer> {

	WipECMInteraction findByLineAndLoadNumber(String line, String loadNumber);

}
