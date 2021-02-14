package app;

import java.util.EventListener;
import java.util.Locale;
import java.util.Scanner;

public class Main {

       static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        int escolha; boolean flag;
        Database database = new Database();
        do {
            menu();
            escolha = input.nextInt();
            flag = escolha != 0;
            operacoes(escolha, database);

        }while (flag);

    }
    static void menu(){
        System.out.println("******** BANCO CEFET ********");
        System.out.println("\nSelecione uma opcao: ");
        System.out.println("1 - Criar conta");
        System.out.println("2 - Sacar");
        System.out.println("3 - Depositar");
        System.out.println("4 - Transferir");
        System.out.println("5 - Mostrar contas cadastradas no sistema");
    }
    static void operacoes(int escolha, Database database){
        int numero, tipo, cpf;
        double saldo;
        String nome;
        Conta conta, destino;
        switch (escolha){
            case 1:
                System.out.print("Numero da conta: ");
                 numero = input.nextInt();

                System.out.print("Tipo da conta: ");
                tipo = input.nextInt();

                System.out.print("Saldo: ");
                saldo = input.nextDouble();

                System.out.print("Cpf: ");
                cpf = input.nextInt();

                System.out.print("Nome do titular: ");
                input.nextLine();
                nome = input.nextLine();

                database.add(new Conta(numero, tipo, saldo, new Cliente(nome, cpf)));
                break;
            case 2:

                System.out.print("Insira o numero da conta: ");
                numero = input.nextInt();
                conta = database.query(numero);
                if(conta != null) {
                    System.out.print("Valor a ser sacado: ");
                    double quantidade = input.nextDouble();
                    conta.sacar(quantidade);
                } else
                    System.out.println("Conta nao cadastrada no nosso sistema!");
                break;
            case 3:

                System.out.print("Insira o numero da conta: ");
                numero = input.nextInt();
                conta = database.query(numero);
                if(conta != null) {
                    System.out.print("Valor a ser depositado: ");
                    double quantidade = input.nextDouble();
                    conta.depositar(quantidade);
                } else
                    System.out.println("Conta nao cadastrada no nosso sistema!");
                break;
            case 4:

                System.out.print("Insira o numero da sua conta: ");
                numero = input.nextInt();
                conta = database.query(numero);
                if(conta != null) {
                    System.out.print("Insira o numero da conta de destino: ");
                    numero = input.nextInt();
                    System.out.print("Valor a ser transferido: ");
                    double quantidade = input.nextDouble();
                    conta.transferirPara(numero, quantidade, database);
                } else
                    System.out.println("Sua conta nao cadastrada no nosso sistema!");
                break;
            case 5:
                database.mostrarContas();
                break;
        }
        System.out.println();
        System.out.println();
        System.out.println();
    }

}
