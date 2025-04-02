package model;

public class Funcionario extends Pessoa{
    private int salario;
    private String funcao;

    public Funcionario(String cpf, String nome, String endereco, int salario, String funcao) {
        super(cpf, nome, endereco);
        this.salario = salario;
        this.funcao = funcao;
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    @Override
    public String toString() {
        return super.toString() +
                ";" + salario +
                ";" + funcao + ";FUNCIONARIO";
    }
}
