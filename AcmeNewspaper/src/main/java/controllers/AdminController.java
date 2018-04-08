
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

import services.AdminService;
import services.ArticleService;
import controllers.AbstractController;
import domain.Admin;

@Controller
@RequestMapping("/admin")
public class AdminController extends AbstractController {

	@Autowired
	private AdminService	adminService;
	@Autowired
	private ArticleService	articleService;


	//Constructor
	public AdminController() {
		super();
	}
	
	@RequestMapping("/article/inappropriate")
	public ModelAndView setArticleInappropriate(@RequestParam(required=true)int articleId){
		articleService.markAsInappropriate(articleId);
		return new ModelAndView("redirect: ../../../../article/list.do");
	}
}