package camt.se234.lab11.dao;

import camt.se234.lab11.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {
    List<Student> students;
    List<Student> students2;
    public StudentDaoImpl(){
        students = new ArrayList<>();
        students.add(new Student("123","A","temp",2.33));
        students.add(new Student("5016","Thananan","Asanthia",3.00));
        students.add(new Student("959","AAA","BBBB",3.99));
        students.add(new Student("536","SSD","HDD",1.25));
        students.add(new Student("12","AS","ZX",2.50));

//        students2 = new ArrayList<>();
//        students.add(new Student("123","A","temp",4.00));
//        students.add(new Student("5016","Thananan","Asanthia",3.75));
//        students.add(new Student("959","AAA","BBBB",3.75));
//        students.add(new Student("536","SSD","HDD",1.25));
//        students.add(new Student("12","AS","ZX",2.50));

    }

    @Override
    public List<Student> findAll() {
        return this.students;
    }
}
