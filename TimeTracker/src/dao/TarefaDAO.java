package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import connection.Conexao;
import dto.Dificuldade;
import dto.Estado;
import dto.Tarefa;
import dto.TipoAtividade;

public class TarefaDAO {
    final String TABELA = "Tarefa";

    // Create
    public boolean create(Tarefa tarefa) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "INSERT INTO " + TABELA + " (titulo, descricao, data_entrega, id_tipo_atividade, id_estado, id_dificuldade) VALUES (?,?,?,?,?,?);";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, tarefa.getTitulo());
            ps.setString(2, tarefa.getDescricao());
            ps.setTimestamp(3, Timestamp.valueOf(tarefa.getDataEntrega()));
            ps.setInt(4, tarefa.getTipoAtividade().getId());
            ps.setInt(5, tarefa.getEstado().getId());
            ps.setInt(6, tarefa.getDificuldade().getId());
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
    public Tarefa readById(int id) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + TABELA + " WHERE id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            List<Tarefa> lista = createListFromSelect(rs);
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
    public List<Tarefa> readAll() {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + TABELA + ";";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            List<Tarefa> lista = createListFromSelect(rs);

            ps.close();
            conn.close();
            return lista;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    

    // Update
    public boolean update(Tarefa tarefa) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "UPDATE " + TABELA + " SET titulo = ?, descricao = ?, data_entrega = ?, id_tipo_atividade = ?, id_estado = ?, id_dificuldade = ? WHERE id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, tarefa.getTitulo());
            ps.setString(2, tarefa.getDescricao());
            ps.setTimestamp(3, Timestamp.valueOf(tarefa.getDataEntrega()));
            ps.setInt(4, tarefa.getTipoAtividade().getId());
            ps.setInt(5, tarefa.getEstado().getId());
            ps.setInt(6, tarefa.getDificuldade().getId());
            ps.setInt(7, tarefa.getId());

            ps.executeUpdate();
            ps.close();
            conn.close();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    private List<Tarefa> createListFromSelect(ResultSet rs){
    	TipoAtividadeDAO taDao = new TipoAtividadeDAO();
    	List<Tarefa> lista = new ArrayList<Tarefa>();
    	
        try {
            while (rs.next()) {
            	TipoAtividade tipoAtv = taDao.readById(rs.getInt("id_tipo_atividade"));
            	
            	Tarefa tarefa = new Tarefa(rs.getInt(1),
                		rs.getString("titulo"),
                		rs.getString("descricao"),
                		rs.getTimestamp("data_entrega").toLocalDateTime(),
                		Estado.getFromId(rs.getInt("id_estado")),
                		Dificuldade.getFromId(rs.getInt("id_dificuldade")),
                		tipoAtv
                );
            	
                lista.add(tarefa);
            }
            return lista;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    

    // Delete
    public boolean delete(int id) {
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

