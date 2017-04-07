package com.Model.Repository.Interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Model.Entities.CurrentAccount;

@Repository
public interface CurrentAccountRepository extends CrudRepository<CurrentAccount, Integer>, CurrentAccountRepositoryCustom {
}
