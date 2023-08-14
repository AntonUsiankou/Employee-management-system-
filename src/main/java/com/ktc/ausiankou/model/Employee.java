package com.ktc.ausiankou.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

import static com.ktc.ausiankou.Constants.*;

@Entity
@Table(name = "employee")
@NoArgsConstructor
@Getter
@ToString
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name", length = 50, nullable = false)
    private String name;
    @Column(name = "birthday", nullable = false)
    private Date birthday;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Role role;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Department department;
    @OneToOne(cascade = CascadeType.ALL)
    private Salary salary;

    public Employee(String name, Date birthday, Role role, Department department, Salary salary) {
        setName(name);
        setBirthday(birthday);
        setRole(role);
        setDepartment(department);
        setSalary(salary);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException(INVALID_EMPLOYEE_NAME + EMPTY);
        }
        if (name.length() > 50) {
            throw new IllegalArgumentException(INVALID_EMPLOYEE_NAME + LENGTH_MORE_50);
        }
        this.name = name;
    }

    public void setBirthday(Date birthday) {
        if (birthday == null) {
            throw new IllegalArgumentException(INVALID_EMPLOYEE_BIRTHDAY + STRING_NULL);
        }
        this.birthday = birthday;
    }

    public void setRole(Role role) {
        if (role == null) {
            throw new IllegalArgumentException(INVALID_EMPLOYEE_ROLE + STRING_NULL);
        }
        this.role = role;
    }

    public void setDepartment(Department department) {
        if (department == null) {
            throw new IllegalArgumentException(INVALID_DEPARTMENT_NAME + STRING_NULL);
        }
        this.department = department;
    }

    public void setSalary(Salary salary) {
        if (salary == null) {
            throw new IllegalArgumentException(INVALID_EMPLOYEE_SALARY + STRING_NULL);
        }
        this.salary = salary;
    }
}
