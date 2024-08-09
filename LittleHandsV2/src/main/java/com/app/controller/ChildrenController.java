package com.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ChildrenDto;
import com.app.service.ChildrenService;

@RestController
@RequestMapping("/childrens")
public class ChildrenController {

	@Autowired
	private ChildrenService childrenService;
	
	@PostMapping("/addchildren")
	public ResponseEntity<?> addNewChildren(@Valid @RequestBody ChildrenDto childrenDto){
		return ResponseEntity.status(HttpStatus.OK)
				.body(childrenService.addNewchildren(childrenDto));
	}
	
	@GetMapping("/getallchildren")
	public ResponseEntity<?> getAllChildren(){
		return ResponseEntity.status(HttpStatus.OK)
				.body(childrenService.getAllChildren());
	}
	
	@PutMapping("/updatechildren/{id}")
	public ResponseEntity<?> updateChildren(@PathVariable Long id 
			,@Valid @RequestBody ChildrenDto childrenDto){
		return ResponseEntity.status(HttpStatus.OK)
				.body(childrenService.updateChildren(id, childrenDto));
	}
	
	@DeleteMapping("/deletechildren/{id}")
	public ResponseEntity<?> deleteChildren(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.OK)
				.body(childrenService.deleteChildren(id));
	}
	
}
