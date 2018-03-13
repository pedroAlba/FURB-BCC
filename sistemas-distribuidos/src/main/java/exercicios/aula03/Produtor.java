package exercicios.aula03;

import java.util.concurrent.BlockingQueue;
import java.util.function.Supplier;

public class Produtor<T> {

	private BlockingQueue<T> queue;

	public Produtor(BlockingQueue<T> queue) {
		this.queue = queue;
	}

	public void produce(Supplier<T> supplier) {
		final T msg = supplier.get();
		try {
			queue.put(msg);
			System.out.println("Adicionou mensagem: " + msg);
			
			System.out.println("tamanho fila depois produzir" + queue.size());

			// Simulate a long running process
			Thread.sleep(1250);

		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
