package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import connection.Conexao;
import dto.Tempo;

public class TempoDAO {

    final String TABELA = "Tempo";

    // Create
    public boolean create(Tempo tempo) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "INSERT INTO " + TABELA + " (data_inicial, data_final, tarefa_id) VALUES (?,?,?);";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setTimestamp(1, Timestamp.valueOf(tempo.getDataInicial()));
            ps.setTimestamp(2, Timestamp.valueOf(tempo.getDataFinal()));
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
            return lista;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<Tempo> readByTarefaId(int tarefaId) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + TABELA + " WHERE tarefa_id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, tarefaId);

            ResultSet rs = ps.executeQuery();
            return createListFromSelect(rs);

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
                    rs.getInt("tarefa_id"),
                    rs.getTimestamp("data_inicial").toLocalDateTime(),
                    rs.getTimestamp("data_final").toLocalDateTime()
                    
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
            String sql = "UPDATE " + TABELA + " SET data_inicial = ?, data_final = ?, tarefa_id = ? WHERE id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setTimestamp(1, Timestamp.valueOf(tempo.getDataInicial()));
            ps.setTimestamp(2, Timestamp.valueOf(tempo.getDataFinal()));
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
