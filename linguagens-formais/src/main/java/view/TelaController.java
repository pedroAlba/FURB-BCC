package view;

import java.net.URL;
import java.util.ResourceBundle;

import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Palavra;

public class TelaController implements Initializable {

	@FXML private CodeArea textArea;
	@FXML private Button btnAnalisar;
	@FXML private Button btnLimpar;
	@FXML private Button btnEquipe;

	@FXML private TableView<Palavra> tabela;
	@FXML private TableColumn<Palavra, Integer> linha;
	@FXML private TableColumn<Palavra, String> sequencia;
	@FXML private TableColumn<Palavra, String> resultado;
	@FXML private TableColumn<Palavra, String> reconhecimento;

	private ObservableList<Palavra> listPalavras = FXCollections.observableArrayList();

	/**
	 * Ponto de entrada para a classe de controller;
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		textArea.setParagraphGraphicFactory(LineNumberFactory.get(textArea));

		linha.setCellValueFactory(
			    new PropertyValueFactory<Palavra, Integer>("linha")
		);

		sequencia.setCellValueFactory(
				new PropertyValueFactory<Palavra, String>("sequencia")
		);

		resultado.setCellValueFactory(
				new PropertyValueFactory<Palavra, String>("resultado")
		);

		reconhecimento.setCellValueFactory(
				new PropertyValueFactory<Palavra, String>("reconhecimento")
		);

		tabela.setItems(listPalavras);
	}

	@FXML
	public void btnEquipeOnClick() {
		MessageBox.showMembers();
	}

	@FXML
	public void btnLimparOnClick() {
		textArea.replaceText(0, textArea.getText().length(), "");
		tabela.getItems().clear();
	}

	@FXML
	public void btnAnalisarOnClick() {

		 //TODO: Implementar reconhecimento das palavras.
		 listPalavras.add(new Palavra(100, "resultado", "sequencia", "reconhecimento"));

		 for(String word : textArea.textProperty().getValue().split("\r\n")) {


		 }
	}



}
