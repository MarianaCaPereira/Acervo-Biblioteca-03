/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Mapa;
import view.FTelaCadastroMapa;
/**
 *
 * @author marip
 */
public class MapaControl {
    
    private ArrayList<Mapa> listMapas;
    FTelaCadastroMapa tcm;
    
    public MapaControl() {
        listMapas = new ArrayList<>();
    }
    
    public ArrayList<Mapa> getListMapa() {
        return listMapas;
    }

    public void setListMapa(ArrayList<Mapa> listMapas) {
        this.listMapas = listMapas;
    }

    public FTelaCadastroMapa getTcm() {
        return tcm;
    }

    public void setTcm(FTelaCadastroMapa tcm) {
        this.tcm = tcm;
    }

    public void carregarMapas() {
        obterMapasDB();
        DefaultTableModel tb = (DefaultTableModel) getTcm().gettMapas().getModel();
        for (Mapa mapa : listMapas) {
            tb.addRow(new Object[]{mapa.getId(), mapa.getTitulo(),mapa.getQuantidade(), mapa.getEmprestimo()});
        }
    }

    private void obterMapasDB() {
        try {
            listMapas = Mapa.getMapasDB();
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(getTcm(), "Erro ao acessar o banco de dados", "Erro", JOptionPane.ERROR_MESSAGE);
            System.out.println("Exception: " + ex.getMessage());
        }
    }
    
    private boolean validarCadastroMapa() {

        if (getTcm().getTfTitulo().getText().isEmpty()) {
            return false;
        }
        if (getTcm().getTfAutores().getText().isEmpty()) {
            return false;
        }
        if (getTcm().getTfCidade().getText().isEmpty()) {
            return false;
        }
        if (getTcm().getFtfAno().getText().isEmpty()) {
            return false;
        }
        if (getTcm().getTfEdicao().getText().isEmpty()) {
            return false;
        }
        if (getTcm().getTfPaginas().getText().isEmpty()) {
            return false;
        }
        if (getTcm().getTfEditora().getText().isEmpty()) {
            return false;
        }
        if (getTcm().getTfTamanho().getText().isEmpty()) {
            return false;
        }
        if (getTcm().getTfQuantidade().getText().isEmpty()) {
            return false;
        }
        if (getTcm().getTfAssunto().getText().isEmpty()) {
            return false;
        }
        if (getTcm().getTfPalavra1().getText().isEmpty()) {
            return false;
        }
        if (getTcm().getTfPalavra2().getText().isEmpty()) {
            return false;
        }
        if (getTcm().getFtfCdu().getText().isEmpty()) {
            return false;
        }
        if (getTcm().getTfid().getText().isEmpty()) {
            return false;
        }
        if (getTcm().getTfEmprestimo().getText().isEmpty()) {
            return false;
        }

        return true;
    }
    
    public void cadastrarMapa() {

        if (validarCadastroMapa()) {

            Mapa mapa = new Mapa();
            mapa.setAutores(getTcm().getTfAutores().getText());
            mapa.setTitulo(getTcm().getTfTitulo().getText());
            mapa.setCidade(getTcm().getTfCidade().getText());
            mapa.setEditora(getTcm().getTfEditora().getText());
            mapa.setCDU(getTcm().getFtfCdu().getText());
            mapa.setAssunto(getTcm().getTfAssunto().getText());
            mapa.setPalavra1(getTcm().getTfPalavra1().getText());
            mapa.setPalavra2(getTcm().getTfPalavra2().getText());
         
            //Conversão do tipo da variável
            int edicao = Integer.parseInt(getTcm().getTfEdicao().getText());
            mapa.setEdicao(edicao);
            
            int ano = Integer.parseInt(getTcm().getFtfAno().getText());
            mapa.setAno(ano);
            
            int quantidade = Integer.parseInt(getTcm().getTfQuantidade().getText());
            mapa.setQuantidade(quantidade);
            
            float tamanho = Float.parseFloat(getTcm().getTfTamanho().getText());
            mapa.setTamanho(tamanho);
            
            int emprestimo = Integer.parseInt(getTcm().getTfEmprestimo().getText());
            mapa.setEmprestimo(emprestimo);
            
            int paginas = Integer.parseInt(getTcm().getTfPaginas().getText());
            mapa.setPaginas(paginas);
            
            int id = Integer.parseInt(getTcm().getTfid().getText());
            mapa.setId(id);
            
            DefaultTableModel tb = (DefaultTableModel) getTcm().gettMapas().getModel();
            tb.addRow(new Object[]{mapa.getId(), mapa.getTitulo(), mapa.getQuantidade(), mapa.getEmprestimo()});

            try {
                mapa.cadastrarMapaDB();
                listMapas.add(mapa);
                limparCamposMapa();
            } catch (ClassNotFoundException | SQLException ex) {
                JOptionPane.showMessageDialog(getTcm(), "Erro ao acessar o banco de dados", "Erro", JOptionPane.ERROR_MESSAGE);
                System.out.println("Exception: " + ex.getMessage());
            }

        } else {
            JOptionPane.showMessageDialog(getTcm(), "Favor, entrar com todos os campos obrigatórios!", "Erro", JOptionPane.ERROR_MESSAGE);
        }

    }
    
    private void limparCamposMapa() {

        getTcm().getTfid().setText("");
        getTcm().getTfAutores().setText("");
        getTcm().getFtfCdu().setText("");
        getTcm().getTfTitulo().setText("");
        getTcm().getTfEdicao().setText("");
        getTcm().getTfCidade().setText("");
        getTcm().getTfEditora().setText("");
        getTcm().getFtfAno().setText("");
        getTcm().getTfAssunto().setText("");
        getTcm().getTfPalavra1().setText("");
        getTcm().getTfPalavra2().setText("");
        getTcm().getTfTamanho().setText("");
        getTcm().getTfQuantidade().setText("");
        getTcm().getTfEmprestimo().setText("");
        getTcm().getTfPaginas().setText("");
    }
    public void alterarMapa(){
       
        if (getTcm().gettMapas().getSelectedRow() > -1) {

            int identificador = (int) getTcm().gettMapas().getValueAt(getTcm().gettMapas().getSelectedRow(), 0);
            Mapa mapa = new Mapa();
            mapa.setId(identificador);
            
            int index = listMapas.indexOf(mapa);
                
            mapa = listMapas.get(index);  //Passando todos os dados

                if (index > -1) {
                    
                    //Convertendo os dados para passar pro TextField
                    String edicao = String.valueOf(mapa.getEdicao()) ;
                    String quantidade = String.valueOf(mapa.getQuantidade()) ;
                    String tamanho = String.valueOf(mapa.getTamanho()) ;
                    String emprestimo = String.valueOf(mapa.getEmprestimo()) ;
                    String paginas = String.valueOf(mapa.getPaginas()) ;
                    String id = String.valueOf(mapa.getId()) ;
                    String ano = String.valueOf(mapa.getAno());

                    //Passando os dados pro TextField
                    getTcm().getTfid().setText(id);
                    getTcm().getTfAutores().setText(mapa.getAutores());
                    getTcm().getFtfCdu().setText(mapa.getCDU());
                    getTcm().getTfTitulo().setText(mapa.getTitulo());
                    getTcm().getTfEdicao().setText(edicao);
                    getTcm().getTfCidade().setText(mapa.getCidade());
                    getTcm().getTfEditora().setText(mapa.getEditora());
                    getTcm().getFtfAno().setText(ano);
                    getTcm().getTfAssunto().setText(mapa.getAssunto());
                    getTcm().getTfPalavra1().setText(mapa.getPalavra1());
                    getTcm().getTfPalavra2().setText(mapa.getPalavra2());
                    getTcm().getTfTamanho().setText(tamanho);
                    getTcm().getTfQuantidade().setText(quantidade);
                    getTcm().getTfEmprestimo().setText(emprestimo);
                    getTcm().getTfPaginas().setText(paginas);
                    
                    
                    listMapas.set(index, mapa);  //Alterando a lista
                }
        }
    }
    public void excluirMapa() {

        if (getTcm().gettMapas().getSelectedRow() > -1) {
            
            int id = (int) getTcm().gettMapas().getValueAt(getTcm().gettMapas().getSelectedRow(), 0);
            Mapa mapa = new Mapa();
            mapa.setId(id);
            
            int index = listMapas.indexOf(mapa);

            if (index > -1) {
                try {
                    mapa.excluirMapaDB();
                    listMapas.remove(index);
                    ((DefaultTableModel) getTcm().gettMapas().getModel()).removeRow(getTcm().gettMapas().getSelectedRow());
                } catch (ClassNotFoundException | SQLException ex) {
                    JOptionPane.showMessageDialog(getTcm(), "Erro ao acessar o banco de dados", "Erro", JOptionPane.ERROR_MESSAGE);
                    System.out.println("Exception: " + ex.getMessage());
                }
            }
        }
    }
}
