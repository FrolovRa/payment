package com.crud.payment.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Iban {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "iban")
    private String iban;

    public Iban() {
    }

    public Iban(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Iban iban1 = (Iban) o;
        return Objects.equals(id, iban1.id)
                && Objects.equals(iban, iban1.iban);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, iban);
    }

    @Override
    public String toString() {
        return "Iban{" +
                "id=" + id +
                ", iban='" + iban + '\'' +
                '}';
    }
}
