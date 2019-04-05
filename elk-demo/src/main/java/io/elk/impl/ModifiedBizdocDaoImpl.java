package io.elk.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import io.elk.dao.ModifiedBizdocDao;
import io.elk.entities.ModifiedBizdoc;
import io.elk.repository.ModifiedBizdocRepository;
import io.elk.service.ModifiedBizdocService;


@Repository
public class ModifiedBizdocDaoImpl implements ModifiedBizdocDao {

private static final Logger logger = LoggerFactory.getLogger(ModifiedBizdocDaoImpl.class);
	
	@Autowired
	private ModifiedBizdocRepository ModifiedBizdocRepository;
	
	
	public ModifiedBizdocDaoImpl()
	{
		
	}
	
	@Override
	public List<ModifiedBizdoc> findAll() {
		try
		{
			List<ModifiedBizdoc> list = new ArrayList<ModifiedBizdoc>();
			
			list= ModifiedBizdocRepository.findAll();
			System.out.println(list);
			System.out.println("Inside dao impl for get all");
			return list;
			
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(), e);
		}
		return null;
	}
	
	public List<ModifiedBizdoc> scanForLatest()
	{
		List<ModifiedBizdoc> list = new ArrayList<ModifiedBizdoc>();
		try
		{
			
			
			list= (List<ModifiedBizdoc>) ModifiedBizdocRepository.scanForLatest(ModifiedBizdocService.timestamp2);
			System.out.println(list+"  scna scna ");
			System.out.println("Inside dao impl for Scan");
			
			for(ModifiedBizdoc m:list)
			{
				System.out.println(m.getInternalid()+" "+m.getReceiver());
			}
			return list;
			
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(), e);
		}
		return list;
	}
	
     public ModifiedBizdoc scanForTimestamp()
     {
    	 try
    	 {
    		 ModifiedBizdoc modifiedBizdoc = ModifiedBizdocRepository.scanForTimestamp();
    		 return modifiedBizdoc;
    	 }
    	 catch(Exception e)
 		{
 			logger.error(e.getMessage(), e);
 		}
 		return null;
     }

}
