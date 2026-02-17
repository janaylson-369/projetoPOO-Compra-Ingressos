package com.ingressosjogos.bd.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.ingressosjogos.bd.model.Estadio;
import com.ingressosjogos.bd.util.ConnectionPostgres;

public class EstadioDAO {
    
    // Método agora retorna o ID (int) gerado pelo banco
    public int salvarRetornandoId(Estadio estadio) throws Exception {
        String sql = "INSERT INTO Estadio(nome, localizacao, capacidade) VALUES (?, ?, ?)";
        int idGerado = -1;

        // Statement.RETURN_GENERATED_KEYS avisa o JDBC que queremos pegar o ID de volta
        try (Connection con = ConnectionPostgres.getConection();
             PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            st.setString(1, estadio.getNome());
            st.setString(2, estadio.getLocalizacao());
            st.setInt(3, estadio.getCapacidade());
            
            st.executeUpdate();
            
            // Pega o ID gerado pelo PostgreSQL (coluna SERIAL)
            try (ResultSet rs = st.getGeneratedKeys()) {
                if (rs.next()) {
                    idGerado = rs.getInt(1);
                    estadio.setId(idGerado); // Atualiza o objeto com o novo ID
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao salvar Estádio: " + e.getMessage());
            throw e; // Lança o erro para o Controller tratar
        }
        
        return idGerado;
    }

    public List<Estadio> listar() throws Exception {
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
            System.out.println("Erro ao listar Estádios: " + e.getMessage());
            throw e;
        }
        return lista;
    }
}