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
import java.util.concurrent.TimeUnit;

public class Controller {

    @FXML
    private Label lblInfo;
    @FXML
    private Label lblTempoRestante;

    @FXML
    private Button btnAdquire;
    private Licenca currentLic;

    private CloseableHttpClient httpClient = HttpClients.createDefault();

    private Task sleeper;
    private final Integer UM_MINUTO = 60000;
    private final String API_URL = "http://localhost:8080/api";
    private Integer renovationTime = UM_MINUTO;

    private CloseableHttpResponse requestForNewLicense() throws IOException {
        HttpGet httpGet = new HttpGet(API_URL);
        return httpClient.execute(httpGet);
    }

    public void adquire() throws IOException {
        CloseableHttpResponse response = requestForNewLicense();
        HttpEntity responseEntity = response.getEntity();
        if (response.getStatusLine().getStatusCode() == 200) {
            updateLicInfo(responseEntity);
        } else {
            updateLabel("Sem licencas disponiveis");
            waitUntilNewLicenseIsAvaliable();
        }
    }

    private void updateLicInfo(HttpEntity responseEntity) throws IOException {
        String licJson = IOUtils.toString(responseEntity.getContent());
        currentLic = new Gson().fromJson(licJson, Licenca.class);
        updateLabel("");
        waitUntilRenovationTime().start();
    }

    private void waitUntilNewLicenseIsAvaliable() {

        btnAdquire.setDisable(true);
        Platform.runLater(() -> lblTempoRestante.setText("0"));
        renovationTime = UM_MINUTO;

        sleeper = new Task<Void>() {
            @Override
            protected Void call() throws IOException, InterruptedException {
                    while (true) {

                        CloseableHttpResponse response = requestForNewLicense();
                        if (response.getStatusLine().getStatusCode() == 200){
                            updateLicInfo(response.getEntity());
                            btnAdquire.setDisable(false);
                            return null;
                        }
                        Thread.sleep(1000);
                        updateLabel("Esperando nova LIC");
                        System.out.println("Esperando nova LIC");
                    }
            }
        };
        new Thread(sleeper).start();
     }

    private Thread waitUntilRenovationTime() {
        sleeper = new Task<Void>() {
            @Override
            protected Void call() {
                try {
                    while (renovationTime > 0) {
                        Thread.sleep(1);
                        renovationTime--;
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
        return new Thread(sleeper);
    }

    public void renova() {
        System.out.println("renova");
        renovationTime += UM_MINUTO;
    }

    public void devolve() throws IOException {
        System.out.println("devolve");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(API_URL + "/" + currentLic.getId());
        CloseableHttpResponse response = httpClient.execute(httpPost);
        if (response.getStatusLine().getStatusCode() == 200) {
            updateLabel("Devolveu lic");
            sleeper.cancel(true);
            Platform.runLater(() -> lblTempoRestante.setText("0"));
            renovationTime = UM_MINUTO;
        }
    }

    private void updateLabel(String value) {
        if (value.isEmpty()) {
            Platform.runLater(() -> lblInfo.setText(currentLic.getId() + " - " + currentLic.getObtido()));
        } else {
            Platform.runLater(() -> lblInfo.setText(value));
        }
    }
}
