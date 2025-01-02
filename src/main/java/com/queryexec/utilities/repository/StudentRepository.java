package com.queryexec.utilities.repository;

import com.queryexec.utilities.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    // No need to define a custom save method, JpaRepository already provides it

}
