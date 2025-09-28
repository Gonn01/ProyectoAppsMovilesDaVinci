package com.example.proyectoappsmovilesdavinci.dtos;

public class PurchaseHomeDto {

                 private final int id;
          private final double amount;
                 private final String name;
 private final int financialEntityId;

    public PurchaseHomeDto(
            int id,
            double amount,
            String name,
            int financialEntityId
    ) {
        this.id = id;
        this.amount = amount;
        this.name = name;
        this.financialEntityId = financialEntityId;
    }

    public int getId() { return id; }
    public double getAmount() { return amount; }
    public String getName() { return name; }
    public int getFinancialEntityId() { return financialEntityId; }

}
