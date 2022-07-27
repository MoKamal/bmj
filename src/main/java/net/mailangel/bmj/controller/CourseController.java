package net.mailangel.bmj.controller;

import io.swagger.v3.oas.annotations.Operation;
import net.mailangel.bmj.entity.Course;
import net.mailangel.bmj.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/api/courses")
public class CourseController {
    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

    @Autowired
    CourseService courseService;

    @Operation(summary = "Get all courses")
    @GetMapping("")
    List<Course> getAll() {
        return courseService.findAll();
    }

    @Operation(summary = "Endpoint 1: endpoint that accepts a text search term and returns all the records that contain said search term.\n")
    @GetMapping("/{title}")
    List<Course> getByTitle(@PathVariable String title) {
        return courseService.findByTitle( title );
    }

    @Operation(summary = "Endpoint 2: An endpoint that accepts a text search term and returns all the records that contain said search term, additionally it should return the sum of the hours from those records.")
    @GetMapping("/{title}/sum")
    Map<String, Object> getByTitleAndSum(@PathVariable String title) {

        List<Course> list = getByTitle(title).stream().filter(x -> x.getTime() !=null ).collect(Collectors.toList());//filter null values

        double sum = list.stream().mapToDouble(Course::getTime).sum();

        Map map = new HashMap<String, Object>();
        map.put("courses", list);
        map.put("sum", sum);

        return map;
    }
}