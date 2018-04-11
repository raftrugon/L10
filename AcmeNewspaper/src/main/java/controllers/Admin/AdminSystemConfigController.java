
package controllers.Admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AdminService;
import services.SystemConfigService;
import controllers.AbstractController;
import domain.Admin;
import domain.SystemConfig;

@Controller
@RequestMapping("/systemConfig")
public class AdminSystemConfigController extends AbstractController {

	@Autowired
	private SystemConfigService	systemConfigService;
	@Autowired
	private AdminService	adminService;


	//Constructor
	public AdminSystemConfigController() {
		super();
	}
	//
	//	@RequestMapping("/list")
	//	public ModelAndView list() {
	//		ModelAndView result;
	//		final List<SystemConfig> systemConfigs = new ArrayList<SystemConfig>(adminService.findByPrincipal().getSystemConfigs());
	//		result = new ModelAndView("systemConfig/list");
	//		result.addObject("systemConfigs", systemConfigs);
	//		result.addObject("requestUri", "admin/systemConfig/list.do");
	//		return result;
	//	}
	//
	//	@RequestMapping(value = "/create", method = RequestMethod.GET)
	//	public ModelAndView create() {
	//		ModelAndView result;
	//		try {
	//			SystemConfig systemConfig = systemConfigService.create();
	//			result = newEditModelAndView(systemConfig);
	//		} catch (Throwable oops) {
	//			result = new ModelAndView("redirect:list.do");
	//		}
	//		return result;
	//	}
	//
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		SystemConfig systemConfig = systemConfigService.get();
		try{
			Admin a = adminService.findByPrincipal();
			return newEditModelAndView(systemConfig);
		} catch(Throwable oops) {
			return new ModelAndView("redirect:/");
		}
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final SystemConfig systemConfig, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors())
			result = newEditModelAndView(systemConfig);
		else
			try {
				systemConfigService.save(systemConfig);
				result = new ModelAndView("redirect:");
			} catch (Throwable oops) {
				result = newEditModelAndView(systemConfig);
				result.addObject("message", "systemConfig.commitError");
				oops.printStackTrace();
			}
		return result;
	}
	//
	//	@RequestMapping(value = "/save", method = RequestMethod.POST, params = "delete")
	//	public ModelAndView delete(@Valid final SystemConfig systemConfig, final BindingResult binding) {
	//		ModelAndView result;
	//		if (binding.hasErrors())
	//			result = newEditModelAndView(systemConfig);
	//		else
	//			try {
	//				systemConfigService.delete(systemConfig);
	//				result = new ModelAndView("redirect:list.do");
	//			} catch (Throwable oops) {
	//				result = newEditModelAndView(systemConfig);
	//				result.addObject("message", "systemConfig.commitError");
	//			}
	//		return result;
	//	}
	protected ModelAndView newEditModelAndView(final SystemConfig systemConfig) {
		ModelAndView result;
		result = new ModelAndView("systemConfig/edit");
		result.addObject("systemConfig", systemConfig);
		result.addObject("actionUri", "admin/systemConfig/save.do");
		return result;
	}
}