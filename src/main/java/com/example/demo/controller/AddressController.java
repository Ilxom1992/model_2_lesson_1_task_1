package com.example.demo.controller;

import com.example.demo.entity.Address;
import com.example.demo.payload.AddressDto;
import com.example.demo.payload.ApiResponse;
import com.example.demo.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping
    public ResponseEntity<List<Address>> getCostumer(){
       return ResponseEntity.ok(addressService.getAddress());
    }

    @GetMapping("{id}")
    public ResponseEntity<Address> getCostumerByID(@PathVariable Integer id){
return ResponseEntity.ok(addressService.getAddressById(id));
    }
@PostMapping
    public ResponseEntity<ApiResponse> addCostumer(@Valid @RequestBody AddressDto addressDto){
 return   ResponseEntity.ok(addressService.addAddress(addressDto));
}
@PutMapping("{id}")
public  ResponseEntity<ApiResponse> editCostumer(@Valid @PathVariable Integer id , @RequestBody AddressDto addressDto){
return ResponseEntity.ok(addressService.editAddress(id,addressDto));
}
@DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCustomer(@PathVariable Integer id){
return ResponseEntity.ok(addressService.deleteCustomer(id));
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
