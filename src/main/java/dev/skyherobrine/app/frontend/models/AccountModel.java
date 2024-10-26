package dev.skyherobrine.app.frontend.models;

import dev.skyherobrine.app.backend.models.Account;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

public class AccountModel {

    private final String URL = "http://localhost:8080/review_gk/api/accounts";

    public List<Account> getListAccounts() {
        List<Account> results = new ArrayList<>();

        try(Client client = ClientBuilder.newClient()) {
            WebTarget target = client.target(URL);
            Response response = target.request().get();

            results = response.readEntity(new GenericType<List<Account>>() {});
        }

        return results;
    }

    public List<Account> getAccountByOwnerName(String ownerName) {
        List<Account> results = new ArrayList<>();

        try(Client client = ClientBuilder.newClient()) {
            WebTarget target = client.target(URL + "/owner_name/" + ownerName);
            Response response = target.request().get();
            results = response.readEntity(new GenericType<List<Account>>() {});
        }

        return results;
    }

    public List<Account> getAccountByAmount(double amount) {
        List<Account> results = new ArrayList<>();

        try(Client client = ClientBuilder.newClient()) {
            WebTarget target = client.target(URL + "/amount/" + amount);
            Response response = target.request().get();
            results = response.readEntity(new GenericType<List<Account>>() {});
        }

        return results;
    }
}
