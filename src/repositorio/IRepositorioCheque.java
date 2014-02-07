package repositorio;

import java.util.List;

import modelo.Cheque;

public interface IRepositorioCheque {
	
	//metado para salvar Cheque passando o objeto cheque
	void salvarCheque(Cheque cheque);
	
	//metado para remover Cheque passando o objeto cheque
	void removerCheque(Cheque cheque);
	
	//metado para atualizar Cheque passando o objeto cheque
	void atualizarCheque(Cheque cheque);
	
	//metado para salvar Cheque passando o objeto cheque
	public List<Cheque> procurarCheque(String texto);
	
	//metado para listar todos os cheque que estão no BD
	public List<Cheque> listarCheque();


}
