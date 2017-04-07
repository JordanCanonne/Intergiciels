package com.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Controller.Services.Interfaces.CurrentAccountService;
import com.Controller.Services.Interfaces.SavingAccountService;
import com.Controller.Services.Interfaces.UserService;
import com.Model.Entities.User;

@RestController
public class RESTController {

	@Autowired
	UserService userService;
	@Autowired
	CurrentAccountService currentAccountService;
	@Autowired
	SavingAccountService savingAccountService;

	@RequestMapping(value = "/getClient/{client}", method = RequestMethod.GET, produces = "application/JSON")
	public User getClient(@PathVariable("client") String client){

		User user = userService.findUser(client);
		return user;
		
	}

	@RequestMapping(value = "/debit/{sender}/{receiver}/{amount}", method = RequestMethod.POST , produces = "application/XML")
	public boolean operationMaker(@PathVariable("sender") String sender, @PathVariable("receiver") String receiver, @PathVariable("amount") int amount){
		
		User senderUser = userService.findUser(sender);
		User receiverUser = userService.findUser(receiver);
		
		return currentAccountService.virement(senderUser.getCurrentAccount().getId(), receiverUser.getCurrentAccount().getId(), amount);
	}

}
