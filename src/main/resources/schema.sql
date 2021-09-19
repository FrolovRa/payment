create table iban
(
    id   bigint             not null auto_increment primary key,
    iban varchar(34) unique not null
);

create table user
(
    id       bigint              not null auto_increment primary key,
    iban_id  bigint              not null,
    name     varchar(255) unique not null,
    password varchar(255)        not null,

    constraint fk__user__iban foreign key (iban_id) references iban (id)
);

create table payment_type
(
    id              bigint             not null auto_increment primary key,
    payment_type    varchar(10) unique not null,
    fee_coefficient decimal(4, 3)
);

create table currency
(
    id       bigint             not null auto_increment primary key,
    currency varchar(5) unique not null
);

create table bic_code
(
    id       bigint             not null auto_increment primary key,
    bic_code varchar(11) unique not null
);

create table payment
(
    id                    bigint    not null auto_increment primary key,
    creditor_iban_id      bigint    not null,
    debtor_iban_id        bigint    not null,
    payment_type_id       bigint    not null,
    currency_id           bigint    not null,
    bic_code_id           bigint    not null,
    cancel_for_payment_id bigint,
    amount                numeric   not null,
    details               varchar(255),
    created_at            timestamp not null,

    constraint fk__payment__creditor_iban foreign key (creditor_iban_id) references iban (id),
    constraint fk__payment__debtor_iban foreign key (debtor_iban_id) references iban (id),
    constraint fk__payment__payment_type foreign key (payment_type_id) references payment_type (id),
    constraint fk__payment__currency foreign key (currency_id) references currency (id),
    constraint fk__payment__bic_code foreign key (bic_code_id) references bic_code (id),
    constraint fk__payment__payment foreign key (cancel_for_payment_id) references payment (id)
);
