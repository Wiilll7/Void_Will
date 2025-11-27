package bo;


import java.util.List;

import dao.TipoAtividadeDAO;
import dto.TipoAtividade;

public class TipoAtividadeBO {
	public boolean create(TipoAtividade tipo_atividade) {
	    TipoAtividadeDAO dao = new TipoAtividadeDAO();
	    return dao.create(tipo_atividade);
	}

	public TipoAtividade readById(int id) {
	    TipoAtividadeDAO dao = new TipoAtividadeDAO();
	    return dao.readById(id);
	}

	public List<TipoAtividade> readAll() {
	    TipoAtividadeDAO dao = new TipoAtividadeDAO();
	    return dao.readAll();
	}

	public boolean update(TipoAtividade tipo_atividade) {
	    TipoAtividadeDAO dao = new TipoAtividadeDAO();
	    return dao.update(tipo_atividade);
	}

	public boolean deleteById(int id) {
	    TipoAtividadeDAO dao = new TipoAtividadeDAO();
	    return dao.deleteById(id);
	}
}
