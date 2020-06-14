package com.toa.trelloclone.TrelloClone.controller;


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

import com.toa.trelloclone.TrelloClone.model.MyList;
import com.toa.trelloclone.TrelloClone.repository.MyListRepository;

@RestController
@CrossOrigin
@RequestMapping("/list")
public class MyListController {
	@Autowired
	MyListRepository listRepository;
	
	
	@GetMapping
	public java.util.List<MyList> getAll() {
		return listRepository.findAll();
	}
	
	@GetMapping("{id}")
	public MyList getbyId(@PathVariable Long id){
		return listRepository.getOne(id);
	}
	
	
	@PostMapping
	public MyList create(@RequestBody MyList list) {
		return listRepository.saveAndFlush(list);
	}
	
	
	@RequestMapping(value = "{id}",method = RequestMethod.PUT)
	public MyList update(@PathVariable Long id,@RequestBody MyList list) {
		MyList existingList = listRepository.getOne(id);
		BeanUtils.copyProperties(list, existingList, "id","date_created");
		return listRepository.saveAndFlush(existingList);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		listRepository.deleteById(id);
	}
}
