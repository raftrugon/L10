
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
import controllers.AbstractController;
import domain.Admin;

@Controller
@RequestMapping("/admin")
public class AdminController extends AbstractController {

	@Autowired
	private AdminService	adminService;


	//Constructor
	public AdminController() {
		super();
	}

	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView result;
		final List<Admin> admins = new ArrayList<Admin>(adminService.findAll());
		result = new ModelAndView("admin/list");
		result.addObject("admins", admins);
		result.addObject("requestUri", "admin/list.do");
		return result;
	}
	
		@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		try {
			Admin admin = adminService.create();
			result = newEditModelAndView(admin);
		} catch (Throwable oops) {
			result = new ModelAndView("redirect:list.do");
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam(required = true) final int adminId) {
		return newEditModelAndView(admin);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Admin admin, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors())
			result = newEditModelAndView(admin);
		else
			try {
				adminService.save(admin);
				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {
				result = newEditModelAndView(admin);
				result.addObject("message", "admin.commitError");
			}
		return result;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid final Admin admin, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors())
			result = newEditModelAndView(admin);
		else
			try {
				adminService.delete(admin);
				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {
				result = newEditModelAndView(admin);
				result.addObject("message", "admin.commitError");
			}
		return result;
	}
	protected ModelAndView newEditModelAndView(final Admin admin) {
		ModelAndView result;
		result = new ModelAndView("admin/edit");
		result.addObject("admin", admin);
		result.addObject("actionUri", "admin/save.do");
		return result;
	}
}