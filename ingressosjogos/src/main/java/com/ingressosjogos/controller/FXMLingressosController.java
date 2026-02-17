/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.ingressosjogos.controller;

import com.ingressosjogos.bd.model.Torcedor;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FXMLingressosController implements Initializable {

    @FXML
    private TextField campoNome;
    @FXML
    private TextField campoEmail;
    @FXML
    private TextField campoCPF;
    @FXML
    private Label labelMensagem;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    

    @FXML
    private void salvarTorcedor(ActionEvent event) {
        String nome = campoNome.getText().trim();
        String email = campoEmail.getText().trim();
        String cpf = campoCPF.getText().trim();

        if (nome.isEmpty() || email.isEmpty() || cpf.isEmpty()) {
            labelMensagem.setText("Por favor, preencha todos os campos!");
            labelMensagem.setStyle("-fx-text-fill: red;");
            return;
        } 
        
        try {
            Torcedor novoTorcedor = new Torcedor(nome, email, cpf);
            
            // salva o torcedor no Banco de Dados
            com.ingressosjogos.bd.DAO.TorcedorDAO dao = new com.ingressosjogos.bd.DAO.TorcedorDAO();
            dao.salvar(novoTorcedor);

            com.ingressosjogos.App.setRoot("tela_jogos");
            
        } catch (Exception e) {
            labelMensagem.setText("Erro ao salvar ou abrir a tela: " + e.getMessage());
            labelMensagem.setStyle("-fx-text-fill: red;");
            e.printStackTrace(); 
        }
    }
    }