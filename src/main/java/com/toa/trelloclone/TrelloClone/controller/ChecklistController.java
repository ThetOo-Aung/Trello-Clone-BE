package com.toa.trelloclone.TrelloClone.controller;

import java.util.List;

import javax.transaction.Transactional;

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

import com.toa.trelloclone.TrelloClone.model.Card;
import com.toa.trelloclone.TrelloClone.model.Checklist;
import com.toa.trelloclone.TrelloClone.model.MainModel;
import com.toa.trelloclone.TrelloClone.repository.CardRepository;
import com.toa.trelloclone.TrelloClone.repository.ChecklistRepository;

@RestController
@CrossOrigin
@RequestMapping(path = "/checklist")
@Transactional
public class ChecklistController extends MainModel{
	
	@Autowired
	private ChecklistRepository checklistRepository;
	
	@Autowired
	private CardRepository cardRepository;
	
	
	@GetMapping
	public List<Checklist> get() {
		return checklistRepository.findAll();
	}
	
	@GetMapping(value = "{id}")
	public Checklist getById(@PathVariable Long id) {
		return checklistRepository.getOne(id);
	}
	
	@PostMapping
	public Checklist create(@RequestBody Checklist checklist) {
		return checklistRepository.saveAndFlush(checklist);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public Checklist update(@PathVariable Long id, @RequestBody Checklist checklist) {
		Checklist existingCl = checklistRepository.getOne(id);
		BeanUtils.copyProperties(checklist, existingCl, "id", "card_id");
		return checklistRepository.saveAndFlush(existingCl);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		checklistRepository.deleteById(id);
	}
	
//	@RequestMapping(value = "{card_id}/removeAll",method = RequestMethod.DELETE)
//	public void removeChecklsit(@PathVariable Long card_id) {
//		List<Checklist> checklists = checklistRepository.findByCardId(card_id);
//	}
//	
}
