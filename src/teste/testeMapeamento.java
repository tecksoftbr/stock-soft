package teste;


import java.util.HashSet;
import java.util.Set;

import fachada.Fachada;
import modelo.Fornecedor;
import modelo.TelefoneFornecedor;

public class testeMapeamento {

	public static void main(String[] ags){
		
		Fachada fachada = Fachada.getInstance();
		
		Fornecedor fornecedor = new Fornecedor();
		
	    fornecedor.setRazaoSocial("thiago silva");
		fornecedor.setNomeFantasia("Gerente de projeto");
		
	
		TelefoneFornecedor telefone1 = new TelefoneFornecedor();
		
		//telefone1.setTelefone("37210056");
		//telefone1.setFornecedor(fornecedor);
		
		TelefoneFornecedor telefone2 = new TelefoneFornecedor();
		telefone2.setTelefone("97245203");
		telefone2.setFornecedor(fornecedor);
		
		Set<TelefoneFornecedor> telefoneFornecedor = new HashSet<TelefoneFornecedor>();

		//telefoneFornecedor.add(telefone1);
		telefoneFornecedor.add(telefone2);
		
		//fornecedor.setTelefoneFornecedor(telefoneFornecedor);
		fornecedor.setTelefoneFornecedor(telefoneFornecedor);
		
		try {
			
			fachada.salvarFornecedor(fornecedor);
			fachada.salvarTelefoneFornecedor(telefone2);
			
		} catch (Exception e) {
			e.getMessage();
		}

	}
			
}