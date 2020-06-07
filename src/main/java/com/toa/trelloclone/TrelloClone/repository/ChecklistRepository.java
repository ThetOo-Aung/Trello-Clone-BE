package com.toa.trelloclone.TrelloClone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.toa.trelloclone.TrelloClone.model.Checklist;

public interface ChecklistRepository extends JpaRepository<Checklist, Long>{

	public List<Checklist> findByCardId(Long cardId);
}
