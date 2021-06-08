package com.mainapp.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mainapp.model.StatusUpdate;
import com.mainapp.service.StatusUpdateService;

@Controller //from model-view-controller. This class will figure out for each url what content should be served up to the browser
public class PageController {
	
	@Autowired
	private StatusUpdateService statusUpdateService;
	
	@RequestMapping("/") // when the user goes to this url, that request will be handled by the home() function
//	@ResponseBody // what we return in this function is what we want to send to the browser (we don't need it if we use jsp)
	String home() {
		return "app.homepage"; // renders the definition in the tiles.xml file
	}

	@RequestMapping("/about")
	String about() {
		return "app.about"; //the tilesViewResolver in App.java will check in tiles.xml what to render exactly
	}
	
	@RequestMapping(value="/viewstatus", method=RequestMethod.GET)
	ModelAndView viewStatus(ModelAndView modelAndView, @RequestParam(name="p", defaultValue="1") int pageNumber) { //the name is p (?p=), and the default page is 1 (doing only /viewstatus)
		
//		System.out.println();
//		System.out.println("=========" + pageNumber + "=========="); //when doing /viewstatus?p=11 for example, I see ==== 11 ===== on the console
//		System.out.println();
		
		Page<StatusUpdate> page = statusUpdateService.getPage(pageNumber);
		
		modelAndView.getModel().put("page", page);
		
		modelAndView.setViewName("app.viewStatus");
		
		return modelAndView;
	}
	
	@RequestMapping(value="/addstatus", method=RequestMethod.GET)
//	With @ModelAttribute("statusUpdate") we tell spring that StatusUpdate statusUpdate is something it should create an object of, and add it to the model
//	statusUpdate in @ModelAttribute("statusUpdate") matches the commandName on the form (<form:form modelAttribute="statusUpdate"> in addstatus.jsp)
	ModelAndView addStatus(ModelAndView modelAndView, @ModelAttribute("statusUpdate") StatusUpdate statusUpdate) { //a model is passed to the page, and we can add data to that model
		modelAndView.setViewName("app.addStatus"); //the view that will be rendered
//		StatusUpdate statusUpdate = new StatusUpdate();
		
		StatusUpdate latestStatusUpdate = statusUpdateService.getLatest();
//		modelAndView.getModel().put("statusUpdate", statusUpdate); //put(key, value)
		modelAndView.getModel().put("latestStatusUpdate", latestStatusUpdate);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/addstatus", method=RequestMethod.POST)
	//@Valid makes sure that the form will get validated according to the validation annotations we added in StatusUpdate.java (like @NotNull and @Size(min=5, max=255)). The result of that validation, will end up being the BindingResult parameter
	ModelAndView addStatus(ModelAndView modelAndView, @Valid StatusUpdate statusUpdate, BindingResult result) {  
		modelAndView.setViewName("app.addStatus");
		
		if(!result.hasErrors()) { //the validation did not fail
			statusUpdateService.save(statusUpdate); //this saves to the database what we write in the textarea in addstatus.jsp (we can see it in mysql workbench)
			modelAndView.getModel().put("statusUpdate", new StatusUpdate()); //now the form goes blank after submitting
		} 
		
		StatusUpdate latestStatusUpdate = statusUpdateService.getLatest();
		modelAndView.getModel().put("latestStatusUpdate", latestStatusUpdate);
		
		return modelAndView;
	}
}
