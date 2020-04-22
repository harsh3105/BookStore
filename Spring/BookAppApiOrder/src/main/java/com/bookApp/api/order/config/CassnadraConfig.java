package com.bookApp.api.order.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.AbstractClusterConfiguration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;

@Configuration
public class CassnadraConfig extends AbstractClusterConfiguration{
	
	public static final String KEYSPACE = "orderdb";
	 
	@Bean
    @Override
    public CassandraClusterFactoryBean cluster() {

      RetryingCassandraClusterFactoryBean bean = new RetryingCassandraClusterFactoryBean();
      System.out.println("chala");
      bean.setAddressTranslator(getAddressTranslator());
      bean.setAuthProvider(getAuthProvider());
      bean.setClusterBuilderConfigurer(getClusterBuilderConfigurer());
      bean.setClusterName(getClusterName());
      bean.setCompressionType(getCompressionType());
      bean.setContactPoints(getContactPoints());
      bean.setLoadBalancingPolicy(getLoadBalancingPolicy());
      bean.setMaxSchemaAgreementWaitSeconds(getMaxSchemaAgreementWaitSeconds());
      bean.setMetricsEnabled(getMetricsEnabled());
      bean.setNettyOptions(getNettyOptions());
      bean.setPoolingOptions(getPoolingOptions());
      bean.setPort(getPort());
      bean.setProtocolVersion(getProtocolVersion());
      bean.setQueryOptions(getQueryOptions());
      bean.setReconnectionPolicy(getReconnectionPolicy());
      bean.setRetryPolicy(getRetryPolicy());
      bean.setSpeculativeExecutionPolicy(getSpeculativeExecutionPolicy());
      bean.setSocketOptions(getSocketOptions());
      bean.setTimestampGenerator(getTimestampGenerator());

      bean.setKeyspaceCreations(getKeyspaceCreations());
      bean.setKeyspaceDrops(getKeyspaceDrops());
      bean.setStartupScripts(getStartupScripts());
      bean.setShutdownScripts(getShutdownScripts());

      return bean;
    }

	    @Override
	    protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
	    	System.out.println("created");
	        CreateKeyspaceSpecification specification = CreateKeyspaceSpecification.createKeyspace(KEYSPACE).ifNotExists();

	        return Arrays.asList(specification);
	    }


	    @Override
	    protected String getContactPoints() {
	      return "cassandra";
	    }
}
