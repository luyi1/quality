package com.ge.digital.quality.mapper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ge.digital.quality.entity.View;

@Repository
public interface ViewRepository extends JpaRepository<View, String> {

	@Query(value = "select convert(int,uview.loadQuantity) as loadQuantity from udvMa_AllMaterialProperyWithCode_v as uview where uview.materialCode=?1 and uview.line=?2", nativeQuery = true)
	Integer findByMaterialCodeAndLine(String materialCode, String line);

}
