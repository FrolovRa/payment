package com.crud.payment.domain;

import com.crud.payment.validation.GreaterThanZeroBigInteger;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.Instant;
import java.util.Objects;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "creditor_iban_id", referencedColumnName = "id")
    private Iban creditorIban;

    @ManyToOne
    @JoinColumn(name = "debtor_iban_id", referencedColumnName = "id")
    private Iban debtorIban;

    @ManyToOne
    @JoinColumn(name = "payment_type_id", referencedColumnName = "id")
    private PaymentType paymentType;

    @ManyToOne
    @JoinColumn(name = "currency_id", referencedColumnName = "id")
    private Currency currency;

    @ManyToOne
    @JoinColumn(name = "bic_code_id", referencedColumnName = "id")
    private BicCode bicCode;

    @OneToOne
    private Payment cancelForPayment;

    @GreaterThanZeroBigInteger
    @Column(name = "amount")
    private BigInteger amount;

    @Column(name = "details")
    private String details;

    @CreatedDate
    @Column(name = "created_at")
    private Instant createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Iban getCreditorIban() {
        return creditorIban;
    }

    public void setCreditorIban(Iban creditorIban) {
        this.creditorIban = creditorIban;
    }

    public Iban getDebtorIban() {
        return debtorIban;
    }

    public void setDebtorIban(Iban debtorIban) {
        this.debtorIban = debtorIban;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BicCode getBicCode() {
        return bicCode;
    }

    public void setBicCode(BicCode bicCode) {
        this.bicCode = bicCode;
    }

    public Payment getCancelForPayment() {
        return cancelForPayment;
    }

    public void setCancelForPayment(Payment canceledForPayment) {
        this.cancelForPayment = canceledForPayment;
    }

    public BigInteger getAmount() {
        return amount;
    }

    public void setAmount(BigInteger amount) {
        this.amount = amount;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id)
                && Objects.equals(creditorIban, payment.creditorIban)
                && Objects.equals(debtorIban, payment.debtorIban)
                && Objects.equals(paymentType, payment.paymentType)
                && Objects.equals(currency, payment.currency)
                && Objects.equals(bicCode, payment.bicCode)
                && Objects.equals(cancelForPayment, payment.cancelForPayment)
                && Objects.equals(amount, payment.amount)
                && Objects.equals(details, payment.details)
                && Objects.equals(createdAt, payment.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, creditorIban, debtorIban, paymentType, currency,
                bicCode, cancelForPayment, amount, details, createdAt);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", creditorIban=" + creditorIban +
                ", debtorIban=" + debtorIban +
                ", paymentType=" + paymentType +
                ", currency=" + currency +
                ", bicCode=" + bicCode +
                ", cancelForPayment=" + cancelForPayment +
                ", amount=" + amount +
                ", details='" + details + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
