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
import model.Cartaz;
import view.FTelaCadastroCartaz;
/**
 *
 * @author marip
 */
public class CartazControl {
    
    private ArrayList<Cartaz> listCartazes;
    FTelaCadastroCartaz tcc;
    
    public CartazControl() {
        listCartazes = new ArrayList<>();
    }
    
    public ArrayList<Cartaz> getListCartaz() {
        return listCartazes;
    }

    public void setListCartaz(ArrayList<Cartaz> listCartazes) {
        this.listCartazes = listCartazes;
    }

    public FTelaCadastroCartaz getTcc() {
        return tcc;
    }

    public void setTcc(FTelaCadastroCartaz tcc) {
        this.tcc = tcc;
    }

    public void carregarCartazes() {
        obterCartazesDB();
        DefaultTableModel tb = (DefaultTableModel) getTcc().gettCartazes().getModel();
        for (Cartaz cartaz : listCartazes) {
            tb.addRow(new Object[]{cartaz.getId(), cartaz.getTitulo(), cartaz.getQuantidade(), cartaz.getEmprestimo()});
        }
    }

    private void obterCartazesDB() {
        try {
            listCartazes = Cartaz.getCartazesDB();
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(getTcc(), "Erro ao acessar o banco de dados", "Erro", JOptionPane.ERROR_MESSAGE);
            System.out.println("Exception: " + ex.getMessage());
        }
    }
    
    private boolean validarCadastroCartaz() {

        if (getTcc().getTfTitulo().getText().isEmpty()) {
            return false;
        }
        if (getTcc().getTfAutores().getText().isEmpty()) {
            return false;
        }
        if (getTcc().getTfCidade().getText().isEmpty()) {
            return false;
        }
        if (getTcc().getFtfAno().getText().isEmpty()) {
            return false;
        }
        if (getTcc().getTfEdicao().getText().isEmpty()) {
            return false;
        }
        if (getTcc().getTfPaginas().getText().isEmpty()) {
            return false;
        }
        if (getTcc().getTfEditora().getText().isEmpty()) {
            return false;
        }
        if (getTcc().getTfTamanho().getText().isEmpty()) {
            return false;
        }
        if (getTcc().getTfQuantidade().getText().isEmpty()) {
            return false;
        }
        if (getTcc().getTfAssunto().getText().isEmpty()) {
            return false;
        }
        if (getTcc().getTfPalavra1().getText().isEmpty()) {
            return false;
        }
        if (getTcc().getTfPalavra2().getText().isEmpty()) {
            return false;
        }
        if (getTcc().getFtfCdu().getText().isEmpty()) {
            return false;
        }
        if (getTcc().getTfid().getText().isEmpty()) {
            return false;
        }
        if (getTcc().getTfEmprestimo().getText().isEmpty()) {
            return false;
        }

        return true;
    }
    
    public void cadastrarCartaz() {

        if (validarCadastroCartaz()) {

            Cartaz cartaz = new Cartaz();
            cartaz.setAutores(getTcc().getTfAutores().getText());
            cartaz.setTitulo(getTcc().getTfTitulo().getText());
            cartaz.setCidade(getTcc().getTfCidade().getText());
            cartaz.setEditora(getTcc().getTfEditora().getText());
            cartaz.setCDU(getTcc().getFtfCdu().getText());
            cartaz.setAssunto(getTcc().getTfAssunto().getText());
            cartaz.setPalavra1(getTcc().getTfPalavra1().getText());
            cartaz.setPalavra2(getTcc().getTfPalavra2().getText());
         
            //Conversão do tipo da variável
            int edicao = Integer.parseInt(getTcc().getTfEdicao().getText());
            cartaz.setEdicao(edicao);
            
            int ano = Integer.parseInt(getTcc().getFtfAno().getText());
            cartaz.setAno(ano);
            
            int quantidade = Integer.parseInt(getTcc().getTfQuantidade().getText());
            cartaz.setQuantidade(quantidade);
            
            float tamanho = Float.parseFloat(getTcc().getTfTamanho().getText());
            cartaz.setTamanho(tamanho);
            
            int emprestimo = Integer.parseInt(getTcc().getTfEmprestimo().getText());
            cartaz.setEmprestimo(emprestimo);
            
            int paginas = Integer.parseInt(getTcc().getTfPaginas().getText());
            cartaz.setPaginas(paginas);
            
            int id = Integer.parseInt(getTcc().getTfid().getText());
            cartaz.setId(id);
            
            DefaultTableModel tb = (DefaultTableModel) getTcc().gettCartazes().getModel();
            tb.addRow(new Object[]{cartaz.getId(), cartaz.getTitulo(), cartaz.getQuantidade(), cartaz.getEmprestimo()});

            try {
                cartaz.cadastrarCartazDB();
                listCartazes.add(cartaz);
                limparCamposCartaz();
            } catch (ClassNotFoundException | SQLException ex) {
                JOptionPane.showMessageDialog(getTcc(), "Erro ao acessar o banco de dados", "Erro", JOptionPane.ERROR_MESSAGE);
                System.out.println("Exception: " + ex.getMessage());
            }

        } else {
            JOptionPane.showMessageDialog(getTcc(), "Favor, entrar com todos os campos obrigatórios!", "Erro", JOptionPane.ERROR_MESSAGE);
        }

    }
    
    private void limparCamposCartaz() {

        getTcc().getTfid().setText("");
        getTcc().getTfAutores().setText("");
        getTcc().getFtfCdu().setText("");
        getTcc().getTfTitulo().setText("");
        getTcc().getTfEdicao().setText("");
        getTcc().getTfCidade().setText("");
        getTcc().getTfEditora().setText("");
        getTcc().getFtfAno().setText("");
        getTcc().getTfAssunto().setText("");
        getTcc().getTfPalavra1().setText("");
        getTcc().getTfPalavra2().setText("");
        getTcc().getTfTamanho().setText("");
        getTcc().getTfQuantidade().setText("");
        getTcc().getTfEmprestimo().setText("");
        getTcc().getTfPaginas().setText("");
    }
    public void alterarCartaz(){
       
        if (getTcc().gettCartazes().getSelectedRow() > -1) {

            int identificador = (int) getTcc().gettCartazes().getValueAt(getTcc().gettCartazes().getSelectedRow(), 0);
            Cartaz cartaz = new Cartaz();
            cartaz.setId(identificador);
            
            int index = listCartazes.indexOf(cartaz);
                
            cartaz = listCartazes.get(index);  //Passando todos os dados

                if (index > -1) {
                    
                    //Convertendo os dados para passar pro TextField
                    String edicao = String.valueOf(cartaz.getEdicao()) ;
                    String quantidade = String.valueOf(cartaz.getQuantidade()) ;
                    String tamanho = String.valueOf(cartaz.getTamanho()) ;
                    String emprestimo = String.valueOf(cartaz.getEmprestimo()) ;
                    String paginas = String.valueOf(cartaz.getPaginas()) ;
                    String id = String.valueOf(cartaz.getId()) ;
                    String ano = String.valueOf(cartaz.getAno());

                    //Passando os dados pro TextField
                    getTcc().getTfid().setText(id);
                    getTcc().getTfAutores().setText(cartaz.getAutores());
                    getTcc().getFtfCdu().setText(cartaz.getCDU());
                    getTcc().getTfTitulo().setText(cartaz.getTitulo());
                    getTcc().getTfEdicao().setText(edicao);
                    getTcc().getTfCidade().setText(cartaz.getCidade());
                    getTcc().getTfEditora().setText(cartaz.getEditora());
                    getTcc().getFtfAno().setText(ano);
                    getTcc().getTfAssunto().setText(cartaz.getAssunto());
                    getTcc().getTfPalavra1().setText(cartaz.getPalavra1());
                    getTcc().getTfPalavra2().setText(cartaz.getPalavra2());
                    getTcc().getTfTamanho().setText(tamanho);
                    getTcc().getTfQuantidade().setText(quantidade);
                    getTcc().getTfEmprestimo().setText(emprestimo);
                    getTcc().getTfPaginas().setText(paginas);
                    
                    
                    listCartazes.set(index, cartaz);  //Alterando a lista
                }
        }
    }
    public void excluirCartaz() {

        if (getTcc().gettCartazes().getSelectedRow() > -1) {
            
            int id = (int) getTcc().gettCartazes().getValueAt(getTcc().gettCartazes().getSelectedRow(), 0);
            Cartaz cartaz = new Cartaz();
            cartaz.setId(id);
            
            int index = listCartazes.indexOf(cartaz);

            if (index > -1) {
                try {
                    cartaz.excluirCartazDB();
                    listCartazes.remove(index);
                    ((DefaultTableModel) getTcc().gettCartazes().getModel()).removeRow(getTcc().gettCartazes().getSelectedRow());
                } catch (ClassNotFoundException | SQLException ex) {
                    JOptionPane.showMessageDialog(getTcc(), "Erro ao acessar o banco de dados", "Erro", JOptionPane.ERROR_MESSAGE);
                    System.out.println("Exception: " + ex.getMessage());
                }
            }
        }
    }
}
