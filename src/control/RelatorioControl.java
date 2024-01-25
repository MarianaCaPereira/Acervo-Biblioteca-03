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
import model.Relatorio;
import view.FTelaCadastroRelatorio;
/**
 *
 * @author marip
 */
public class RelatorioControl {
    
    private ArrayList<Relatorio> listRelatorios;
    FTelaCadastroRelatorio tcr;
    
    public RelatorioControl() {
        listRelatorios = new ArrayList<>();
    }
    
    public ArrayList<Relatorio> getListRelatorio() {
        return listRelatorios;
    }

    public void setListRelatorio(ArrayList<Relatorio> listRelatorios) {
        this.listRelatorios = listRelatorios;
    }

    public FTelaCadastroRelatorio getTcr() {
        return tcr;
    }

    public void setTcr(FTelaCadastroRelatorio tcr) {
        this.tcr = tcr;
    }

    public void carregarRelatorios() {
        obterRelatoriosDB();
        DefaultTableModel tb = (DefaultTableModel) getTcr().gettRelatorios().getModel();
        for (Relatorio relatorio : listRelatorios) {
            tb.addRow(new Object[]{relatorio.getId(), relatorio.getTitulo(),relatorio.getQuantidade(), relatorio.getEmprestimo()});
        }
    }

    private void obterRelatoriosDB() {
        try {
            listRelatorios = Relatorio.getRelatoriosDB();
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(getTcr(), "Erro ao acessar o banco de dados", "Erro", JOptionPane.ERROR_MESSAGE);
            System.out.println("Exception: " + ex.getMessage());
        }
    }
    
    private boolean validarCadastroRelatorio() {

        if (getTcr().getTfTitulo().getText().isEmpty()) {
            return false;
        }
        if (getTcr().getTfAutores().getText().isEmpty()) {
            return false;
        }
        if (getTcr().getTfCidade().getText().isEmpty()) {
            return false;
        }
        if (getTcr().getFtfAno().getText().isEmpty()) {
            return false;
        }
        if (getTcr().getTfEdicao().getText().isEmpty()) {
            return false;
        }
        if (getTcr().getTfPaginas().getText().isEmpty()) {
            return false;
        }
        if (getTcr().getTfEditora().getText().isEmpty()) {
            return false;
        }
        if (getTcr().getTfTamanho().getText().isEmpty()) {
            return false;
        }
        if (getTcr().getTfQuantidade().getText().isEmpty()) {
            return false;
        }
        if (getTcr().getTfAssunto().getText().isEmpty()) {
            return false;
        }
        if (getTcr().getTfPalavra1().getText().isEmpty()) {
            return false;
        }
        if (getTcr().getTfPalavra2().getText().isEmpty()) {
            return false;
        }
        if (getTcr().getFtfCdu().getText().isEmpty()) {
            return false;
        }
        if (getTcr().getTfid().getText().isEmpty()) {
            return false;
        }
        if (getTcr().getTfEmprestimo().getText().isEmpty()) {
            return false;
        }

        return true;
    }
    
    public void cadastrarRelatorio() {

        if (validarCadastroRelatorio()) {

            Relatorio relatorio = new Relatorio();
            relatorio.setAutores(getTcr().getTfAutores().getText());
            relatorio.setTitulo(getTcr().getTfTitulo().getText());
            relatorio.setCidade(getTcr().getTfCidade().getText());
            relatorio.setEditora(getTcr().getTfEditora().getText());
            relatorio.setCDU(getTcr().getFtfCdu().getText());
            relatorio.setAssunto(getTcr().getTfAssunto().getText());
            relatorio.setPalavra1(getTcr().getTfPalavra1().getText());
            relatorio.setPalavra2(getTcr().getTfPalavra2().getText());
         
            //Conversão do tipo da variável
            int edicao = Integer.parseInt(getTcr().getTfEdicao().getText());
            relatorio.setEdicao(edicao);
            
            int ano = Integer.parseInt(getTcr().getFtfAno().getText());
            relatorio.setAno(ano);
            
            int quantidade = Integer.parseInt(getTcr().getTfQuantidade().getText());
            relatorio.setQuantidade(quantidade);
            
            float tamanho = Float.parseFloat(getTcr().getTfTamanho().getText());
            relatorio.setTamanho(tamanho);
            
            int emprestimo = Integer.parseInt(getTcr().getTfEmprestimo().getText());
            relatorio.setEmprestimo(emprestimo);
            
            int paginas = Integer.parseInt(getTcr().getTfPaginas().getText());
            relatorio.setPaginas(paginas);
            
            int id = Integer.parseInt(getTcr().getTfid().getText());
            relatorio.setId(id);
            
            DefaultTableModel tb = (DefaultTableModel) getTcr().gettRelatorios().getModel();
            tb.addRow(new Object[]{relatorio.getId(), relatorio.getTitulo(), relatorio.getQuantidade(), relatorio.getEmprestimo()});

            try {
                relatorio.cadastrarRelatorioDB();
                listRelatorios.add(relatorio);
                limparCamposRelatorio();
            } catch (ClassNotFoundException | SQLException ex) {
                JOptionPane.showMessageDialog(getTcr(), "Erro ao acessar o banco de dados", "Erro", JOptionPane.ERROR_MESSAGE);
                System.out.println("Exception: " + ex.getMessage());
            }

        } else {
            JOptionPane.showMessageDialog(getTcr(), "Favor, entrar com todos os campos obrigatórios!", "Erro", JOptionPane.ERROR_MESSAGE);
        }

    }
    
    private void limparCamposRelatorio() {

        getTcr().getTfid().setText("");
        getTcr().getTfAutores().setText("");
        getTcr().getFtfCdu().setText("");
        getTcr().getTfTitulo().setText("");
        getTcr().getTfEdicao().setText("");
        getTcr().getTfCidade().setText("");
        getTcr().getTfEditora().setText("");
        getTcr().getFtfAno().setText("");
        getTcr().getTfAssunto().setText("");
        getTcr().getTfPalavra1().setText("");
        getTcr().getTfPalavra2().setText("");
        getTcr().getTfTamanho().setText("");
        getTcr().getTfQuantidade().setText("");
        getTcr().getTfEmprestimo().setText("");
        getTcr().getTfPaginas().setText("");
    }
    public void alterarRelatorio(){
       
        if (getTcr().gettRelatorios().getSelectedRow() > -1) {

            int identificador = (int) getTcr().gettRelatorios().getValueAt(getTcr().gettRelatorios().getSelectedRow(), 0);
            Relatorio relatorio = new Relatorio();
            relatorio.setId(identificador);
            
            int index = listRelatorios.indexOf(relatorio);
                
            relatorio = listRelatorios.get(index);  //Passando todos os dados

                if (index > -1) {
                    
                    //Convertendo os dados para passar pro TextField
                    String edicao = String.valueOf(relatorio.getEdicao()) ;
                    String quantidade = String.valueOf(relatorio.getQuantidade()) ;
                    String tamanho = String.valueOf(relatorio.getTamanho()) ;
                    String emprestimo = String.valueOf(relatorio.getEmprestimo()) ;
                    String paginas = String.valueOf(relatorio.getPaginas()) ;
                    String id = String.valueOf(relatorio.getId()) ;
                    String ano = String.valueOf(relatorio.getAno());

                    //Passando os dados pro TextField
                    getTcr().getTfid().setText(id);
                    getTcr().getTfAutores().setText(relatorio.getAutores());
                    getTcr().getFtfCdu().setText(relatorio.getCDU());
                    getTcr().getTfTitulo().setText(relatorio.getTitulo());
                    getTcr().getTfEdicao().setText(edicao);
                    getTcr().getTfCidade().setText(relatorio.getCidade());
                    getTcr().getTfEditora().setText(relatorio.getEditora());
                    getTcr().getFtfAno().setText(ano);
                    getTcr().getTfAssunto().setText(relatorio.getAssunto());
                    getTcr().getTfPalavra1().setText(relatorio.getPalavra1());
                    getTcr().getTfPalavra2().setText(relatorio.getPalavra2());
                    getTcr().getTfTamanho().setText(tamanho);
                    getTcr().getTfQuantidade().setText(quantidade);
                    getTcr().getTfEmprestimo().setText(emprestimo);
                    getTcr().getTfPaginas().setText(paginas);
                    
                    
                    listRelatorios.set(index, relatorio);  //Alterando a lista
                }
        }
    }
    public void excluirRelatorio() {

        if (getTcr().gettRelatorios().getSelectedRow() > -1) {
            
            int id = (int) getTcr().gettRelatorios().getValueAt(getTcr().gettRelatorios().getSelectedRow(), 0);
            Relatorio relatorio = new Relatorio();
            relatorio.setId(id);
            
            int index = listRelatorios.indexOf(relatorio);

            if (index > -1) {
                try {
                    relatorio.excluirRelatorioDB();
                    listRelatorios.remove(index);
                    ((DefaultTableModel) getTcr().gettRelatorios().getModel()).removeRow(getTcr().gettRelatorios().getSelectedRow());
                } catch (ClassNotFoundException | SQLException ex) {
                    JOptionPane.showMessageDialog(getTcr(), "Erro ao acessar o banco de dados", "Erro", JOptionPane.ERROR_MESSAGE);
                    System.out.println("Exception: " + ex.getMessage());
                }
            }
        }
    }
}
