
package controllers.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AdminService;
import services.ArticleService;
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


	//Constructor
	public AdminController() {
		super();
	}

	@RequestMapping("/article/inappropriate")
	public ModelAndView setArticleInappropriate(@RequestParam(required=true)int articleId){
		articleService.markAsInappropriate(articleId);
		return new ModelAndView("redirect: ../../../../article/list.do");
	}
	
	@RequestMapping("/newspaper/inappropriate")
	public ModelAndView setNewspaperInappropriate(@RequestParam(required=true)int newspaperId){
		newspaperService.markAsInappropriate(newspaperId);
		return new ModelAndView("redirect: ../../../../newspaper/list.do");
	}
}