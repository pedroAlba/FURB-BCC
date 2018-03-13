package exercicios.aula03;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.function.Consumer;
import java.util.function.Supplier;
 
public class App {
 
    private static final int MAX = 10;
 
    private final BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);
 
    public static void main(String[] args) {
        new App().startEngine();
    }
 
    public void startEngine() {
        startProducer();
        startConsumer();
    }
 
    private void startProducer() {
 
        final Produtor<String> myProducer = new Produtor<>(queue);
        final Supplier<String> supplier = () -> "MENSAGEM " + new Random().nextInt(10);
        new Thread(() -> {
            for (int i = 0; i < MAX; i++) {
                myProducer.produce(supplier);
            }
        }).start();
    }
 
    private void startConsumer() {
 
        final Consumidor<String> myConsumer = new Consumidor<>(queue);
        final Consumer<String> consumer = s -> System.out.println("Consumiu mensagem: " + s);
        new Thread(() -> {
            for (int i = 0; i < MAX; i++)
                myConsumer.consume(consumer);
        }).start();
    }
}