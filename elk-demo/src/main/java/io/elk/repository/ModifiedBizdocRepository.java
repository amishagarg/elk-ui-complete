package io.elk.repository;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import io.elk.entities.ModifiedBizdoc;


@Transactional(readOnly = true)
public interface ModifiedBizdocRepository extends CrudRepository<ModifiedBizdoc, String> {
	
	@Transactional(timeout = 10)
	List<ModifiedBizdoc> findAll();
	
	@Query(value = "SELECT * FROM MODIFIEDBIZDOC mb WHERE mb.TIMESTAMP > :offsettimestamp ",nativeQuery = true)
	Collection<ModifiedBizdoc> scanForLatest(@Param("offsettimestamp") Timestamp timestamp);

	@Query(value = "SELECT * from (SELECT * FROM MODIFIEDBIZDOC ORDER BY MODIFIEDBIZDOC.TIMESTAMP DESC) where rownum <= 1",nativeQuery = true)
	ModifiedBizdoc scanForTimestamp();
	
	

}
