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

import services.ArticleService;
import services.UserService;
import controllers.AbstractController;
import domain.Article;
import domain.User;

@Controller
@RequestMapping("/article")
public class ArticleController extends AbstractController {

	@Autowired
	private ArticleService	articleService;


	//Constructor
	public ArticleController() {
		super();
	}

	@RequestMapping("/list")
	public ModelAndView list(@RequestParam(required=false,defaultValue="")String keyword) {
		ModelAndView result;
		List<Article> articles = new ArrayList<Article>(articleService.findAllPublishedKeyword(keyword));
		result = new ModelAndView("article/list");
		result.addObject("articles", articles);
		result.addObject("keyword",keyword);
		result.addObject("requestUri", "article/list.do");
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