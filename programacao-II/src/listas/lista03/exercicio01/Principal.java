package listas.lista03.exercicio01;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.TreeSet;
import java.util.Vector;

public class Principal {

	static int[] array;

	public static void main(String[] args) {

		array = new int[100000];

		Random rand = new Random();
		for (int i = 0; i < array.length - 1; i++) {
			array[i] = rand.nextInt(10000000);
		}
		array[99999] = 10000001;

		List<TreeSet<ExecutionTime>> resultadosFULL = Arrays.asList(new TreeSet<>(), new TreeSet<>(), new TreeSet<>());

		Collection[] colecoes = { new LinkedList<>(), new ArrayList<>(), new Vector<>(), new HashSet<>(),
				new LinkedHashSet<>(), new TreeSet<>(), new PriorityQueue<>(), new ArrayDeque<>() };

		for (int i = 0; i < colecoes.length; i++) {

			String collectionName = colecoes[i].getClass().getName().substring(10,
					colecoes[i].getClass().getName().length());

			resultadosFULL.get(0)
					.add(new ExecutionTime(collectionName, insere(colecoes[i]), colecoes[i].size()));

			resultadosFULL.get(1)
					.add(new ExecutionTime(collectionName, getLastElement(colecoes[i]), colecoes[i].size()));

			resultadosFULL.get(2)
					.add(new ExecutionTime(collectionName, removeOneEachTwoElements(colecoes[i]), colecoes[i].size()));
		}

		for (int i = 0; i < 3; i++) {
			System.out.println("\n" + getType(i) + "\n");
			for (ExecutionTime e : resultadosFULL.get(i)) {
				
				
				String collectionName = String.format("%-16s", e.getNome());
				String timeFormatted = String.format("%,8d%n", e.getTempoTotal());
				
				timeFormatted = timeFormatted.substring(0, timeFormatted.length() - 2);
				timeFormatted = String.format("%-10s", timeFormatted);// padding
				
				System.out.print(collectionName + "TEMPO " + timeFormatted + "\tQTD " + e.getQtdItens() + "\n");
			}
		}
	}

	private static String getType(int i) {
		if (i == 0)
			return "=============== RESULTADOS INSER��O ===============";
		if (i == 1)
			return "=============== RESULTADOS GET_ULTIMOS ===============";
		if (i == 2)
			return "=============== RESULTADOS REMOVE 1 A CADA 2 ===============";

		return "";
	}

	public static long insere(Collection c) {
		long time = System.nanoTime();
		for (int i = 0; i < array.length; i++) {
			c.add(array[i]);
		}
		return System.nanoTime() - time;
	}

	public static long getLastElement(Collection c) {
		Iterator i = c.iterator();
		long startTime = System.nanoTime();
		while (i.hasNext()) {
			if (i.next().equals(10000001)) {
				return System.nanoTime() - startTime;
			}
		}
		return -1;
	}

	public static long removeOneEachTwoElements(Collection c) {
		Iterator i = c.iterator();
		long startTime = System.nanoTime();
		while (i.hasNext()) {
			i.next();
			i.remove();
			if (i.hasNext())
				i.next();
		}
		return System.nanoTime() - startTime;
	}
}
