package dev.skyherobrine.app.frontend.controllers;

import dev.skyherobrine.app.backend.models.Account;
import dev.skyherobrine.app.frontend.models.AccountModel;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ControllerServlet", urlPatterns = "/controller")
public class ControllerServlet extends HttpServlet {

    private AccountModel accountModel = new AccountModel();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Account> accounts = accountModel.getListAccounts();
        req.setAttribute("accounts", accounts);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String getTypeSearch = req.getParameter("type_search");
        String getInput = req.getParameter("inputSearch");
        if(getInput == null || getInput.isEmpty()) {
            List<Account> accounts = accountModel.getListAccounts();
            req.setAttribute("accounts", accounts);
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        } if(getTypeSearch.equalsIgnoreCase("amount")) {
            Double getAmount = Double.parseDouble(getInput);
            List<Account> accounts = accountModel.getAccountByAmount(getAmount);
            req.setAttribute("accounts", accounts);
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        } else {
            List<Account> accounts = accountModel.getAccountByOwnerName(getInput);
            req.setAttribute("accounts", accounts);
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }
}
