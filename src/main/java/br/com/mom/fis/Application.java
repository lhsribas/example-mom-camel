package br.com.mom.fis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"classpath:spring/*.xml"})
public class Application {

  public static void main(final String[] args) {
    SpringApplication.run(Application.class, args);
  }

}
