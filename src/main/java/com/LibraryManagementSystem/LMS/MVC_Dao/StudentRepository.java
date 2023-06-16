package com.LibraryManagementSystem.LMS.MVC_Dao;

import com.LibraryManagementSystem.LMS.Enum.Department;
import com.LibraryManagementSystem.LMS.Models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    // to extend Jpa repository we use interface not class
// JpaRepository<Student,Integer> - < tableName , datatype of the primary key(wrapperClass)>
// why interface not class ------ because if we make it a class then we have the responsibility to implement the jpaRepository
// but that not our reposibility and we wont be allowed to that in spring. however ORM framework(Hibenate) has the responsibilty
// to implement and that's why we make it interface not class.


    List<Student> findByName(String name);
    List<Student> findByDepartment(Department department);

}
// why not implements instead on extend? -  both are interface(studentRepository and JPA repository)
// and cannot make class because implementation is given by ORM itself thats why developer is extending it