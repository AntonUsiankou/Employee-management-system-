package com.ktc.ausiankou.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import static com.ktc.ausiankou.Constants.INVALID_DEPARTMENT_NAME;
import static com.ktc.ausiankou.Constants.LENGTH_MORE_20;
import static com.ktc.ausiankou.Constants.EMPTY;

@Entity
@Table(name = "department")
@NoArgsConstructor
@Getter
@ToString
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name", length = 10, nullable = false, unique = true)
    private String name;

    public Department(String name) {
        setName(name);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException(INVALID_DEPARTMENT_NAME + EMPTY);
        }
        if (name.length() > 20) {
            throw new IllegalArgumentException(INVALID_DEPARTMENT_NAME + LENGTH_MORE_20);
        }
        this.name = name;
    }
}
