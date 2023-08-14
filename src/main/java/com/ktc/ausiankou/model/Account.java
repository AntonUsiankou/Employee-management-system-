package com.ktc.ausiankou.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import static com.ktc.ausiankou.Constants.*;


@Entity
@Table(name = "account")
@NoArgsConstructor
@ToString
@Getter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "login", length = 20, nullable = false, unique = true)
    private String login;
    @Column(name = "pass", length = 20, nullable = false)
    private String pass;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Employee employee;

    public Account(String login, String pass, Employee employee) {
        setLogin(login);
        setPass(pass);
        setEmployee(employee);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String login) {
        if (login.isEmpty()) {
            throw new IllegalArgumentException(INVALID_ACCOUNT_LOGIN + EMPTY);
        }
        if (login.length() > 20) {
            throw new IllegalArgumentException(INVALID_ACCOUNT_LOGIN + LENGTH_MORE_20);
        }
        this.login = login;
    }

    public void setPass(String pass) {
        if (pass.isEmpty()) {
            throw new IllegalArgumentException(INVALID_ACCOUNT_PASS + EMPTY);
        }
        if (pass.length() > 20) {
            throw new IllegalArgumentException(INVALID_ACCOUNT_PASS + LENGTH_MORE_20);
        }
        this.pass = pass;
    }

    public void setEmployee(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException(INVALID_ACCOUNT_EMPLOYEE + STRING_NULL);
        }
        this.employee = employee;
    }
}
