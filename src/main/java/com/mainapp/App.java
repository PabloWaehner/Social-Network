package com.mainapp;

// Spring revolves around the idea of Beans. Beans are java objects, but instead of creating them
// oneself with the new keyword, they are created for one by spring. 

// This uses embedded Tomcat. The browser contacts Tomcat, Tomcat goes to the application, finds the mapping for the url where we've gone 
// to, and gets back the content from the home() 

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

@SpringBootApplication // this is three annotations in one, and covers the two below
//@EnableAutoConfiguration // it will try to use the beans
//@ComponentScan //this tells Spring, when it starts with the main application, that it should search for other Classes that have been marked with annotations like @Controller (like in PageController.java). And it should create objects from those classes (Beans)
public class App extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args); // first argument is the name of the class, second argument is the
												// String argument from the command line
	}
	
//	@RequestMapping("/") //if the user goes to this url, hello world will be returned
//	@ResponseBody
//	String home() {
//		return "home"; // spring boot is goint to take the prefix /WEB-INF/jsps/ in our application.prop file, and add the suffix .jsp (to render home.jsp)
//	}

//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//		return application.sources(App.class);
//	}

	//We need to tell tiles what configuration to use (the xml file is used to configure the layout)
	@Bean
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer tilesConfigurer = new TilesConfigurer();
		String[] defs = { "/WEB-INF/tiles.xml" };
		tilesConfigurer.setDefinitions(defs);
		return tilesConfigurer;
	}

	//Behind the scenes, Spring has a way of taking a url pattern and figuring out what view to display for that url (what jsp to display in this case)
	//But we want to change that: we want to take a url and go to a tiles config file (tiles.xml), which specifies which jsp we should use to create the view
	//Spring has a url based ViewResolver that looks at urls and in this case translates them to jsp files. But we want to configure our own
	@Bean // this tells Spring that this object should be considered a bean
	public UrlBasedViewResolver tilesViewResolver() {
		UrlBasedViewResolver tilesViewResolver = new UrlBasedViewResolver();
		tilesViewResolver.setViewClass(TilesView.class);
		return tilesViewResolver;
	}

}
// The build tool we are using in this case is Maven, but we could use another one like Gradle (for Dependencies Management for example)
// curl --head localhost:8080 in the terminal after running App.java shows information regarding the server, and so on
// JSP: JavaServer Pages -> template engine (under src/main/webapp/WEB-INF)
// the jsps will be transformed into servlets, which are special java classes that can handle web requests
// (home.jsp, for example, will become an object)
// Apache tiles allows us to specify the layout of our site in a single config file (tiles.xml)
// To connect to the database, we add the dependencies mysql-connector-java and spring-boot-starter-data-jpa to pom.xml. Also add configuration to application.properties
// Annotations are used to save to the database (JPA annotations, but behind the scenes it's hibernate)

