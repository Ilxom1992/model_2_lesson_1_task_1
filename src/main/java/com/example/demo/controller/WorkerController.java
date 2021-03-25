package com.example.demo.controller;

import com.example.demo.entity.Worker;
import com.example.demo.payload.ApiResponse;
import com.example.demo.payload.WorkerDto;
import com.example.demo.service.WorkerService;
import org.springframework.http.HttpStatus;
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

    /**
     * Bu yerda barcha Workerlar ro'yhatini qaytaradi
     * @return Workers
     */
    @GetMapping
    public List<Worker> getWorker(){
return workerService.getworker();
    }

    /**
     *
     * @param id id orqali costumr ni qaytaradi
     * @return Worker
     */
    @GetMapping("{id}")
    public Worker getWorkerByID(@PathVariable Integer id){
return workerService.getWorkerById(id);
    }
@PostMapping
    public ApiResponse addWorker(@Valid @RequestBody WorkerDto workerDto){
    ApiResponse apiResponse = workerService.addWorker(workerDto);
return apiResponse;
}

@PutMapping("{id}")
public  ApiResponse editWorker(@Valid @PathVariable Integer id , @RequestBody WorkerDto WorkerDto){
return workerService.editWorker(id,WorkerDto);
}
@DeleteMapping("/{id}")
    public ApiResponse deleteCustomer(@PathVariable Integer id){
return workerService.deleteWorker(id);
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
