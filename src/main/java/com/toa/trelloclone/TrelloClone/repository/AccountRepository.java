package com.toa.trelloclone.TrelloClone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.toa.trelloclone.TrelloClone.model.Account;


public interface AccountRepository extends JpaRepository<Account, String> {

}