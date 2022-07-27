package net.mailangel.bmj.service;

import net.mailangel.bmj.entity.Course;

import java.util.List;

public interface CourseService {
    Course save(Course course);

    List<Course> findAll();

    List<Course> findByTitle(String title);
}
