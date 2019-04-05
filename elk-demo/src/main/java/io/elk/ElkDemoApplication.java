package io.elk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import io.elk.batch.PushingElasticBatch;
import io.elk.service.ModifiedBizdocElasticService;

@SpringBootApplication
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@EnableScheduling
public class ElkDemoApplication {

	@Autowired
	private static ModifiedBizdocElasticService modifiedBizdocElasticService ;
	
	public static void main(String[] args) {
		SpringApplication.run(ElkDemoApplication.class, args);
		
		/*
		 * PushingElasticBatch peb = new PushingElasticBatch(); peb.schedulerForDb();
		 */
	}
	//max file upload size
    private int maxUploadSizeInMb = 10 * 1024 * 1024; // 10 MB

}
