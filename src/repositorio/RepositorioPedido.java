package repositorio;

import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import fachada.HibernateUtil;

import modelo.Pedido;

public class RepositorioPedido implements IRepositorioPedido {

	Session sessao;
	Transaction tx;

	@Override
	public void salvarPedido(Pedido pedido) {
		// TODO Auto-generated method stub
		try {

			sessao = HibernateUtil.getSession();
			tx = sessao.beginTransaction();

			sessao.save(pedido);
			tx.commit();
			sessao.close();

		} catch (Exception e) {
			// TODO: handle exception

			JOptionPane.showMessageDialog(null, "erro no banco ");
		}
	}

	@Override
	public void removerPedido(Pedido pedido) {
		// TODO Auto-generated method stub

		sessao = HibernateUtil.getSession();
		tx = sessao.beginTransaction();

		if (procurarCodigo(pedido.getCodigo()) == null) {
			sessao.delete(pedido);
			tx.commit();
			sessao.close();

		} else {
			JOptionPane.showMessageDialog(null, "Usuário não Existe!");
		}
	}

	@Override
	public void atualizarPedido(Pedido pedido) {
		// TODO Auto-generated method stub

		sessao = HibernateUtil.getSession();
		tx = sessao.beginTransaction();

		if (procurarCodigo(pedido.getCodigo()) == null) {
			sessao.update(pedido);
			tx.commit();
			sessao.close();

		} else {
			JOptionPane.showMessageDialog(null, "Usuário não Existe!");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pedido> procurarPedido(String texto) {
		// TODO Auto-generated method stub

		sessao = HibernateUtil.getSession();
		tx = sessao.beginTransaction();

		Query selecao = sessao.createQuery("FROM Pedido e WHERE nome LIKE'"
				+ texto + "%'");

		return selecao.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pedido> listarPedido() {
		// TODO Auto-generated method stub

		sessao = HibernateUtil.getSession();
		tx = sessao.beginTransaction();

		List<Pedido> pedido = null;

		Criteria todos = sessao.createCriteria(Pedido.class);

		pedido = todos.list();

		return pedido;
	}

	// declaração para procurar codigo utilizado no metado remover e atualizar
	private Pedido procurarCodigo(long codigoPedido) {
		sessao = HibernateUtil.getSession();
		tx = sessao.beginTransaction();

		Query selecao = sessao
				.createQuery("FROM Pedido WHERE id =:codigoPedido");
		selecao.setLong("codigoPedido", codigoPedido);

		Pedido pedido = (Pedido) selecao.uniqueResult();

		return pedido;

	}

}
