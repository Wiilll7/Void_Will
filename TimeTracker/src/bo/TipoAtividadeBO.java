package bo;


import java.util.List;

import dao.TipoAtividadeDAO;
import dao.UsuarioDAO;
import dto.TipoAtividade;
import dto.Usuario;

public class TipoAtividadeBO {
	public boolean create(TipoAtividade tipo_atividade) {
	    TipoAtividadeDAO dao = new TipoAtividadeDAO();
        
        List<TipoAtividade> lista = readAll();
        boolean allow = true;
        for (TipoAtividade ta : lista) {
        	if (ta.getNome().equals(tipo_atividade.getNome())) {
        		allow = false;
        		break;
        	}
        }
        if (allow) {
        	return dao.create(tipo_atividade);
        } else {
        	return false;
        }
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
	    
        List<TipoAtividade> lista = readAll();
        boolean allow = true;
        for (TipoAtividade ta : lista) {
        	if (ta.getNome().equals(tipo_atividade.getNome()) && ta.getId() != tipo_atividade.getId()) {
        		allow = false;
        		break;
        	}
        }
        if (allow) {
        	return dao.update(tipo_atividade);
        } else {
        	return false;
        }
	}

	public boolean deleteById(int id) {
	    TipoAtividadeDAO dao = new TipoAtividadeDAO();
	    return dao.deleteById(id);
	}
}
