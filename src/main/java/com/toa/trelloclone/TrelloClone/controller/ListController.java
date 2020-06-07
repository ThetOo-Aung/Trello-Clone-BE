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

import com.toa.trelloclone.TrelloClone.model.List;
import com.toa.trelloclone.TrelloClone.repository.ListRepository;


@RestController
@CrossOrigin
@RequestMapping("/list")
public class ListController {
	@Autowired
	ListRepository listRepository;
	
	
	@GetMapping
	public java.util.List<List> getAll() {
		return listRepository.findAll();
	}
	
	@GetMapping("{id}")
	public List getbyId(@PathVariable Long id){
		return listRepository.getOne(id);
	}
	
	@PostMapping
	public List save(@RequestBody List list) {
		return listRepository.saveAndFlush(list);
	}
	@RequestMapping(method = RequestMethod.PUT)
	public List update(@RequestBody List list) {
		List oldList = listRepository.getOne(list.getId());
		BeanUtils.copyProperties(list, oldList, "id","status","position");
		return listRepository.saveAndFlush(oldList);
	}
}
