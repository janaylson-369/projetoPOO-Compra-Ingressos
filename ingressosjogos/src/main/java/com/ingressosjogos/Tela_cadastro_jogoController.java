/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.ingressosjogos;

import java.net.URL;
import java.security.Timestamp;
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

    // ⚽ Dados dos Times
    @FXML
    private TextField campoTimeCasa;
    @FXML
    private TextField campoTimeFora;

    // 📅 Dados da Partida
    @FXML
    private DatePicker campoDataJogo;
    @FXML
    private TextField campoHoraJogo;

    // 🎟️ Dados dos Ingressos
    @FXML
    private TextField campoPrecoIngresso;

    // Label para mensagens de erro/sucesso
    @FXML
    private Label labelMensagemAdmin;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicializações automáticas podem ficar aqui (ex: focar no primeiro campo)
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

        // Conversões de (Texto para Número / Data)
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

        // ultimo passo - Salvar no Banco de Dados
    }
}