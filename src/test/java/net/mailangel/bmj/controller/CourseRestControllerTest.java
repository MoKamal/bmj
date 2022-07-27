package net.mailangel.bmj.controller;

import net.mailangel.bmj.entity.Course;
import net.mailangel.bmj.service.CourseService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(value = CourseController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
public class CourseRestControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CourseService service;

    private List<Course> allCourses;

    @Before
    public void setUp() throws Exception {
        Course course1 = new Course(1.0, "Test1");
        Course course2 = new Course(2.0, "Test2");
        Course course3 = new Course(3.0, "Test3");

        allCourses = Arrays.asList(course1, course2, course3);
    }

    @Test
    public void givenCourses_whenGetCourses_thenReturnAll() throws Exception {
        String url = "/api/courses";

        given(service.findAll()).willReturn(allCourses);

        mvc.perform( get( url ).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].title", is(allCourses.get(0).getTitle() ) ) )
                .andExpect(jsonPath("$[1].title", is(allCourses.get(1).getTitle() ) ) )
                .andExpect(jsonPath("$[2].title", is(allCourses.get(2).getTitle() ) ) )
        ;

        verify(service, VerificationModeFactory.times(1)).findAll();

        reset(service);
    }

    @Test
    public void givenTitle_whenGetCourses_thenReturnAllMatchingTitle() throws Exception {
        String title = "Test1";
        String url = "/api/courses/" + title;

        given(service.findByTitle( title )).willReturn(allCourses.subList(0, 1));

        mvc.perform( get( url ).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title", is(allCourses.get(0).getTitle() ) ) );

        verify(service, VerificationModeFactory.times(1)).findByTitle( title );

        reset(service);
    }

    @Test
    public void givenTitle_whenGetCoursesSum_thenReturnMatchJsonArray() throws Exception {
        String title = "Test";
        Double sum = allCourses.stream().mapToDouble(Course::getTime).sum();//test with 1, 3, 5, etc for failure!!!
        String url = "/api/courses/" + title + "/sum";

        given(service.findByTitle( title ) ).willReturn(allCourses);

        mvc.perform( get( url ).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.courses", hasSize(3)))
                .andExpect(jsonPath("$.courses.[0].title", is(allCourses.get(0).getTitle() ) ) )
                .andExpect(jsonPath("$.courses.[1].title", is(allCourses.get(1).getTitle() ) ) )
                .andExpect(jsonPath("$.courses.[2].title", is(allCourses.get(2).getTitle() ) ) )
                .andExpect(jsonPath("$.sum", is( sum ) ) )
                ;

        verify(service, VerificationModeFactory.times(1)).findByTitle( title );

        reset(service);
    }

}