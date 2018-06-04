package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import sun.net.www.http.HttpClient;

import java.io.IOException;

public class Controller {

    @FXML
    private Button btAdquire;
    @FXML
    private Button btRenova;
    @FXML
    private Button btDevolve;
    @FXML
    private Label lblInfo;


    public void adquire() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://localhost:8080/api");
        CloseableHttpResponse response = httpClient.execute(httpGet);
        HttpEntity responseEntity = response.getEntity();
        System.out.println(responseEntity);
    }

    public void renova(){
        System.out.println("renova");
    }

    public void devolve(){
        System.out.println("devolve");
    }
}
