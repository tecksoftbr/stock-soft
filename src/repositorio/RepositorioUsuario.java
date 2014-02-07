/*
 * Projeto - StockSoft
 * Repositorio Usuário 
 * Data de Criação: 16/09/2012 às 21:50 PM
 * Autor:Paulo Roberto
 * Propiedade da TechCode
 */

package repositorio;

import java.util.List;

import javax.swing.JOptionPane;

import modelo.Usuario;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import fachada.HibernateUtil;

public class RepositorioUsuario implements IRepositorioUsuario {

	Session sessao;
	Transaction tx;

	// declaração do metado salvar passando usuario
	public void salvarUsuario(Usuario usuario) {
		try {

			sessao = HibernateUtil.getSession();
			tx = sessao.beginTransaction();
			sessao.save(usuario);
			tx.commit();
			sessao.close();

		} catch (Exception e) {
			// TODO: handle exception

			JOptionPane.showMessageDialog(null, "erro no banco ");
		}

	}

	// declaração do metado remover passando usuario
	public void removerUsuario(Usuario usuario) {

		sessao = HibernateUtil.getSession();
		tx = sessao.beginTransaction();

		if (procurarCodigo(usuario.getCodigo()) != null) {
			
			sessao.flush();
			sessao.clear();
			sessao.delete(usuario);
			tx.commit();
			sessao.close();

		} else {
			JOptionPane.showMessageDialog(null, "Usuário não Existe!");
		}

	}

	// declaração do metado atualizar passando usuario
	public void atualizarUsuario(Usuario usuario) {

		sessao = HibernateUtil.getSession();
		tx = sessao.beginTransaction();

		if (procurarCodigo(usuario.getCodigo()) == null) {
			
			sessao.clear();
			sessao.flush();
			sessao.update(usuario);
			tx.commit();
			sessao.close();

		} else {
			JOptionPane.showMessageDialog(null, "Usuário não Existe!");
		}

	}

	// declaração do metado lista todos os usuários do BD
	@SuppressWarnings("unchecked")
	public List<Usuario> listarUsuario() {

		sessao = HibernateUtil.getSession();
		tx = sessao.beginTransaction();

		List<Usuario> usuario = null;

		Criteria todos = sessao.createCriteria(Usuario.class);

		usuario = todos.list();

		return usuario;

	}

	// declaração do metado lista procurando usuario por qualquer paramentro
	@SuppressWarnings("unchecked")
	public List<Usuario> procurarUsuario(String texto) {

		sessao = HibernateUtil.getSession();
		tx = sessao.beginTransaction();

		Query selecao = sessao
				.createQuery("FROM Usuario e WHERE nomeCompleto LIKE'"
						+ texto + "%'");

		return selecao.list();
	}

	// declaração para procurar codigo utilizado no metado remover e atualizar
	private Usuario procurarCodigo(long codigoUsuario) {
		sessao = HibernateUtil.getSession();
		tx = sessao.beginTransaction();
		
		Query selecao = sessao
				.createQuery("FROM Usuario WHERE codigo =:codigoUsuario");
		selecao.setLong("codigoUsuario", codigoUsuario);

		Usuario usuario = (Usuario) selecao.uniqueResult();

		return usuario;

	}

}
