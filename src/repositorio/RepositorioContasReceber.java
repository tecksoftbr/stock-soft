package repositorio;

import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import fachada.HibernateUtil;

import modelo.Agenda;
import modelo.ContasReceber;

public class RepositorioContasReceber implements IRepositorioContasReceber {

	Session sessao;
	Transaction tx;
	@Override
	public void salvarContasReceber(ContasReceber contasReceber) {
		// TODO Auto-generated method stub
		try {
			
			sessao = HibernateUtil.getSession();
			tx = sessao.beginTransaction();
			
			sessao.save(contasReceber);
			tx.commit();
			sessao.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			
			JOptionPane.showMessageDialog(null, "erro no banco ");
		}
		
	}

	@Override
	public void removerContasReceber(ContasReceber contasReceber) {
		// TODO Auto-generated method stub
		sessao = HibernateUtil.getSession();
		tx = sessao.beginTransaction();
		
		if(procurarCodigo(contasReceber.getCodigo())== null){
	        sessao.delete(contasReceber);
			tx.commit();
			sessao.close();
			
		}
		else{
			JOptionPane.showMessageDialog(null, "Usuário não Existe!");
		}
	}

	@Override
	public void atualizarContasReceber(ContasReceber contasReceber) {
		// TODO Auto-generated method stub
		
		sessao = HibernateUtil.getSession();
		tx = sessao.beginTransaction();
		
		if(procurarCodigo(contasReceber.getCodigo())== null){
	        sessao.update(contasReceber);
			tx.commit();
			sessao.close();
			
		}
		else{
			JOptionPane.showMessageDialog(null, "Usuário não Existe!");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ContasReceber> procurarcontasReceber(String texto) {
		// TODO Auto-generated method stub
		
		sessao = HibernateUtil.getSession();
		tx = sessao.beginTransaction();

		Query selecao = sessao.createQuery("FROM ContasReceber  e WHERE nome LIKE'" + texto + "%'");

		return selecao.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ContasReceber> listarContasReceber() {
		// TODO Auto-generated method stub
		sessao = HibernateUtil.getSession();
		tx = sessao.beginTransaction();

		List<ContasReceber> contasReceber = null;

		Criteria todos = sessao.createCriteria(Agenda.class);

		contasReceber = todos.list();

		return contasReceber;
	}
	
	//declaração para procurar codigo utilizado no metado remover e atualizar
	private ContasReceber procurarCodigo(long codigoContasReceber){
		sessao = HibernateUtil.getSession();
		tx = sessao.beginTransaction();

		Query selecao = sessao.createQuery("FROM ContasReceber WHERE id =:codigoContasReceber");
		selecao.setLong("codigoContasReceber", codigoContasReceber);

	    ContasReceber contasReceber = (ContasReceber) selecao.uniqueResult();

		return contasReceber;
		
	}

}
