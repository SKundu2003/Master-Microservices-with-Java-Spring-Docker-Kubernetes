package com.accounts.Repositories;


import com.accounts.Model.Accounts;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepository extends CrudRepository<Accounts, Integer> {
    public Accounts findAccountsByCustomerId(int id);
}
