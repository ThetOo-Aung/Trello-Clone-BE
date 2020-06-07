package com.toa.trelloclone.TrelloClone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.toa.trelloclone.TrelloClone.model.Card;

public interface CardRepository extends JpaRepository<Card, Long>{

}
