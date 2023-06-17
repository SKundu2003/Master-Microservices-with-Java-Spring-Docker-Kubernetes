package com.loans.Repositories;

import java.util.List;

import com.loans.Model.Loans;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LoansRepository extends CrudRepository<Loans, Long> {


//    JPA is smart enough so run "where/order by" commands in function format
//    By help of Method name a customer is able to chive that
    List<Loans> findByCustomerIdOrderByStartDtDesc(int customerId);

}
