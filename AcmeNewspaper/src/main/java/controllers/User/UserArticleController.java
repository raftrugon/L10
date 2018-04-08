
package controllers.User;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ArticleService;
import services.NewspaperService;
import services.UserService;
import controllers.AbstractController;
import domain.Article;
import domain.Newspaper;

@Controller
@RequestMapping("/user/article")
public class UserArticleController extends AbstractController {

	@Autowired
	private ArticleService	articleService;
	@Autowired
	private NewspaperService	newspaperService;
	@Autowired
	private UserService		userService;


	//Constructor
	public UserArticleController() {
		super();
	}

	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView result;
		//final List<Article> articles = new ArrayList<Article>(this.userService.findByPrincipal().getArticles());
		result = new ModelAndView("user/article/list");
		//result.addObject("articles", articles);
		result.addObject("requestUri", "user/article/list.do");
		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		try {
			Article article = this.articleService.create();
			result = this.newEditModelAndView(article);
		} catch (Throwable oops) {
			result = new ModelAndView("redirect:list.do");
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam(required = true) final int articleId) {
		ModelAndView result;

		try{
			Article article = this.articleService.findOne(articleId);
			//Assert.isTrue(article.getNewspaper().getUser().equals(this.userService.findByPrincipal()));
			article.setNewspaper(null);
			result = this.newEditModelAndView(article);

		} catch(Throwable oops){
			result = new ModelAndView("redirect:list.do");
		}

		return result;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Article article, final BindingResult binding) {
		ModelAndView result;
		System.out.println(binding);
		if (binding.hasErrors()){
			article.setNewspaper(null);
			result = this.newEditModelAndView(article);
		}
		else
			try {
				System.out.println("1");
				this.articleService.save(article);
				System.out.println("2");
				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {
				oops.printStackTrace();
				article.setNewspaper(null);
				result = this.newEditModelAndView(article, "article.commitError");
			}
		return result;
	}
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


	protected ModelAndView newEditModelAndView(final Article article) {
		ModelAndView result;
		result = this.newEditModelAndView(article, null);
		return result;
	}

	protected ModelAndView newEditModelAndView(final Article article, final String message) {
		System.out.println("1");
		ModelAndView result;
		Collection<Newspaper> nonPublishedNewspapers = this.newspaperService.findMyNonPublished();

		result = new ModelAndView("user/article/edit");
		result.addObject("nonPublishedNewspapers", nonPublishedNewspapers);
		System.out.println("2");
		result.addObject("article", article);
		System.out.println("3");
		result.addObject("actionUri", "user/article/edit.do");
		result.addObject("message", message);
		return result;
	}
}