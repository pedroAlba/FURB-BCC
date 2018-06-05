package sample;

import com.google.gson.Gson;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import sun.net.www.http.HttpClient;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Button btAdquire;
    @FXML
    private Button btRenova;
    @FXML
    private Button btDevolve;
    @FXML
    private Label lblInfo;
    @FXML
    private Label lblTempoRestante;

    private Licenca currentLic;

    private Task sleeper;
    private final Integer UM_MINUTO = 60000;
    private Integer renovationTime = UM_MINUTO;

    public void adquire() throws IOException, InterruptedException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://localhost:8080/api");
        CloseableHttpResponse response = httpClient.execute(httpGet);
        HttpEntity responseEntity = response.getEntity();
        if(response.getStatusLine().getStatusCode() == 200){
            String licJson = IOUtils.toString(responseEntity.getContent());
            currentLic = new Gson().fromJson(licJson, Licenca.class);
            updateLabel("");
            System.out.println("Esperando 1 min");

            sleeper = new Task<Void>() {
                @Override
                protected Void call() {
                    try {
                        while(renovationTime > 0){
                            Thread.sleep(1);
                            renovationTime--;
                            System.out.println(renovationTime);
                            Platform.runLater(() -> lblTempoRestante.setText(renovationTime.toString()));
                        }
                    } catch (InterruptedException e) {
                        //do nothing
                    }
                    return null;
                }
            };
            sleeper.setOnSucceeded(event -> {
                try {
                    devolve();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            new Thread(sleeper).start();
        }else{
            updateLabel("Sem licencas disponiveis");
            if(sleeper != null){
                sleeper.cancel(true);
                Platform.runLater(() -> lblTempoRestante.setText("0"));
                renovationTime = UM_MINUTO;
            }
        }
    }

    public void renova(){
        System.out.println("renova");
        renovationTime += UM_MINUTO;
    }

    public void devolve() throws IOException {
        System.out.println("devolve");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:8080/api/" + currentLic.getId());
        CloseableHttpResponse response = httpClient.execute(httpPost);
        if(response.getStatusLine().getStatusCode() == 200){
            updateLabel("Devolveu lic");
            sleeper.cancel(true);
            Platform.runLater(() -> lblTempoRestante.setText("0"));
            renovationTime = UM_MINUTO;
        }
    }

    public void updateLabel(String value){
        if(value.isEmpty()){
            Platform.runLater(() -> lblInfo.setText(currentLic.getId() + " - " + currentLic.getObtido()));
        }else{
            Platform.runLater(() ->lblInfo.setText(value));
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //lblTempoRestante.textProperty().bind(new SimpleIntegerProperty(renovationTime).asString());
    }
}
