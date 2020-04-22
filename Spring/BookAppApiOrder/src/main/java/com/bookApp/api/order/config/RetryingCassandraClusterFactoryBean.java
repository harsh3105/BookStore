package com.bookApp.api.order.config;

import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;

import com.datastax.driver.core.exceptions.NoHostAvailableException;
import com.datastax.driver.core.exceptions.TransportException;

public class RetryingCassandraClusterFactoryBean extends CassandraClusterFactoryBean{
	
	@Override
	  public void afterPropertiesSet() throws Exception {
	    connect();
	  }

	  private void connect() throws Exception {
	    try {
	      super.afterPropertiesSet();
	    } catch (TransportException | IllegalArgumentException | NoHostAvailableException e) {
	    	System.out.println(e.getMessage());
	    	System.out.println("Retrying connection in 10 seconds");
	      //LOG.warn(e.getMessage());
	      //LOG.warn("Retrying connection in 10 seconds");
	      sleep();
	      connect();
	    }
	  }

	  private void sleep() {
	    try {
	      Thread.sleep(10000);
	    } catch (InterruptedException ignored) {
	    }
	  }

}
