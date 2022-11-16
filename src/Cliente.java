public class Cliente {
    private String nomeCliente;
    private String cpf;

public Cliente (String nome, String cpf){
    this.nomeCliente = nome;
    this.cpf = cpf;
}
public String getNomeCliente(){
    return this.nomeCliente;
}
public String getCpf(){
    return this.cpf;
}
}

