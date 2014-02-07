package repositorio;

import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import fachada.HibernateUtil;
import modelo.Agenda;


public class RepositorioAgenda implements IRepositorioAgenda {

	Session sessao;
	Transaction tx;
	
	@Override
	public void salvarAgenda(Agenda agenda) {
		// TODO Auto-generated method stub
		try {

			sessao = HibernateUtil.getSession();
			tx = sessao.beginTransaction();
			
			sessao.save(agenda);
			tx.commit();
			sessao.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			
			JOptionPane.showMessageDialog(null, "erro no banco ");
		}
		
	}

	@Override
	public void removerAgenda(Agenda agenda) {
		// TODO Auto-generated method stub
		sessao = HibernateUtil.getSession();
		tx = sessao.beginTransaction();
		
		if(procurarCodigo(agenda.getCodigoAgenda())== null){
	        sessao.delete(agenda);
			tx.commit();
			sessao.close();
			
		}
		else{
			JOptionPane.showMessageDialog(null, "Usuário não Existe!");
		}
	}

	@Override
	public void atualizarAgenda(Agenda agenda) {
		// TODO Auto-generated method stub
		
		sessao = HibernateUtil.getSession();
		tx = sessao.beginTransaction();
		
		if(procurarCodigo(agenda.getCodigoAgenda())== null){
	        sessao.update(agenda);
			tx.commit();
			sessao.close();
			
		}
		else{
			JOptionPane.showMessageDialog(null, "Usuário não Existe!");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Agenda> procurarAgenda(String texto) {
		// TODO Auto-generated method stub
		sessao = HibernateUtil.getSession();
		tx = sessao.beginTransaction();

		Query selecao = sessao.createQuery("FROM Agenda  e WHERE nome LIKE'" + texto + "%'");

		return selecao.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Agenda> listarAgenda() {
		// TODO Auto-generated method stub
		
		sessao = HibernateUtil.getSession();
		tx = sessao.beginTransaction();

		List<Agenda> Agenda = null;

		Criteria todos = sessao.createCriteria(Agenda.class);

		Agenda = todos.list();

		return Agenda;
	}

	//declaração para procurar codigo utilizado no metado remover e atualizar
	private Agenda procurarCodigo(long codigoAgenda){
		sessao = HibernateUtil.getSession();
		tx = sessao.beginTransaction();

		Query selecao = sessao.createQuery("FROM Empresa WHERE id =:codigoEmpresa");
		selecao.setLong("codigoEmpresa", codigoAgenda);

	    Agenda agenda = (Agenda) selecao.uniqueResult();

		return agenda;
		
	}
}
