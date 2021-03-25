package com.example.demo.repository;

import com.example.demo.entity.Address;
import com.example.demo.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CompanyRepository extends JpaRepository<Company,Integer> {

boolean existsByCorpName(String corpName);
boolean existsByCorpNameAndAddress_IdAndIdNot(String corpName, Integer address_id, Integer id);

}
