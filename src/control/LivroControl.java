/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Livro;
import view.FTelaCadastroLivro;
/**
 *
 * @author marip
 */
public class LivroControl {
    
    private ArrayList<Livro> listLivros;
    FTelaCadastroLivro tcl;
    
    public LivroControl() {
        listLivros = new ArrayList<>();
    }
    
    public ArrayList<Livro> getListLivro() {
        return listLivros;
    }

    public void setListLivro(ArrayList<Livro> listLivros) {
        this.listLivros = listLivros;
    }

    public FTelaCadastroLivro getTcl() {
        return tcl;
    }

    public void setTcl(FTelaCadastroLivro tcl) {
        this.tcl = tcl;
    }

    public void carregarLivros() {
        obterLivrosDB();
        DefaultTableModel tb = (DefaultTableModel) getTcl().gettLivros().getModel();
        for (Livro livro : listLivros) {
            tb.addRow(new Object[]{livro.getId(), livro.getTitulo(),livro.getQuantidade(), livro.getEmprestimo()});
        }
    }

    private void obterLivrosDB() {
        try {
            listLivros = Livro.getLivrosDB();
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(getTcl(), "Erro ao acessar o banco de dados", "Erro", JOptionPane.ERROR_MESSAGE);
            System.out.println("Exception: " + ex.getMessage());
        }
    }
    
    private boolean validarCadastroLivro() {

        if (getTcl().getTfTitulo().getText().isEmpty()) {
            return false;
        }
        if (getTcl().getTfAutores().getText().isEmpty()) {
            return false;
        }
        if (getTcl().getTfCidade().getText().isEmpty()) {
            return false;
        }
        if (getTcl().getFtfAno().getText().isEmpty()) {
            return false;
        }
        if (getTcl().getTfEdicao().getText().isEmpty()) {
            return false;
        }
        if (getTcl().getTfPaginas().getText().isEmpty()) {
            return false;
        }
        if (getTcl().getTfEditora().getText().isEmpty()) {
            return false;
        }
        if (getTcl().getTfTamanho().getText().isEmpty()) {
            return false;
        }
        if (getTcl().getTfQuantidade().getText().isEmpty()) {
            return false;
        }
        if (getTcl().getTfAssunto().getText().isEmpty()) {
            return false;
        }
        if (getTcl().getTfPalavra1().getText().isEmpty()) {
            return false;
        }
        if (getTcl().getTfPalavra2().getText().isEmpty()) {
            return false;
        }
        if (getTcl().getFtfisbn().getText().isEmpty()) {
            return false;
        }
        if (getTcl().getFtfCdu().getText().isEmpty()) {
            return false;
        }
        if (getTcl().getTfid().getText().isEmpty()) {
            return false;
        }
        if (getTcl().getTfEmprestimo().getText().isEmpty()) {
            return false;
        }

        return true;
    }
    
    public void cadastrarLivro() {

        if (validarCadastroLivro()) {

            Livro livro = new Livro();
            livro.setAutores(getTcl().getTfAutores().getText());
            livro.setTitulo(getTcl().getTfTitulo().getText());
            livro.setCidade(getTcl().getTfCidade().getText());
            livro.setEditora(getTcl().getTfEditora().getText());
            livro.setCDU(getTcl().getFtfCdu().getText());
            livro.setAssunto(getTcl().getTfAssunto().getText());
            livro.setPalavra1(getTcl().getTfPalavra1().getText());
            livro.setPalavra2(getTcl().getTfPalavra2().getText());
            livro.setISBN(getTcl().getFtfisbn().getText());
            
            //Conversão do tipo da variável
            int edicao = Integer.parseInt(getTcl().getTfEdicao().getText());
            livro.setEdicao(edicao);
            
            int ano = Integer.parseInt(getTcl().getFtfAno().getText());
            livro.setAno(ano);
            
            int quantidade = Integer.parseInt(getTcl().getTfQuantidade().getText());
            livro.setQuantidade(quantidade);
            
            float tamanho = Float.parseFloat(getTcl().getTfTamanho().getText());
            livro.setTamanho(tamanho);
            
            int emprestimo = Integer.parseInt(getTcl().getTfEmprestimo().getText());
            livro.setEmprestimo(emprestimo);
            
            int paginas = Integer.parseInt(getTcl().getTfPaginas().getText());
            livro.setPaginas(paginas);
            
            int id = Integer.parseInt(getTcl().getTfid().getText());
            livro.setId(id);
            
            DefaultTableModel tb = (DefaultTableModel) getTcl().gettLivros().getModel();
            tb.addRow(new Object[]{livro.getId(), livro.getTitulo(), livro.getQuantidade(), livro.getEmprestimo()});

            try {
                livro.cadastrarLivroDB();
                listLivros.add(livro);
                limparCamposLivro();
            } catch (ClassNotFoundException | SQLException ex) {
                JOptionPane.showMessageDialog(getTcl(), "Erro ao acessar o banco de dados", "Erro", JOptionPane.ERROR_MESSAGE);
                System.out.println("Exception: " + ex.getMessage());
            }

        } else {
            JOptionPane.showMessageDialog(getTcl(), "Favor, entrar com todos os campos obrigatórios!", "Erro", JOptionPane.ERROR_MESSAGE);
        }

    }
    
    private void limparCamposLivro() {

        getTcl().getTfid().setText("");
        getTcl().getTfAutores().setText("");
        getTcl().getFtfCdu().setText("");
        getTcl().getTfTitulo().setText("");
        getTcl().getTfEdicao().setText("");
        getTcl().getTfCidade().setText("");
        getTcl().getTfEditora().setText("");
        getTcl().getFtfAno().setText("");
        getTcl().getTfAssunto().setText("");
        getTcl().getTfPalavra1().setText("");
        getTcl().getTfPalavra2().setText("");
        getTcl().getTfTamanho().setText("");
        getTcl().getTfQuantidade().setText("");
        getTcl().getTfEmprestimo().setText("");
        getTcl().getFtfisbn().setText("");
        getTcl().getTfPaginas().setText("");
    }
    public void alterarLivro(){
       
        if (getTcl().gettLivros().getSelectedRow() > -1) {

            int identificador = (int) getTcl().gettLivros().getValueAt(getTcl().gettLivros().getSelectedRow(), 0);
            Livro livro = new Livro();
            livro.setId(identificador);
            
            int index = listLivros.indexOf(livro);
                
            livro = listLivros.get(index);  //Passando todos os dados

                if (index > -1) {
                    
                    //Convertendo os dados para passar pro TextField
                    String edicao = String.valueOf(livro.getEdicao()) ;
                    String quantidade = String.valueOf(livro.getQuantidade()) ;
                    String tamanho = String.valueOf(livro.getTamanho()) ;
                    String emprestimo = String.valueOf(livro.getEmprestimo()) ;
                    String paginas = String.valueOf(livro.getPaginas()) ;
                    String id = String.valueOf(livro.getId()) ;
                    String ano = String.valueOf(livro.getAno());

                    //Passando os dados pro TextField
                    getTcl().getTfid().setText(id);
                    getTcl().getTfAutores().setText(livro.getAutores());
                    getTcl().getFtfCdu().setText(livro.getCDU());
                    getTcl().getTfTitulo().setText(livro.getTitulo());
                    getTcl().getTfEdicao().setText(edicao);
                    getTcl().getTfCidade().setText(livro.getCidade());
                    getTcl().getTfEditora().setText(livro.getEditora());
                    getTcl().getFtfAno().setText(ano);
                    getTcl().getTfAssunto().setText(livro.getAssunto());
                    getTcl().getTfPalavra1().setText(livro.getPalavra1());
                    getTcl().getTfPalavra2().setText(livro.getPalavra2());
                    getTcl().getTfTamanho().setText(tamanho);
                    getTcl().getTfQuantidade().setText(quantidade);
                    getTcl().getTfEmprestimo().setText(emprestimo);
                    getTcl().getFtfisbn().setText(livro.getISBN());
                    getTcl().getTfPaginas().setText(paginas);
                    
                    
                    listLivros.set(index, livro);  //Alterando a lista
                }
        }
    }
    public void excluirLivro() {

        if (getTcl().gettLivros().getSelectedRow() > -1) {
            
            int id = (int) getTcl().gettLivros().getValueAt(getTcl().gettLivros().getSelectedRow(), 0);
            Livro livro = new Livro();
            livro.setId(id);
            
            int index = listLivros.indexOf(livro);

            if (index > -1) {
                try {
                    livro.excluirLivroDB();
                    listLivros.remove(index);
                    ((DefaultTableModel) getTcl().gettLivros().getModel()).removeRow(getTcl().gettLivros().getSelectedRow());
                } catch (ClassNotFoundException | SQLException ex) {
                    JOptionPane.showMessageDialog(getTcl(), "Erro ao acessar o banco de dados", "Erro", JOptionPane.ERROR_MESSAGE);
                    System.out.println("Exception: " + ex.getMessage());
                }
            }
        }
    }
}
