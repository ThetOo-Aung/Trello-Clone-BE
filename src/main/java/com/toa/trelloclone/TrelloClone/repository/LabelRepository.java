package com.toa.trelloclone.TrelloClone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.toa.trelloclone.TrelloClone.model.Label;


public interface LabelRepository extends JpaRepository<Label, Long> {

}