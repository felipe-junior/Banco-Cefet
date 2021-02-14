package app;

import java.util.ArrayList;
import java.util.List;

public class Database {

    private List<Conta> listaDeContas = new ArrayList<>();

    public boolean add(Conta conta){
        //listaDeContas.contains(conta.getNumero());

        for (Conta contas: listaDeContas) {
            if (conta.getCliente().getCpf() == contas.getCliente().getCpf()){
                System.out.println("Cpf ja cadastrado em nosso banco de dados.\n");
                return false;
            }
        }
        System.out.println("Conta criada");
        listaDeContas.add(conta);
        return true;
    }
    Conta query(Conta conta){
        for (Conta contas: listaDeContas) {
            if(contas.getNumero() == conta.getNumero()){
                return contas;
            }
        }
        return null;
    }
    Conta query(int numero){
        for (Conta contas: listaDeContas) {
            if(contas.getNumero() == numero){
                return contas;
            }
        }
        return null;
    }

    void mostrarContas() {
        System.out.print( (listaDeContas.isEmpty() )? "Lista de contas vazias.\n" : "");

        for (Conta contas: listaDeContas) {
            System.out.println("Titular: " +contas.getCliente().getNome());
            System.out.println("Cpf: "+ contas.getCliente().getCpf());
            System.out.println("Numero: "+ contas.getNumero());
            System.out.printf("Saldo: %.2f", contas.getSaldo());

            System.out.println("\n------------\n");
        }
    }
}
