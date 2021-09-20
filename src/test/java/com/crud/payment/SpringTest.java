package com.crud.payment;

import com.crud.payment.service.FeeCalculationService;
import com.crud.payment.service.IbanService;
import com.crud.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@ActiveProfiles("test")
@SpringBootTest
@SqlGroup({
        @Sql(scripts = {
                "/db/iban-data.sql",
                "/db/bic-code-data.sql"
        }, executionPhase = BEFORE_TEST_METHOD),
        @Sql(statements = {
                "delete from `payment`",
                "delete from `iban`",
                "delete from `bic_code`",
        }, executionPhase = AFTER_TEST_METHOD)
})
public class SpringTest {
    @Autowired
    protected PaymentService paymentService;

    @Autowired
    protected IbanService ibanService;

    @Autowired
    protected FeeCalculationService feeCalculationService;
}
