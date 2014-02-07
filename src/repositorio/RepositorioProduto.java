package repositorio;

import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import fachada.HibernateUtil;
import modelo.Produto;

public class RepositorioProduto implements IRepositorioProduto {

	
	Session sessao;
	Transaction tx;
	@Override
	public void salvarProduto(Produto produto) {
		// TODO Auto-generated method stub
		try {
			
			sessao = HibernateUtil.getSession();
			tx = sessao.beginTransaction();
			
			sessao.save(produto);
			tx.commit();
			sessao.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			
			JOptionPane.showMessageDialog(null, "erro no banco ");
		}
	}

	@Override
	public void removerProduto(Produto produto) {
		// TODO Auto-generated method stub
		
		sessao = HibernateUtil.getSession();
		tx = sessao.beginTransaction();
		
		if(procurarCodigo(produto.getCodigo())== null){
	        sessao.delete(produto);
			tx.commit();
			sessao.close();
			
		}
		else{
			JOptionPane.showMessageDialog(null, "Usuário não Existe!");
		}
	}

	@Override
	public void atualizarProduto(Produto produto) {
		// TODO Auto-generated method stub
		sessao = HibernateUtil.getSession();
		tx = sessao.beginTransaction();
		
		if(procurarCodigo(produto.getCodigo())== null){
	        sessao.update(produto);
			tx.commit();
			sessao.close();
			
		}
		else{
			JOptionPane.showMessageDialog(null, "Usuário não Existe!");
		}	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Produto> procurarProduto(String texto) {
		// TODO Auto-generated method stub
		sessao = HibernateUtil.getSession();
		tx = sessao.beginTransaction();

		Query selecao = sessao.createQuery("FROM Produto e WHERE nome LIKE'" + texto + "%'");

		return selecao.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Produto> listarProduto() {
		// TODO Auto-generated method stub
		sessao = HibernateUtil.getSession();
		tx = sessao.beginTransaction();

		List<Produto> produto = null;

		Criteria todos = sessao.createCriteria(Produto.class);

		produto = todos.list();

		return produto;
	}

	//declaração para procurar codigo utilizado no metado remover e atualizar
	private Produto procurarCodigo(long codigoProduto){
		sessao = HibernateUtil.getSession();
		tx = sessao.beginTransaction();

		Query selecao = sessao.createQuery("FROM Produto WHERE id =:codigoProduto");
		selecao.setLong("codigoProduto", codigoProduto);

	    Produto produto= (Produto) selecao.uniqueResult();

		return produto;
		
	}
}
