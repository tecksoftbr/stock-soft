package repositorio;

import java.util.List;

import javax.swing.JOptionPane;


import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;

import fachada.HibernateUtil;
import modelo.Fornecedor;

public class RepositorioFornecedor  implements IRepositorioFornecedor{

	
	Session sessao;
	org.hibernate.Transaction tx;
	@Override
	public void salvarFornecedor(Fornecedor fornecedor) {
		// TODO Auto-generated method stub
	
		try {
			
			sessao = HibernateUtil.getSession();
			tx = sessao.beginTransaction();
			
			sessao.save(fornecedor);
			tx.commit();
			sessao.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			
			JOptionPane.showMessageDialog(null, "erro no banco fornecedor ");
		}
	}

	@Override
	public void removerFornecedor(Fornecedor fornecedor) {
		// TODO Auto-generated method stub
		
		sessao = HibernateUtil.getSession();
		tx = sessao.beginTransaction();
		
		if(procurarCodigo(fornecedor.getCodigo())== null){
	        sessao.delete(fornecedor);
			tx.commit();
			sessao.close();
			
		}
		else{
			JOptionPane.showMessageDialog(null, "Usuário não Existe!");
		}
		
	}

	@Override
	public void atualizarFornecedor(Fornecedor fornecedor) {
		// TODO Auto-generated method stub
		
		
		sessao = HibernateUtil.getSession();
		tx = sessao.beginTransaction();
		
		if(procurarCodigo(fornecedor.getCodigo())== null){
	        sessao.update(fornecedor);
			tx.commit();
			sessao.close();
			
		}
		else{
			JOptionPane.showMessageDialog(null, "Usuário não Existe!");
		}	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Fornecedor> procurarFornecedor(String texto) {
		// TODO Auto-generated method stub
		
		sessao = HibernateUtil.getSession();
		tx = sessao.beginTransaction();

		Query selecao = sessao.createQuery("FROM Fornecedor  e WHERE nome LIKE'" + texto + "%'");

		return selecao.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Fornecedor> listarFornecedor() {
		// TODO Auto-generated method stub
		sessao = HibernateUtil.getSession();
		tx = sessao.beginTransaction();

		List<Fornecedor> fornecedor = null;

		Criteria todos = sessao.createCriteria(Fornecedor.class);

		fornecedor = todos.list();

		return fornecedor;
	}

	//declaração para procurar codigo utilizado no metado remover e atualizar
	private Fornecedor procurarCodigo(long codigoFornecedor){
		sessao = HibernateUtil.getSession();
		tx = sessao.beginTransaction();

		Query selecao = sessao.createQuery("FROM Fornecedor WHERE id =:codigoFornecedor");
		selecao.setLong("codigoEmpresa", codigoFornecedor);

	    Fornecedor fornecedor = (Fornecedor) selecao.uniqueResult();

		return fornecedor;
		
	}
}
