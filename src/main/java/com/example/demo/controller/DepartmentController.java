package com.example.demo.controller;
import com.example.demo.entity.Department;
import com.example.demo.payload.ApiResponse;
import com.example.demo.payload.DepartmentDto;
import com.example.demo.service.DepartmentService;
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
@RequestMapping("/api/department")
public class DepartmentController {
   final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    @GetMapping
    public ResponseEntity<List<Department>> getDepartment(){
return ResponseEntity.ok(departmentService.getDepartment());
    }


    @GetMapping("{id}")
    public Department getDepartmentByID(@PathVariable Integer id){
return departmentService.getDepartmentById(id);
    }
@PostMapping
    public ResponseEntity<ApiResponse> addDepartment(@Valid @RequestBody DepartmentDto departmentDto){
   return ResponseEntity.ok(departmentService.addDepartment(departmentDto));

}

@PutMapping("{id}")
public  ResponseEntity<ApiResponse> editDepartment(@Valid @PathVariable Integer id , @RequestBody DepartmentDto DepartmentDto){
return ResponseEntity.ok(departmentService.editDepartment(id,DepartmentDto));
}
@DeleteMapping("/{id}")
    public ApiResponse deleteCustomer(@PathVariable Integer id){
return departmentService.deleteCustomer(id);
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
