package exercicios.aula03;

import java.util.concurrent.BlockingQueue;
import java.util.function.Consumer;

public class Consumidor<T> {

	private BlockingQueue<T> queue;

	public Consumidor(BlockingQueue<T> queue) {
		this.queue = queue;
	}

	public void consume(Consumer<T> consumer) {
		try {
			consumer.accept(queue.take());
			
			System.out.println("tamanho fila depois consumir" + queue.size());

			Thread.sleep(1250);

		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}