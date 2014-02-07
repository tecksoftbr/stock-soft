package repositorio;

import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import fachada.HibernateUtil;
import modelo.ContasPagar;

public class RepositorioContasPagar implements IRepositorioContasPagar {

	
	Session sessao;
	Transaction tx;
	@Override
	public void salvarContasPagar(ContasPagar contasPagar) {
		// TODO Auto-generated method stub
		
		try {
			
			sessao = HibernateUtil.getSession();
			tx = sessao.beginTransaction();
			
			sessao.save(contasPagar);
			tx.commit();
			sessao.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			
			JOptionPane.showMessageDialog(null, "erro no banco ");
		}
		
	}

	@Override
	public void removerContasPagar(ContasPagar contasPagar) {
		// TODO Auto-generated method stub
		sessao = HibernateUtil.getSession();
		tx = sessao.beginTransaction();
		
		if(procurarCodigo(contasPagar.getCodigo())== null){
	        sessao.delete(contasPagar);
			tx.commit();
			sessao.close();
			
		}
		else{
			JOptionPane.showMessageDialog(null, "Usuário não Existe!");
		}
		
	}

	@Override
	public void atualizarContasPagar(ContasPagar contasPagar) {
		// TODO Auto-generated method stub
		
		sessao = HibernateUtil.getSession();
		tx = sessao.beginTransaction();
		
		if(procurarCodigo(contasPagar.getCodigo())== null){
	        sessao.update(contasPagar);
			tx.commit();
			sessao.close();
			
		}
		else{
			JOptionPane.showMessageDialog(null, "Usuário não Existe!");
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ContasPagar> procurarContasPagar(String texto) {
		// TODO Auto-generated method stub
		sessao = HibernateUtil.getSession();
		tx = sessao.beginTransaction();

		Query selecao = sessao.createQuery("FROM ContasPagar  e WHERE nome LIKE'" + texto + "%'");

		return selecao.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ContasPagar> listarContasPagar() {
		// TODO Auto-generated method stub
		sessao = HibernateUtil.getSession();
		tx = sessao.beginTransaction();

		List<ContasPagar> contasPagar = null;

		Criteria todos = sessao.createCriteria(ContasPagar.class);

		contasPagar = todos.list();

		return contasPagar;
	}
	//declaração para procurar codigo utilizado no metado remover e atualizar
	private ContasPagar procurarCodigo(long codigoContasPagar){
		sessao = HibernateUtil.getSession();
		tx = sessao.beginTransaction();

		Query selecao = sessao.createQuery("FROM ContasPagar WHERE id =:codigoContasPagar");
		selecao.setLong("codigoContasPagar", codigoContasPagar);

	    ContasPagar contasPagar = (ContasPagar) selecao.uniqueResult();

		return contasPagar;
		
	}

}
