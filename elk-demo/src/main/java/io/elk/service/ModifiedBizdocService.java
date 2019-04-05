package io.elk.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.elk.configuration.ElasticConfiguration;
import io.elk.dao.ModifiedBizdocDao;
import io.elk.entities.ModifiedBizdoc;


@Service
@Transactional
public class ModifiedBizdocService {
	
	@Autowired
	private ModifiedBizdocDao modifiedBizdocDao;
	
	ElasticConfiguration elasticConfiguration = new ElasticConfiguration();
	@Autowired
	private ModifiedBizdocElasticService modifiedBizdocElasticService ;
	private RestHighLevelClient restHighLevelClient=elasticConfiguration.createInstance();
	private ObjectMapper objectMapper;
	public static Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	public static Timestamp timestamp2 = Timestamp.valueOf("2019-01-05 17:06:48.505");
	public ModifiedBizdocService()
	{
		
	}

	public List<ModifiedBizdoc> findAll()
	{
		List<ModifiedBizdoc> list = new ArrayList<ModifiedBizdoc>();
		list=modifiedBizdocDao.findAll();
		if (list.size() > 0) {
			
			for(ModifiedBizdoc modifiedBizdoc : list)
			{
				modifiedBizdocElasticService.insertModifiedBizdoc(modifiedBizdoc);
			}
			System.out.println("Successfully transferred to Elastic Search");
            return list;
        } else {
            return null;
        }
	}
	
	public List<ModifiedBizdoc> scanForLatest()
	{
		List<ModifiedBizdoc> list = new ArrayList<ModifiedBizdoc>();
		if(modifiedBizdocDao!=null)
		{
			System.out.println("not null");
		}
		if(modifiedBizdocDao==null)
		{
			System.out.println("is null");
		}
		list=modifiedBizdocDao.scanForLatest();
		if (list.size() > 0) {
			
			System.out.println("Not an Empty List");
            return list;
        } else {
        	System.out.println("Empty List");
            return list;
        }
	}
	
	public ModifiedBizdoc scanForTimestamp()
	{
		ModifiedBizdoc modifiedBizdoc = modifiedBizdocDao.scanForTimestamp();
		return modifiedBizdoc;
	}
}
