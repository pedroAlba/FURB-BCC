package view;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.AutomatoFinito;
import model.Palavra;
@SuppressWarnings("restriction")
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

		Image imageLupa = new Image(getClass().getClassLoader().getResourceAsStream("imagens/loupe.png"));
		btnAnalisar.setContentDisplay(ContentDisplay.RIGHT);
		btnAnalisar.setGraphic(new ImageView(imageLupa));

		Image imageLimpa = new Image(getClass().getClassLoader().getResourceAsStream("imagens/clear-button.png"));
		btnLimpar.setContentDisplay(ContentDisplay.RIGHT);
		btnLimpar.setGraphic(new ImageView(imageLimpa));

		Image imageTeam = new Image(getClass().getClassLoader().getResourceAsStream("imagens/team.png"));
		btnEquipe.setContentDisplay(ContentDisplay.RIGHT);
		btnEquipe.setGraphic(new ImageView(imageTeam));
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
		int linhaAtual = 0;
		for (String string : Arrays.asList(textArea.textProperty().getValue().split("\n"))) {

			for (String palavra: Arrays.asList(string.split(" ")).stream()
																 .filter(s -> ! s.isEmpty())
																 .collect(Collectors.toList())) {

				 listPalavras.add(AutomatoFinito.validaPalavra(palavra, linhaAtual++));
			}
		}
	}
}
