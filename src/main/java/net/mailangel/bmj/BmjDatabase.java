package net.mailangel.bmj;

import net.mailangel.bmj.entity.Course;
import net.mailangel.bmj.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BmjDatabase {
    private static final Logger logger = LoggerFactory.getLogger( BmjDatabase.class );

    @Bean
    CommandLineRunner init(CourseService courseService) {
        /*
        "5.00,Introduction to mechanical ventilation",
"3.5,Introduction to coronavirus disease 2019 (COVID-19)",
"2.00,Clinical pointers: COVID-19 in primary care",
"1,Clinical pointers: remote consultations in primary care",
"Quick tips: introduction to asthma",

"10.25,Infection control - including basic personal protective equipment",
"7.50,Introduction to testing for COVID-19",
"2.00,Airways management: tracheal intubation",
"2.50,Quick tips: proning in critical care",
"3.0,Quick tips: introduction to asthma"

         */
        return args -> {
            logger.info("*** Database init ...");
            logger.info("Creating .. " + courseService.save(new Course(5.00, "Introduction to mechanical ventilation") ) );
            logger.info("Creating .. " + courseService.save(new Course(3.5,"Introduction to coronavirus disease 2019 (COVID-19)") ) );
            logger.info("Creating .. " + courseService.save(new Course(2.00, "Clinical pointers: COVID-19 in primary care") ) );
            logger.info("Creating .. " + courseService.save(new Course(1.00, "Clinical pointers: remote consultations in primary care") ) );
            logger.info("Creating .. " + courseService.save(new Course(null, "Quick tips: introduction to asthma") ) );
            logger.info("Creating .. " + courseService.save(new Course(10.25, "Infection control - including basic personal protective equipment") ) );
            logger.info("Creating .. " + courseService.save(new Course(7.50, "Introduction to testing for COVID-19") ) );
            logger.info("Creating .. " + courseService.save(new Course(2.00, "Airways management: tracheal intubation") ) );
            logger.info("Creating .. " + courseService.save(new Course(2.50, "Quick tips: proning in critical care") ) );
            logger.info("Creating .. " + courseService.save(new Course(3.0, "Quick tips: introduction to asthma") ) );
            logger.info("*** Completed Database init.");
        };
    }
}
