package camt.se234.lab11.service;

import camt.se234.lab11.NoDataException;
import camt.se234.lab11.dao.StudentDao;
import camt.se234.lab11.dao.StudentDaoImpl;
import camt.se234.lab11.entity.Student;
import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.NaN;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class StudentServiceTestImpl {
    StudentDao studentDao;
    StudentServiceImpl studentService;

    @Before
    public void setup(){
        studentDao = mock(StudentDao.class);
        studentService = new StudentServiceImpl();
        studentService.setStudentDao(studentDao);
    }
    @Test
    public void testFindById(){
        StudentDaoImpl studentDao = new StudentDaoImpl();
        StudentServiceImpl studentService = new StudentServiceImpl();
        studentService.setStudentDao(studentDao);
        assertThat(studentService.findStudentById("123"),is(new Student("123","A","temp",2.33)));
        assertThat(studentService.findStudentById("5016"),is(new Student("5016","Thananan","Asanthia",3.00)));
    }
    @Test
    public void testAverageGpa() {
        StudentDaoImpl studentDao = new StudentDaoImpl();
        StudentServiceImpl studentService = new StudentServiceImpl();
        studentService.setStudentDao(studentDao);
        assertThat(studentService.getAverageGpa(),closeTo(2.61,0.01));
    }
    @Test
    public void testWithMock(){
        List<Student> mockStudents = new ArrayList<>();
        mockStudents.add(new Student("123","A","temp",2.33));
        mockStudents.add(new Student("124","B","temp",2.33));
        mockStudents.add(new Student("223","C","temp",2.33));
        mockStudents.add(new Student("224","D","temp",2.33));
        when(studentDao.findAll()).thenReturn(mockStudents);
        assertThat(studentService.findStudentById("224"),is(new Student("224","D","temp",2.33)));
    }

    @Test
    public void testWithMock2(){

        List<Student> mockStudents2 = new ArrayList<>();

        mockStudents2.add(new Student("123","A","temp",1.00));
        mockStudents2.add(new Student("124","B","temp",2.00));
        mockStudents2.add(new Student("223","C","temp",3.50));
        mockStudents2.add(new Student("224","D","temp",0.01));
        when(studentDao.findAll()).thenReturn(mockStudents2);
        assertThat(studentService.findStudentById("224"),is(new Student("224","D","temp",0.01)));
    }
    @Test
    public void testAverageGpaWithMock(){

        List<Student> mockStudents2 = new ArrayList<>();

        mockStudents2.add(new Student("123","A","temp",1.00));
        mockStudents2.add(new Student("124","B","temp",2.00));
        mockStudents2.add(new Student("223","C","temp",3.50));
        when(studentDao.findAll()).thenReturn(mockStudents2);
        assertThat(studentService.getAverageGpa(),closeTo(2.17,0.01));

    }
    @Test
    public void testFindByPartOfId(){

        List<Student> mockStudents2 = new ArrayList<>();

        mockStudents2.add(new Student("123","A","temp",1.00));
        mockStudents2.add(new Student("124","B","temp",2.00));
        mockStudents2.add(new Student("223","C","temp",3.50));
        mockStudents2.add(new Student("224","D","temp",0.01));
        when(studentDao.findAll()).thenReturn(mockStudents2);
       assertThat(studentService.findStudentByPartOfId("22"),hasItem(new Student("223","C","temp",3.50)));
        assertThat(studentService.findStudentByPartOfId("22"),hasItems(new Student("223","C","temp",3.50),
                new Student("224","D","temp",0.01)));

        assertThat(studentService.findStudentByPartOfId("12"),hasItem(new Student("123","A","temp",1.00)));
        assertThat(studentService.findStudentByPartOfId("12"),hasItems(new Student("123","A","temp",1.00),
                new Student("124","B","temp",2.00)));

    }
    @Test(expected = NoDataException.class)
    public void testNoDataException(){
        List<Student> mockStudents = new ArrayList<>();
        mockStudents.add(new Student("123","A","temp",1.00));
        when(studentDao.findAll()).thenReturn(mockStudents);
        assertThat(studentService.findStudentById("55"),nullValue());

    }
    @Test(expected = Exception.class)
    public void testNoDataException2(){
        List<Student> mockStudents = new ArrayList<>();
       // mockStudents.add(new Student("123","A","temp",1.00));
        when(studentDao.findAll()).thenReturn(mockStudents);
        assertThat(studentService.findStudentByPartOfId("55"),nullValue());

    }
    @Test(expected = ArithmeticException.class)
    public void testNoDataException3(){
        List<Student> mockStudents = new ArrayList<>();
        // mockStudents.add(new Student("123","A","temp",1.00));
        when(studentDao.findAll()).thenReturn(mockStudents);
        assertThat(studentService.getAverageGpa(),is(NaN));

    }
}
