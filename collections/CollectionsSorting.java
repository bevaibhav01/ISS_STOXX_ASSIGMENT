package collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// Custom Class: Student
class Student implements Comparable<Student> {
    private final int id;
    private final String name;

    // Constructor
    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Student[ID=" + id + ", Name=" + name + "]";
    }

    // Comparable implementation: Sort by ID (ascending)
    @Override
    public int compareTo(Student other) {
        return Integer.compare(this.id, other.id);
    }
}

// Main Class
public class CollectionsSorting {
    private static final Logger logger = LoggerFactory.getLogger(CollectionsSorting.class);

    public static void main(String[] args) {
        // Sorting Primitives
        List<Integer> numbers = new ArrayList<>();
        numbers.add(42);
        numbers.add(10);
        numbers.add(73);
        numbers.add(5);

        logger.info("Original List of Numbers: {}", numbers);
        Collections.sort(numbers); // Sort in ascending order
        logger.info("Sorted List of Numbers: {}", numbers);

        // Sorting Custom Objects
        List<Student> students = new ArrayList<>();
        students.add(new Student(3, "Alice"));
        students.add(new Student(1, "Bob"));
        students.add(new Student(2, "Charlie"));

        logger.info("Original List of Students: {}", students);

        // Sorting using Comparable (by ID)
        Collections.sort(students);
        logger.info("Students Sorted by ID: {}", students);

        // Sorting using Comparator (by Name)
        students.sort(Comparator.comparing(Student::getName));
        logger.info("Students Sorted by Name: {}", students);
    }
}
