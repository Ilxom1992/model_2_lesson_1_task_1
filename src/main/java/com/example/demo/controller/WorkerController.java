package com.example.demo.controller;

import com.example.demo.entity.Worker;
import com.example.demo.payload.ApiResponse;
import com.example.demo.payload.WorkerDto;
import com.example.demo.service.WorkerService;
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
@RequestMapping("/api/worker")
public class WorkerController {
final WorkerService workerService;

    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }


    @GetMapping
    public ResponseEntity<List<Worker>> getWorker(){
return ResponseEntity.ok(workerService.getWorker());
    }


    @GetMapping("{id}")
    public ResponseEntity<Worker> getWorkerByID(@PathVariable Integer id){
return ResponseEntity.ok(workerService.getWorkerById(id));
    }
@PostMapping
    public ResponseEntity<ApiResponse> addWorker(@Valid @RequestBody WorkerDto workerDto){
    return ResponseEntity.ok(workerService.addWorker(workerDto));
}

@PutMapping("{id}")
public  ResponseEntity<ApiResponse> editWorker(@Valid @PathVariable Integer id , @RequestBody WorkerDto WorkerDto){
return ResponseEntity.ok(workerService.editWorker(id,WorkerDto));
}
@DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCustomer(@PathVariable Integer id){
return ResponseEntity.ok(workerService.deleteWorker(id));
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
