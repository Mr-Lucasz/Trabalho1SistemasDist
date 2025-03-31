package model;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private String nome;
    private String endereco;
    private int quartos;
    private int vagas;
    private double classificacao;
    List<Pessoa> pessoas;

    public Hotel(String nome, String endereco, int quartos, int vagas, double classificacao) {
        this.nome = nome;
        this.endereco = endereco;
        this.quartos = quartos;
        this.vagas = vagas;
        this.classificacao = classificacao;
        pessoas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getQuartos() {
        return quartos;
    }

    public void setQuartos(int quartos) {
        this.quartos = quartos;
    }

    public int getVagas() {
        return vagas;
    }

    public void setVagas(int vagas) {
        this.vagas = vagas;
    }

    public double getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(double classificacao) {
        this.classificacao = classificacao;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    public void addCliente(Cliente cliente){
        pessoas.add(cliente);
    }
    public boolean removeCliente(String cpf){
        Cliente cliente = getCliente(cpf);
        if(cliente != null)
        {
            pessoas.remove(cliente);
            return true;
        }else
            return false;
    }
    public void addFuncionario(Funcionario funcionario){
        pessoas.add(funcionario);
    }
    public boolean removeFuncionario(String cpf){
        Funcionario funcionario = getFuncionario(cpf);
        if(funcionario != null)
        {
            pessoas.remove(funcionario);
            return true;
        }else
            return false;
    }
    public Funcionario getFuncionario(String cpf) {
        for (Pessoa pessoa : pessoas)
        {
            if (pessoa.getCpf().equals(cpf) && pessoa instanceof Funcionario)
                return (Funcionario) pessoa;
        }
        return null;
    }
    public Cliente getCliente(String cpf) {
        for (Pessoa pessoa : pessoas)
        {
            if (pessoa.getCpf().equals(cpf) && pessoa instanceof Cliente)
                return (Cliente) pessoa;
        }
        return null;
    }

    @Override
    public String toString() {
        return "NOME: " + nome + "  ENDERECO: " + endereco + "  QUARTOS: " + quartos + "  VAGAS: " + vagas + "  CLASSIFICAÇÃO: " + classificacao;
    }
}
