package view;

import java.net.URL;
import java.util.ResourceBundle;

import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TelaController implements Initializable {

	@FXML private CodeArea textArea;
	@FXML private Button btnAnalisar;
	@FXML private Button btnLimpar;
	@FXML private Button btnEquipe;

	@FXML private TableView<String> tabela;
	@FXML private TableColumn<String, String> colinaLinha;
	@FXML private TableColumn<String, String> resultado;
	@FXML private TableColumn<String, String> reconhecimento;

	/**
	 * Ponto de entrada para a classe de controller;
	 */
	public void initialize(URL location, ResourceBundle resources) {
		textArea.setParagraphGraphicFactory(LineNumberFactory.get(textArea));
	}

	@FXML
	public void btnEquipeOnClick() {
		MessageBox.showMembers();
	}

	@FXML
	public void btnLimparOnClick() {
		textArea.replaceText(0, textArea.getText().length(), "");
	}

	@FXML
	public void btnAnalisarOnClick() {

	}
}
