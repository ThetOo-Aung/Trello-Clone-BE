package com.toa.trelloclone.TrelloClone.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.toa.trelloclone.TrelloClone.model.Account;
import com.toa.trelloclone.TrelloClone.repository.AccountRepository;

@RestController
@CrossOrigin
@RequestMapping("account")
public class AccountController {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@GetMapping
	public List<Account> getAll() {
		return accountRepository.findAll();
	}
	
	@GetMapping("{username}")
	public Account getByUserName(@PathVariable String username) {
		return accountRepository.getOne(username);
	}
	
	@PostMapping
	public Account create(@RequestBody Account account) {
		return accountRepository.saveAndFlush(account);
	}
	
	@RequestMapping(value = "{username}", method = RequestMethod.PUT)
	public Account update(@PathVariable String username, @RequestBody Account account) {
		Account existingAccount = accountRepository.getOne(username);
		BeanUtils.copyProperties(account, existingAccount, "username", "id");
		return accountRepository.saveAndFlush(existingAccount);
	}
	
	@RequestMapping(value = "{username}", method = RequestMethod.DELETE)
	public void delete(@PathVariable String username) {
		accountRepository.deleteById(username);
	}

}