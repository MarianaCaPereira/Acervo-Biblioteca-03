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
import model.Periodico;
import view.FTelaCadastroPeriodico;
/**
 *
 * @author marip
 */
public class PeriodicoControl {
    
    private ArrayList<Periodico> listPeriodicos;
    FTelaCadastroPeriodico tcp;
    
    public PeriodicoControl() {
        listPeriodicos = new ArrayList<>();
    }
    
    public ArrayList<Periodico> getListPeriodico() {
        return listPeriodicos;
    }

    public void setListPeriodico(ArrayList<Periodico> listPeriodicos) {
        this.listPeriodicos = listPeriodicos;
    }

    public FTelaCadastroPeriodico getTcp() {
        return tcp;
    }

    public void setTcp(FTelaCadastroPeriodico tcp) {
        this.tcp = tcp;
    }

    public void carregarPeriodicos() {
        obterPeriodicosDB();
        DefaultTableModel tb = (DefaultTableModel) getTcp().gettPeriodicos().getModel();
        for (Periodico periodico : listPeriodicos) {
            tb.addRow(new Object[]{periodico.getId(), periodico.getTitulo(),periodico.getQuantidade(), periodico.getEmprestimo()});
        }
    }

    private void obterPeriodicosDB() {
        try {
            listPeriodicos = Periodico.getPeriodicosDB();
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(getTcp(), "Erro ao acessar o banco de dados", "Erro", JOptionPane.ERROR_MESSAGE);
            System.out.println("Exception: " + ex.getMessage());
        }
    }
    
    private boolean validarCadastroPeriodico() {

        if (getTcp().getTfTitulo().getText().isEmpty()) {
            return false;
        }
        if (getTcp().getTfAutores().getText().isEmpty()) {
            return false;
        }
        if (getTcp().getTfCidade().getText().isEmpty()) {
            return false;
        }
        if (getTcp().getFtfAno().getText().isEmpty()) {
            return false;
        }
        if (getTcp().getTfEdicao().getText().isEmpty()) {
            return false;
        }
        if (getTcp().getTfPaginas().getText().isEmpty()) {
            return false;
        }
        if (getTcp().getTfEditora().getText().isEmpty()) {
            return false;
        }
        if (getTcp().getTfTamanho().getText().isEmpty()) {
            return false;
        }
        if (getTcp().getTfQuantidade().getText().isEmpty()) {
            return false;
        }
        if (getTcp().getTfAssunto().getText().isEmpty()) {
            return false;
        }
        if (getTcp().getTfPalavra1().getText().isEmpty()) {
            return false;
        }
        if (getTcp().getTfPalavra2().getText().isEmpty()) {
            return false;
        }
        if (getTcp().getFtfissn().getText().isEmpty()) {
            return false;
        }
        if (getTcp().getFtfCdu().getText().isEmpty()) {
            return false;
        }
        if (getTcp().getTfid().getText().isEmpty()) {
            return false;
        }
        if (getTcp().getTfEmprestimo().getText().isEmpty()) {
            return false;
        }
        if (getTcp().getCbTipo().getSelectedIndex() < 0) {
            return false;
        }
        
        return true;
    }
    
    public void cadastrarPeriodico() {

        if (validarCadastroPeriodico()) {

            Periodico periodico = new Periodico();
            periodico.setAutores(getTcp().getTfAutores().getText());
            periodico.setTitulo(getTcp().getTfTitulo().getText());
            periodico.setCidade(getTcp().getTfCidade().getText());
            periodico.setEditora(getTcp().getTfEditora().getText());
            periodico.setCDU(getTcp().getFtfCdu().getText());
            periodico.setAssunto(getTcp().getTfAssunto().getText());
            periodico.setPalavra1(getTcp().getTfPalavra1().getText());
            periodico.setPalavra2(getTcp().getTfPalavra2().getText());
            periodico.setISSN(getTcp().getFtfissn().getText());
            periodico.setTipo((String) getTcp().getCbTipo().getSelectedItem());
            
            //Conversão do tipo da variável
            int edicao = Integer.parseInt(getTcp().getTfEdicao().getText());
            periodico.setEdicao(edicao);
            
            int ano = Integer.parseInt(getTcp().getFtfAno().getText());
            periodico.setAno(ano);
            
            int quantidade = Integer.parseInt(getTcp().getTfQuantidade().getText());
            periodico.setQuantidade(quantidade);
            
            float tamanho = Float.parseFloat(getTcp().getTfTamanho().getText());
            periodico.setTamanho(tamanho);
            
            int emprestimo = Integer.parseInt(getTcp().getTfEmprestimo().getText());
            periodico.setEmprestimo(emprestimo);
            
            int paginas = Integer.parseInt(getTcp().getTfPaginas().getText());
            periodico.setPaginas(paginas);
            
            int id = Integer.parseInt(getTcp().getTfid().getText());
            periodico.setId(id);
            
            DefaultTableModel tb = (DefaultTableModel) getTcp().gettPeriodicos().getModel();
            tb.addRow(new Object[]{periodico.getId(), periodico.getTitulo(), periodico.getQuantidade(), periodico.getEmprestimo()});

            try {
                periodico.cadastrarPeriodicoDB();
                listPeriodicos.add(periodico);
                limparCamposPeriodico();
            } catch (ClassNotFoundException | SQLException ex) {
                JOptionPane.showMessageDialog(getTcp(), "Erro ao acessar o banco de dados", "Erro", JOptionPane.ERROR_MESSAGE);
                System.out.println("Exception: " + ex.getMessage());
            }

        } else {
            JOptionPane.showMessageDialog(getTcp(), "Favor, entrar com todos os campos obrigatórios!", "Erro", JOptionPane.ERROR_MESSAGE);
        }

    }
    
    private void limparCamposPeriodico() {

        getTcp().getTfid().setText("");
        getTcp().getTfAutores().setText("");
        getTcp().getFtfCdu().setText("");
        getTcp().getTfTitulo().setText("");
        getTcp().getTfEdicao().setText("");
        getTcp().getTfCidade().setText("");
        getTcp().getTfEditora().setText("");
        getTcp().getFtfAno().setText("");
        getTcp().getTfAssunto().setText("");
        getTcp().getTfPalavra1().setText("");
        getTcp().getTfPalavra2().setText("");
        getTcp().getTfTamanho().setText("");
        getTcp().getTfQuantidade().setText("");
        getTcp().getTfEmprestimo().setText("");
        getTcp().getFtfissn().setText("");
        getTcp().getTfPaginas().setText("");
        getTcp().getCbTipo().setSelectedIndex(-1);
    }
    public void alterarPeriodico(){
       
        if (getTcp().gettPeriodicos().getSelectedRow() > -1) {

            int identificador = (int) getTcp().gettPeriodicos().getValueAt(getTcp().gettPeriodicos().getSelectedRow(), 0);
            Periodico periodico = new Periodico();
            periodico.setId(identificador);
            
            int index = listPeriodicos.indexOf(periodico);
                
            periodico = listPeriodicos.get(index);  //Passando todos os dados

                if (index > -1) {
                    
                    //Convertendo os dados para passar pro TextField
                    String edicao = String.valueOf(periodico.getEdicao()) ;
                    String quantidade = String.valueOf(periodico.getQuantidade()) ;
                    String tamanho = String.valueOf(periodico.getTamanho()) ;
                    String emprestimo = String.valueOf(periodico.getEmprestimo()) ;
                    String paginas = String.valueOf(periodico.getPaginas()) ;
                    String id = String.valueOf(periodico.getId()) ;
                    String ano = String.valueOf(periodico.getAno());

                    //Passando os dados pro TextField
                    getTcp().getTfid().setText(id);
                    getTcp().getTfAutores().setText(periodico.getAutores());
                    getTcp().getFtfCdu().setText(periodico.getCDU());
                    getTcp().getTfTitulo().setText(periodico.getTitulo());
                    getTcp().getTfEdicao().setText(edicao);
                    getTcp().getTfCidade().setText(periodico.getCidade());
                    getTcp().getTfEditora().setText(periodico.getEditora());
                    getTcp().getFtfAno().setText(ano);
                    getTcp().getTfAssunto().setText(periodico.getAssunto());
                    getTcp().getTfPalavra1().setText(periodico.getPalavra1());
                    getTcp().getTfPalavra2().setText(periodico.getPalavra2());
                    getTcp().getTfTamanho().setText(tamanho);
                    getTcp().getTfQuantidade().setText(quantidade);
                    getTcp().getTfEmprestimo().setText(emprestimo);
                    getTcp().getFtfissn().setText(periodico.getISSN());
                    getTcp().getTfPaginas().setText(paginas);
      
                    
                    listPeriodicos.set(index, periodico);  //Alterando a lista
                }
        }
    }
    public void excluirPeriodico() {

        if (getTcp().gettPeriodicos().getSelectedRow() > -1) {
            
            int id = (int) getTcp().gettPeriodicos().getValueAt(getTcp().gettPeriodicos().getSelectedRow(), 0);
            Periodico periodico = new Periodico();
             periodico.setId(id);
            
            int index = listPeriodicos.indexOf( periodico);

            if (index > -1) {
                try {
                     periodico.excluirPeriodicoDB();
                    listPeriodicos.remove(index);
                    ((DefaultTableModel) getTcp().gettPeriodicos().getModel()).removeRow(getTcp().gettPeriodicos().getSelectedRow());
                } catch (ClassNotFoundException | SQLException ex) {
                    JOptionPane.showMessageDialog(getTcp(), "Erro ao acessar o banco de dados", "Erro", JOptionPane.ERROR_MESSAGE);
                    System.out.println("Exception: " + ex.getMessage());
                }
            }
        }
    }
}
