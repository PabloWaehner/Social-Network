package com.mainapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //from model-view-controller. This class will figure out for each url what content should be served up to the browser
public class PageController {
	@RequestMapping("/") // when the user goes to this url, that request will be handled by the home() function
//	@ResponseBody // what we return in this function is what we want to send to the browser (we don't need it if we use jsp)
	String home() {
		return "app.homepage"; // renders the definition in the tiles.xml file
	}

	@RequestMapping("/about")
	String about() {
		return "app.about"; //the tilesViewResolver in App.java will check in tiles.xml what to render exactly
	}
	
	@RequestMapping("/addstatus")
	String addStatus() {
		return "app.addStatus";
	}
}
