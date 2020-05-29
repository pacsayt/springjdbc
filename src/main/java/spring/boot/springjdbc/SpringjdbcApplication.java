package spring.boot.springjdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * Spring Boot JDBC Examples
 * https://mkyong.com/spring-boot/spring-boot-jdbc-examples/
 * This is much better than the Baeldung tutorial :
 * https://www.baeldung.com/spring-jdbc-jdbctemplate
 */

@SpringBootApplication
public class SpringjdbcApplication
{

	private static ApplicationContext applicationContext;

	public static void main(String[] args)
	{
		applicationContext = SpringApplication.run(SpringjdbcApplication.class, args);
	}
}
