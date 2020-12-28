package com.company;

import javax.xml.transform.TransformerException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ClientAccountException, BankAccountException, TransformerException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nTodo lo que pones en el login da igual menos el DNI (nom, cognom, numCuenta da igual)");
        System.out.println("DATOS PARA QUE PUEDAS PROBAR EXCEPTIONS");
        System.out.println("DNI -> 58585858Z");
        System.out.println("Cuentas para transferencia -> 123123A  |  123123B");

        CompteEstalvi numComptetest1 = new CompteEstalvi("123123A");;
        CompteEstalvi numComptetest2 = new CompteEstalvi("123123B");;

        System.out.println("\nBienvenido al la banca.");
        System.out.println("Por favor introduce tus datos de login");
        System.out.println("----------------------------------------");

        System.out.println("Introduce el numero de tu cuenta (Ex: 234456)");
        String cuenta = scanner.next();
        CompteEstalvi compteEstalvi = new CompteEstalvi(cuenta);
        
        System.out.println("Introduce el Nombre");
        String nom = scanner.next();
        System.out.println("Introduce el Apellido");
        String apellido = scanner.next();
        System.out.println("Introduce el DNI");
        String dni = scanner.next();

        Client dani = new Client(nom, apellido, dni);
        Client paco = new Client("Paco", "Jose", "58585858Z");
        Client maria = new Client("Maria", "Koko", "58585858Z");

        System.out.println("Numero de la Cuenta Seleccionada: " + compteEstalvi.getNumCompte());
        compteEstalvi.addUser(dani);
        compteEstalvi.addUser(paco);
        compteEstalvi.addUser(maria);
        System.out.println("Usuarios en esta cuenta: " + compteEstalvi.getLlista_usuaris().size());

        int seleccion;
        do {
            System.out.println("\nQue quieres hacer?");
            System.out.println("1. Ingresar Dinero");
            System.out.println("2. Sacar Dinero");
            System.out.println("3. Borrar Usuario");
            System.out.println("4. Transferencia");
            System.out.println("5. Ver Informacion Cuentas");
            System.out.println("6. Salir");
            seleccion = scanner.nextInt();

            switch (seleccion) {
                case 1 -> {
                    System.out.println("Cuanto dinero quieres ingresar");
                    double dineroIngresar = scanner.nextDouble();
                    compteEstalvi.ingressar(dineroIngresar);
                    System.out.println(compteEstalvi.getSaldo());
                }
                case 2 -> {
                    System.out.println("Cuanto dinero quiere sacar");
                    double dineroSacar = scanner.nextDouble();
                    compteEstalvi.treure(dineroSacar);
                    System.out.println(compteEstalvi.getSaldo());
                }
                case 3 -> {
                    System.out.println("Que usuario quieres borrar de esta cuenta");
                    for (int i = 0; i < compteEstalvi.getLlista_usuaris().size(); i++) {
                        System.out.println("Nombre: " + compteEstalvi.getLlista_usuaris().get(i).getNom());
                    }

                    System.out.println("\nIntroduce el nombre del usuario que quieres borrar");
                    String user = scanner.next();
                    compteEstalvi.removeUser(user);
                    for (int i = 0; i < compteEstalvi.getLlista_usuaris().size(); i++) {
                        System.out.println("Nombre: " + compteEstalvi.getLlista_usuaris().get(i).getNom());
                    }
                }
                case 4 -> {
                    System.out.println("Introduce la cuenta a la que quieres hacer una transferencia");
                    String cuentaIntroducida = scanner.next();
                    if (cuentaIntroducida.equals(numComptetest1.getNumCompte()) || cuentaIntroducida.equals(numComptetest2.getNumCompte())) {
                        System.out.println("Este es tu saldo " + compteEstalvi.getSaldo() + " euros, de cuanto quieres hacer la transferencia? ");
                        double saldoTransferencia = scanner.nextDouble();
                        if (cuentaIntroducida.equals(numComptetest1.getNumCompte())){
                            if (compteEstalvi.getSaldo() > saldoTransferencia) {
                                numComptetest1.ingressar(saldoTransferencia);
                                compteEstalvi.treure(saldoTransferencia);
                                System.out.println("Saldo Restante: " + compteEstalvi.getSaldo());
                            } else {
                                throw new BankAccountException(ExceptionMessage.TRANSFER_ERROR);
                            }
                        } else {
                            if (compteEstalvi.getSaldo() > saldoTransferencia) {
                                numComptetest2.ingressar(saldoTransferencia);
                                compteEstalvi.treure(saldoTransferencia);
                                System.out.println("Saldo Restante: " + compteEstalvi.getSaldo());
                            } else {
                                throw new BankAccountException(ExceptionMessage.TRANSFER_ERROR);
                            }
                        }
                    } else {
                        throw new TransformerException(ExceptionMessage.ACCOUNT_NOT_FOUND);
                    }
                    compteEstalvi.transferencia();
                }
                case 5 -> {
                    System.out.println("Informacion de Cuentas");
                    System.out.println("Numero Compte: " + compteEstalvi.getNumCompte() + " Saldo: " + compteEstalvi.getSaldo());
                    System.out.println("Numero Compte: " + numComptetest1.getNumCompte() + " Saldo: " + numComptetest1.getSaldo());
                    System.out.println("Numero Compte: " + numComptetest2.getNumCompte() + " Saldo: " + numComptetest2.getSaldo());
                }

                case 6 -> System.out.println("Adios");
                default -> System.out.println("Introduce un numero entre 1-6");
            }


        } while (seleccion != 6);
    }
}
