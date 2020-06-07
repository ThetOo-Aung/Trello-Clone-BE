package com.toa.trelloclone.TrelloClone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.toa.trelloclone.TrelloClone.model.Account;

@Service
public interface AccountRepository extends JpaRepository<Account, String>{

}
