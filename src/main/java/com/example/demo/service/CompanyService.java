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
    public Company getCompanyById(Integer id){
        Optional<Company> optionalCostumer = companyRepository.findById(id);
        return optionalCostumer.orElse(null);
    }
    public ApiResponse addCompany(CompanyDto companyDto){
        boolean exists = companyRepository.existsByCorpName(companyDto.getCorpName());
        if (exists){
            return new ApiResponse("Bumday companiya nomi mavjud ",false);
        }

        Company company=new Company();
        company.setCorpName(companyDto.getCorpName());
        company.setDirectorName(company.getDirectorName());
        Optional<Address> optionalAddress = addressRepository.findById(companyDto.getAddressId());
        if (!optionalAddress.isPresent()){
            return new ApiResponse("Bumday Addrss mavjud emas",false);
        }
        company.setAddress(optionalAddress.get());
        return new ApiResponse("Companiya saqlandi Saqlandi ",true);
    }
    public  ApiResponse editCompany(Integer id,CompanyDto companyDto) {
        boolean exists = companyRepository.existsByCorpNameAndAddress_IdAndIdNot(companyDto.getCorpName(), companyDto.getAddressId(), id);
        if (exists) {
            return new ApiResponse("Bunday companiya mavjud", false);
        }
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (!optionalCompany.isPresent()) {
            return new ApiResponse("Bunday companiya mavjud  emas", false);
        }
        Company company = optionalCompany.get();
        company.setCorpName(companyDto.getCorpName());
        company.setDirectorName(company.getDirectorName());
        Optional<Address> optionalAddress = addressRepository.findById(companyDto.getAddressId());
        if (!optionalAddress.isPresent()) {
            return new ApiResponse("Bumday Addrss mavjud emas", false);
        }
        company.setAddress(optionalAddress.get());
        return new ApiResponse("Companiya saqlandi Saqlandi ", true);
    }
    public ApiResponse deleteCompany(Integer id){
      companyRepository.deleteById(id);
        return new ApiResponse("This  company deleted",true);
    }


}
