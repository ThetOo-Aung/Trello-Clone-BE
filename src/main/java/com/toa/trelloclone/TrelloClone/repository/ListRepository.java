package com.toa.trelloclone.TrelloClone.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.toa.trelloclone.TrelloClone.model.List;

public interface ListRepository extends JpaRepository<List, Long> {
	
//	public java.util.List<List> findByPositionGreaterThanEqual(Integer position);
//	public java.util.List<List> findByTitleContaining(String searchTerm);
}
