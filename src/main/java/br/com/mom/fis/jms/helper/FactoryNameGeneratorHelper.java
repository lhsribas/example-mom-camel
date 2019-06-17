package br.com.mom.fis.jms.helper;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class FactoryNameGeneratorHelper {

	public String name() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
}
