package com.Pill.Popper.controller.dto;

import java.util.List;

import com.Pill.Popper.dao.entity.Medicine;
import com.Pill.Popper.dao.service.MedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("api/v1/")
public class MedicineController {
    @Autowired
    private MedService medservice;
    public MedicineController(MedService medservice) {
        super();
        this.medservice = medservice;
    }

    @GetMapping("medicines")
    public List<Medicine> getAllMedicines(){
        return this.medservice.getAllMedicines();
    }
    @PostMapping("medicines")
    public Medicine createMedicine(@RequestBody Medicine medicines) {
        return this.medservice.saveMed(medicines);

    }
    @GetMapping("medicines/{id}")
    public ResponseEntity<Medicine> getMedicineById(@PathVariable("id") long id){
        return new ResponseEntity<Medicine>(medservice.getMedById(id), HttpStatus.OK);

    }

    @PutMapping("medicines/{id}")
    public ResponseEntity<Medicine> updateMedicine(@PathVariable("id") long id, @RequestBody Medicine medicine){

        return new ResponseEntity<Medicine>(medservice.updateMed(medicine, id), HttpStatus.OK);
    }

    @DeleteMapping("medicines/{id}")
    public ResponseEntity<String> deleteMedicine(@PathVariable("id") long id){
        medservice.deleteMed(id);

        return new ResponseEntity<String>("Medicine details deleted successfully!", HttpStatus.OK);
    }

    @GetMapping("Alarm")
    @Scheduled(fixedRate=10000)
    public String getAllMedicinesAlarm(){
        return this.medservice.getAllMedicinesAlarm();
    }
}