package dev.skyherobrine.app.backend.facade;

import dev.skyherobrine.app.backend.models.Account;
import dev.skyherobrine.app.backend.services.impl.AccountService;
import jakarta.data.repository.Param;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/accounts")
public class AccountResource {

    private AccountService as = new AccountService();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Account addAccount(Account account) {
        Account result = as.insert(account);
        if(result == null) {
            throw new BadRequestException("Invalid account number");
        } else {
            return result;
        }
    }

    @GET
    @Produces("application/json")
    public List<Account> findAll() {
        System.out.println("Calling get all accounts");
        return as.findAll();
    }

    @GET
    @Path("/balance")
    @Produces("application/json")
    public List<Account> findAccountBetweenBalance(@QueryParam("min") double min, @QueryParam("max") double max) {
        return as.findAccountBetweenBalance(min, max);
    }

    @GET
    @Path("{city}")
    @Produces("application/json")
    public List<Account> findAccountByCity(@PathParam("city") String address) {
        return as.findAccountByCity(address);
    }

    @GET
    @Path("/owner_name/{ownerName}")
    @Produces("application/json")
    public List<Account> findAccountByOwnerName(@PathParam("ownerName") String ownerName) {
        return as.findAccountByOwnerName(ownerName);
    }

    @GET
    @Path("/amount/{amount}")
    @Produces("application/json")
    public List<Account> findAccountByAmount(@PathParam("amount") double amount) {
        return as.findAccountByAmount(amount);
    }
}
