package com.example.demo.service;

import com.example.demo.entity.Address;
import com.example.demo.entity.Company;
import com.example.demo.payload.ApiResponse;
import com.example.demo.payload.CompanyDto;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

final CompanyRepository companyRepository;
final AddressRepository addressRepository;

    public CompanyService(CompanyRepository companyRepository, AddressRepository addressRepository) {
        this.companyRepository = companyRepository;
        this.addressRepository = addressRepository;
    }

    public List<Company> getCompany(){
return companyRepository.findAll();
    }
    //READ COMPANY BY ID
    public Company getCompanyById(Integer id){
        Optional<Company> optionalCostumer = companyRepository.findById(id);
        return optionalCostumer.orElse(null);
    }
    //CREATE COMPANY
    public ApiResponse addCompany(CompanyDto companyDto){
        boolean exists = companyRepository.existsByCorpName(companyDto.getCorpName());
        if (exists){
            return new ApiResponse("Bumday companiya nomi mavjud ",false);
        }
        //NEW COMPANY
        Company company=new Company();
        company.setCorpName(companyDto.getCorpName());
        company.setDirectorName(companyDto.getDirectorName());
         //NEW ADDRESS
        Address address=new Address();
        address.setStreet(companyDto.getStreet());
        address.setHomeNumber(companyDto.getHomeNumber());
        //ADDRESS SAVED DATABASE
        Address saveAddress = addressRepository.save(address);

        company.setAddress(saveAddress);

        companyRepository.save(company);
        return new ApiResponse("Companiya saqlandi Saqlandi ",true);
    }
    //UPDATE COMPANY
    public  ApiResponse editCompany(Integer id,CompanyDto companyDto) {

        Optional<Company> optionalCompany = companyRepository.findById(id);


        if (!optionalCompany.isPresent()) {
            return new ApiResponse("Bunday companiya mavjud  emas", false);
        }

        Company company = optionalCompany.get();
        company.setCorpName(companyDto.getCorpName());
        company.setDirectorName(companyDto.getDirectorName());
        //EDIT NEW ADDRESS
        Address address= addressRepository.findById(company.getAddress().getId()).get();
        address.setStreet(companyDto.getStreet());
        address.setHomeNumber(companyDto.getHomeNumber());
        //ADDRESS SAVED DATABASE
        Address saveAddress = addressRepository.save(address);

        company.setAddress(saveAddress);
        companyRepository.save(company);

        return new ApiResponse("Companiya saqlandi Saqlandi ", true);
    }
    //DELETE COMPANY
    public ApiResponse deleteCompany(Integer id){
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (!optionalCompany.isPresent()){
            return new ApiResponse("Bunday companiya mavjud  emas", false);
        }
        //COMPANY DELETED
        companyRepository.deleteById(id);

        return new ApiResponse("This  company deleted",true);
    }


}
