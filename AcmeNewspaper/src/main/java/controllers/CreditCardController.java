
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

import services.CreditCardService;
import controllers.AbstractController;
import domain.CreditCard;

@Controller
@RequestMapping("/creditCard")
public class CreditCardController extends AbstractController {

	@Autowired
	private CreditCardService	creditCardService;


	//Constructor
	public CreditCardController() {
		super();
	}
//
//	@RequestMapping("/list")
//	public ModelAndView list() {
//		ModelAndView result;
//		final List<CreditCard> creditCards = new ArrayList<CreditCard>(creditCardService.findAll());
//		result = new ModelAndView("creditCard/list");
//		result.addObject("creditCards", creditCards);
//		result.addObject("requestUri", "creditCard/list.do");
//		return result;
//	}
//	
//		@RequestMapping(value = "/create", method = RequestMethod.GET)
//	public ModelAndView create() {
//		ModelAndView result;
//		try {
//			CreditCard creditCard = creditCardService.create();
//			result = newEditModelAndView(creditCard);
//		} catch (Throwable oops) {
//			result = new ModelAndView("redirect:list.do");
//		}
//		return result;
//	}
//
//	@RequestMapping(value = "/edit", method = RequestMethod.GET)
//	public ModelAndView edit(@RequestParam(required = true) final int creditCardId) {
//		return newEditModelAndView(creditCard);
//	}
//
//	@RequestMapping(value = "/save", method = RequestMethod.POST, params = "save")
//	public ModelAndView save(@Valid final CreditCard creditCard, final BindingResult binding) {
//		ModelAndView result;
//		if (binding.hasErrors())
//			result = newEditModelAndView(creditCard);
//		else
//			try {
//				creditCardService.save(creditCard);
//				result = new ModelAndView("redirect:list.do");
//			} catch (Throwable oops) {
//				result = newEditModelAndView(creditCard);
//				result.addObject("message", "creditCard.commitError");
//			}
//		return result;
//	}
//
//	@RequestMapping(value = "/save", method = RequestMethod.POST, params = "delete")
//	public ModelAndView delete(@Valid final CreditCard creditCard, final BindingResult binding) {
//		ModelAndView result;
//		if (binding.hasErrors())
//			result = newEditModelAndView(creditCard);
//		else
//			try {
//				creditCardService.delete(creditCard);
//				result = new ModelAndView("redirect:list.do");
//			} catch (Throwable oops) {
//				result = newEditModelAndView(creditCard);
//				result.addObject("message", "creditCard.commitError");
//			}
//		return result;
//	}
//	protected ModelAndView newEditModelAndView(final CreditCard creditCard) {
//		ModelAndView result;
//		result = new ModelAndView("creditCard/edit");
//		result.addObject("creditCard", creditCard);
//		result.addObject("actionUri", "creditCard/save.do");
//		return result;
//	}
}