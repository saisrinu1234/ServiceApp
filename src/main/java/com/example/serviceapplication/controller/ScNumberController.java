package com.example.serviceapplication.controller;

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

import com.example.serviceapplication.model.ScNumber;
import com.example.serviceapplication.repository.ScNumberRepository;

@RestController
@RequestMapping("/api")
public class ScNumberController {
    @Autowired
    public ScNumberRepository scNumberRepository;

    @PostMapping("/insert")
    public ResponseEntity<String> postuser(@RequestBody ScNumber user) {
        if (scNumberRepository.existsByscNumber(user.scNumber)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User alreay exists");
        } else {
            scNumberRepository.save(user);
            return ResponseEntity.ok("User uploaded successsfully");
        }
    }

    @GetMapping("/getuser/{scnumber}")
    public ResponseEntity<?> getuser(@PathVariable String scnumber) {
        if (scNumberRepository.existsByscNumber(scnumber)) {
            ScNumber scuser = scNumberRepository.findByscNumber(scnumber);
            return ResponseEntity.ok(scuser);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User Not found");
        }
    }

    @PutMapping("/update")

    public ResponseEntity<?> updateuser(@RequestBody ScNumber updateuser) {
        if (scNumberRepository.existsByscNumber(updateuser.scNumber)) {
            ScNumber scuser = scNumberRepository.findByscNumber(updateuser.scNumber);
            scuser.setScNumber(updateuser.scNumber);
            scuser.setAddress(updateuser.address);
            scuser.setMobileNumber(updateuser.mobileNumber);
            scuser.setUserName(updateuser.userName);
            scNumberRepository.save(scuser);
            return ResponseEntity.ok("Update Successfully");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User Not found");
        }
    }

    @DeleteMapping("/deleteuser/{scnumber}")
    public ResponseEntity<?> deleteuser(@PathVariable String scnumber) {
        if (scNumberRepository.existsByscNumber(scnumber)) {
            scNumberRepository.deleteByscNumber(scnumber);
            return ResponseEntity.ok("Delete successfully");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User Not found");
        }
    }

}
