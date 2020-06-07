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

import com.toa.trelloclone.TrelloClone.model.Checklist;
import com.toa.trelloclone.TrelloClone.repository.ChecklistRepository;

@RestController
@CrossOrigin
@RequestMapping("/checklist")
public class ChecklistController {
	
	@Autowired
	ChecklistRepository checklistRepository;
	
	
	@GetMapping
	public List<Checklist> getAll() {
		return checklistRepository.findAll();	
	}
	@GetMapping("/{cardId}/{id}")
	public Checklist getbyId(@PathVariable Long id) {
		return checklistRepository.getOne(id);
	}
	
	@PostMapping
	public Checklist save(@RequestBody Checklist checklist) {
		return checklistRepository.saveAndFlush(checklist);
	}
	
	
	@RequestMapping(method = RequestMethod.PUT)
	public Checklist update(@RequestBody Checklist checklist) {
		Checklist oldChecklist = checklistRepository.getOne(checklist.getId());
		BeanUtils.copyProperties(checklist, oldChecklist, "id", "cardId", "position", "title");
		return checklistRepository.saveAndFlush(oldChecklist);
	}
	@RequestMapping(value = "{id}",method = RequestMethod.DELETE)
	public boolean delete(@PathVariable Long id) {
		checklistRepository.deleteById(id);
		return true;
	}
	
	@GetMapping("{cardId}")
	public List<Checklist> getbyCardId(@PathVariable Long cardId){
		return checklistRepository.findByCardId(cardId);
	}
	
	@PostMapping("{cardId}")
	public List<Checklist> saveAll(@RequestBody List<Checklist> checklists){
		return checklistRepository.saveAll(checklists);
	}
	
}
