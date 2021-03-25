package com.example.demo.repository;

import com.example.demo.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AddressRepository extends JpaRepository<Address,Integer> {
boolean existsByHomeNumberAndStreet(Integer homeNumber, String street);
boolean existsByHomeNumberAndStreetAndIdNot(Integer homeNumber, String street, Integer id);
}
