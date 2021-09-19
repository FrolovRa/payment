package com.crud.payment.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class BicCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bic_code")
    private String bicCode;

    public BicCode() {
    }

    public BicCode(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBicCode() {
        return bicCode;
    }

    public void setBicCode(String bicCode) {
        this.bicCode = bicCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BicCode bicCode1 = (BicCode) o;
        return Objects.equals(id, bicCode1.id)
                && Objects.equals(bicCode, bicCode1.bicCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bicCode);
    }

    @Override
    public String toString() {
        return "BicCode{" +
                "id=" + id +
                ", bicCode='" + bicCode + '\'' +
                '}';
    }
}
