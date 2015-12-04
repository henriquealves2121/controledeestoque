/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estoque;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Marlon Santos
 */
public class MySQL {
    public static Connection conectar() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost/estoque", "root", "");
            return(c);
        } catch (ClassNotFoundException e) {
            System.out.println("Problema na configuração do Driver do MySQL!");
        } catch (SQLException e) {
            System.out.println("Problema na conexão com o banco de dados!");
        }
        return(null);
    }
    
    public static int CadastrarCliente(String nome, String cpf, String cidade, String endereco, String numero, String bairro){
        String insercao = "INSERT INTO cliente (nome, cpf, cidade, endereco, numero, bairro) VALUES ('"+nome+"', '"+cpf+"', '"+cidade+"', '"+endereco+"', '"+numero+"', '"+bairro+"');";
        Connection con = conectar();
        Statement st;
        try {
            st = con.createStatement();
            return(st.executeUpdate(insercao));
        } catch (SQLException e) {
            System.out.println("Problema na inserção do Cliente!");
        }
        return(0);
    }
    
    public static int CadastrarFornecedor(String razao, String cnpj, String cidade, String endereco, String numero, String bairro){
        String insercao = "INSERT INTO fornecedor (razao_social, cnpj, cidade, endereco, numero, bairro) VALUES ('"+razao+"', '"+cnpj+"', '"+cidade+"', '"+endereco+"', '"+numero+"', '"+bairro+"');";
        Connection con = conectar();
        Statement st;
        try {
            st = con.createStatement();
            return(st.executeUpdate(insercao));
        } catch (SQLException e) {
            System.out.println("Problema na inserção do Fornecedor!");
        }
        return(0);
    }
    
    public static int CadastrarProduto(String nome, int id_Fornecedor){
        String insercao = "INSERT INTO produto (Nome, fornecedor_id_Fornecedor, Data ) VALUES ('"+nome+"', "+id_Fornecedor+", CURRENT_DATE)";

        Connection con = conectar();
        Statement st;
        try {
            st = con.createStatement();
            return(st.executeUpdate(insercao));
        } catch (SQLException e) {
            System.out.println("Problema na inserção do Produto!");
        }
        return(0);
    }

    public static ResultSet ListaFornecedores() {
        Connection con = conectar();
        Statement st;
        try {
            st = con.createStatement();
            return(st.executeQuery("SELECT id_fornecedor, razao_social FROM fornecedor;"));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Problema Listagem! dos Fornecedores!!!");
        }
        return(null);
    }
    
    public static ResultSet ListaProdutoTodos() {
        Connection con = conectar();
        Statement st;
        try {
            st = con.createStatement();
           return(st.executeQuery("SELECT id_produto, nome FROM produto;"));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Problema Listagem! dos produtos!!!");
        }
        return(null);
    }
     
    public static ResultSet ListaProduto() {
        Connection con = conectar();
        Statement st;
        try {
            st = con.createStatement();
           // return(st.executeQuery("SELECT id_produto, nome FROM produto;"));
           //return(st.executeQuery("SELECT pro.id_produto, pro.nome FROM comprar com, produto pro, estoque est where(com.Fk_Produto = pro.id_produto and est.quantidade > 0 );"));
           return(st.executeQuery("select DISTINCT  pro.id_Produto, pro.Nome from produto pro, comprar com, estoque est where ( est.id_produto = com.fk_produto and com.fk_produto = pro.id_produto and est.quantidade > 0);"));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Problema Listagem! dos produtos!!!");
        }
        return(null);
    }
                        
    public static int Adiciona_Estoque(int id_produto, int quantidade){
        String insercao = "INSERT INTO estoque (id_produto, Quantidade) VALUES ("+id_produto+", "+quantidade+");";
        int id;
        Connection con = conectar();
        Statement st;
        try {
            st = con.createStatement();
            return(st.executeUpdate(insercao));
        } catch (SQLException e) {
            System.out.println("Problema na inserção do Comprar !");
        }
        return(0);
    }
    
    public static int CadastrarComprar(int id_produto, int quantidade, float valor_unidade){
        String insercao = "INSERT INTO comprar (fk_produto, quantidade, valor_unidade, data) VALUES ("+id_produto+", "+quantidade+", "+valor_unidade+", CURRENT_DATE)";
        Connection con = conectar();
        Statement st;
        try {
            st = con.createStatement();
            return(st.executeUpdate(insercao));
        } catch (SQLException e) {
            System.out.println("Problema na inserção do Comprar !");
        }
        return(0);
    }
    
    public static ResultSet ListaCliente() {
        Connection con = conectar();
        Statement st;
        try {
            st = con.createStatement();
            return(st.executeQuery("SELECT id_cliente, nome FROM cliente;"));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Problema Listagem! dos Cliente!!!");
        }
        return(null);
    }
    
    public static int RemoverItemEstoque(int id_Produto, int quantidade){
        String atualizacao = "UPDATE cliente, vender, estoque SET estoque.Quantidade = estoque.Quantidade - "+quantidade+" WHERE ( vender.Fk_cliente = cliente.id_cliente and vender.Fk_Produto = estoque.id_produto and estoque.id_produto = "+id_Produto+" );";
	Connection con = conectar();
	Statement st;
	try {
            st = con.createStatement();
            return(st.executeUpdate(atualizacao));
	} catch (SQLException e) {
            System.out.println("Problema na atualização do estoque!");
	}
	return(0);
    }
    
    public static int CadastrarVenda(int id_Cliente, int id_Produto, int quantidade, float valor_Venda, float desconto){
        String insercao = "INSERT INTO vender (Fk_Cliente, Fk_Produto, Quantidade, Valor_Unidade, Desconto, Data) VALUES ("+id_Cliente+", "+id_Produto+", "+quantidade+", "+valor_Venda+", "+desconto+", CURRENT_DATE);";
        Connection con = conectar();
        Statement st;
        try {
            st = con.createStatement();
            return(st.executeUpdate(insercao));
        } catch (SQLException e) {
            System.out.println("Problema na inserção do Venda!");
        }
        return(0);
    }

    public static ResultSet quantidadeValorEntrada(int id_produto){
        Connection con = conectar();
        Statement st;
        try {
            st = con.createStatement();
            return(st.executeQuery("select est.quantidade, com.valor_unidade from estoque est, comprar com where( est.id_produto = com.fk_produto and com.fk_produto = "+id_produto+");"));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Problema Quantidade e no valor da entrda !!");
        }
        return(null);
    }
    
    public static ResultSet RelatorioAtual() {
        Connection con = conectar();
        Statement st;
        try {
            st = con.createStatement();
           return(st.executeQuery("select pro.nome, est.quantidade, com.valor_unidade, com.data, forn.Razao_Social from estoque est, comprar com, produto pro, fornecedor forn where ( pro.id_produto = com.fk_produto and est.id_produto = com.fk_produto and pro.fornecedor_id_fornecedor = id_fornecedor ) ORDER BY pro.nome asc;"));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Problema no Relatório !!!");
        }
        return(null);
    }
    
    public static ResultSet ProdutosSemEstoque() {
        Connection con = conectar();
        Statement st;
        try {
            st = con.createStatement();
           return(st.executeQuery("select pro.nome, est.quantidade, com.valor_unidade, com.data, forn.Razao_Social from estoque est, comprar com, produto pro, fornecedor forn where ( est.quantidade = 0 and pro.id_produto = com.fk_produto and est.id_produto = com.fk_produto and pro.fornecedor_id_fornecedor = id_fornecedor) ORDER BY pro.nome asc;"));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Problema no Relatório !!!");
        }
        return(null);
    }
    
    public static ResultSet RelatorioProdutoDataSaida(String data){
        Connection con = conectar();
        Statement st;
        try {
           st = con.createStatement();
           return(st.executeQuery("select pro.nome, est.quantidade, com.valor_unidade, com.data, forn.Razao_Social from estoque est, comprar com, produto pro, fornecedor forn where ( com.data = '"+data+"' and pro.id_produto = com.fk_produto and est.id_produto = com.fk_produto and pro.fornecedor_id_fornecedor = id_fornecedor) ORDER BY pro.nome asc;"));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Problema no Relatório !!!");
        }
        return(null);
    }
        
    public static ResultSet RelatorioProdutoDataEntrada(String data){
        Connection con = conectar();
        Statement st;
        try {
            st = con.createStatement();
            return(st.executeQuery("select pro.nome, est.quantidade, com.valor_unidade, com.data, forn.Razao_Social from estoque est, comprar com, produto pro, fornecedor forn where ( com.data = '"+data+"' and pro.id_produto = com.fk_produto and est.id_produto = com.fk_produto and pro.fornecedor_id_fornecedor = id_fornecedor) ORDER BY pro.nome asc;"));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Problema no Relatório !!!");
        }
        return(null);
    }
    
    public static ResultSet QuantidadeBaixa(int quantidade) {
        Connection con = conectar();
        Statement st;
        try {
            st = con.createStatement();
           return(st.executeQuery("select pro.nome, est.quantidade, com.valor_unidade, com.data, forn.Razao_Social from estoque est, comprar com, produto pro, fornecedor forn where ( est.quantidade <= "+quantidade+" and pro.id_produto = com.fk_produto and est.id_produto = com.fk_produto and pro.fornecedor_id_fornecedor = id_fornecedor) ORDER BY pro.nome asc;"));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Problema no Relatório !!!");
        }
        return(null);
    }
    
    public static ResultSet PesquisaCliente(String nome){
        Connection con = conectar();
        Statement st;
        try {
           st = con.createStatement();
           return(st.executeQuery("SELECT * FROM cliente WHERE ( nome like '"+nome+"%');"));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Problema na pesquisa cliente!!!");
        }
        return(null);
    }
    
    public static ResultSet PesquisaFornecedor(String nome){
        Connection con = conectar();
        Statement st;
        try {
           st = con.createStatement();
           return(st.executeQuery("SELECT * FROM fornecedor WHERE ( Razao_Social like '"+nome+"%');"));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Problema na pesquisa fornecedor !!!");
        }
        return(null);
    }
    
    public static ResultSet PesquisaProduto(String nome){
        Connection con = conectar();
        Statement st;
        try {
           st = con.createStatement();
           return(st.executeQuery("select pro.id_produto, pro.nome as Produto, forn.razao_social as Fornecedor, est.quantidade, com.valor_unidade as valor_comprar from produto pro, fornecedor forn, estoque est, comprar com where ( pro.nome like '"+nome+"%' and pro.fornecedor_id_fornecedor = forn.id_fornecedor and est.id_produto = com.fk_produto and com.fk_produto = pro.id_produto );"));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Problema na pesquisa dp produto !!!");
        }
        return(null);
    }
}
