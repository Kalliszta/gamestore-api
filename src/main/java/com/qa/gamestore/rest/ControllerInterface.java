package com.qa.gamestore.rest;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

public interface ControllerInterface<T> {
	
	ResponseEntity<T> create(T info, HttpServletResponse response);
	
	ResponseEntity<List<T>> get();
	
	ResponseEntity<T> get(Long id);
	
	ResponseEntity<T> update(Long id, T newInfo);
	
	ResponseEntity<Boolean> delete(Long id);
}
