/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import javax.swing.JOptionPane;
import singleton.MySqlConnection;

/**
 *
 * @author marip
 */
public class Periodico extends Geral{
    
    private int paginas;
    private String ISSN;  // 9 digitos
    private String tipo;  //revistas e jornais

    //Construtores
    public Periodico() {
       
    }

    public Periodico(int paginas, String ISSN, String tipo) {
        this.paginas = paginas;
        this.ISSN = ISSN;
        this.tipo = tipo;
    }

    public Periodico(int paginas, String ISSN, String tipo, String autores, String titulo, int edicao, String cidade, String editora, int ano, String CDU, int quantidade, String assunto, String palavra1, String palavra2, float tamanho, int id, int emprestimo) {
        super(autores, titulo, edicao, cidade, editora, ano, CDU, quantidade, assunto, palavra1, palavra2, tamanho, id, emprestimo);
        this.paginas = paginas;
        this.ISSN = ISSN;
        this.tipo = tipo;
    }

    //GET
    public int getPaginas() {
        return paginas;
    }

    public String getISSN() {
        return ISSN;
    }

    public String getTipo() {
        return tipo;
    }

    //SET
    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public void setISSN(String ISSN) {
        this.ISSN = ISSN;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    @Override
    public boolean equals(Object obj) {
        return ((Periodico) obj).hashCode() == hashCode();
    }
    
    public int hashCode() {
        int hash = 1;
        hash = 71 * hash + Objects.hashCode(this.getId());
        return hash;
    }
    
    public void cadastrarPeriodicoDB() throws ClassNotFoundException, SQLException {
        
        //Tabela 1 - Geral
        Connection conn = MySqlConnection.getInstance().getConnection();
        String sql = "insert into Geral (id, cdu, autores, titulo, edicao, cidade, editora, ano, assunto, palavra1, palavra2, tamanho, quantidade, emprestimo ) ";
        sql += "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, getId());
        ps.setString(2, getCDU());
        ps.setString(3, getAutores());
        ps.setString(4, getTitulo());
        ps.setInt(5, getEdicao());
        ps.setString(6, getCidade());
        ps.setString(7, getEditora());
        ps.setInt(8, getAno());
        ps.setString(9, getAssunto());
        ps.setString(10, getPalavra1());
        ps.setString(11, getPalavra2());
        ps.setFloat(12, getTamanho());
        ps.setInt(13, getQuantidade());
        ps.setInt(14, getEmprestimo());
        
        ps.execute();
        
        //Tabela 2 - 
        Connection conn2 = MySqlConnection.getInstance().getConnection();
        String sql2 = "insert into Periodico (id, issn, paginas, tipo)";
        sql2 += "values (?, ?, ?, ?)";
        
        PreparedStatement ps2 = conn2.prepareStatement(sql2);
        ps2.setInt(1, getId());
        ps2.setString(2, getISSN());
        ps2.setInt(3, getPaginas());
        ps2.setString(4, getTipo());
        ps2.execute();
    }
    
    public void excluirPeriodicoDB() throws ClassNotFoundException, SQLException {
        
        //Tabela 2 
        Connection conn2 = MySqlConnection.getInstance().getConnection();
        String sql2 = "delete from Periodico where id = ?";

        PreparedStatement ps2 = conn2.prepareStatement(sql2);
        ps2.setInt(1, getId());

        ps2.execute();
        
        //Tabela 1 - Geral
        Connection conn = MySqlConnection.getInstance().getConnection();
        String sql = "delete from Geral where id = ?";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, getId());
       
        ps.execute();
        
    }
    public static ArrayList<Periodico> getPeriodicosDB() throws ClassNotFoundException, SQLException {

        ArrayList<Periodico> listPeriodicos = new ArrayList<>();
        Connection conn;
        conn = MySqlConnection.getInstance().getConnection();
        String sql = "select * from Geral";
        PreparedStatement ps = conn.prepareStatement(sql);
        
        Connection conn2;
        conn2 = MySqlConnection.getInstance().getConnection();
        String sql2 = "select * from Periodico";
        PreparedStatement ps2 = conn2.prepareStatement(sql2);

        ResultSet rs = ps.executeQuery();
        ResultSet rs2 = ps2.executeQuery();
        
        while (rs2.next()) {
               
            Periodico periodico = new Periodico();
            int id = rs2.getInt("id");
            String issn = rs2.getString("issn");
            int paginas = rs2.getInt("paginas");
            String tipo = rs2.getString("tipo");
            periodico.setISSN(issn);
            periodico.setPaginas(paginas);
            periodico.setId(id);
            periodico.setTipo(tipo);
            
                while (rs.next()) { //Procurando a chave na tabela 1
                    
                    if(rs.getInt("id") == id){  //Verificando se as chaves são iguais
                    
                        String cdu = rs.getString("cdu");
                        String autores = rs.getString("autores");
                        String titulo = rs.getString("titulo");
                        int edicao = rs.getInt("edicao");
                        String cidade = rs.getString("cidade");
                        String editora = rs.getString("editora");
                        int ano = rs.getInt("ano");
                        String assunto = rs.getString("assunto");
                        String palavra1 = rs.getString("palavra1");
                        String palavra2 = rs.getString("palavra2");
                        float tamanho = rs.getFloat("tamanho");
                        int quantidade = rs.getInt("quantidade");
                        int emprestimo = rs.getInt("emprestimo");

                        periodico.setCDU(cdu);
                        periodico.setAutores(autores);
                        periodico.setTitulo(titulo);
                        periodico.setEdicao(edicao);
                        periodico.setCidade(cidade);
                        periodico.setEditora(editora);
                        periodico.setAno(ano);
                        periodico.setAssunto(assunto);
                        periodico.setPalavra1(palavra1);
                        periodico.setPalavra2(palavra2);
                        periodico.setTamanho(tamanho);
                        periodico.setQuantidade(quantidade);
                        periodico.setEmprestimo(emprestimo);

                        listPeriodicos.add(periodico);
                    }
                }
            
                
        }

        return listPeriodicos;

    }
}
