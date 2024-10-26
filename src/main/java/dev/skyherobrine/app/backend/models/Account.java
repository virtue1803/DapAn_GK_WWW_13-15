package dev.skyherobrine.app.backend.models;

import jakarta.persistence.*;

@Entity
@Table(name = "bank_account")
@NamedQueries({
        @NamedQuery(name = "Account.findAll", query = "select a from Account a"),
        @NamedQuery(name = "Account.findByAmountBetween", query = "select a from Account a where a.amount between :startAmount and :endAmount"),
        @NamedQuery(name = "Account.findByOwnerAddressLike", query = "select a from Account a where a.ownerAddress like :ownerAddress"),
        @NamedQuery(name = "Account.findByOwnerName", query = "select a from Account a where a.ownerName = :ownerName"),
        @NamedQuery(name = "Account.findByAmount", query = "select a from Account a where a.amount = :amount")
})
public class Account {
    @Id @Column(name = "account_number", length = 10, nullable = false)
    private String accountNumber;
    @Column(name = "owner_name", length = 155, nullable = false)
    private String ownerName;
    @Column(name = "owner_address", nullable = false)
    private String ownerAddress;
    @Column(nullable = false)
    private double amount;

    public Account() {
    }

    public Account(String accountNumber, String ownerName, String ownerAddress, double amount) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.ownerAddress = ownerAddress;
        this.amount = amount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerAddress() {
        return ownerAddress;
    }

    public void setOwnerAddress(String ownerAddress) {
        this.ownerAddress = ownerAddress;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber='" + accountNumber + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", ownerAddress='" + ownerAddress + '\'' +
                ", amount=" + amount +
                '}';
    }
}
