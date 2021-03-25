package com.example.demo.controller;

import com.example.demo.entity.Company;
import com.example.demo.payload.ApiResponse;
import com.example.demo.payload.CompanyDto;
import com.example.demo.service.CompanyService;
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
@RequestMapping("/api/company")
public class CompanyController {
    final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }



    @GetMapping
    public ResponseEntity<List<Company>> getCostumer(){
return ResponseEntity.ok(companyService.getCompany());
    }


    @GetMapping("{id}")
    public ResponseEntity<Company> getCompanyByID(@PathVariable Integer id){
return ResponseEntity.ok(companyService.getCompanyById(id));
    }
@PostMapping
    public ResponseEntity<ApiResponse> addCompany(@Valid @RequestBody CompanyDto companyDto){
    return ResponseEntity.ok(companyService.addCompany(companyDto));
}

@PutMapping("{id}")
public  ResponseEntity<ApiResponse> editCostumer(@Valid @PathVariable Integer id , @RequestBody CompanyDto companyDto){
return ResponseEntity.ok(companyService.editCompany(id,companyDto));
}
@DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCustomer(@PathVariable Integer id){
return ResponseEntity.ok(companyService.deleteCompany(id));
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
