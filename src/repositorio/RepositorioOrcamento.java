package repositorio;

import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import fachada.HibernateUtil;

import modelo.Orcamento;

public class RepositorioOrcamento implements IRepositorioOrcamento {

	Session sessao;
	Transaction tx;
	
	@Override
	public void salvarOrcamento(Orcamento orcamento) {
		// TODO Auto-generated method stub
		try {

			sessao = HibernateUtil.getSession();
			tx = sessao.beginTransaction();

			sessao.save(orcamento);
			tx.commit();
			sessao.close();

		} catch (Exception e) {
			// TODO: handle exception

			JOptionPane.showMessageDialog(null, "erro no banco ");
		}
		
	}

	@Override
	public void removerOrcamento(Orcamento orcamento) {
		// TODO Auto-generated method stub
		sessao = HibernateUtil.getSession();
		tx = sessao.beginTransaction();

		if (procurarCodigo(orcamento.getCodigo()) == null) {
			sessao.delete(orcamento);
			tx.commit();
			sessao.close();

		} else {
			JOptionPane.showMessageDialog(null, "Usuário não Existe!");
		}
	}

	@Override
	public void atualizarOrcamento(Orcamento orcamento) {
		// TODO Auto-generated method stub

		sessao = HibernateUtil.getSession();
		tx = sessao.beginTransaction();

		if (procurarCodigo(orcamento.getCodigo()) == null) {
			sessao.update(orcamento);
			tx.commit();
			sessao.close();

		} else {
			JOptionPane.showMessageDialog(null, "Usuário não Existe!");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Orcamento> procurarOrcamento(String texto) {
		// TODO Auto-generated method stub

		sessao = HibernateUtil.getSession();
		tx = sessao.beginTransaction();

		Query selecao = sessao.createQuery("FROM Orcamento e WHERE nome LIKE'"
				+ texto + "%'");

		return selecao.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Orcamento> listarOrcamento() {
		// TODO Auto-generated method stub
		sessao = HibernateUtil.getSession();
		tx = sessao.beginTransaction();

		List<Orcamento> orcamento = null;

		Criteria todos = sessao.createCriteria(Orcamento.class);

		orcamento= todos.list();

		return orcamento;
	}
	// declaração para procurar codigo utilizado no metado remover e atualizar
	private Orcamento procurarCodigo(long codigoOrcamento) {
		sessao = HibernateUtil.getSession();
		tx = sessao.beginTransaction();

		Query selecao = sessao
				.createQuery("FROM Orcamento WHERE id =:codigoOrcamento");
		selecao.setLong("codigoOrcamento", codigoOrcamento);

		Orcamento orcamento = (Orcamento) selecao.uniqueResult();

		return orcamento;
	}

}
