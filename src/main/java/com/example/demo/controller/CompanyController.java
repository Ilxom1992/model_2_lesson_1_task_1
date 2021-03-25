package com.example.demo.controller;

import com.example.demo.entity.Company;
import com.example.demo.payload.ApiResponse;
import com.example.demo.payload.CompanyDto;
import com.example.demo.service.CompanyService;
import org.springframework.http.HttpStatus;
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
    public List<Company> getCostumer(){
return companyService.getCompany();
    }


    @GetMapping("{id}")
    public Company getCompanyByID(@PathVariable Integer id){
return companyService.getCompanyById(id);
    }
@PostMapping
    public ApiResponse addCompany(@Valid @RequestBody CompanyDto companyDto){
    return companyService.addCompany(companyDto);
}

@PutMapping("{id}")
public  ApiResponse editCostumer(@Valid @PathVariable Integer id , @RequestBody CompanyDto companyDto){
return companyService.editCompany(id,companyDto);
}
@DeleteMapping("/{id}")
    public ApiResponse deleteCustomer(@PathVariable Integer id){
return companyService.deleteCompany(id);
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
