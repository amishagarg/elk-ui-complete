package io.elk.service;

import java.io.IOException;
import java.util.Map;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.elk.configuration.ElasticConfiguration;
import io.elk.entities.ModifiedBizdoc;


@Service
public class ModifiedBizdocElasticService {

	public final String INDEX="bizdocsdata";
	public final String TYPE="bizdocs";
	//private RestHighLevelClient restHighLevelClient;
	private ObjectMapper objectMapper;
	ElasticConfiguration elasticConfiguration = new ElasticConfiguration();
	private RestHighLevelClient restHighLevelClient=elasticConfiguration.createInstance();
	
	 public ModifiedBizdocElasticService() {
	  
	  }
	 
	public ModifiedBizdocElasticService(ObjectMapper objectMapper,RestHighLevelClient restHighLevelClient) throws Exception{
		
		this.objectMapper=objectMapper;
		this.restHighLevelClient=restHighLevelClient;
	}
	
	@JsonInclude(Include.NON_DEFAULT)
	public ModifiedBizdoc insertModifiedBizdoc(ModifiedBizdoc modifiedBizdoc) {
		
		@SuppressWarnings("unchecked")
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String,Object> documentMapper = objectMapper.convertValue(modifiedBizdoc, Map.class);	
		System.out.println(modifiedBizdoc);
		System.out.println(documentMapper);
		IndexRequest indexRequest = new IndexRequest(INDEX,TYPE,modifiedBizdoc.getInternalid()).source(documentMapper);
		System.out.println(indexRequest.toString());
		try
		{
			/*
			 * restHighLevelClient = new RestHighLevelClient(RestClient.builder(new
			 * HttpHost("localhost",9200,"http"), (new HttpHost("localhost",9201,"http"))));
			 */
			System.out.println("hi folks");
			IndexResponse indexResponse = restHighLevelClient.index(indexRequest);
			System.out.println(indexResponse.getIndex()+" "+indexResponse.getType());
			System.out.println("Successfully sent the concerned bizdoc");
		//restHighLevelClient.close();
		//indexResponse.forcedRefresh();
		
			
		}
		
		catch(IOException e)
		{
			e.getLocalizedMessage();
			System.out.println(e);
		}
		catch(Exception e)
		{
			e.getLocalizedMessage();
			System.out.println(e);
		}
		
		return modifiedBizdoc;
		
	}
	
	
}
