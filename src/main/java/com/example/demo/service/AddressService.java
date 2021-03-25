package com.example.demo.service;

import com.example.demo.entity.Address;
import com.example.demo.payload.AddressDto;
import com.example.demo.payload.ApiResponse;
import com.example.demo.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AddressService {

    final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }


    public List<Address> getAddress(){
return addressRepository.findAll();
    }
    public Address getAddressById(Integer id){
        Optional<Address> optionalCostumer = addressRepository.findById(id);
        return optionalCostumer.orElse(null);
    }
    public ApiResponse addAddress(AddressDto addressDto){
        boolean existsByPhoneNumber = addressRepository.existsByHomeNumberAndStreet(addressDto.getHomeNumber(), addressDto.getStreet());
        if (existsByPhoneNumber){
            return new  ApiResponse("Bunday Manzil mavjud",false);
        }
        Address address= new Address();
        address.setStreet(addressDto.getStreet());
        address.setHomeNumber(addressDto.getHomeNumber());
        addressRepository.save(address);
        return new ApiResponse("Mijoz Saqlandi ",true);
    }
    public  ApiResponse editAddress(Integer id,AddressDto addressDto){
        boolean exists = addressRepository.existsByHomeNumberAndStreetAndIdNot(addressDto.getHomeNumber(), addressDto.getStreet(), id);
        if (exists){
            return new ApiResponse("Bunday Address mavjud",false);
        }
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (!optionalAddress.isPresent()){
            return new ApiResponse("Bunday  Address mavjud emas",false);
        }
        Address address= optionalAddress.get();
        address.setStreet(addressDto.getStreet());
        address.setHomeNumber(addressDto.getHomeNumber());
        addressRepository.save(address);
        return new ApiResponse("Address  edited",true);
    }
    public ApiResponse deleteCustomer(Integer id){
      addressRepository.deleteById(id);
        return new ApiResponse("Address deleted",true);
    }


}
