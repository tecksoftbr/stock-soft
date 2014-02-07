package repositorio;

import java.util.List;

import javax.swing.JOptionPane;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;

import fachada.HibernateUtil;
import modelo.Empresa;


public class RepositorioEmpresa  implements IRepositorioEmpresa{
	
	Session sessao;
	org.hibernate.Transaction tx;

	@Override
	public void salvarEmpresa(Empresa empresa) {
		// TODO Auto-generated method stub
		try {
			
			sessao = HibernateUtil.getSession();
			tx = sessao.beginTransaction();
			
			sessao.save(empresa);
			tx.commit();
			sessao.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			
			JOptionPane.showMessageDialog(null, "erro no banco ");
		}
		
	}

	@Override
	public void removerEmpresa(Empresa empresa) {
		// TODO Auto-generated method stub
		
		sessao = HibernateUtil.getSession();
		tx = sessao.beginTransaction();
		
		if(procurarCodigo(empresa.getCodigo())== null){
	        sessao.delete(empresa);
			tx.commit();
			sessao.close();
			
		}
		else{
			JOptionPane.showMessageDialog(null, "Usuário não Existe!");
		}
		
	}

	@Override
	public void atualizarEmpresa(Empresa empresa) {
		// TODO Auto-generated method stub
		
		
		sessao = HibernateUtil.getSession();
		tx = sessao.beginTransaction();
		
		if(procurarCodigo(empresa.getCodigo())== null){
	        sessao.update(empresa);
			tx.commit();
			sessao.close();
			
		}
		else{
			JOptionPane.showMessageDialog(null, "Usuário não Existe!");
		}	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Empresa> procurarEmpresa(String texto) {
		// TODO Auto-generated method stub
		
		sessao = HibernateUtil.getSession();
		tx = sessao.beginTransaction();

		Query selecao = sessao.createQuery("FROM Empresa  e WHERE nome LIKE'" + texto + "%'");

		return selecao.list();

		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Empresa> listarEmpresa() {
		// TODO Auto-generated method stub
		sessao = HibernateUtil.getSession();
		tx = sessao.beginTransaction();

		List<Empresa> empresa = null;

		Criteria todos = sessao.createCriteria(Empresa.class);

		empresa = todos.list();

		return empresa;
	}

	//declaração para procurar codigo utilizado no metado remover e atualizar
	private Empresa procurarCodigo(long codigoEmpresa){
		sessao = HibernateUtil.getSession();
		tx = sessao.beginTransaction();

		Query selecao = sessao.createQuery("FROM Empresa WHERE id =:codigoEmpresa");
		selecao.setLong("codigoEmpresa", codigoEmpresa);

	    Empresa empresa= (Empresa) selecao.uniqueResult();

		return empresa;
		
	}
}
