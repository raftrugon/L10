
package controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ArticleService;
import services.NewspaperService;
import domain.Newspaper;

@Controller
@RequestMapping("/newspaper")
public class NewspaperController extends AbstractController {

	@Autowired
	private NewspaperService	newspaperService;
	@Autowired
	private ArticleService articleService;


	//Constructor
	public NewspaperController() {
		super();
	}

	@RequestMapping("/list")
	public ModelAndView list(@RequestParam(required = false, defaultValue="") String keyword ) {
		ModelAndView result;
		final List<Newspaper> newspapers = new ArrayList<Newspaper>(newspaperService.findByKeyword(keyword));
		result = new ModelAndView("newspaper/list");		
		result.addObject("newspapers", newspapers);
		result.addObject("requestUri", "newspaper/list.do");
		result.addObject("keyword", keyword);
		return result;
	}
	
	@RequestMapping("/display")
	public ModelAndView display(@RequestParam(required=true) int newspaperId){
		ModelAndView res;
		Newspaper newspaper = newspaperService.findOne(newspaperId);
		res = new ModelAndView("newspaper/display");
		res.addObject("newspaper",newspaper);
		res.addObject("articles", articleService.findAllPublishedForNewspaper(newspaper));
		res.addObject("requestUri", "newspaper/display.do");
		return res;
	}
}