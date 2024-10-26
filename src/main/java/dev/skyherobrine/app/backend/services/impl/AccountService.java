package dev.skyherobrine.app.backend.services.impl;

import dev.skyherobrine.app.backend.models.Account;
import dev.skyherobrine.app.backend.repositories.impl.AccountRepository;
import dev.skyherobrine.app.backend.services.IServices;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountService implements IServices<Account,String> {

    private AccountRepository ar = new AccountRepository();

    private int calculateOddNumber(int number) {
        String num = String.valueOf(number * 2);
        if(num.length() == 1) {
            return Integer.parseInt(num);
        } else {
            return Integer.parseInt(String.valueOf(num.charAt(0))) + Integer.parseInt(String.valueOf(num.charAt(1)));
        }
    }

    @Override
    public Account insert(Account account) {
        String getOwnerId = account.getAccountNumber();
        List<Integer> oddNumbers = new ArrayList<>();
        List<Integer> evenNumbers = new ArrayList<>();

        for(int i = 0; i < getOwnerId.length(); ++i) {
            if(i % 2 != 0) {
                oddNumbers.add(calculateOddNumber(Character.getNumericValue(getOwnerId.charAt(i))));
            } else {
                evenNumbers.add(Character.getNumericValue(getOwnerId.charAt(i)));
            }
        }

        int sumOddNumber = oddNumbers.stream().reduce(0, Integer::sum);
        //int sumOddNumber = oddNumbers.stream().mapToInt(Integer::intValue).sum();
        int sumEvenNumber = evenNumbers.stream().mapToInt(Integer::intValue).sum();

        if((sumOddNumber + sumEvenNumber) % 10 == 0) {
            return ar.insert(account);
        } else return null;
    }

    @Override
    public Account update(Account account) {
        return ar.update(account);
    }

    @Override
    public Account delete(String s) {
        return ar.delete(s);
    }

    @Override
    public Optional<Account> findById(String s) {
        return ar.findById(s);
    }

    @Override
    public List<Account> findAll() {
        return ar.findAll();
    }

    public List<Account> findAccountBetweenBalance(double min, double max) {
        return ar.findAccountBetweenBalance(min, max);
    }

    public List<Account> findAccountByCity(String address) {
        return ar.findAccountByCity(address);
    }

    public List<Account> findAccountByOwnerName(String ownerName) {
        return ar.findAccountByOwnerName(ownerName);
    }

    public List<Account> findAccountByAmount(double amount) {
        return ar.findAccountByAmount(amount);
    }
}
