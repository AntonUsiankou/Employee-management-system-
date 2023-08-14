package com.ktc.ausiankou.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import static com.ktc.ausiankou.Constants.INVALID_SALARY_QUANTITY;
import static com.ktc.ausiankou.Constants.NON_POSITIVE_QUANTITY;

@Entity
@Table(name = "salary")
@NoArgsConstructor
@Getter
@ToString
public class Salary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "quantity", nullable = false)
    private int quantity;

    public Salary(int quantity) {
        setQuantity(quantity);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException(INVALID_SALARY_QUANTITY + NON_POSITIVE_QUANTITY + quantity);
        }
        this.quantity = quantity;
    }
}
