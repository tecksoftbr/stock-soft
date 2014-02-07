package repositorio;

import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Session;
import org.hibernate.Transaction;

import fachada.HibernateUtil;

import modelo.Estoque;

public class RepositorioEstoque implements IRepositorioEstoque {

	Session sessao;
	Transaction tx;
	@Override
	public void salvarEstoque(Estoque estoque) {
		// TODO Auto-generated method stub
		try {
			
			sessao = HibernateUtil.getSession();
			tx = sessao.beginTransaction();
			
			sessao.save(estoque);
			tx.commit();
			sessao.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			
			JOptionPane.showMessageDialog(null, "erro no banco ");
		}
	}

	@Override
	public void removerEstoque(Estoque estoque) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void atualizarEstoque(Estoque estoque) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Estoque> procurarEstoque(String texto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Estoque> listarEstoque() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
