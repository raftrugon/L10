
package controllers.Admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AdminService;
import services.ArticleService;
import services.ChirpService;
import services.NewspaperService;
import services.UserService;
import controllers.AbstractController;
import domain.Newspaper;

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
	@Autowired
	private UserService userService;

	//Constructor
	public AdminController() {
		super();
	}

	@RequestMapping("/article/inappropriate")
	public ModelAndView setArticleInappropriate(@RequestParam(required=true) final int articleId){
		this.articleService.markAsInappropriate(articleId);
		return new ModelAndView("redirect: ../../../../article/list.do");
	}

	@RequestMapping("/article/taboo-list")
	public ModelAndView listInappropriateArticles(){
		ModelAndView res;
		try{

			res = new ModelAndView("article/list");
			res.addObject("articles",this.articleService.findAllTaboo());
			res.addObject("requestUri", "admin/article/taboo-list.do");
		} catch(Throwable oops) {
			res = new ModelAndView("redirect:/");
		}
		return res;
	}

	@RequestMapping("/newspaper/inappropriate")
	public ModelAndView setNewspaperInappropriate(@RequestParam(required=true) final int newspaperId){
		this.newspaperService.markAsInappropriate(newspaperId);
		return new ModelAndView("redirect: ../../../../newspaper/list.do");
	}

	@RequestMapping("/newspaper/taboo-list")
	public ModelAndView listInappropriateNewspaper(){
		ModelAndView res ;
		try{
			res = new ModelAndView("newspaper/list");
			res.addObject("newspapers",this.newspaperService.findAllTaboo());
			res.addObject("requestUri", "admin/newspaper/taboo-list.do");
		} catch(Throwable oops) {
			res = new ModelAndView("redirect:/");
		}
		return res;
	}

	@RequestMapping("/chirp/inappropriate")
	public ModelAndView setChirpInappropriate(@RequestParam(required=true) final int chirpId){
		this.chirpService.markAsInappropriate(chirpId);
		return new ModelAndView("redirect:taboo-list.do");
	}


	@RequestMapping("/chirp/taboo-list")
	public ModelAndView listInappropriateChirps(){
		ModelAndView res ;
		try{
			res = new ModelAndView("chirp/list");
			res.addObject("chirps",this.chirpService.findAllTaboo());
			res.addObject("requestUri", "admin/chirp/taboo-list.do");
		} catch(Throwable oops) {
			res = new ModelAndView("redirect:/");
		}
		return res;
	}

	@RequestMapping("/chirp/list")
	public ModelAndView listChirpsAdmin() {
		ModelAndView res;
		try{
			res = new ModelAndView("chirp/list");
			res.addObject("chirps",this.chirpService.findAll());
			res.addObject("requestUri", "admin/chirp/list.do");
		} catch(Throwable oops) {
			res = new ModelAndView("redirect:/");
		}
		return res;
	}

	@RequestMapping("/dashboard")
	public ModelAndView dashboard() {
		ModelAndView result;
		//try{
		result = new ModelAndView("admin/dashboard");

		List<Double> avgs = new ArrayList<Double>();
		avgs.add(this.userService.getStatsOfNewspapersPerUser()[0]);
		avgs.add(this.userService.getStatsOfArticlesPerUser()[0]);
		avgs.add(this.newspaperService.getStatsOfArticlesPerNewspaper()[0]);
		avgs.add(this.articleService.getFollowUpsPerArticleAvg());
		avgs.add(this.articleService.getFollowUpsPerArticleAvgAfterOneWeek());
		avgs.add(this.articleService.getFollowUpsPerArticleAvgAfterTwoWeeks());
		avgs.add(this.userService.getStatsOfChirpsPerUser()[0]);
		avgs.add(this.newspaperService.getArticleAvgForPrivateNewspapers());
		avgs.add(this.newspaperService.getArticleAvgForPublicNewspapers());
		result.addObject("avgs",avgs);

		List<Double> stddevs = new ArrayList<Double>();
		stddevs.add(this.userService.getStatsOfNewspapersPerUser()[1]);
		stddevs.add(this.userService.getStatsOfArticlesPerUser()[1]);
		stddevs.add(this.newspaperService.getStatsOfArticlesPerNewspaper()[1]);
		stddevs.add(this.userService.getStatsOfChirpsPerUser()[1]);
		result.addObject("stddevs",stddevs);

		List<Double> ratios = new ArrayList<Double>();
		ratios.add(this.userService.getRatioOfUsersWhoHaveCreatedNewspapers());
		ratios.add(this.userService.getRatioOfUsersWhoHavePostedMOreChirpsThan75Avg());
		ratios.add(this.newspaperService.getRatioOfPublicOverPrivateNewspapers());
		ratios.add(this.newspaperService.getRatioOfSubscribersVersusCustomersTotal());
		//Añadir metodo que falta

		List<Newspaper> newspapersOverAvg = new ArrayList<Newspaper>(this.newspaperService.getNewspapersOverAvg());
		result.addObject("newspapersOverAvg",newspapersOverAvg);
		List<Newspaper> newspapersUnderAvg = new ArrayList<Newspaper>(this.newspaperService.getNewspapersUnderAvg());
		result.addObject("newspapersUnderAvg",newspapersUnderAvg);
		/*} catch(Throwable oops) {
			result = new ModelAndView("redirect:/");
		}*/

		return result;
	}
}