package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import connection.Conexao;
import dto.Tempo;

public class TempoDAO {

    final String TABELA = "Tempo";
    
    static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    public static String dateToString(LocalDateTime date) {
    	if (date != null) {
    		return date.format(formatter);
    	} else {
    		return null;
    	}
    }
    public static LocalDateTime stringToDate(String date) {
    	if (date != null) {
	    	System.out.println(date);
	    	String[] separate_ms = date.split("\\.");
	    	return LocalDateTime.parse(separate_ms[0],formatter);
    	} else {
    		return null;
    	}
    }

    // Create
    public boolean create(Tempo tempo) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "INSERT INTO " + TABELA + " (data_inicial, data_final, id_tarefa) VALUES (?,?,?);";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, dateToString(tempo.getDataInicial()));
            ps.setString(2, dateToString(tempo.getDataFinal()));
            ps.setInt(3, tempo.getTarefaId());

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
    public Tempo readById(int id) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + TABELA + " WHERE id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            List<Tempo> lista = createListFromSelect(rs);
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
    public List<Tempo> readAll() {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + TABELA + ";";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            List<Tempo> lista = createListFromSelect(rs);
            ps.close();
            conn.close();
            return lista;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<Tempo> readByTarefaId(int tarefaId) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + TABELA + " WHERE id_tarefa = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, tarefaId);

            ResultSet rs = ps.executeQuery();
            List<Tempo> lista = createListFromSelect(rs);
			ps.close();
            conn.close();
			if (lista.size() > 0) {
				return lista;
			} else {
				return null;
			}
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public Tempo readLastAdded(int tarefaId) {
    	try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + TABELA + " WHERE id_tarefa = ? ORDER BY id DESC LIMIT 1;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, tarefaId);

            ResultSet rs = ps.executeQuery();
            List<Tempo> lista = createListFromSelect(rs);
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

    
    public List<Tempo> createListFromSelect(ResultSet rs) {
        List<Tempo> lista = new ArrayList<Tempo>();

        try {
            while (rs.next()) {

                Tempo tempo = new Tempo(
                    rs.getInt("id"),
                    rs.getInt("id_tarefa"),
                    stringToDate(rs.getString("data_inicial")),
                    stringToDate(rs.getString("data_final"))
                );

                lista.add(tempo);
            }

            return lista;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Update
    public boolean update(Tempo tempo) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "UPDATE " + TABELA + " SET data_inicial = ?, data_final = ?, id_tarefa = ? WHERE id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, dateToString(tempo.getDataInicial()));
            ps.setString(2, dateToString(tempo.getDataFinal()));
            
            ps.setInt(3, tempo.getTarefaId());
            ps.setInt(4, tempo.getId());

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
