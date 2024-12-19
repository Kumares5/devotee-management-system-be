package com.Project.devotee.repository;

import com.Project.devotee.entity.Devotee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DevoteeRepository extends JpaRepository<Devotee,Long> {


}
