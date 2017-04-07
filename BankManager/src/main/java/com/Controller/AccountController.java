package com.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Controller.Services.Interfaces.CurrentAccountService;
import com.Controller.Services.Interfaces.SavingAccountService;
import com.Controller.Services.Interfaces.UserService;
import com.Model.Entities.CurrentAccount;
import com.Model.Entities.SavingAccount;
import com.Model.Entities.User;

@Controller
public class AccountController {

	@Autowired
	UserService userService;
	@Autowired
	CurrentAccountService currentAccountService;
	@Autowired
	SavingAccountService savingAccountService;

	@RequestMapping("/")
	public String racine(){
		return "redirect:/home";
	}

	@RequestMapping("/home")
	public void home(){
	}

	@GetMapping("/initDataBase")
	public String initDataBase(){
		userService.createSampleList();
		return "redirect:/login";
	}

	@GetMapping("/login")
	public String loginGet(){
		return "login";
	}

	@PostMapping("/login")
	public String loginPost(@RequestParam(required = false)String username,
			@RequestParam(required = false)String password,
			@RequestParam(required = true) String mode,
			Model model,
			HttpServletRequest request){

		switch (mode) {

		case "signIn":
			User user = userService.findUser(username);
			if (user == null){
				model.addAttribute("error", "Login non reconnu");
			} else {
				if (user.getPassword().equals(password)){
					request.getSession().setAttribute("user", user);
					return "redirect:/monCompte";
				} else
					model.addAttribute("error", "Mot de passe invalide");
			}
			break;


		case "signOut" :
			request.getSession().removeAttribute("user");
			model.addAttribute("error", "Vous êtes maintenant déconneté");

			break;
		}
		return "login";
	}

	@GetMapping("/monCompte")
	public void myAccount(Model model, HttpServletRequest request){
		User currentUser = (User) request.getSession().getAttribute("user");
		if (currentUser != null){
			User user = userService.findUser(currentUser.getUserName());
			if (user != null){
				model.addAttribute("clientName", user.getUserName());
				model.addAttribute("currentAccount", user.getCurrentAccount());
				model.addAttribute("savingAccount", user.getListSavindAccount());
			}
		}
	}

	@PostMapping("/monCompte")
	public String bankingOperation(@RequestParam(required = true) String type,
			@RequestParam(required  = true) String accountType,
			@RequestParam(required = true) int montant,
			@RequestParam(required = true) String accountID,
			HttpServletRequest request,
			HttpServletResponse response,
			Model model){
		String result;
		if (montant > 0){
			int id = Integer.parseInt(accountID);

			switch (type) {
			case "credit":
				switch (accountType) {
				case "current":
					CurrentAccount account = currentAccountService.findAccount(id);
					currentAccountService.credit(account, montant);
					result = "sucess";
					break;

				case "saving":
					SavingAccount account2 = savingAccountService.findAccount(id);
					savingAccountService.credit(account2, montant);
					result = "sucess";
					break;
				default : 
					result = "failed";
					break;
				}
				break;
			case "debit":
				switch (accountType) {
				case "current":
					CurrentAccount account = currentAccountService.findAccount(id);
					if(currentAccountService.debit(account, montant))
						result = "sucess";
					else
						result = "failed";
					break;

				case "saving":
					SavingAccount account2 = savingAccountService.findAccount(id);
					if (savingAccountService.debit(account2, montant))
						result = "sucess";
					else
						result = "failed";
					break;

				default:
					result = "failed";
					break;
				}
				break;
			default :
				result = "failed";
				break;
			}
		}
		else
			result = "montant";
		String url = "/monCompte?" + result;
		return "redirect:" + url;
	}

	@GetMapping("/borrow")
	public void borrow(){
	}

	@PostMapping("/borrow")
	public String makeBorrow(@RequestParam(required = true) int montant, HttpServletRequest request, Model model){
		String result;
		if (montant > 0){
			User user = userService.findUser(((User)request.getSession().getAttribute("user")).getUserName());
			if (currentAccountService.borrow(user.getCurrentAccount(), montant))
				result = "Prêt accepté : le montant à été crédité sur votre compte courant";
			else
				result = "Prêt refusé, vous êtes à découvert";
			model.addAttribute("result", result);
			return "borrow";
		}
		result = "Veuillez saisir un montant postif";
		model.addAttribute("result", result);
		return "borrow";
	}

	@GetMapping("/virement")
	public void virement(HttpServletRequest request, Model model){
		User user = (User) request.getSession().getAttribute("user");
		user = userService.findUser(user.getUserName());
		model.addAttribute("currentAccount", user.getCurrentAccount());
		model.addAttribute("savingAccount", user.getListSavindAccount());
	}

	@PostMapping("/virement")
	public String makeVirement(@RequestParam(required = true) String fromStr,
			@RequestParam(required = true) String toStr,
			@RequestParam(required = true) int montant){
		
		String[] fromSplit = fromStr.split(";");
		int from = Integer.parseInt(fromSplit[0]);
		String fromType = fromSplit[1];
		String[] toSplit = toStr.split(";");
		int to = Integer.parseInt(toSplit[0]);
		String toType = toSplit[1];		
		
		String result;
		
		
		// Impossible de faire un virement sur le même compte
		if((fromType.equals(toType) && from == to) || montant < 1)
			result = "failed";
		else{
			if (savingAccountService.virement(from, fromType, to, toType, montant))
				result = "sucess";
			else
				result = "failed";
		}
		String url = "/virement?" + result;
		return "redirect:" + url;
	}
}
