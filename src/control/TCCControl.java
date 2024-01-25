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
import model.TCC;
import view.FTelaCadastroTCC;
/**
 *
 * @author marip
 */
public class TCCControl {
    
    private ArrayList<TCC> listTCCs;
    FTelaCadastroTCC tct;
    
    public TCCControl() {
        listTCCs = new ArrayList<>();
    }
    
    public ArrayList<TCC> getListTCC() {
        return listTCCs;
    }

    public void setListTCC(ArrayList<TCC> listTCCs) {
        this.listTCCs = listTCCs;
    }

    public FTelaCadastroTCC getTct() {
        return tct;
    }

    public void setTct(FTelaCadastroTCC tct) {
        this.tct = tct;
    }

    public void carregarTCCs() {
        obterTCCsDB();
        DefaultTableModel tb = (DefaultTableModel) getTct().gettTCCs().getModel();
        for (TCC tcc : listTCCs) {
            tb.addRow(new Object[]{tcc.getId(), tcc.getTitulo(),tcc.getQuantidade(), tcc.getEmprestimo()});
        }
    }

    private void obterTCCsDB() {
        try {
            listTCCs = TCC.getTCCsDB();
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(getTct(), "Erro ao acessar o banco de dados", "Erro", JOptionPane.ERROR_MESSAGE);
            System.out.println("Exception: " + ex.getMessage());
        }
    }
    
    private boolean validarCadastroTCC() {

        if (getTct().getTfTitulo().getText().isEmpty()) {
            return false;
        }
        if (getTct().getTfAutores().getText().isEmpty()) {
            return false;
        }
        if (getTct().getTfCidade().getText().isEmpty()) {
            return false;
        }
        if (getTct().getFtfAno().getText().isEmpty()) {
            return false;
        }
        if (getTct().getTfEdicao().getText().isEmpty()) {
            return false;
        }
        if (getTct().getTfPaginas().getText().isEmpty()) {
            return false;
        }
        if (getTct().getTfEditora().getText().isEmpty()) {
            return false;
        }
        if (getTct().getTfTamanho().getText().isEmpty()) {
            return false;
        }
        if (getTct().getTfQuantidade().getText().isEmpty()) {
            return false;
        }
        if (getTct().getTfAssunto().getText().isEmpty()) {
            return false;
        }
        if (getTct().getTfPalavra1().getText().isEmpty()) {
            return false;
        }
        if (getTct().getTfPalavra2().getText().isEmpty()) {
            return false;
        }
        if (getTct().getFtfCdu().getText().isEmpty()) {
            return false;
        }
        if (getTct().getTfid().getText().isEmpty()) {
            return false;
        }
        if (getTct().getTfEmprestimo().getText().isEmpty()) {
            return false;
        }
        if (getTct().getCbTipo().getSelectedIndex() < 0) {
            return false;
        }
        
        return true;
    }
    
    public void cadastrarTCC() {

        if (validarCadastroTCC()) {

            TCC tcc = new TCC();
            tcc.setAutores(getTct().getTfAutores().getText());
            tcc.setTitulo(getTct().getTfTitulo().getText());
            tcc.setCidade(getTct().getTfCidade().getText());
            tcc.setEditora(getTct().getTfEditora().getText());
            tcc.setCDU(getTct().getFtfCdu().getText());
            tcc.setAssunto(getTct().getTfAssunto().getText());
            tcc.setPalavra1(getTct().getTfPalavra1().getText());
            tcc.setPalavra2(getTct().getTfPalavra2().getText());
            tcc.setTipo((String) getTct().getCbTipo().getSelectedItem());
            
            //Conversão do tipo da variável
            int edicao = Integer.parseInt(getTct().getTfEdicao().getText());
            tcc.setEdicao(edicao);
            
            int ano = Integer.parseInt(getTct().getFtfAno().getText());
            tcc.setAno(ano);
            
            int quantidade = Integer.parseInt(getTct().getTfQuantidade().getText());
            tcc.setQuantidade(quantidade);
            
            float tamanho = Float.parseFloat(getTct().getTfTamanho().getText());
            tcc.setTamanho(tamanho);
            
            int emprestimo = Integer.parseInt(getTct().getTfEmprestimo().getText());
            tcc.setEmprestimo(emprestimo);
            
            int paginas = Integer.parseInt(getTct().getTfPaginas().getText());
            tcc.setPaginas(paginas);
            
            int id = Integer.parseInt(getTct().getTfid().getText());
            tcc.setId(id);
            
            DefaultTableModel tb = (DefaultTableModel) getTct().gettTCCs().getModel();
            tb.addRow(new Object[]{tcc.getId(), tcc.getTitulo(), tcc.getQuantidade(), tcc.getEmprestimo()});

            try {
                tcc.cadastrarTCCDB();
                listTCCs.add(tcc);
                limparCamposTCC();
            } catch (ClassNotFoundException | SQLException ex) {
                JOptionPane.showMessageDialog(getTct(), "Erro ao acessar o banco de dados", "Erro", JOptionPane.ERROR_MESSAGE);
                System.out.println("Exception: " + ex.getMessage());
            }

        } else {
            JOptionPane.showMessageDialog(getTct(), "Favor, entrar com todos os campos obrigatórios!", "Erro", JOptionPane.ERROR_MESSAGE);
        }

    }
    
    private void limparCamposTCC() {

        getTct().getTfid().setText("");
        getTct().getTfAutores().setText("");
        getTct().getFtfCdu().setText("");
        getTct().getTfTitulo().setText("");
        getTct().getTfEdicao().setText("");
        getTct().getTfCidade().setText("");
        getTct().getTfEditora().setText("");
        getTct().getFtfAno().setText("");
        getTct().getTfAssunto().setText("");
        getTct().getTfPalavra1().setText("");
        getTct().getTfPalavra2().setText("");
        getTct().getTfTamanho().setText("");
        getTct().getTfQuantidade().setText("");
        getTct().getTfEmprestimo().setText("");
        getTct().getTfPaginas().setText("");
        getTct().getCbTipo().setSelectedIndex(-1);
    }
    public void alterarTCC(){
       
        if (getTct().gettTCCs().getSelectedRow() > -1) {

            int identificador = (int) getTct().gettTCCs().getValueAt(getTct().gettTCCs().getSelectedRow(), 0);
            TCC tcc = new TCC();
            tcc.setId(identificador);
            
            int index = listTCCs.indexOf(tcc);
                
            tcc = listTCCs.get(index);  //Passando todos os dados

                if (index > -1) {
                    
                    //Convertendo os dados para passar pro TextField
                    String edicao = String.valueOf(tcc.getEdicao()) ;
                    String quantidade = String.valueOf(tcc.getQuantidade()) ;
                    String tamanho = String.valueOf(tcc.getTamanho()) ;
                    String emprestimo = String.valueOf(tcc.getEmprestimo()) ;
                    String paginas = String.valueOf(tcc.getPaginas()) ;
                    String id = String.valueOf(tcc.getId()) ;
                    String ano = String.valueOf(tcc.getAno());

                    //Passando os dados pro TextField
                    getTct().getTfid().setText(id);
                    getTct().getTfAutores().setText(tcc.getAutores());
                    getTct().getFtfCdu().setText(tcc.getCDU());
                    getTct().getTfTitulo().setText(tcc.getTitulo());
                    getTct().getTfEdicao().setText(edicao);
                    getTct().getTfCidade().setText(tcc.getCidade());
                    getTct().getTfEditora().setText(tcc.getEditora());
                    getTct().getFtfAno().setText(ano);
                    getTct().getTfAssunto().setText(tcc.getAssunto());
                    getTct().getTfPalavra1().setText(tcc.getPalavra1());
                    getTct().getTfPalavra2().setText(tcc.getPalavra2());
                    getTct().getTfTamanho().setText(tamanho);
                    getTct().getTfQuantidade().setText(quantidade);
                    getTct().getTfEmprestimo().setText(emprestimo);
                    getTct().getTfPaginas().setText(paginas);
                    
                    
                    listTCCs.set(index, tcc);  //Alterando a lista
                }
        }
    }
    public void excluirTCC() {

        if (getTct().gettTCCs().getSelectedRow() > -1) {
            
            int id = (int) getTct().gettTCCs().getValueAt(getTct().gettTCCs().getSelectedRow(), 0);
            TCC tcc = new TCC();
            tcc.setId(id);
            
            int index = listTCCs.indexOf(tcc);

            if (index > -1) {
                try {
                     tcc.excluirTCCDB();
                    listTCCs.remove(index);
                    ((DefaultTableModel) getTct().gettTCCs().getModel()).removeRow(getTct().gettTCCs().getSelectedRow());
                } catch (ClassNotFoundException | SQLException ex) {
                    JOptionPane.showMessageDialog(getTct(), "Erro ao acessar o banco de dados", "Erro", JOptionPane.ERROR_MESSAGE);
                    System.out.println("Exception: " + ex.getMessage());
                }
            }
        }
    }
}
