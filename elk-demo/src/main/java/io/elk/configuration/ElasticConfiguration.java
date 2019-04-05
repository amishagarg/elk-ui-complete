package io.elk.configuration;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.mapper.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.context.annotation.Configuration;

@SuppressWarnings("rawtypes")
@Configuration
public class ElasticConfiguration extends AbstractFactoryBean{
	
	
	private static final Logger LOG= LoggerFactory.getLogger(ElasticConfiguration.class);
	
	//@Value("${spring.data.elasticsearch.cluster-nodes}")
	private String clusterNodes;
	
	//@Value("${spring.data.elasticsearch.cluster-name}")
	private String clusterName;
	
	private RestHighLevelClient restHighLevelClient;
	
	
	private ObjectMapper objectMapper;
	
	public ElasticConfiguration()
	{
		
	}
	
	@Override
	public void destroy()
	{
		try
		{
			if(restHighLevelClient !=null)
			{
				restHighLevelClient.close();
			}
		}
		catch(final Exception e)
		{
			LOG.error("Error closing Elastic search Client : ",e);
		}
	}
	
	@Override
	public boolean isSingleton()
	{
		return false;
	}
	
	@Override
	public RestHighLevelClient createInstance()
	{
		return buildClient();
	}
	
	private RestHighLevelClient buildClient()
	{
		try
		{
			restHighLevelClient = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost",9200,"http"),
					(new HttpHost("localhost",9201,"http"))));
			
			Header[] defaultHeaders = new Header[]{new BasicHeader("header", "value")};
		}
		catch(Exception e)
		{
			LOG.error("Error cresting the Rest client for Elastic search",e);
		}
		return restHighLevelClient;
		
	}
	
	public Class<RestHighLevelClient> getObjectType()
	{
		return RestHighLevelClient.class;
	}

}
