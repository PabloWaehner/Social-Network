package com.mainapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mainapp.model.StatusUpdate;
import com.mainapp.service.StatusUpdateService;

@Controller //from model-view-controller. This class will figure out for each url what content should be served up to the browser
public class PageController {
	
//	@RequestMapping("/") // when the user goes to this url, that request will be handled by the home() function
////	@ResponseBody // what we return in this function is what we want to send to the browser (we don't need it if we use jsp)
//	String home() {
//		return "app.homepage"; // renders the definition in the tiles.xml file
//	}
	
	@Autowired
	private StatusUpdateService statusUpdateService;
	
	@RequestMapping("/") 
	ModelAndView home(ModelAndView modelAndView) {
		StatusUpdate statusUpdate = statusUpdateService.getLatest();
		modelAndView.getModel().put("statusUpdate", statusUpdate);
		modelAndView.setViewName("app.homepage");
		return modelAndView;
	}

	@RequestMapping("/about")
	String about() {
		return "app.about"; //the tilesViewResolver in App.java will check in tiles.xml what to render exactly
	}
	
}
