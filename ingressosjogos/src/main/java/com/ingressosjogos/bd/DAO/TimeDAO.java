package com.ingressosjogos.bd.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.ingressosjogos.bd.model.Time;
import com.ingressosjogos.bd.util.ConnectionPostgres;

public class TimeDAO {
    
    public void salvar(Time time) throws Exception{

        String sql = "INSERT INTO Time(nome) VALUES (?)";

        try (Connection c = ConnectionPostgres.getConection();
            PreparedStatement ps = c.prepareStatement(sql)) {

                ps.setString(1, time.getNome());
                ps.executeUpdate();
           
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public int salvarRetornandoId(Time time) throws Exception {
        String sqlBusca = "SELECT id FROM Times WHERE nome = ?";
        
        try (Connection c = ConnectionPostgres.getConection();
             PreparedStatement psBusca = c.prepareStatement(sqlBusca)) {
            
            psBusca.setString(1, time.getNome());
            try (ResultSet rs = psBusca.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        }

        String sqlCriarTime = "INSERT INTO Times(nome) VALUES (?)";
        int idGerado = -1;

        try (Connection c = ConnectionPostgres.getConection();
             PreparedStatement psInsert = c.prepareStatement(sqlCriarTime, Statement.RETURN_GENERATED_KEYS)) {
             
            psInsert.setString(1, time.getNome());
            psInsert.executeUpdate();
            try (ResultSet rs = psInsert.getGeneratedKeys()) {
                if (rs.next()) {
                    idGerado = rs.getInt(1);
                    time.setId(idGerado);
                }
            }
        }
        return idGerado;
    }

    public List<Time> listar() throws Exception{
        List<Time> lista = new ArrayList<>();
        
        String sql = "SELECT * FROM Time";

        try (Connection con = ConnectionPostgres.getConection();
            Statement st = con.createStatement()) {
                ResultSet rs = st.executeQuery(sql);

                while (rs.next()) {
                    lista.add(new Time(
                        rs.getInt("id"),
                        rs.getString("nome")

                    ));
                }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }
}
