package net.mailangel.bmj.repo;

import net.mailangel.bmj.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CourseRepo extends JpaRepository<Course, Long> {

    List<Course> findByTitleContainingIgnoreCase(String title);
}
