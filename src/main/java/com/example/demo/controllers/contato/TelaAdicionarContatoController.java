package com.example.demo.controllers.contato;

import com.example.demo.entities.Contato;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

@Controller
public class TelaAdicionarContatoController {

    @FXML
    private TextField textFieldDescricao;

    @FXML
    private TextField textFieldTipo;

    private TelaContatosController telaContatosController;

    @FXML
    protected void onAdicionarContatoButtonClick(){
        try {
            String descricao = textFieldDescricao.getText();
            String tipo = textFieldTipo.getText();

            Contato contato = new Contato();
            contato.setDescricao(descricao);
            contato.setTipo(tipo);

            telaContatosController.adicionarContato(contato);

            fechaJanela();
        } catch (Exception e) {
            System.out.println("Você preencheu algum campo incorretamente!");
            System.out.println(e.getMessage());
        }
    }

    private void fechaJanela() {
        Stage stage = (Stage) textFieldDescricao.getScene().getWindow();
        stage.close();
    }

    public void setContatoController(TelaContatosController telaContatosController) {
        this.telaContatosController = telaContatosController;
    }
}
