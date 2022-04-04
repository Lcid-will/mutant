/*
 * Developed on 29/03/2022
 * by: William Rozo
 */
package com.co.lcid.mutant;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

// TODO: Auto-generated Javadoc
/**
 * The Class ServletInitializer.
 */
public class ServletInitializer extends SpringBootServletInitializer {

	/**
	 * Configure.
	 *
	 * @param application the application
	 * @return the spring application builder
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MutantApplication.class);
	}

}
