package com.aui.ecomt.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public final class Transaction {
    private final String id;
    private final BigDecimal amount;
    private final String reference;
    private final Instant instant;

    private Transaction(String id, BigDecimal amount, String reference, Instant instant) {
        this.id = id;
        this.amount = amount;
        this.reference = reference;
        this.instant = instant;
    }

    @JsonCreator
    public Transaction(
            @JsonProperty("amount") BigDecimal amount,
            @JsonProperty("reference") String reference) {
        this.id = UUID.randomUUID().toString();
        this.amount = amount;
        this.reference = reference;
        this.instant = Instant.now();
    }

    public String getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getReference() {
        return reference;
    }

    public String getInstant() {
        return instant
                .atOffset(ZoneOffset.UTC)
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm'Z'"));
    }
}