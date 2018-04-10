
package controllers.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AdminService;
import services.ArticleService;
import services.ChirpService;
import services.NewspaperService;
import controllers.AbstractController;

@Controller
@RequestMapping("/admin")
public class AdminController extends AbstractController {

	@Autowired
	private AdminService	adminService;
	@Autowired
	private ArticleService	articleService;
	@Autowired
	private NewspaperService newspaperService;
	@Autowired
	private ChirpService chirpService;


	//Constructor
	public AdminController() {
		super();
	}

	@RequestMapping("/article/inappropriate")
	public ModelAndView setArticleInappropriate(@RequestParam(required=true)int articleId){
		articleService.markAsInappropriate(articleId);
		return new ModelAndView("redirect: ../../../../article/list.do");
	}
	
	@RequestMapping("/article/taboo-list")
	public ModelAndView listInappropriateArticles(){
		ModelAndView res = new ModelAndView("article/list");
		res.addObject("articles",articleService.findAllTaboo());
		return res;
	}
	
	@RequestMapping("/newspaper/inappropriate")
	public ModelAndView setNewspaperInappropriate(@RequestParam(required=true)int newspaperId){
		newspaperService.markAsInappropriate(newspaperId);
		return new ModelAndView("redirect: ../../../../newspaper/list.do");
	}
	
	@RequestMapping("/newspaper/taboo-list")
	public ModelAndView listInappropriateNewspaper(){
		ModelAndView res = new ModelAndView("newspaper/list");
		res.addObject("newspapers",newspaperService.findAllTaboo());
		return res;
	}
	
	@RequestMapping("/chirp/inappropriate")
	public ModelAndView setChirpInappropriate(@RequestParam(required=true)int chirpId){
		chirpService.markAsInappropriate(chirpId);
		return new ModelAndView("redirect:taboo-list.do");
	}
	
	
	@RequestMapping("/chirp/taboo-list")
	public ModelAndView listInappropriateChirps(){
		ModelAndView res = new ModelAndView("chirp/list");
		res.addObject("chirps",chirpService.findAllTaboo());
		return res;
	}
}