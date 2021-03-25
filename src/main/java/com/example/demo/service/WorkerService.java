package com.example.demo.service;

import com.example.demo.entity.Address;
import com.example.demo.entity.Department;
import com.example.demo.entity.Worker;
import com.example.demo.payload.ApiResponse;
import com.example.demo.payload.WorkerDto;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.WorkerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class WorkerService {

final WorkerRepository workerRepository;
final AddressRepository addressRepository;
final DepartmentRepository departmentRepository;
    public WorkerService(WorkerRepository workerRepository, AddressRepository addressRepository, DepartmentRepository departmentRepository) {
        this.workerRepository = workerRepository;
        this.addressRepository = addressRepository;
        this.departmentRepository = departmentRepository;
    }
    public List<Worker> getworker(){
return workerRepository.findAll();
    }
    public Worker getWorkerById(Integer id){
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        return optionalWorker.orElse(null);
    }
    public ApiResponse addWorker(WorkerDto workerDto){
        boolean existsByPhoneNumber = workerRepository.existsByPhoneNumber(workerDto.getPhoneNumber());
        if (existsByPhoneNumber){
            return new  ApiResponse("Bunday raqam mavjud",false);
        }
        Worker worker= new Worker();
       worker.setName(worker.getName());
       worker.setPhoneNumber(worker.getPhoneNumber());
        Optional<Address> optionalAddress = addressRepository.findById(workerDto.getAddressId());
        Optional<Department> optionalDepartment = departmentRepository.findById(workerDto.getDepartmentId());
        if (!optionalAddress.isPresent()){
            return new  ApiResponse("Bunday Address mavjud emas",false);
        }
        if (!optionalDepartment.isPresent()){
            return new  ApiResponse("Bunday dapartment mavjud emas",false);
        }
        worker.setDepartment(optionalDepartment.get());
        worker.setAddress(optionalAddress.get());
        workerRepository.save(worker);
        return new ApiResponse("Worker Saved ",true);
    }
    public  ApiResponse editWorker(Integer id,WorkerDto workerDto){
        boolean existsByPhoneNumber = workerRepository.existsByPhoneNumberAndIdNot(workerDto.getPhoneNumber(),id);
        if (existsByPhoneNumber){
            return new ApiResponse("Bunday Telfon raqamli mijoz mavjud",false);
        }
        Optional<Worker> optionalworker = workerRepository.findById(id);
        if (!optionalworker.isPresent()){
            return new ApiResponse("Bunday  mijoz mavjud emas",false);
        }
        Worker worker= optionalworker.get();
        worker.setName(worker.getName());
        worker.setPhoneNumber(worker.getPhoneNumber());
        Optional<Address> optionalAddress = addressRepository.findById(workerDto.getAddressId());
        Optional<Department> optionalDepartment = departmentRepository.findById(workerDto.getDepartmentId());
        if (!optionalAddress.isPresent()){
            return new  ApiResponse("Bunday Address mavjud emas",false);
        }
        if (!optionalDepartment.isPresent()){
            return new  ApiResponse("Bunday dapartment mavjud emas",false);
        }
        worker.setDepartment(optionalDepartment.get());
        worker.setAddress(optionalAddress.get());
        workerRepository.save(worker);
        return new ApiResponse("Worker Edited ",true);
    }
    public ApiResponse deleteWorker(Integer id){
      workerRepository.deleteById(id);
        return new ApiResponse("This  worker deleted",true);
    }
}