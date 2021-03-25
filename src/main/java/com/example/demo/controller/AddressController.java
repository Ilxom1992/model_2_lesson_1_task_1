package com.example.demo.controller;

import com.example.demo.entity.Address;
import com.example.demo.payload.AddressDto;
import com.example.demo.payload.ApiResponse;
import com.example.demo.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/address")
public class AddressController {
    final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }


    /**
     * Bu yerda barcha addreslar ro'yhatini qaytaradi
     * @return Address
     */
    @GetMapping
    public List<Address> getCostumer(){
return addressService.getAddress();
    }

    /**
     *
     * @param id id orqali costumr ni qaytaradi
     * @return costumer
     */
    @GetMapping("{id}")
    public Address getCostumerByID(@PathVariable Integer id){
return addressService.getAddressById(id);
    }
@PostMapping
    public ApiResponse addCostumer(@Valid @RequestBody AddressDto addressDto){
    ApiResponse apiResponse = addressService.addAddress(addressDto);
return apiResponse;
}

@PutMapping("{id}")
public  ApiResponse editCostumer(@Valid @PathVariable Integer id , @RequestBody AddressDto addressDto){
return addressService.editAddress(id,addressDto);
}
@DeleteMapping("/{id}")
    public ApiResponse deleteCustomer(@PathVariable Integer id){
return addressService.deleteCustomer(id);
}

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
