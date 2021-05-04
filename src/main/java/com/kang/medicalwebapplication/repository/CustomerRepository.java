package com.kang.medicalwebapplication.repository;

import com.kang.medicalwebapplication.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, String> {

}
