package com.LibraryManagementSystem.LMS.Models;

import com.LibraryManagementSystem.LMS.Enum.Department;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor      // part of lombok-Dependency
@AllArgsConstructor     // part of lombok-Dependency
@Getter                 // part of lombok-Dependency
@Setter                 // part of lombok-Dependency
@Table(name = "student")
public class Student {
    /* while class is empty and having @entity annotation it shows the error becoz there should be a primary key attibute,
    which is a basic requirement of any table.
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int age;
    private String mobileNo;
    private String email;

    @Enumerated(EnumType.STRING)
    private Department department; // since it's in another package, so it has to be imported
// Department is an attribute of Student but since its fixed and comman
// so its written in Enum package under Department Class
// and thus has to be imported while use.

// to establish connection with card class,so attibute "status" is defined
    @OneToOne (mappedBy ="students",cascade = CascadeType.ALL)
    private LibraryCard cards;    // linking with LibraryCard


    // now will write constructors and Getter-n-Setter -used annotation

 /* By now If we execute, the database will not get created, so it will not create the table
    so we have to connect to the database.
 */

}
