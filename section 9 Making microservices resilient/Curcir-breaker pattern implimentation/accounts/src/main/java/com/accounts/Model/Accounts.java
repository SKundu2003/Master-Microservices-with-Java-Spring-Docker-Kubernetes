package com.accounts.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Data
@ToString
public class Accounts {


    @Id
    private long accountNumber;

    @Column(name = "customer_id")
    private int customerId;
    private String accountType;
    private String branchAddress;
    private LocalDate createDt;

}
