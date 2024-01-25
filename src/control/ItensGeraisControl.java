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
import model.ItensGerais;
import view.FTelaEmprestimoDevolucao;
import view.FTelaPesquisaItem;
/**
 *
 * @author marip
 */
public class ItensGeraisControl {
    
    private ArrayList<ItensGerais> listItensGerais;
    FTelaEmprestimoDevolucao ted;
    FTelaPesquisaItem tpi;
    
    public ItensGeraisControl() {
        listItensGerais = new ArrayList<>();
    }
    
    public ArrayList<ItensGerais> getlistItensGerais() {
        return listItensGerais;
    }

    public void setlistItensGerais(ArrayList<ItensGerais> listItensGerais) {
        this.listItensGerais = listItensGerais;
    }

    public FTelaEmprestimoDevolucao getTed() {
        return ted;
    }

    public void setTed(FTelaEmprestimoDevolucao ted) {
        this.ted = ted;
    }
    
    public FTelaPesquisaItem getTpi() {
        return tpi;
    }

    public void setTpi(FTelaPesquisaItem tpi) {
        this.tpi = tpi;
    }
    
    private void obterItensDB() {
        try {
            listItensGerais = ItensGerais.getItensGeraisDB();
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(getTed(), "Erro ao acessar o banco de dados", "Erro", JOptionPane.ERROR_MESSAGE);
            System.out.println("Exception: " + ex.getMessage());
        }
    }
    
    private boolean validarPesquisaTED(){
        if (getTed().getTfPesquisa().getText().isEmpty()) {
            return false;
        }
        
        return true;
    }
    private boolean validarPesquisaTPI(){
        if (getTpi().getTfPesquisa().getText().isEmpty()) {
            return false;
        }
        
        return true;
    }
    private void limparCampoPesquisaTED() {

        getTed().getTfPesquisa().setText("");
       
    }
    private void limparCampoPesquisaTPI() {

        getTpi().getTfPesquisa().setText("");
       
    }
    public void PesquisarItem(){
        
        if(validarPesquisaTPI()){
            
            obterItensDB();
            ItensGerais item = new ItensGerais();
            int id = Integer.parseInt(getTpi().getTfPesquisa().getText());
            item.setId(id);

            int index = listItensGerais.indexOf(item);
            
            limparCampoPesquisaTPI();
            
                if (index > -1) {
                item = listItensGerais.get(index); //Passando todos os dados
                    //Colando o item encontrado na tabela
                    DefaultTableModel tb = (DefaultTableModel) getTpi().gettItens().getModel();  
                    tb.addRow(new Object[]{item.getId(), item.getCDU(), item.getTitulo(), item.getAutores(), item.getQuantidade(), item.getEmprestimo()});
                }else{
                    JOptionPane.showMessageDialog(getTed(), "Item não encontrado!", "Item", JOptionPane.ERROR_MESSAGE);
                }
        }else{
            JOptionPane.showMessageDialog(getTed(), "Favor, entrar com algum ID!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
            
        
    }
    public void LimparTabelaPesquisa(){
    
        //Excluindo linha da tabela do item pesquisado
        ((DefaultTableModel) getTpi().gettItens().getModel()).setRowCount(0);
         
    }
    public void ProcurarItem(){
        
        
        if(validarPesquisaTED()){
            
            obterItensDB();
            ItensGerais item = new ItensGerais();
            int id = Integer.parseInt(getTed().getTfPesquisa().getText());
            item.setId(id);

            int index = listItensGerais.indexOf(item);
            
            limparCampoPesquisaTED();
            
                if (index > -1) {
                item = listItensGerais.get(index); //Passando todos os dados
                    //Colando o item encontrado na tabela
                    DefaultTableModel tb = (DefaultTableModel) getTed().gettItens().getModel();  
                    tb.addRow(new Object[]{item.getId(), item.getTitulo(), item.getQuantidade(), item.getEmprestimo()});
                }else{
                    JOptionPane.showMessageDialog(getTed(), "Item não encontrado!", "Item", JOptionPane.ERROR_MESSAGE);
                }
        }else{
            JOptionPane.showMessageDialog(getTed(), "Favor, entrar com algum ID!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
            
        
    } 
    
    public void EmprestarItem(){
    
         if (getTed().gettItens().getSelectedRow() > -1) {
            
            ItensGerais item = new ItensGerais();
            int id = (int) getTed().gettItens().getValueAt(getTed().gettItens().getSelectedRow(), 0);
            item.setId(id);
            
            int index = listItensGerais.indexOf(item);
            item = listItensGerais.get(index); //Passando todos os dados
            
            //Excluindo linha da tabela do item pesquisado
            ((DefaultTableModel) getTed().gettItens().getModel()).removeRow(getTed().gettItens().getSelectedRow());             
            
            if (index > -1) {
                
                try {
                        if(item.getQuantidade() > item.getEmprestimo()){  //Verificando se o item está disponível
                        item.setEmprestimo(item.getEmprestimo()+1);  //Aumentando mais um empréstimo
                        item.AtualizarItemDB();  //Atualizando banco de dados
                        listItensGerais.set(index, item);  //Alterando a lista
                        JOptionPane.showMessageDialog(getTed(), "Empréstimo finalizado!", "Item", JOptionPane.INFORMATION_MESSAGE);
                        }else{  //Caso não tenha mais itens disponivéis
                        JOptionPane.showMessageDialog(getTed(), "Item não disponível!", "Item", JOptionPane.ERROR_MESSAGE);
                        }
                    
                    
                } catch (ClassNotFoundException | SQLException ex) {
                    JOptionPane.showMessageDialog(getTed(), "Erro ao acessar o banco de dados", "Erro", JOptionPane.ERROR_MESSAGE);
                    System.out.println("Exception: " + ex.getMessage());
                }
            }
        }
    }
    public void DevolverItem(){
    
         if (getTed().gettItens().getSelectedRow() > -1) {
            
            ItensGerais item = new ItensGerais();
            int id = (int) getTed().gettItens().getValueAt(getTed().gettItens().getSelectedRow(), 0);
            item.setId(id);
            
            int index = listItensGerais.indexOf(item);
            item = listItensGerais.get(index); //Passando todos os dados
            
            //Excluindo linha da tabela do item pesquisado
            ((DefaultTableModel) getTed().gettItens().getModel()).removeRow(getTed().gettItens().getSelectedRow()); 
            
            if (index > -1) {
                
                try {
                        if(item.getEmprestimo() >= 1){  //Verificando se tem item emprestado
                        
                        item.setEmprestimo(item.getEmprestimo()-1);  //Diminuindo mais um empréstimo
                        item.AtualizarItemDB();
                        listItensGerais.set(index, item);  //Alterando a lista
                        JOptionPane.showMessageDialog(getTed(), "Devolução finalizada!", "Item", JOptionPane.INFORMATION_MESSAGE);
                        }else{  //Caso não tenha mais itens disponivéis
                        JOptionPane.showMessageDialog(getTed(), "Não foi possível devolver o item!", "Item", JOptionPane.ERROR_MESSAGE);
                        }
                    
                } catch (ClassNotFoundException | SQLException ex) {
                    JOptionPane.showMessageDialog(getTed(), "Erro ao acessar o banco de dados", "Erro", JOptionPane.ERROR_MESSAGE);
                    System.out.println("Exception: " + ex.getMessage());
                }
            }
            
             
        }
    }
    
 }
    

