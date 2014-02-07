package mapeamento;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class GeraBanco {

	public static void main(String[] args) {

		try {

			// Faz a configuração do Hibernate com o banco.
			
			Configuration cfg = new Configuration();
			cfg.configure();

			SchemaExport se = new SchemaExport(cfg);
			se.create(true, true);

		} 
		
		catch (Exception erro) {
			
			erro.printStackTrace();
			System.out.println(erro);
			
		}

	}

}