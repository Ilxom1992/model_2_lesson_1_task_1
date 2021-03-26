package com.example.demo.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class WorkerDto {

    @NotNull(message = "Name Bo'sh bo'lmasligi kerak")
    private String name;
    @NotNull(message = "phone number Bo'sh bo'lmasligi kerak")
    private  String phoneNumber;

    @NotNull(message = "Department Id Bo'sh bo'lmasligi kerak")
    private  Integer departmentId;

    @NotNull(message = "street name Bo'sh bo'lmasligi kerak")
    private String street;
    @NotNull(message = "home Number Bo'sh bo'lmasligi kerak")
    private  Integer homeNumber;
}
//    Worker(name, phoneNumber, Address, Department)
