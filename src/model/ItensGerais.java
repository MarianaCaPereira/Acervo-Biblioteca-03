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
/**
 *
 * @author marip
 */
public class ItensGerais extends Geral{      
    
    
    
    public ItensGerais() {    
    }

    //Classe Criada para ter uma lista de apenas com os itens da classe Geral.
    public ItensGerais(String autores, String titulo, int edicao, String cidade, String editora, int ano, String CDU, int quantidade, String assunto, String palavra1, String palavra2, float tamanho, int id, int emprestimo) {
        super(autores, titulo, edicao, cidade, editora, ano, CDU, quantidade, assunto, palavra1, palavra2, tamanho, id, emprestimo);
    }
    
    @Override
    public boolean equals(Object obj) {
        return ((ItensGerais) obj).hashCode() == hashCode();
    }
    
    public int hashCode() {
        int hash = 1;
        hash = 71 * hash + Objects.hashCode(this.getId());
        return hash;
    }
    
    public void AtualizarItemDB() throws ClassNotFoundException, SQLException {
        
       
        //Tabela 1 - Geral
        Connection conn = MySqlConnection.getInstance().getConnection();
        String sql = "update Geral set emprestimo = ? where id = ?";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, getEmprestimo());
        ps.setInt(2, getId());
       
        ps.execute();
        
    }
    
    public static ArrayList<ItensGerais> getItensGeraisDB() throws ClassNotFoundException, SQLException {

        ArrayList<ItensGerais> listItensGerais = new ArrayList<>();
        Connection conn;
        conn = MySqlConnection.getInstance().getConnection();
        String sql = "select * from Geral";
        PreparedStatement ps = conn.prepareStatement(sql);
        
        ResultSet rs = ps.executeQuery();
        
         while (rs.next()) { //Procurando os itens da tabela Geral
            
            ItensGerais itensgerais = new ItensGerais();
                    
                int id = rs.getInt("id");   
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
                
                itensgerais.setId(id);
                itensgerais.setCDU(cdu);
                itensgerais.setAutores(autores);
                itensgerais.setTitulo(titulo);
                itensgerais.setEdicao(edicao);
                itensgerais.setCidade(cidade);
                itensgerais.setEditora(editora);
                itensgerais.setAno(ano);
                itensgerais.setAssunto(assunto);
                itensgerais.setPalavra1(palavra1);
                itensgerais.setPalavra2(palavra2);
                itensgerais.setTamanho(tamanho);
                itensgerais.setQuantidade(quantidade);
                itensgerais.setEmprestimo(emprestimo);

                listItensGerais.add(itensgerais);       
        }
         
        return listItensGerais;
    }
  
}
