package io.elk.batch;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import io.elk.dao.ModifiedBizdocDao;
import io.elk.entities.ModifiedBizdoc;
import io.elk.repository.ModifiedBizdocRepository;
import io.elk.service.ModifiedBizdocElasticService;
import io.elk.service.ModifiedBizdocService;

@Component
public class PushingElasticBatch {

	@Autowired
	ModifiedBizdocService modifiedBizdocService;
	@Autowired
	private ModifiedBizdocRepository modifiedBizdocRepository;
	@Autowired
	private ModifiedBizdocDao modifiedBizdocDao;
	@Autowired
	private ModifiedBizdocElasticService modifiedBizdocElasticService ;
	
	
	
	/*
	 * @Autowired public PushingElasticBatch(ModifiedBizdocService
	 * modifiedBizdocService) { this.modifiedBizdocService=modifiedBizdocService; }
	 */
	
	@Scheduled(fixedRate=50000)
	public void schedulerForDb()
	{
		 
		System.out.println("hello");
		
		 List<ModifiedBizdoc> updateList = new ArrayList<ModifiedBizdoc>();
		 //modifiedBizdocService=new ModifiedBizdocService();
		 updateList=(List<ModifiedBizdoc>) modifiedBizdocService.scanForLatest(); 
		 if(updateList.size()>0)
		 {
			 System.out.println("Some thing has come");
			 System.out.println(ModifiedBizdocService.timestamp2);
			 for(ModifiedBizdoc modifiedBizdoc : updateList) {
				 
				 //System.out.println("");
				 modifiedBizdocElasticService.insertModifiedBizdoc(modifiedBizdoc);
				 }
			 
			 ModifiedBizdoc modifiedBizdoc = modifiedBizdocService.scanForTimestamp();
			 ModifiedBizdocService.timestamp2=modifiedBizdoc.getTimestamp();
			 System.out.println(ModifiedBizdocService.timestamp2);
		 }
		 else
		 {
			 System.out.println("We have nothing to insert into our elastic search ");
			 System.out.println("The Last successfull push was on : "+ModifiedBizdocService.timestamp2);
		 }
		 
		 
		
	
		
		/*
		 * List<Car> updateListCars = new ArrayList<Car>(); updateListCars=(List<Car>)
		 * employeeRepository.scanForCars(); System.out.println(updateListCars);
		 */

		
	}

}
