package com.ingressosjogos.bd.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

import com.ingressosjogos.bd.model.Estadio;
import com.ingressosjogos.bd.util.ConnectionPostgres;

public class EstadioDAO {
    
    public void salvar(Estadio estadio) throws Exception{

        String sql = "INSERT INTO Estadio(nome,localizacao,capacidade) VALUES"+
        "(?,?,?)";

        try (Connection con = ConnectionPostgres.getConection();
            PreparedStatement st = con.prepareStatement(sql)) {
                st.setString(1, estadio.getNome());
                st.setString(2,estadio.getLocalizacao());
                st.setInt(3, estadio.getCapacidade());
                st.executeUpdate();
            
        } catch (Exception e) {
            System.out.println("erro: "+ e.getMessage());
        }
    }

    public List<Estadio> listar() throws Exception{
        List<Estadio> lista = new ArrayList<>();

        String sql = "SELECT * FROM Estadio";

        try (Connection con = ConnectionPostgres.getConection();
            Statement st = con.createStatement()) {
                ResultSet rs = st.executeQuery(sql);
                
                while (rs.next()) {
                    lista.add(new Estadio(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("localizacao"),
                    rs.getInt("capacidade")
                    ));
                }
        } catch (Exception e) {
            System.out.println("Erro: "+ e.getMessage());
        }
        return lista;
    }
}
