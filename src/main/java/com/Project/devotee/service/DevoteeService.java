package com.Project.devotee.service;

import com.Project.devotee.entity.Devotee;
import com.Project.devotee.repository.DevoteeRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.hibernate.query.sqm.EntityTypeException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class DevoteeService {

    private final DevoteeRepository devoteeRepository;

    public DevoteeService(DevoteeRepository devoteeRepository) {
        this.devoteeRepository = devoteeRepository;
    }
    @Transactional
    public Devotee postDevotee(Devotee devotee){
        return devoteeRepository.save(devotee);
    }

    public List<Devotee> getAllDevotees(){
        return devoteeRepository.findAll();
    }

    public void deleteDevotee(Long id){
        if(!devoteeRepository.existsById(id)){
            throw new EntityNotFoundException("Devotee with id "+id+" not found");
        }
        devoteeRepository.deleteById(id);
    }

    public Devotee getDevoteeById(Long id){
        return devoteeRepository.findById(id).orElse(null);
    }

    public Devotee updateDevotee(Long id, Devotee devotee){
        Optional<Devotee> optionalDevotee = devoteeRepository.findById(id);
        if(optionalDevotee.isPresent()){
            Devotee existingDevotee = optionalDevotee.get();

            existingDevotee.setEmail(devotee.getEmail());
            existingDevotee.setName(devotee.getName());
            existingDevotee.setPhone(devotee.getPhone());
            existingDevotee.setDepartment(devotee.getDepartment());

            return devoteeRepository.save(existingDevotee);
        }
        return null;
    }
}
