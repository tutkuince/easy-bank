package com.incetutku.accountservice.repository;

import com.incetutku.accountservice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
