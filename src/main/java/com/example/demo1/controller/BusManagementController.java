package com.example.demo1.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo1.controller.exceptionHandling.ResourceNotFoundException;
import com.example.demo1.controller.exceptionHandling.model.BusManagement;
import com.example.demo1.controller.exceptionHandling.model.repository.BusManagementRepository;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/BusManagement.com")

public class BusManagementController {
	
	@Autowired
	private BusManagementRepository busManagementRepository;

	// get all BusManagements
	@GetMapping("/busManagements")
	public List<BusManagement> getAllBusManagement(){
		return busManagementRepository.findAll();
	}		

	// create BusManagement rest api
	@PostMapping("/busManagements")
	public BusManagement createBusManagement(@RequestBody BusManagement busManagement) {
		return busManagementRepository.save(busManagement);
	}

	// get busManagement by id rest api
	@GetMapping("/busManagements/{id}")
	public ResponseEntity<BusManagement> getBusManagementById(@PathVariable Integer id) {
		BusManagement busManagement = busManagementRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("BusManagement not exist with id :" + id));
		return ResponseEntity.ok(busManagement);
	}

	// update BusManagement rest api

	@PutMapping("/busManagements/{id}")
	public ResponseEntity<BusManagement> updateEmployee(@PathVariable Integer id, @RequestBody BusManagement busManagementDetails){
		BusManagement busManagement = busManagementRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("BusManagement not exist with id :" + id));

		busManagement.setDriverName(busManagementDetails.getDriverName());
		busManagement.setBusNumber(busManagementDetails.getBusNumber());
		busManagement.setBusType(busManagementDetails.getBusType());

		BusManagement updatedBusManagement = busManagementRepository.save(busManagement);
		return ResponseEntity.ok(updatedBusManagement);
	}

	// delete busManagement rest api
	@DeleteMapping("busMangements/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteBusManagement(@PathVariable Integer id){
		BusManagement busManagement = busManagementRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("BusManagement not exist with id :" + id));

		busManagementRepository.delete(busManagement);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}


}
 

