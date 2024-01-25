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
import model.Midia;
import view.FTelaCadastroMidia;
/**
 *
 * @author marip
 */
public class MidiaControl {
    
    private ArrayList<Midia> listMidias;
    FTelaCadastroMidia tcmi;
    
    public MidiaControl() {
        listMidias = new ArrayList<>();
    }
    
    public ArrayList<Midia> getListMidia() {
        return listMidias;
    }

    public void setListMidia(ArrayList<Midia> listMidias) {
        this.listMidias = listMidias;
    }

    public FTelaCadastroMidia getTcmi() {
        return tcmi;
    }

    public void setTcmi(FTelaCadastroMidia tcmi) {
        this.tcmi = tcmi;
    }

    public void carregarMidias() {
        obterMidiasDB();
        DefaultTableModel tb = (DefaultTableModel) getTcmi().gettMidias().getModel();
        for (Midia midia : listMidias) {
            tb.addRow(new Object[]{midia.getId(), midia.getTitulo(),midia.getQuantidade(), midia.getEmprestimo()});
        }
    }

    private void obterMidiasDB() {
        try {
            listMidias = Midia.getMidiasDB();
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(getTcmi(), "Erro ao acessar o banco de dados", "Erro", JOptionPane.ERROR_MESSAGE);
            System.out.println("Exception: " + ex.getMessage());
        }
    }
    
    private boolean validarCadastroMidia() {

        if (getTcmi().getTfTitulo().getText().isEmpty()) {
            return false;
        }
        if (getTcmi().getTfAutores().getText().isEmpty()) {
            return false;
        }
        if (getTcmi().getTfCidade().getText().isEmpty()) {
            return false;
        }
        if (getTcmi().getFtfAno().getText().isEmpty()) {
            return false;
        }
        if (getTcmi().getTfEdicao().getText().isEmpty()) {
            return false;
        }
        if (getTcmi().getTfEditora().getText().isEmpty()) {
            return false;
        }
        if (getTcmi().getTfTamanho().getText().isEmpty()) {
            return false;
        }
        if (getTcmi().getTfQuantidade().getText().isEmpty()) {
            return false;
        }
        if (getTcmi().getTfAssunto().getText().isEmpty()) {
            return false;
        }
        if (getTcmi().getTfPalavra1().getText().isEmpty()) {
            return false;
        }
        if (getTcmi().getTfPalavra2().getText().isEmpty()) {
            return false;
        }
        if (getTcmi().getFtfCdu().getText().isEmpty()) {
            return false;
        }
        if (getTcmi().getTfid().getText().isEmpty()) {
            return false;
        }
        if (getTcmi().getTfEmprestimo().getText().isEmpty()) {
            return false;
        }
        if (getTcmi().getCbTipo().getSelectedIndex() < 0) {
            return false;
        }
        
        return true;
    }
    
    public void cadastrarMidia() {

        if (validarCadastroMidia()) {

            Midia midia = new Midia();
            midia.setAutores(getTcmi().getTfAutores().getText());
            midia.setTitulo(getTcmi().getTfTitulo().getText());
            midia.setCidade(getTcmi().getTfCidade().getText());
            midia.setEditora(getTcmi().getTfEditora().getText());
            midia.setCDU(getTcmi().getFtfCdu().getText());
            midia.setAssunto(getTcmi().getTfAssunto().getText());
            midia.setPalavra1(getTcmi().getTfPalavra1().getText());
            midia.setPalavra2(getTcmi().getTfPalavra2().getText());
            midia.setTipo((String) getTcmi().getCbTipo().getSelectedItem());
            
            //Conversão do tipo da variável
            int edicao = Integer.parseInt(getTcmi().getTfEdicao().getText());
            midia.setEdicao(edicao);
            
            int ano = Integer.parseInt(getTcmi().getFtfAno().getText());
            midia.setAno(ano);
            
            int quantidade = Integer.parseInt(getTcmi().getTfQuantidade().getText());
            midia.setQuantidade(quantidade);
            
            float tamanho = Float.parseFloat(getTcmi().getTfTamanho().getText());
            midia.setTamanho(tamanho);
            
            int emprestimo = Integer.parseInt(getTcmi().getTfEmprestimo().getText());
            midia.setEmprestimo(emprestimo);
            
            int id = Integer.parseInt(getTcmi().getTfid().getText());
            midia.setId(id);
            
            DefaultTableModel tb = (DefaultTableModel) getTcmi().gettMidias().getModel();
            tb.addRow(new Object[]{midia.getId(), midia.getTitulo(), midia.getQuantidade(), midia.getEmprestimo()});

            try {
                midia.cadastrarMidiaDB();
                listMidias.add(midia);
                limparCamposMidia();
            } catch (ClassNotFoundException | SQLException ex) {
                JOptionPane.showMessageDialog(getTcmi(), "Erro ao acessar o banco de dados", "Erro", JOptionPane.ERROR_MESSAGE);
                System.out.println("Exception: " + ex.getMessage());
            }

        } else {
            JOptionPane.showMessageDialog(getTcmi(), "Favor, entrar com todos os campos obrigatórios!", "Erro", JOptionPane.ERROR_MESSAGE);
        }

    }
    
    private void limparCamposMidia() {

        getTcmi().getTfid().setText("");
        getTcmi().getTfAutores().setText("");
        getTcmi().getFtfCdu().setText("");
        getTcmi().getTfTitulo().setText("");
        getTcmi().getTfEdicao().setText("");
        getTcmi().getTfCidade().setText("");
        getTcmi().getTfEditora().setText("");
        getTcmi().getFtfAno().setText("");
        getTcmi().getTfAssunto().setText("");
        getTcmi().getTfPalavra1().setText("");
        getTcmi().getTfPalavra2().setText("");
        getTcmi().getTfTamanho().setText("");
        getTcmi().getTfQuantidade().setText("");
        getTcmi().getTfEmprestimo().setText("");
        getTcmi().getCbTipo().setSelectedIndex(-1);
    }
    public void alterarMidia(){
       
        if (getTcmi().gettMidias().getSelectedRow() > -1) {

            int identificador = (int) getTcmi().gettMidias().getValueAt(getTcmi().gettMidias().getSelectedRow(), 0);
            Midia midia = new Midia();
            midia.setId(identificador);
            
            int index = listMidias.indexOf(midia);
                
            midia = listMidias.get(index);  //Passando todos os dados

                if (index > -1) {
                    
                    //Convertendo os dados para passar pro TextField
                    String edicao = String.valueOf(midia.getEdicao()) ;
                    String quantidade = String.valueOf(midia.getQuantidade()) ;
                    String tamanho = String.valueOf(midia.getTamanho()) ;
                    String emprestimo = String.valueOf(midia.getEmprestimo()) ;
                    String id = String.valueOf(midia.getId()) ;
                    String ano = String.valueOf(midia.getAno());

                    //Passando os dados pro TextField
                    getTcmi().getTfid().setText(id);
                    getTcmi().getTfAutores().setText(midia.getAutores());
                    getTcmi().getFtfCdu().setText(midia.getCDU());
                    getTcmi().getTfTitulo().setText(midia.getTitulo());
                    getTcmi().getTfEdicao().setText(edicao);
                    getTcmi().getTfCidade().setText(midia.getCidade());
                    getTcmi().getTfEditora().setText(midia.getEditora());
                    getTcmi().getFtfAno().setText(ano);
                    getTcmi().getTfAssunto().setText(midia.getAssunto());
                    getTcmi().getTfPalavra1().setText(midia.getPalavra1());
                    getTcmi().getTfPalavra2().setText(midia.getPalavra2());
                    getTcmi().getTfTamanho().setText(tamanho);
                    getTcmi().getTfQuantidade().setText(quantidade);
                    getTcmi().getTfEmprestimo().setText(emprestimo);
                    
                    listMidias.set(index, midia);  //Alterando a lista
                }
        }
    }
    public void excluirMidia() {

        if (getTcmi().gettMidias().getSelectedRow() > -1) {
            
            int id = (int) getTcmi().gettMidias().getValueAt(getTcmi().gettMidias().getSelectedRow(), 0);
            Midia midia = new Midia();
            midia.setId(id);
            
            int index = listMidias.indexOf(midia);

            if (index > -1) {
                try {
                     midia.excluirMidiaDB();
                    listMidias.remove(index);
                    ((DefaultTableModel) getTcmi().gettMidias().getModel()).removeRow(getTcmi().gettMidias().getSelectedRow());
                } catch (ClassNotFoundException | SQLException ex) {
                    JOptionPane.showMessageDialog(getTcmi(), "Erro ao acessar o banco de dados", "Erro", JOptionPane.ERROR_MESSAGE);
                    System.out.println("Exception: " + ex.getMessage());
                }
            }
        }
    }
}
