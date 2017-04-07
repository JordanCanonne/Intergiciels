package com.Model.Repository.Interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Model.Entities.SavingAccount;

@Repository
public interface SavingAccountRepository extends CrudRepository<SavingAccount, Integer>, SavingAccountRepositoryCustom {

}
