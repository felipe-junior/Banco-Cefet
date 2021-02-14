package app;

import java.util.ArrayList;
import java.util.List;

public class Conta {
    private int numero;
    private int tipo;
    private double saldo;
    private Cliente cliente;
    private List<HistoricoTransacoes> transacoes = new ArrayList<HistoricoTransacoes>();

    public Conta(int numero, int tipo, double saldo, Cliente cliente) {
        this.numero = numero;
        this.tipo = tipo;
        this.saldo = saldo;
        this.cliente = cliente;
    }


    public int getNumero() {
        return numero;
    }

    public int getTipo() {
        return tipo;
    }

    public double getSaldo() {
        return saldo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    //Procedimentos
    void depositar(double quantidade) {
        quantidade =Math.abs(quantidade);
        saldo += quantidade;
       // System.out.printf("O valor foi depositado com sucesso.\nNovo saldo: R$ %.2f .\n", getSaldo());
    }
    void sacar(double quantidade){
        quantidade = Math.abs(quantidade);
        if(getSaldo() - quantidade <0)
            System.out.printf("Saldo insuficiente, a operacao foi cancelada!\nSaldo atual: R$ %.2f\n", getSaldo());
        else{
            saldo -= quantidade;
            System.out.printf("Saque realizado!\nNovo saldo: R$ %.2f .\n", getSaldo());
            //add transacoes
        }
    }
    void transferirPara(int numero, double quantidade, Database database){
        quantidade = Math.abs(quantidade);
        Conta destino = database.query(numero);
        if(this.equals(destino))
            System.out.println("A transferencia precisa ser feita entre diferentes contas. Operacao cancelada!");
        else{

            if(destino!=null) {
                saldo -= quantidade;
                destino.depositar(quantidade);
                System.out.printf("O valor R$ %.2f foi transferido da sua conta para a conta %d do titular %s\n", quantidade, destino.numero, destino.cliente.getNome());
                System.out.printf("Saldo anterior: %.2f -> Saldo Atual: %.2f\n", getSaldo() + quantidade, getSaldo());
            } else{
                System.out.println("Erro na Transferencia: Conta nao cadastrada no nosso sistema.");
            }
        }
    }
}
