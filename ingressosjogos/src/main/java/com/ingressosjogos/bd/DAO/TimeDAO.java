package com.ingressosjogos.bd.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

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
