package com.aui.web;

import com.aui.context.ApplicationContext;
import com.aui.model.Transaction;
import com.aui.service.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class TransactionServlet extends HttpServlet {
    public ObjectMapper objectMapper = ApplicationContext.objectMapper;
    public TransactionService transactionService = ApplicationContext.transactionService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json; charset=UTF-8");
        if (req.getRequestURI().equalsIgnoreCase("/transactions")) {
            List<Transaction> transactions = transactionService.getAllTransactions();
            String transactionsAsString = objectMapper.writeValueAsString(transactions);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write(transactionsAsString);
        } else if (req.getRequestURI().equalsIgnoreCase("/transaction")) {
            String idParam = req.getParameter("id");
            transactionService.getTransaction(idParam).ifPresentOrElse(trx -> {
                        try {
                            String transactionsAsString = objectMapper.writeValueAsString(trx);
                            resp.setStatus(HttpServletResponse.SC_OK);
                            resp.getWriter().write(transactionsAsString);
                        } catch (Exception e) {
                            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        }
                    },
                    () -> {
                        try {
                            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                            resp.getWriter().write("Transaction not found!");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
            );
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json; charset=UTF-8");
        if (req.getRequestURI().contains("/transaction")) {
            Transaction trx = objectMapper.readValue(req.getInputStream(), Transaction.class);
            transactionService.postTransaction(trx);
            resp.setStatus(HttpServletResponse.SC_CREATED);
            resp.getWriter().write("Saved Successfully!");
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
