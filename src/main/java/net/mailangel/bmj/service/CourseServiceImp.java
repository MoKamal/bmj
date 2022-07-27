package net.mailangel.bmj.service;

import net.mailangel.bmj.entity.Course;
import net.mailangel.bmj.repo.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "courseService")
public class CourseServiceImp implements CourseService {
    @Autowired
    CourseRepo courseRepo;

    @Override
    public Course save(Course course) {
        return courseRepo.save( course );
    }

    public List<Course> findAll() {
        return courseRepo.findAll();
    }

    public List<Course> findByTitle(String title) {
        return courseRepo.findByTitleContainingIgnoreCase( title );
    }
}
