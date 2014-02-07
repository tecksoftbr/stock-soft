package repositorio;

import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import fachada.HibernateUtil;

import modelo.Cheque;
import modelo.Empresa;

public class RepositorioCheque  implements IRepositorioCheque{

	Session sessao;
	Transaction tx;
	@Override
	public void salvarCheque(Cheque cheque) {
		// TODO Auto-generated method stub
		
		try {
			
			sessao = HibernateUtil.getSession();
			tx = sessao.beginTransaction();
			
			sessao.save(cheque);
			tx.commit();
			sessao.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			
			JOptionPane.showMessageDialog(null, "erro no banco ");
		}
	}

	@Override
	public void removerCheque(Cheque cheque) {
		// TODO Auto-generated method stub
		sessao = HibernateUtil.getSession();
		tx = sessao.beginTransaction();
		
		if(procurarCodigo(cheque.getCodigo())== null){
	        sessao.delete(cheque);
			tx.commit();
			sessao.close();
			
		}
		else{
			JOptionPane.showMessageDialog(null, "Usuário não Existe!");
		}
	}

	@Override
	public void atualizarCheque(Cheque cheque) {
		// TODO Auto-generated method stub
		sessao = HibernateUtil.getSession();
		tx = sessao.beginTransaction();
		
		if(procurarCodigo(cheque.getCodigo())== null){
	        sessao.update(cheque);
			tx.commit();
			sessao.close();
			
		}
		else{
			JOptionPane.showMessageDialog(null, "Usuário não Existe!");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cheque> procurarCheque(String texto) {
		// TODO Auto-generated method stub
		sessao = HibernateUtil.getSession();
		tx = sessao.beginTransaction();

		Query selecao = sessao.createQuery("FROM Cheque  e WHERE nome LIKE'" + texto + "%'");

		return selecao.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cheque> listarCheque() {
		// TODO Auto-generated method stub
		sessao = HibernateUtil.getSession();
		tx = sessao.beginTransaction();

		List<Cheque> cheque = null;

		Criteria todos = sessao.createCriteria(Empresa.class);

		cheque = todos.list();

		return cheque;
	}

	//declaração para procurar codigo utilizado no metado remover e atualizar
	private Cheque procurarCodigo(long codigoCheque){
		sessao = HibernateUtil.getSession();
		tx = sessao.beginTransaction();

		Query selecao = sessao.createQuery("FROM Cheque WHERE id =:codigoCheque");
		selecao.setLong("codigoCheque", codigoCheque);

	    Cheque cheque = (Cheque) selecao.uniqueResult();

		return cheque;
		
	}
}
