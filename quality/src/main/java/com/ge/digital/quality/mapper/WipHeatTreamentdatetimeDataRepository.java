package com.ge.digital.quality.mapper;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ge.digital.quality.entity.WipHeatTreamentdatetimeData;

@Repository
public interface WipHeatTreamentdatetimeDataRepository extends JpaRepository<WipHeatTreamentdatetimeData, Integer> {

	WipHeatTreamentdatetimeData findByLoadnumberAndLine(String loadnumber, String line);

}
