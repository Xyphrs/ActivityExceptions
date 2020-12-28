package com.company;

import java.util.ArrayList;
import java.util.List;

public class CompteEstalvi {
    private final String numCompte;
    private double saldo;
    private final List<Client> llista_usuaris = new ArrayList<>();

    public CompteEstalvi(String numCompte) {
        this.numCompte = numCompte;
        saldo = 300;
    }

    public void addUser(Client client) {
        llista_usuaris.add(client);
    }

    public void removeUser(String dni) {
        llista_usuaris.removeIf(u -> {
            try {
                return dni.equals(u.getNom()) && remover();
            } catch (BankAccountException e) {
                e.printStackTrace();
            }
            return false;
        });
    }

    private boolean remover() throws BankAccountException {
        if (llista_usuaris.size() >= 2) {
            return true;
        } else {
            throw new BankAccountException(ExceptionMessage.ACCOUNT_ZERO_USER);
        }
    }

    public void ingressar(double m) {
        saldo += m;
    }

    public void treure(double m) throws BankAccountException {
        if (saldo < m) {
            throw new BankAccountException(ExceptionMessage.ACCOUNT_OVERDRAFT);
        } else {
            saldo -= m;
        }
    }

    public String getNumCompte() {
        return numCompte;
    }

    public double getSaldo() {
        return saldo;
    }

    public List<Client> getLlista_usuaris() {
        return llista_usuaris;
    }
}
