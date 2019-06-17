package br.com.mom.fis.jms.component;

import org.apache.activemq.jms.pool.PooledConnectionFactory;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.component.jms.JmsConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.mom.fis.jms.factory.JMSConnectionFactory;
import br.com.mom.fis.jms.helper.FactoryNameGeneratorHelper;

@Configuration
public class JMSComponentC {

	@Value("${amq.broker.c}")
	private String broker;

	@Value("${amq.broker.c.user}")
	private String user;

	@Value("${amq.broker.c.pwd}")
	private String pwd;

	@Value("${amq.broker.c.max.pooled}")
	private String jmsMaxPooledConnections;

	@Autowired
	private JMSConnectionFactory connectionFactory;

	@Autowired
	private FactoryNameGeneratorHelper factoryName;

	private JmsComponent jms;

	@Bean("brokerC")
	public JmsComponent brokerA() {
		try {
			PooledConnectionFactory pool = connectionFactory.getPooledConnctionFactory(factoryName.name(), broker, user,
					pwd, Integer.parseInt(jmsMaxPooledConnections));

			JmsConfiguration jmsConfiguration = new JmsConfiguration();
			jmsConfiguration.setConnectionFactory(pool);

			setJms(new JmsComponent(jmsConfiguration));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return getJms();
	}

	protected JmsComponent getJms() {
		return jms;
	}

	protected void setJms(JmsComponent jms) {
		this.jms = jms;
	}
}
