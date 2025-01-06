package com.queryexec.utilities.service;

import com.queryexec.utilities.exception.StudentRegistrationException;
import com.queryexec.utilities.model.Course;
import com.queryexec.utilities.model.Student;
import com.queryexec.utilities.repository.CourseRepository;
import com.queryexec.utilities.repository.EnrollmentRepository;
import com.queryexec.utilities.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private static final Logger logger = LoggerFactory.getLogger(CourseService.class);

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    // Method to create a new course
    public Course createCourse(Course course) {
        logger.info("Creating course with name: {}", course.getCourseName());
        if (courseRepository.existsById(course.getId())) {
            throw new StudentRegistrationException("Course already exists with ID: " + course.getId());
        }
        Course savedCourse = courseRepository.save(course);
        logger.info("Course created successfully with ID: {}", savedCourse.getId());
        logger.info("Course created/updated successfully with ID: {}", savedCourse.getId());
        return savedCourse;
    }

    public Course updateCourse(Long courseId, String courseName, String courseDescription) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new StudentRegistrationException("Course not found with ID: " + courseId));
        course.setCourseName(courseName);
        course.setCourseDescription(courseDescription);
        return courseRepository.save(course);
    }

    public List<CourseWithStudentsDTO> getAllCoursesWithStudents() {
        logger.info("Fetching all courses with students");
        List<Course> courses = courseRepository.findAll();
        List<CourseWithStudentsDTO> coursesWithStudents = courses.stream().map(course -> {
            List<Student> students = enrollmentRepository.findByCourseId(course.getId())
                    .stream()
                    .map(enrollment -> enrollment.getStudent())
                    .collect(Collectors.toList());
            return new CourseWithStudentsDTO(course, students);
        }).collect(Collectors.toList());
        logger.info("Fetched {} courses with students", coursesWithStudents.size());
        return coursesWithStudents;
    }

    public static class CourseWithStudentsDTO {
        private Course course;
        private List<Student> students;

        public CourseWithStudentsDTO(Course course, List<Student> students) {
            this.course = course;
            this.students = students;
        }

        public Course getCourse() {
            return course;
        }

        public void setCourse(Course course) {
            this.course = course;
        }

        public List<Student> getStudents() {
            return students;
        }

        public void setStudents(List<Student> students) {
            this.students = students;
        }
    }



    public void deleteCourse(Long courseId) {
        courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with ID: " + courseId));
        courseRepository.deleteById(courseId);
    }



}
