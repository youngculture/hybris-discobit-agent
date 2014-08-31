package com.youngculture.ecom.hybris.discobit;

import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.util.Enumeration;
import java.util.Properties;
import java.util.UUID;
import java.util.logging.Logger;

import com.mashape.unirest.http.Unirest;
import com.v3rticle.oss.discobit.client.DiscobitClientFactory;
import com.v3rticle.oss.discobit.client.DiscobitOperationException;
import com.v3rticle.oss.discobit.client.bootstrap.DiscobitSettings;

public class HybrisDiscobitAgent {

	private static Logger log = Logger.getLogger(HybrisDiscobitAgent.class.getName());
	
	public static void main(String[] args){
		execute();
	}
	
	public static void premain(String agentArgs, Instrumentation inst){
		execute();
	}
	
	
	private static void execute(){
		log.info("executing");
		DiscobitSettings settings = new DiscobitSettings();
		try {
			log.info("Reading discobit configuration from " + settings.getServerURL() + "::" + settings.getDefaultConfigurationUUID());
			Properties props = DiscobitClientFactory.getClient().getConfig(UUID.fromString(settings.getDefaultConfigurationUUID()));
			Enumeration e = props.propertyNames();
		    while (e.hasMoreElements()) {
		      String key = (String) e.nextElement();
		      String value = (String) props.getProperty(key);
		      
		      if (key != null && value != null){
		    	  System.setProperty(key, value);
		      } else if (key != null && value == null){
		    	  System.clearProperty(key);
		      }
		    }

			log.info("injected " + props.size() + " properties to system context");

		} catch (DiscobitOperationException e) {
			e.printStackTrace();
		} finally {
			try {
				Unirest.shutdown();
				log.info("unirest shutdown");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
