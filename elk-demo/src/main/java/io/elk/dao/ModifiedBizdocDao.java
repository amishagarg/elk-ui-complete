package io.elk.dao;

import java.util.List;

import io.elk.entities.ModifiedBizdoc;

public interface ModifiedBizdocDao {
	
	
	public List<ModifiedBizdoc> findAll();
	
	public List<ModifiedBizdoc> scanForLatest();
	
	public ModifiedBizdoc scanForTimestamp();

}
