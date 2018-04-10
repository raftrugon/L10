package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ArticleService;
import services.CustomerService;
import services.UserService;
import controllers.AbstractController;
import domain.Article;
import domain.Customer;
import domain.User;

@Controller
@RequestMapping("/article")
public class ArticleController extends AbstractController {

	@Autowired
	private ArticleService	articleService;
	@Autowired
	private CustomerService customerService;


	//Constructor
	public ArticleController() {
		super();
	}

	@RequestMapping("/list")
	public ModelAndView list(@RequestParam(required=false,defaultValue="")String keyword) {
		ModelAndView result;
		List<Article> articles = new ArrayList<Article>(articleService.findAllPublishedKeyword(keyword));
		Map<Article,Boolean> articlesMap = new HashMap<Article,Boolean>();
		try{
			for (Article a: articles){
				articlesMap.put(a, customerService.isSubscribed(a.getNewspaper()));
			}
		}catch(Throwable oops){
			//Volvemos a coger los artículos ya que al petar el customerService se pierden (BUG de hibernate)
			articles = new ArrayList<Article>(articleService.findAllPublishedKeyword(keyword));
			for (Article a: articles){
				articlesMap.put(a, false);
			}
		}
		result = new ModelAndView("article/list");
		result.addObject("articlesMap", articlesMap);
		result.addObject("articles",articles);
		result.addObject("keyword",keyword);
		result.addObject("requestUri", "article/list.do");
		return result;
	}
	
	@RequestMapping("/display")
	public ModelAndView display(@RequestParam(required=true)int articleId){
		ModelAndView result;
		try{
			Article a = articleService.findOne(articleId);
			if(!articleService.isPublished(a)) throw new Exception();
			if(a.getNewspaper().getIsPrivate()){
				if(!customerService.isSubscribed(a.getNewspaper())) throw new Exception();
			}
			result = new ModelAndView("article/display");
			result.addObject("article",a);
			result.addObject("requestUri", "article/display.do");
		}catch(Throwable oops){
			result = new ModelAndView("redirect:list.do");
		}
		return result;
	}
	
//	
//	@RequestMapping(value = "/create", method = RequestMethod.GET)
//	public ModelAndView create() {
//		ModelAndView result;
//		try {
//			Article article = articleService.create();
//			result = newEditModelAndView(article);
//		} catch (Throwable oops) {
//			result = new ModelAndView("redirect:list.do");
//		}
//		return result;
//	}
//
//	@RequestMapping(value = "/edit", method = RequestMethod.GET)
//	public ModelAndView edit(@RequestParam(required = true) final int articleId) {
//		Article article = articleService.findOne(articleId);
//		if (article.getUser().equals(userService.findByPrincipal()))
//			return newEditModelAndView(article);
//		else
//			return new ModelAndView("redirect:list.do");
//	}
//
//	@RequestMapping(value = "/save", method = RequestMethod.POST, params = "save")
//	public ModelAndView save(@Valid final Article article, final BindingResult binding) {
//		ModelAndView result;
//		if (binding.hasErrors())
//			result = newEditModelAndView(article);
//		else
//			try {
//				articleService.save(article);
//				result = new ModelAndView("redirect:list.do");
//			} catch (Throwable oops) {
//				result = newEditModelAndView(article);
//				result.addObject("message", "article.commitError");
//			}
//		return result;
//	}
//
//	@RequestMapping(value = "/save", method = RequestMethod.POST, params = "delete")
//	public ModelAndView delete(@Valid final Article article, final BindingResult binding) {
//		ModelAndView result;
//		if (binding.hasErrors())
//			result = newEditModelAndView(article);
//		else
//			try {
//				articleService.delete(article);
//				result = new ModelAndView("redirect:list.do");
//			} catch (Throwable oops) {
//				result = newEditModelAndView(article);
//				result.addObject("message", "article.commitError");
//			}
//		return result;
//	}
//	protected ModelAndView newEditModelAndView(final Article article) {
//		ModelAndView result;
//		result = new ModelAndView("article/edit");
//		result.addObject("article", article);
//		result.addObject("actionUri", "user/article/save.do");
//		return result;
//	}
}