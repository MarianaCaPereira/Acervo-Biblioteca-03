/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


public abstract class Geral {
    
    private String autores;
    private String titulo;
    private int edicao;   
    private String cidade;
    private String editora;
    private int ano;
    private String CDU; //13 digitos
    private int quantidade;
    private String assunto;
    private String palavra1;
    private String palavra2;
    private float tamanho;
    private int id; //Identificador de item
    private int emprestimo; //Itens emprestados
    
    //Construtores
    public Geral(){
    }

    public Geral(String autores, String titulo, int edicao, String cidade, String editora, int ano, String CDU, int quantidade, String assunto, String palavra1, String palavra2, float tamanho, int id, int emprestimo) {
        this.autores = autores;
        this.titulo = titulo;
        this.edicao = edicao;
        this.cidade = cidade;
        this.editora = editora;
        this.ano = ano;
        this.CDU = CDU;
        this.quantidade = quantidade;
        this.assunto = assunto;
        this.palavra1 = palavra1;
        this.palavra2 = palavra2;
        this.tamanho = tamanho;
        this.id = id;
        this.emprestimo = emprestimo;
    }

    //GET
    public String getAutores() {
        return autores;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getEdicao() {
        return edicao;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEditora() {
        return editora;
    }

    public int getAno() {
        return ano;
    }

    public String getCDU() {
        return CDU;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public String getAssunto() {
        return assunto;
    }

    public String getPalavra1() {
        return palavra1;
    }

    public String getPalavra2() {
        return palavra2;
    }

    public float getTamanho() {
        return tamanho;
    }

    public int getId() {
        return id;
    }

    public int getEmprestimo() {
        return emprestimo;
    }
    
    //SET
    public void setAutores(String autores) {
        this.autores = autores;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setEdicao(int edicao) {
        this.edicao = edicao;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public void setCDU(String CDU) {
        this.CDU = CDU;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public void setPalavra1(String palavra1) {
        this.palavra1 = palavra1;
    }

    public void setPalavra2(String palavra2) {
        this.palavra2 = palavra2;
    }

    public void setTamanho(float tamanho) {
        this.tamanho = tamanho;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmprestimo(int emprestimo) {
        this.emprestimo = emprestimo;
    }
   
}
