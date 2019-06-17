package br.com.mom.fis.route;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SendMessageToBrokerB extends RouteBuilder{
	
	@Value("${broker.a.queue}")
	private String queueA;
	
	@Value("${broker.a.dlq.queue}")
	private String dlqQueueA;
	
	@Value("${broker.b.queue}")
	private String queueB;

	@Override
	public void configure() throws Exception {
		//@formatter:off
		onException(Exception.class)
			.handled(true)
			.log(LoggingLevel.ERROR, "${exception.message}")
			.to("brokerAt:".concat(dlqQueueA));
		
		from("brokerA:".concat(queueA))
			.routeDescription("Copy message from brokerA to bokerB")
			.routeId("brokera-to-brokerb-id")
			.autoStartup(true)
			.log("${body}")
			.to("brokerB:".concat(queueB))
		.end();
		//@formatter:on
	}

}
