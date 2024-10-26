package dev.skyherobrine.app.backend.repositories.impl;

import dev.skyherobrine.app.backend.models.Account;
import dev.skyherobrine.app.backend.repositories.IRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

public class AccountRepository implements IRepository<Account,String> {

    private static EntityManager em;
    private EntityTransaction trans;

    static {
        em = Persistence.createEntityManagerFactory("review_gk_13_15").createEntityManager();
    }

    @Override
    public Account insert(Account account) {
        trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(account);
            trans.commit();
            return account;
        } catch (Exception e) {
            trans.rollback();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Account update(Account account) {
        trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(account);
            trans.commit();
            return account;
        } catch (Exception e) {
            trans.rollback();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Account delete(String s) {
        trans = em.getTransaction();
        try {
            trans.begin();
            Account account = findById(s).orElseThrow(() -> new Exception("Account not found"));
            em.remove(account);
            trans.commit();
            return account;
        } catch (Exception e) {
            trans.rollback();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Optional<Account> findById(String s) {
        return Optional.of(em.find(Account.class, s));
    }

    @Override
    public List<Account> findAll() {
        return em.createNamedQuery("Account.findAll", Account.class).getResultList();
    }

    public List<Account> findAccountBetweenBalance(double min, double max) {
        return em.createNamedQuery("Account.findByAmountBetween", Account.class)
                .setParameter("startAmount", min)
                .setParameter("endAmount", max)
                .getResultList();
    }

    public List<Account> findAccountByCity(String address) {
        return em.createNamedQuery("Account.findByOwnerAddressLike", Account.class).setParameter("ownerAddress", address).getResultList();
    }

    public List<Account> findAccountByOwnerName(String ownerName) {
        return em.createNamedQuery("Account.findByOwnerName", Account.class).setParameter("ownerName", ownerName).getResultList();
    }

    public List<Account> findAccountByAmount(double amount) {
        return em.createNamedQuery("Account.findByAmount", Account.class).setParameter("amount", amount).getResultList();
    }
}
