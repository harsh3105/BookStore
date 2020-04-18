package com.bookApp.api.order.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;

@Configuration
public class CassnadraConfig extends AbstractCassandraConfiguration{
	
	public static final String KEYSPACE = "orderdb";
	 
	 @Override
	    public SchemaAction getSchemaAction() {
	        return SchemaAction.CREATE_IF_NOT_EXISTS;
	    }

	    @Override
	    protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
	    	System.out.println("created");
	        CreateKeyspaceSpecification specification = CreateKeyspaceSpecification.createKeyspace(KEYSPACE).ifNotExists();

	        return Arrays.asList(specification);
	    }


	    @Override
	    protected String getKeyspaceName() {
	        return KEYSPACE;
	    }

	    @Override
	    public String[] getEntityBasePackages() {
	        return new String[]{"com.bookApp.api.order.model"};
	    }
}
