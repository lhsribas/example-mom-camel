package br.com.mom.fis.route;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;

public class SendMessageToBrokerC extends RouteBuilder{
	
	@Value("${broker.b.queue}")
	private String queueB;
	
	@Value("${broker.b.dlq.queue}")
	private String dlqQueueB;
	
	@Value("${broker.c.queue}")
	private String queueC;

	@Override
	public void configure() throws Exception {
		//@formatter:off
		onException(Exception.class)
			.handled(true)
			.log(LoggingLevel.ERROR, "${exception.message}")
			.to("brokerB:".concat(dlqQueueB));
		
		from("brokerB:".concat(queueB))
			.routeDescription("Copy message from brokerB to bokerC")
			.routeId("brokerb-to-brokerc-id")
			.autoStartup(true)
			.log("${body}")
			.to("brokerC:".concat(queueC))
		.end();
		//@formatter:on
	}

}
