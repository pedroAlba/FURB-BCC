package view;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MessageBox {

	private MessageBox() {

	}

	public static void showMembers() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Desenvolvedores");
		alert.setHeaderText(null);
		alert.setContentText("Pedro Henrique Pereira Alba \n" +
				              "Renato Constancio Filho");
		alert.showAndWait();
	}
}
