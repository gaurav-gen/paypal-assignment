/**
 * 
 */
package com.paypal.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 703177925
 *
 */
@SpringBootApplication
@ComponentScan("com.paypal")
public class ApplicationMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(ApplicationMain.class, args);
	}

}
