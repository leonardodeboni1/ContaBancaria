

public class ContaBancaria {
   private int saldo;
   private int  limite;
   private int numeroConta;
   private Cliente cliente;
   private static int contas = 100;

   public ContaBancaria(Cliente cliente, int limite){
    this(0, cliente, limite);
    
    
   }
   public ContaBancaria(int saldoInicial, Cliente cliente, int limite){
    this.saldo = saldoInicial;
    this.cliente = cliente;
    this.limite = limite*(-1);
    this.numeroConta = contas;
    contas++;
   }

   public boolean sacar(int valor ){
    if (this.saldo - valor >= limite){
        this.saldo -=valor;
        return true;
    }
    else{
        return false;
    }
   }
   public void depositar(int valor){
    this.saldo += valor;
   }
   public int getNumeroConta(){
    return this.numeroConta;
   }
   public double getSaldo(){
    return this.saldo;
   }
   

   public int getLimite(){
    return this.limite;

   }
   public Cliente getDadoCliente(){
    return this.cliente;
   }
   

   }


