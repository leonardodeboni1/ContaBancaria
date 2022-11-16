

import java.util.Scanner;

public class CaixaEletronico {
    ContaBancaria[] contasAtivas;
    private Scanner entrada;
    private int contasCriadas;

public CaixaEletronico(){
    contasAtivas = new ContaBancaria[2];
    entrada = new Scanner(System.in);
    contasCriadas = 0;
}

//metodos auxiliares    
    private String pedirNome(){
        System.out.println ("Informe nome do(a) Cliente:"); 
        String nome = entrada.nextLine();
        return nome;
    }
    private String pedirCpf(){
        System.out.println ("Informe cpf do(a) Cliente:"); 
        String cpf = entrada.nextLine();
        return cpf;
    }
    private int pedirLimite(){
        System.out.println ("Informe limite do(a) Cliente:"); 
        int cpf =Integer.parseInt(entrada.nextLine());
        return cpf;
    }
    private double pedirSaldo(){
        System.out.println ("Informe saldo inicial do(a) Cliente:"); 
        int saldo =Integer.parseInt(entrada.nextLine());
        return saldo;
    }
    private ContaBancaria buscarConta(int numeroConta){
        for (ContaBancaria buscaConta : contasAtivas) {
            if (buscaConta != null) {
                if (buscaConta.getNumeroConta()== numeroConta) {
                    return buscaConta;
                }
            }
        }
        return null;
    }
    
    
//metodos envolvendo menu     
    public void exibirMenu(){
        System.out.println (" 1: Criar conta");
        System.out.println (" 2: Consultar Saldo");
        System.out.println (" 3: Depositar");
        System.out.println (" 4: Sacar");
        System.out.println (" 5: Transferir");
        System.out.println (" 6: sair");
    }         
    public void executar(){
        int opcao;
        do {
            exibirMenu();
            System.out.println("\nDigite sua opção: ");
            opcao = Integer.parseInt(entrada.nextLine());
            escolher(opcao);
        } while (opcao!=6);
    }
    public void escolher(int opcao){
        switch (opcao){
            case 1:
                criarConta();
                break;
            case 2:
                consultarSaldo();
                break;
            case 3:
                depositarConta();
                break;
            case 4:
                sacarConta();
                break;
            case 5:
                transferirConta();
                break;
            case 6:
                System.out.println ("Adeus");
                break;
            default:
                System.out.println ("Opcao invalida, selecione novamente");
 
        }
    }   
 //fim menu
 //criacao de conta
    public void criarConta(){
        int opcao;
        double saldo;
        if (contasCriadas == contasAtivas.length){
            System.out.println("Limite atingido. Impossivel criar \n");
        }
        else{
        String nome = pedirNome();
        String cpf = pedirCpf();
        Cliente cliente = new Cliente (nome, cpf);
        int limite = pedirLimite();
        
        System.out.println("A conta possuira saldo inicial? S/N \n");
        System.out.println("Digite 1 para sim e 0 para nao ");
        

        
            opcao = Integer.parseInt(entrada.nextLine());
            while ( opcao != 1 && opcao !=0){
                System.out.println ("Opcao invalida. Insira novamente \n");
            }

            if (opcao == 1){
                saldo = pedirSaldo();
                contasAtivas[(contasCriadas)] = new ContaBancaria(saldo, cliente, limite);
                System.out.println("Conta " + contasAtivas[contasCriadas].getNumeroConta()+" criada com sucesso \n");
            }
            else if (opcao == 0) {
                contasAtivas[contasCriadas] = new ContaBancaria(cliente, limite);
                System.out.println("Conta " + contasAtivas[contasCriadas].getNumeroConta()+" criada com sucesso \n");
            }
            else{
                System.out.println(" Opcao invalida \n");
            }
            contasCriadas ++;
        
        }
    }
      

//tratamento de contas
        public void consultarSaldo(){
            int valor;
            System.out.println ("Informe o numero da conta \n");
            valor = Integer.parseInt(entrada.nextLine());
            ContaBancaria contaConsulta = buscarConta(valor);
            if (contaConsulta != null){
                Cliente auxilio = contaConsulta.getDadoCliente();
                System.out.println(" Nome do cliente: " +auxilio.getNomeCliente());
                System.out.println(" Cpf do cliente: " +auxilio.getCpf());
                System.out.println(" O saldo atual eh: " +contaConsulta.getSaldo() + "\n");
            }
            else {
                System.out.println("Conta nao encontrada \n");
            }
        }
        public void depositarConta(){
            int valor;
            System.out.println ("Informe o numero da conta \n");
            valor = Integer.parseInt(entrada.nextLine());
            ContaBancaria contaConsulta = buscarConta(valor);
            if (contaConsulta != null){
                System.out.println ("Informe o valor a ser depositado \n");
                valor = Integer.parseInt(entrada.nextLine());
                contaConsulta.depositar(valor);
            }
            else {
                System.out.println("Conta nao encontrada \n");
        }
        }
        public void sacarConta(){
            int valor;
            System.out.println ("Informe o numero da conta \n");
            valor = Integer.parseInt(entrada.nextLine());
            ContaBancaria contaConsulta = buscarConta(valor);
            if (contaConsulta != null){
                System.out.println ("Informe o valor para saque \n");
                valor = Integer.parseInt(entrada.nextLine());
                contaConsulta.depositar(valor);
            }
            else {
                System.out.println("Conta nao encontrada \n");
        }   
        }
        public void transferirConta(){
            int valor;
            System.out.println ("Informe o numero da conta de transferencia \n");
            valor = Integer.parseInt(entrada.nextLine());
            ContaBancaria contaConsulta1 = buscarConta(valor);
            if (contaConsulta1 != null){
                System.out.println ("Informe o numero da conta recebedora \n");
                valor = Integer.parseInt(entrada.nextLine());
                ContaBancaria contaConsulta2 = buscarConta(valor);
                if (contaConsulta2 != null){
                    System.out.println ("Informe o valor a ser transferido \n");
                    valor = Integer.parseInt(entrada.nextLine());
                    if(contaConsulta1.getSaldo()- valor >= contaConsulta1.getLimite()){
                        
                        contaConsulta1.sacar(valor);
                        contaConsulta2.depositar(valor);
                    }else{
                        System.out.println("Saldo insuficiente. Transferencia invalida \n");
                    }
                }else{
                    System.out.println ("Conta Invalida. Falha na transferencia \n");
                }
            }else{
                System.out.println ("Conta Invalida. Falha na transferencia \n");
                }
                
            }
 
            
        

}


