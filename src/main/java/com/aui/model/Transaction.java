package com.aui.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public final class Transaction {
    private final Long id;
    private final BigDecimal amount;
    private final String reference;
    private final ZonedDateTime zonedDateTime;

    private Transaction(Long id, BigDecimal amount, String reference, ZonedDateTime zonedDateTime) {
        this.id = id;
        this.amount = amount;
        this.reference = reference;
        this.zonedDateTime = zonedDateTime;
    }

    public Transaction(Long id, BigDecimal amount, String reference, LocalDateTime localDateTime) {
        this.id = id;
        this.amount = amount;
        this.reference = reference;
        this.zonedDateTime = localDateTime.atOffset(ZoneOffset.UTC).toZonedDateTime();
    }

    public Long getId() {
        return id;
    }
}