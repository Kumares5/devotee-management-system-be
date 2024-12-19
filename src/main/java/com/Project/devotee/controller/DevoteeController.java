package com.Project.devotee.controller;

import com.Project.devotee.entity.Devotee;
import com.Project.devotee.service.DevoteeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class DevoteeController {

    private final DevoteeService devoteeService;

    public DevoteeController(DevoteeService devoteeService) {
        this.devoteeService = devoteeService;
    }

    @PostMapping("/devotee")
    public Devotee postDevotee(@RequestBody Devotee devotee){
        return devoteeService.postDevotee(devotee);
    }

    @GetMapping("/devotees")
    public List<Devotee> getAllDevotees(){
        return devoteeService.getAllDevotees();
    }

    @DeleteMapping("/devotee/{id}")
    public ResponseEntity deleteDevotee(@PathVariable Long id){
        try{
            devoteeService.deleteDevotee(id);
            return new ResponseEntity("Devotee with id " +id+ " deleted successfully", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/devotee/{id}")
    public ResponseEntity getDevoteeById(@PathVariable Long id){
        Devotee devotee = devoteeService.getDevoteeById(id);
        if(devotee == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(devotee);
    }

    @PatchMapping("/devotee/{id}")
    public ResponseEntity updateDevotee(@PathVariable Long id, @RequestBody Devotee devotee){
        Devotee updatedDevotee = devoteeService.updateDevotee(id,devotee);

        if(updatedDevotee == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.ok(updatedDevotee);
    }

}
