package com.toa.trelloclone.TrelloClone.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.toa.trelloclone.TrelloClone.model.Account;
import com.toa.trelloclone.TrelloClone.model.Card;
import com.toa.trelloclone.TrelloClone.model.Label;
import com.toa.trelloclone.TrelloClone.model.MyList;
import com.toa.trelloclone.TrelloClone.repository.AccountRepository;
import com.toa.trelloclone.TrelloClone.repository.CardRepository;
import com.toa.trelloclone.TrelloClone.repository.ChecklistRepository;
import com.toa.trelloclone.TrelloClone.repository.LabelRepository;
import com.toa.trelloclone.TrelloClone.repository.MyListRepository;

@RestController
@CrossOrigin
@RequestMapping(path = "/card")
public class CardController {

	@Autowired
	private CardRepository cardRepository;
	
	@Autowired
	private MyListRepository listRepository;
	
	@Autowired
	private LabelRepository labelRepository;
	
	@Autowired
	private AccountRepository accountRepository;

    @GetMapping
    public List<Card> getAll(){
    	return cardRepository.findAll();
    }
    @GetMapping(value = "{id}")
    public Card getById(@PathVariable Long id){
    	return cardRepository.getOne(id);
    }
    
    @PostMapping("add/{list_id}")
    public Card createCard(@PathVariable Long list_id, @RequestBody Card card) {
    	MyList list = listRepository.getOne(list_id);
    	card.setList(list);
    	return cardRepository.saveAndFlush(card);
    }
    
    @RequestMapping(value = "update/{list_id}/{id}", method = RequestMethod.PUT)
    public Card update(@PathVariable Long list_id,@PathVariable Long id, @RequestBody Card card) {
    	MyList list = listRepository.getOne(list_id);
    	card.setList(list);
    	Card existingCard = cardRepository.getOne(id);
    	BeanUtils.copyProperties(card, existingCard, "id");
    	return cardRepository.saveAndFlush(existingCard);
    }
    
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
    	cardRepository.deleteById(id);
    }
	@RequestMapping(value = "{id}/add/label/{label_id}", method = RequestMethod.PUT)
	public Card addLabel(@PathVariable Long id,@PathVariable Long label_id) {
		Card card = cardRepository.getOne(id);
		Label label = labelRepository.getOne(label_id);
		card.getLabels().add(label);
		return cardRepository.saveAndFlush(card);
	}
	@RequestMapping(value = "{id}/remove/label/{label_id}", method = RequestMethod.DELETE)
	public Card removeLabel(@PathVariable Long id, @PathVariable Long label_id) {
		Card card = cardRepository.getOne(id);
		Label label = labelRepository.getOne(label_id);
		card.getLabels().remove(label);
		return cardRepository.saveAndFlush(card);
	}
	@RequestMapping(value = "{id}/add/member/{username}", method = RequestMethod.PUT)
	public Card addMember(@PathVariable Long id, @PathVariable String username) {
		Card card = cardRepository.getOne(id);
		Account member = accountRepository.getOne(username);
		card.getMembers().add(member);
		return cardRepository.saveAndFlush(card);
	}
	@RequestMapping(value = "{id}/remove/member/{username}", method = RequestMethod.DELETE)
	public Card removeMember(@PathVariable Long id, @PathVariable String username) {
		Card card = cardRepository.getOne(id);
		Account member = accountRepository.getOne(username);
		card.getMembers().remove(member);
		return cardRepository.saveAndFlush(card);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	 
//	 @RequestMapping(value = "{card_id}/add/label/{label_id}", method = RequestMethod.POST)
//	    public Card addLabelToCard(@PathVariable Long card_id, @PathVariable Long label_id) {
//	        Card card = cardRepository.getOne(card_id);
//	        Set<Label> labels = card.getLabels();
//	        if(labels == null) {
//	        	labels = new HashSet<>();
//	        }
//	        labels.add(labelRepository.getOne(label_id));
//	        card.setLabels(labels);
//	        return cardRepository.saveAndFlush(card);
//
//	    }
//	 
//	 
//	 @RequestMapping(value = "{card_id}/remove/label/{label_id}", method = RequestMethod.DELETE)
//	 public Card removeLabelFromCard(@PathVariable Long card_id,@PathVariable Long label_id) {
//		 Card card = cardRepository.getOne(card_id);
//		 Set<Label> labels = card.getLabels();
//			if (labels == null) {
//				labels = new HashSet<>();
//			}
//			labels.removeIf(lbl -> lbl.getId().equals(label_id));
//			card.setLabels(labels);
//			return cardRepository.saveAndFlush(card);
//		 
//	 }

	
}
