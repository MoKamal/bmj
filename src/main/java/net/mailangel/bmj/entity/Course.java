package net.mailangel.bmj.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Course { //should extends base entity class

    private @Id
    @GeneratedValue
    Long id;

    private Double time;
    private String title;

    public Course() {
    }

    public Course(Double time, String title) {
        this.time = time;
        this.title = title;
    }

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(time, course.time) && Objects.equals(title, course.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, title);
    }

    @Override
    public String toString() {
        return "Course{" +
                "time=" + time +
                ", title='" + title + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
