package com.queryexec.utilities.repository;

import com.queryexec.utilities.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}