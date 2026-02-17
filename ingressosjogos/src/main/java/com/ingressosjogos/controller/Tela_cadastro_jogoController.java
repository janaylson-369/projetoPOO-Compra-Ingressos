/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.ingressosjogos.controller;

import com.ingressosjogos.bd.DAO.EstadioDAO;
import bd.DAO.IngressoDAO;
import bd.DAO.JogoDAO;
import com.ingressosjogos.bd.DAO.TimeDAO;
import com.ingressosjogos.bd.model.Estadio;
import com.ingressosjogos.bd.model.Ingresso;
import com.ingressosjogos.bd.model.Jogo;
import com.ingressosjogos.bd.model.Time;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Tela_cadastro_jogoController implements Initializable {

    
    @FXML
    private TextField campoNomeEstadio;
    @FXML
    private TextField campoLocalEstadio;
    @FXML
    private TextField campoCapacidadeEstadio;

    
    @FXML
    private TextField campoTimeCasa;
    @FXML
    private TextField campoTimeFora;

    
    @FXML
    private DatePicker campoDataJogo;
    @FXML
    private TextField campoHoraJogo;

    
    @FXML
    private TextField campoPrecoIngresso;

    //  mensagens de erro/sucesso
    @FXML
    private Label labelMensagemAdmin;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void salvarCadastroCompleto(ActionEvent event) {
        // 1. Capturando e validando os dados da tela
        String nomeEstadio = campoNomeEstadio.getText();
        String localEstadio = campoLocalEstadio.getText();
        String capacidadeTexto = campoCapacidadeEstadio.getText();
        String timeCasa = campoTimeCasa.getText();
        String timeFora = campoTimeFora.getText();
        LocalDate data = campoDataJogo.getValue();
        String horaTexto = campoHoraJogo.getText();
        String precoTexto = campoPrecoIngresso.getText();

        if (nomeEstadio.isEmpty() || capacidadeTexto.isEmpty() || timeCasa.isEmpty() || timeFora.isEmpty() || data == null || horaTexto.isEmpty() || precoTexto.isEmpty()) {
            labelMensagemAdmin.setText("Por favor, preencha todos os campos obrigatórios.");
            labelMensagemAdmin.setStyle("-fx-text-fill: red;");
            return;
        }

        // (Texto para Número / Data)
        int capacidade;
        double precoBase;
        Timestamp dataHoraBanco;

        try {
            capacidade = Integer.parseInt(capacidadeTexto);
            precoBase = Double.parseDouble(precoTexto.replace(",", ".")); 
            
            LocalTime hora = LocalTime.parse(horaTexto);
            LocalDateTime dataHoraCompleta = LocalDateTime.of(data, hora);
            dataHoraBanco = Timestamp.valueOf(dataHoraCompleta);
            
        } catch (Exception e) {
            labelMensagemAdmin.setText("Erro de formato: Verifique a Capacidade, Preço (use ponto) e Hora (HH:MM).");
            labelMensagemAdmin.setStyle("-fx-text-fill: red;");
            return;
        }

        try {
            // 1. Salvar o Estádio e pegar o ID
            Estadio estadio = new Estadio(nomeEstadio, localEstadio, capacidade);
            EstadioDAO estadioDAO = new EstadioDAO();
            int idEstadio = estadioDAO.salvarRetornandoId(estadio);

            // 2. Salvar os Times e pegar os IDs
            TimeDAO timeDAO = new TimeDAO();
            int idTimeCasa = timeDAO.salvarRetornandoId(new Time(timeCasa));
            int idTimeFora = timeDAO.salvarRetornandoId(new Time(timeFora));

            // 3. Salvar o Jogo e pegar o ID
            Jogo jogo = new Jogo(dataHoraBanco, idEstadio, idTimeCasa, idTimeFora);
            JogoDAO jogoDAO = new JogoDAO();
            int idJogo = jogoDAO.salvarRetornandoId(jogo);

            // 4. geracao dos Ingressos 
            IngressoDAO ingressoDAO = new IngressoDAO();
            for(int i = 1; i <= capacidade; i++) {
                String assento = "A" + i; 
                Ingresso ingresso = new Ingresso(precoBase, assento, "livre", idJogo);
                ingressoDAO.salvar(ingresso);
            }

            labelMensagemAdmin.setText("Partida e ingressos cadastrados com sucesso!");
            labelMensagemAdmin.setStyle("-fx-text-fill: green;");

        } catch (Exception e) {
            labelMensagemAdmin.setText("Erro ao salvar no banco: " + e.getMessage());
            labelMensagemAdmin.setStyle("-fx-text-fill: red;");
            e.printStackTrace();
        }
    }
}