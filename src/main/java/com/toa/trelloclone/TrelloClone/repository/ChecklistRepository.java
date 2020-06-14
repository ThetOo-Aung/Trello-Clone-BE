package com.toa.trelloclone.TrelloClone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.toa.trelloclone.TrelloClone.model.Checklist;

public interface ChecklistRepository extends JpaRepository<Checklist, Long> {
	List<Checklist> findByCardId(Long card_id);
}
