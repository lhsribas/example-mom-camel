package br.com.mom.fis.jms.factory;

import org.apache.activemq.artemis.api.jms.ActiveMQJMSClient;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.apache.activemq.jms.pool.PooledConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class JMSConnectionFactory {

	private static final Logger LOGGER = LoggerFactory.getLogger(JMSConnectionFactory.class);

	public PooledConnectionFactory getPooledConnctionFactory(final String name, final String broker, final String user,
			final String pwd, final Integer jmsMaxPooledConnections) throws Exception {
		
		LOGGER.debug("Starting connection :: >> ".concat(broker).concat(" :: with the user >>".concat(user)));
		
		ActiveMQConnectionFactory activeMQConnectionFactory = ActiveMQJMSClient.createConnectionFactory(broker, name);
		activeMQConnectionFactory.setUser(user);
		activeMQConnectionFactory.setPassword(pwd);

		PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory();
		pooledConnectionFactory.setConnectionFactory(activeMQConnectionFactory);
		pooledConnectionFactory.setMaxConnections(jmsMaxPooledConnections);

		return pooledConnectionFactory;
	}

}
