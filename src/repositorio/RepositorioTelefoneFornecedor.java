package repositorio;

import java.util.List;

import javax.swing.JOptionPane;

import modelo.TelefoneFornecedor;

import org.hibernate.Session;
import org.hibernate.Transaction;

import fachada.HibernateUtil;


public class RepositorioTelefoneFornecedor implements IRepositorioTelefoneFornecedor {

	Session sessao;
	Transaction tx;
	
	
	@Override
	public void salvarTelefoneFornecedor(TelefoneFornecedor telefoneFornecedor) {
		// TODO Auto-generated method stub

		try {
			
			sessao = HibernateUtil.getSession();
			tx = sessao.beginTransaction();
			
			sessao.save(telefoneFornecedor);
			tx.commit();
			//sessao.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			
			JOptionPane.showMessageDialog(null, "erro no banco telefoneFornecedor ");
		}
		
	}
	@Override
	public void removerTelefoneFornecedor(TelefoneFornecedor telefoneFornecedor) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void atualizarTelefoneFornecedor(
			TelefoneFornecedor telefoneFornecedor) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<TelefoneFornecedor> procurarTelefoneFornecedor(String texto) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<TelefoneFornecedor> listarTelefoneFornecedor() {
		// TODO Auto-generated method stub
		return null;
	}


}
