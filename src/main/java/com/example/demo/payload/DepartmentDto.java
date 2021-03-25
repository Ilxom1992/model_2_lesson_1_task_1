package com.example.demo.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class DepartmentDto {

    @NotNull(message = "FullName Bo'sh bo'lmasligi kerak")
    private String name;
    @NotNull(message = "company Id  Bo'sh bo'lmasligi kerak")
    private Integer companyId;
}

