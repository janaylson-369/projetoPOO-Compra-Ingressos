/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.ingressosjogos;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Tela_jogosController implements Initializable {

    // A nossa "prateleira" onde vamos jogar os cartões
    @FXML
    private FlowPane painelJogos;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarJogosDoBanco();
    }

    private void carregarJogosDoBanco() {
        // Limpar cartao falso 
        painelJogos.getChildren().clear();

        
        String sql = "SELECT j.id, j.data_hora, e.nome AS estadio, tc.nome AS time_casa, tf.nome AS time_fora, " +
                     "(SELECT MIN(preco) FROM Ingresso WHERE id_jogo = j.id AND status = 'livre') AS preco_min " +
                     "FROM Jogo j " +
                     "JOIN Estadio e ON j.id_estadio = e.id " +
                     "JOIN Times tc ON j.id_time_casa = tc.id " +
                     "JOIN Times tf ON j.id_time_fora = tf.id " +
                     "ORDER BY j.data_hora ASC";

        
    }

    //  cria o visual do Card 
    private VBox criarCartaoJogoDinamicamente(int idJogo, String casa, String fora, String estadio, Timestamp data, double preco) {
        VBox card = new VBox(15);
        card.setPadding(new Insets(20));
        card.setPrefSize(250, 300);
        card.setStyle("-fx-background-color: white; -fx-background-radius: 15; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0, 5);");

        HBox hboxTimes = new HBox(10);
        hboxTimes.setAlignment(Pos.CENTER);
        
        Label lblCasa = new Label(casa);
        lblCasa.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");
        Label lblX = new Label(" X ");
        lblX.setStyle("-fx-font-weight: bold; -fx-text-fill: #888;");
        Label lblFora = new Label(fora);
        lblFora.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");
        
        hboxTimes.getChildren().addAll(lblCasa, lblX, lblFora);

        Label lblEstadio = new Label("📍 " + estadio);
        lblEstadio.setStyle("-fx-text-fill: #555; -fx-font-size: 14px;");


        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy 'às' HH:mm");
        Label lblData = new Label("📅 " + sdf.format(data));
        lblData.setStyle("-fx-text-fill: #555; -fx-font-size: 14px; -fx-background-color: #f0f0f0; -fx-padding: 5; -fx-background-radius: 5;");
        lblData.setMaxWidth(Double.MAX_VALUE);
        lblData.setAlignment(Pos.CENTER);


        HBox hboxCompra = new HBox(20);
        hboxCompra.setAlignment(Pos.CENTER_LEFT);
        

        String textoPreco = (preco > 0) ? String.format("R$ %.2f", preco) : "Esgotado";
        Label lblPreco = new Label(textoPreco);
        lblPreco.setStyle("-fx-font-weight: bold; -fx-font-size: 18px; -fx-text-fill: #28a745;");
        
        Button btnComprar = new Button("Comprar");
        btnComprar.setStyle("-fx-background-color: #ff8c00; -fx-text-fill: white; -fx-background-radius: 5; -fx-font-weight: bold; -fx-cursor: hand;");
        
        if (preco <= 0) {
            btnComprar.setDisable(true); 
        }

    
        btnComprar.setOnAction(e -> {
            System.out.println("Usuário clicou para comprar ingresso do jogo ID: " + idJogo);
          
        });

        hboxCompra.getChildren().addAll(lblPreco, btnComprar);

    
        card.getChildren().addAll(hboxTimes, lblEstadio, lblData, hboxCompra);

        return card;
    }
}