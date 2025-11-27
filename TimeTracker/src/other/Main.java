package other;

import dto.TipoAtividade;
import java.util.List;
import bo.TipoAtividadeBO;

public class Main {
	public static void main(String[] args) {
		
		TipoAtividadeBO taBO = new TipoAtividadeBO();
		
		List<TipoAtividade> list = taBO.readAll();
		
		for (TipoAtividade tipo : list) {
			System.out.println("\nTipo:"
							+ "\nId: "+ tipo.getId()
							+ "\nNome: "+ tipo.getNome()
							+ "\nDescricao: "+ tipo.getDescricao());
		}
		
		if (taBO.deleteById(2)) {
			System.out.println("\nDeleted 2");
		} else {
			System.out.println("\n1 not deleted because it does not exist");
		}
		
		TipoAtividade tip = taBO.readById(2);
		if (tip != null) {
			System.out.println("\nTipo:"
					+ "\nId: "+ tip.getId()
					+ "\nNome: "+ tip.getNome()
					+ "\nDescricao: "+ tip.getDescricao());
		} else {
			System.out.print("TipoAtividade not found by id 2");
		}
	}
}
