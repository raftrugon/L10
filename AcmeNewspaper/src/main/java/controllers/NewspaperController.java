
package controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.NewspaperService;
import services.UserService;
import utilities.internal.SchemaPrinter;
import controllers.AbstractController;
import domain.Newspaper;
import domain.User;

@Controller
@RequestMapping("/newspaper")
public class NewspaperController extends AbstractController {

	@Autowired
	private NewspaperService	newspaperService;


	//Constructor
	public NewspaperController() {
		super();
	}

	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView result;
		final List<Newspaper> newspapers = new ArrayList<Newspaper>(newspaperService.findAll());
		result = new ModelAndView("newspaper/list");
		result.addObject("newspapers", newspapers);
		result.addObject("requestUri", "newspaper/list.do");
		return result;
	}
	
	@RequestMapping("/display")
	public ModelAndView display(@RequestParam(required=true) int newspaperId){
		ModelAndView res;
		Newspaper newspaper = newspaperService.findOne(newspaperId);
		res = new ModelAndView("newspaper/display");
		res.addObject("newspaper",newspaper);
		res.addObject("requestUri", "newspaper/display.do");
		return res;
	}
}