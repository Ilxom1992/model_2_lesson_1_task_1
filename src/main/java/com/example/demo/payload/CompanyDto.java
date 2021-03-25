package com.example.demo.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class CompanyDto {

    @NotNull(message = "corp name Bo'sh bo'lmasligi kerak")
    private String corpName;
    @NotNull(message = "director Name Bo'sh bo'lmasligi kerak")
    private  String directorName;
    @NotNull(message = "Address Id berilmadi")
    private  Integer addressId;
}
//   Company(corpName, directorName, Address)