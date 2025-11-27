package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connection.Conexao;
import dto.TipoAtividade;

public class TipoAtividadeDAO {
	final String TABELA = "TipoAtividade";
	
	// Create
	public boolean create(TipoAtividade tipo_atividade) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "INSERT INTO " + TABELA + " (nome,descricao) VALUES (?,?);";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, tipo_atividade.getNome());
            ps.setString(2, tipo_atividade.getDescricao());
            ps.executeUpdate();
            ps.close();
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
	
	// Read
	public TipoAtividade readById(int id) {
		try {
			Connection conn = Conexao.conectar();
			String sql = "SELECT * FROM "+ TABELA +" WHERE id = ? ;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			List<TipoAtividade> lista = createListFromSelect(rs);
			ps.close();
            conn.close();
			if (lista.size() > 0) {
				return lista.get(0);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<TipoAtividade> readAll() {
		try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + TABELA + ";";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<TipoAtividade> lista = createListFromSelect(rs);
            ps.close();
            conn.close();
            return lista;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	}
	
	public List<TipoAtividade> createListFromSelect(ResultSet rs) {
        List<TipoAtividade> lista = new ArrayList<TipoAtividade>();
        try {
            while (rs.next()) {
            	TipoAtividade tipo_atividade = new TipoAtividade(rs.getInt(1),rs.getString(2),rs.getString(3));
                lista.add(tipo_atividade);
            }
            return lista;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
	// Update
	public boolean update(TipoAtividade tipo_atividade) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "UPDATE " + TABELA + " SET nome = ?,descricao = ? WHERE id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, tipo_atividade.getNome());
            ps.setString(2, tipo_atividade.getDescricao());
            ps.setInt(3, tipo_atividade.getId());
            ps.executeUpdate();
            ps.close();
            conn.close();
            return true;
        } catch (Exception e) {
        	 e.printStackTrace();
             return false;
        }
    }
	
	// Delete
	public boolean deleteById(int id) {
		try {
            if (readById(id) != null) {
            	Connection conn = Conexao.conectar();
                String sql = "DELETE FROM " + TABELA + " WHERE id = ?;";
            	PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, id);
                ps.executeUpdate();
                ps.close();
                conn.close();
                return true;
            } else {
            	return false;
            }
        } catch (Exception e) {
        	 e.printStackTrace();
             return false;
        }
	}
}
