package com.ktc.ausiankou.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

import static com.ktc.ausiankou.Constants.*;

@Entity
@Table(name = "holiday")
@NoArgsConstructor
@Getter
@ToString
public class Holiday {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "date_to", nullable = false)
    private Date dateTo;
    @Column(name = "date_from", nullable = false)
    private Date dateFrom;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Employee employee;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Status status;

    public Holiday(Date dateTo, Date dateFrom, Employee employee, Status status) {
        setDateTo(dateTo);
        setDateFrom(dateFrom);
        setEmployee(employee);
        setStatus(status);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public void setEmployee(Employee employee) {
        if(employee == null) {
            throw new IllegalArgumentException(INVALID_HOLIDAY_EMPLOYEE + STRING_NULL);
        }
        this.employee = employee;
    }

    public void setStatus(Status status) {
        if(status == null){
            throw new IllegalArgumentException(INVALID_HOLIDAY_STATUS + STRING_NULL);
        }
        this.status = status;
    }
}
