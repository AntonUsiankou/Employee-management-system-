package com.ktc.ausiankou.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import static com.ktc.ausiankou.Constants.*;

@Entity
@Table(name = "role")
@NoArgsConstructor
@ToString
@Getter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name", length = 10, nullable = false, unique = true)
    private String name;

    public Role(String name) {
        setName(name);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException(INVALID_ROLE_NAME + STRING_NULL);
        }
        if (name.length() > 10) {
            throw new IllegalArgumentException(INVALID_ROLE_NAME + LENGTH_MORE_10);
        }
        this.name = name;
    }
}
