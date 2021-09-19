package com.crud.payment.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class PaymentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "payment_type")
    private String paymentType;

    @Column(name = "fee_coefficient")
    private Double feeCoefficient;

    public PaymentType() {
    }

    public PaymentType(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Double getFeeCoefficient() {
        return feeCoefficient;
    }

    public void setFeeCoefficient(Double feeCoefficient) {
        this.feeCoefficient = feeCoefficient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentType that = (PaymentType) o;
        return Objects.equals(id, that.id)
                && Objects.equals(paymentType, that.paymentType)
                && Objects.equals(feeCoefficient, that.feeCoefficient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, paymentType, feeCoefficient);
    }

    @Override
    public String toString() {
        return "PaymentType{" +
                "id=" + id +
                ", paymentType='" + paymentType + '\'' +
                ", feeCoefficient=" + feeCoefficient +
                '}';
    }
}
