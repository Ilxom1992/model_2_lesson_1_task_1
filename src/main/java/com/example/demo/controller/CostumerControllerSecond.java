package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/")
public class CostumerControllerSecond {
//    final CostumerService costumerService;
//
//    public CostumerControllerSecond(CostumerService costumerService) {
//        this.costumerService = costumerService;
//    }
//
//
//    /**
//     * Bu yerda barcha costumerlar ro'yhatini qaytaradi
//     * @return Costumers
//     */
//    @GetMapping
//    public ResponseEntity<List<Costumer>> getCostumer(){
//        return ResponseEntity.ok(costumerService.getCostumer());
//    }
//
//    /**
//     *
//     * @param id id orqali costumr ni qaytaradi
//     * @return costumer
//     */
//    @GetMapping("{id}")
//    public Costumer getCostumerByID(@PathVariable Integer id){
//        return costumerService.getCostumerById(id);
//    }
//    @PostMapping
//    public ApiResponse addCostumer(@Valid @RequestBody CostumerDto costumerDto){
//        ApiResponse apiResponse = costumerService.addCustomer(costumerDto);
//        return apiResponse;
//    }
//
//    @PutMapping("{id}")
//    public  ApiResponse editCostumer(@Valid @PathVariable Integer id , @RequestBody CostumerDto costumerDto){
//        return costumerService.editCostumer(id,costumerDto);
//    }
//    @DeleteMapping("/{id}")
//    public ApiResponse deleteCustomer(@PathVariable Integer id){
//        return costumerService.deleteCustomer(id);
//    }
//
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Map<String, String> handleValidationExceptions(
//            MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
//        return errors;
//    }

}
