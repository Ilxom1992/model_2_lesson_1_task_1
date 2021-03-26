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
    @NotNull(message = "street name Bo'sh bo'lmasligi kerak")
    private String street;
    @NotNull(message = "home Number Bo'sh bo'lmasligi kerak")
    private  Integer homeNumber;
}
//   Company(corpName, directorName, Address)