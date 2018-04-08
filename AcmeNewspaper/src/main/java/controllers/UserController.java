
package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ArticleService;
import services.UserService;
import domain.User;

@Controller
@RequestMapping("")
public class UserController extends AbstractController {

	@Autowired
	private UserService	userService;
	@Autowired
	private ArticleService articleService;


	//Constructor
	public UserController() {
		super();
	}

<<<<<<< HEAD
	//	@RequestMapping("/user-list")
	//	public ModelAndView list() {
	//		ModelAndView result = null;
	//		try{
	//			final List<User> users = new ArrayList<User>(this.userService.findAll());
	//			result = new ModelAndView("user/list");
	//			result.addObject("users", users);
	//			result.addObject("requestUri", "user/list.do");
	//		} catch(Throwable oops) {
	//			result = new ModelAndView("welcome/index");
	//		}
	//		return result;
	//	}

=======
	@RequestMapping("/user-list")
	public ModelAndView list() {
		ModelAndView result;
		final List<User> users = new ArrayList<User>(userService.findAll());
		result = new ModelAndView("user/list");
		result.addObject("users", users);
		result.addObject("requestUri", "user-list.do");
		return result;
	}
	
>>>>>>> 248572dcdf7be01f44ac5009f6ca6661bc893374
	@RequestMapping("/user-display")
	public ModelAndView display(@RequestParam(required=false) final Integer userId) {
		ModelAndView result;
<<<<<<< HEAD
		try{
			User user;
			if(userId != null)
				user = this.userService.findOne(userId);
			else
				user = this.userService.findByPrincipal();

			result = new ModelAndView("user/display");
			result.addObject("user", user);
			result.addObject("articles",this.articleService.findAllPublishedForUser(user));
			result.addObject("requestUri", "user/display.do");
		} catch(Throwable oops) {
			result = new ModelAndView("welcome/index");
		}
=======
		Boolean follows = false;
		User user;
		try{
			user = userService.findOne(userId);
		}catch(Throwable oops){
			return new ModelAndView("redirect:user-list.do");
		}
		try{			
			User principal = userService.findByPrincipal();
			follows = user.getFollowedBy().contains(principal) ;
		}catch(Throwable oops){
			user = userService.findOne(userId);
		}
		result = new ModelAndView("user/display");
		result.addObject("user", user);
		result.addObject("articles",articleService.findAllPublishedForUser(user));
		result.addObject("follows",follows);
		result.addObject("requestUri", "user-display.do");
>>>>>>> 248572dcdf7be01f44ac5009f6ca6661bc893374
		return result;
	}
}