package com.example.demo.service;

import com.example.demo.entity.Company;
import com.example.demo.entity.Department;
import com.example.demo.payload.ApiResponse;
import com.example.demo.payload.DepartmentDto;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

   final DepartmentRepository departmentRepository;
   final CompanyRepository companyRepository;

    public DepartmentService(DepartmentRepository departmentRepository, CompanyRepository companyRepository) {
        this.departmentRepository = departmentRepository;
        this.companyRepository = companyRepository;
    }

    public List<Department> getDepartment(){
return departmentRepository.findAll();
    }
    public Department getDepartmentById(Integer id){
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        return optionalDepartment.orElse(null);
    }
    public ApiResponse addDepartment(DepartmentDto departmentDto){
        boolean exists = departmentRepository.existsByName(departmentDto.getName());
        if (exists){
            return new  ApiResponse("Bunday department mavjud",false);
        }
        Department department= new Department();
       department.setName(department.getName());
        Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompanyId());
        if (!optionalCompany.isPresent()){
            return new  ApiResponse("Bunday Compnya mavjud emas",false);
        }
        department.setCompany(optionalCompany.get());
        departmentRepository.save(department);
        return new ApiResponse("Department Saqlandi ",true);
    }

    public  ApiResponse editDepartment(Integer id,DepartmentDto departmentDto){
        boolean exists = departmentRepository.existsByNameAndIdNot(departmentDto.getName(), id);
        if (exists){
            return new ApiResponse("Bunday Nomli Department mavjud",false);
        }
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (!optionalDepartment.isPresent()){
            return new ApiResponse("Bunday  Department mavjud emas",false);
        }
        Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompanyId());
        if (!optionalCompany.isPresent()){
            return new ApiResponse("Bunday  Companiya mavjud emas",false);
        }
        Department department= optionalDepartment.get();

        department.setName(department.getName());
        Optional<Company> optionalCompany2 = companyRepository.findById(departmentDto.getCompanyId());
        if (!optionalCompany2.isPresent()){
            return new  ApiResponse("Bunday Compnya mavjud emas",false);
        }
        department.setCompany(optionalCompany.get());
        departmentRepository.save(department);

        return new ApiResponse("Bunday  mijoz edited",true);
    }
    public ApiResponse deleteCustomer(Integer id){
      departmentRepository.deleteById(id);
        return new ApiResponse("Bunday  mijoz deleted",true);
    }


}
