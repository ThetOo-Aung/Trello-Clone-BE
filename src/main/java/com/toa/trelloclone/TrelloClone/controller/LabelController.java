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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.toa.trelloclone.TrelloClone.model.Label;
import com.toa.trelloclone.TrelloClone.repository.LabelRepository;

@RestController
@CrossOrigin
@RequestMapping("label")
public class LabelController {
	@Autowired
	LabelRepository labelRepository;
	
	
	@GetMapping
	public List<Label> getAll() {
		return labelRepository.findAll();
	}
	
	@GetMapping("{id}")
	public Label getById(@PathVariable Long id) {
		return labelRepository.getOne(id);
	}
	
	@PostMapping
	public Label save(@RequestBody Label label) {
		return labelRepository.saveAndFlush(label);
	}
	@RequestMapping(method = RequestMethod.PUT)
	public Label update(@RequestBody Label label) {
		Label oldLabel = labelRepository.getOne(label.getId());
		BeanUtils.copyProperties(label, oldLabel, "id");
		return labelRepository.saveAndFlush(oldLabel);
	}
	
	

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public boolean delete(@PathVariable Long id) {
		try {
			labelRepository.deleteById(id);
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
