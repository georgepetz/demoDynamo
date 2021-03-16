package com.example.demoDynamo.controller;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.example.demoDynamo.dto.User;
import com.example.demoDynamo.service.UserCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class UserCrudController {

    private final UserCrudService userCrudService;

    @Autowired
    public UserCrudController(UserCrudService userCrudService) {
        this.userCrudService = userCrudService;
    }

    @RequestMapping(value = "/users", produces = {"application/json"}, method = RequestMethod.POST)
    public ResponseEntity createUser(@RequestBody User user) {
        try {
            User response = userCrudService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (AmazonServiceException e) {
            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()), e.getMessage(), e);
        } catch (AmazonClientException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @RequestMapping(value = "/users/{userId}", produces = {"application/json"}, method = RequestMethod.GET)
    public ResponseEntity readUser(@PathVariable String userId) {
        try {
            User response = userCrudService.readUser(userId);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (AmazonServiceException e) {
            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()), e.getMessage(), e);
        } catch (AmazonClientException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @RequestMapping(value = "/users", produces = {"application/json"}, method = RequestMethod.PUT)
    public ResponseEntity updateUser(@RequestBody User user) {
        try {
            User response = userCrudService.updateUser(user);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (AmazonServiceException e) {
            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()), e.getMessage(), e);
        } catch (AmazonClientException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @RequestMapping(value = "/users/{userId}", produces = {"application/json"}, method = RequestMethod.DELETE)
    public ResponseEntity deleteUser(@PathVariable String userId) {
        try {
            userCrudService.deleteUser(userId);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (AmazonServiceException e) {
            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()), e.getMessage(), e);
        } catch (AmazonClientException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }
}