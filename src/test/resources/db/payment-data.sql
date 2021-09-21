insert into payment(id, creditor_iban_id, debtor_iban_id, payment_type_id, currency_id,
                    bic_code_id, cancel_for_payment_id, amount, details, created_at)
values (1, 1, 2, 1, 1, 1, null, 1000000, 'payment details', '2021-09-05'),
       (2, 2, 1, 1, 1, 1, 1, 900000, null, '2021-09-05');