package repositorio;

import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import fachada.HibernateUtil;
import modelo.Lembrete;

public class RepositorioLembrete implements IRepositorioLembrete{

	Session sessao;
	Transaction tx;
	
	@Override
	public void salvarLembrete(Lembrete lembrete) {
		// TODO Auto-generated method stub
		try {
			
			sessao = HibernateUtil.getSession();
			tx = sessao.beginTransaction();
			
			sessao.save(lembrete);
			tx.commit();
			sessao.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			
			JOptionPane.showMessageDialog(null, "erro no banco ");
		}
		
	}

	@Override
	public void removerLembrete(Lembrete lembrete) {
		// TODO Auto-generated method stub
		sessao = HibernateUtil.getSession();
		tx = sessao.beginTransaction();
		
		if(procurarCodigo(lembrete.getCodigo())== null){
	        sessao.delete(lembrete);
			tx.commit();
			sessao.close();
			
		}
		else{
			JOptionPane.showMessageDialog(null, "Usuário não Existe!");
		}
		
	}

	@Override
	public void atualizarLembrete(Lembrete lembrete) {
		// TODO Auto-generated method stub
		sessao = HibernateUtil.getSession();
		tx = sessao.beginTransaction();
		
		if(procurarCodigo(lembrete.getCodigo())== null){
	        sessao.update(lembrete);
			tx.commit();
			sessao.close();
			
		}
		else{
			JOptionPane.showMessageDialog(null, "Usuário não Existe!");
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Lembrete> procurarLembrete(String texto) {
		// TODO Auto-generated method stub
		
		sessao = HibernateUtil.getSession();
		tx = sessao.beginTransaction();

		Query selecao = sessao.createQuery("FROM Lembrete  e WHERE nome LIKE'" + texto + "%'");

		return selecao.list();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Lembrete> listarLembrete() {
		// TODO Auto-generated method stub
		
		sessao = HibernateUtil.getSession();
		tx = sessao.beginTransaction();

		List<Lembrete> lembrete = null;

		Criteria todos = sessao.createCriteria(Lembrete.class);

		lembrete = todos.list();

		return lembrete;
	}
	
	//declaração para procurar codigo utilizado no metado remover e atualizar
	private Lembrete procurarCodigo(long codigoLembrete){
		sessao = HibernateUtil.getSession();
		tx = sessao.beginTransaction();

		Query selecao = sessao.createQuery("FROM Lembrete WHERE id =:codigoLembrete");
		selecao.setLong("codigoLembrete", codigoLembrete);

	    Lembrete lembrete = (Lembrete) selecao.uniqueResult();

		return lembrete;
		
	}

}
