package com.spring.relationship.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastname;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "tbl_alumnos_cursos",
            joinColumns = @JoinColumn(name = "id_alumno"),
            inverseJoinColumns = @JoinColumn(name = "id_curso"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"id_alumno", "id_curso"})
    )
    private Set<Course> courses = new HashSet<>();

    public Student(String name, String lastname) {
        this();
        this.name = name;
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", courses=" + courses +
                '}';
    }

    // Metodos para añadir o quitar cursos bidirecionalmente.
    public void addCourses(Course course) {
        this.courses.add(course); // Toma esta instancia de clase, referencia el Set, añade el course.
        course.getStudents().add(this); // El course obtiene Set de estudiantes, añade este a la instancia de clase.
    }

    public void removeCourses(Course course) {
        this.courses.remove(course); // Toma esta instancia de clase, referencia el Set, remueve el course.
        course.getStudents().remove(this); // El course obtiene Set de estudiantes, remueve esta instancia de clase.
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id) &&
                Objects.equals(name, student.name) &&
                Objects.equals(lastname, student.lastname) &&
                Objects.equals(courses, student.courses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastname, courses);
    }
}
