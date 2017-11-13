package arvoreBinaria;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class BTreePrinter {


	public static <T > void printNode(NoArvoreBinaria<T> raiz) {
		int maxLevel = BTreePrinter.maxLevel(raiz);
		printNoArvoreBinariaInternal(Collections.singletonList(raiz), 1, maxLevel);
	}

	private static <T > void printNoArvoreBinariaInternal(
			List<NoArvoreBinaria<T>> NoArvoreBinarias, int level, int maxLevel) {
		if (NoArvoreBinarias.isEmpty() || BTreePrinter.isAllElementsNull(NoArvoreBinarias))
			return;

		int floor = maxLevel - level;
		int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
		int firstSpaces = (int) Math.pow(2, (floor)) - 1;
		int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

		BTreePrinter.printWhitespaces(firstSpaces);

		List<NoArvoreBinaria<T>> newNoArvoreBinarias = new ArrayList<NoArvoreBinaria<T>>();
		for (NoArvoreBinaria<T> NoArvoreBinaria : NoArvoreBinarias) {
			if (NoArvoreBinaria != null) {
				System.out.print(NoArvoreBinaria.getInfo());
				newNoArvoreBinarias.add(NoArvoreBinaria.getEsq());
				newNoArvoreBinarias.add(NoArvoreBinaria.getDir());
			} else {
				newNoArvoreBinarias.add(null);
				newNoArvoreBinarias.add(null);
				System.out.print(" ");
			}

			BTreePrinter.printWhitespaces(betweenSpaces);
		}
		System.out.println("");

		for (int i = 1; i <= endgeLines; i++) {
			for (int j = 0; j < NoArvoreBinarias.size(); j++) {
				BTreePrinter.printWhitespaces(firstSpaces - i);
				if (NoArvoreBinarias.get(j) == null) {
					BTreePrinter.printWhitespaces(endgeLines + endgeLines + i + 1);
					continue;
				}

				if (NoArvoreBinarias.get(j).getEsq() != null)
					System.out.print("/");
				else
					BTreePrinter.printWhitespaces(1);

				BTreePrinter.printWhitespaces(i + i - 1);

				if (NoArvoreBinarias.get(j).getDir() != null)
					System.out.print("\\");
				else
					BTreePrinter.printWhitespaces(1);

				BTreePrinter.printWhitespaces(endgeLines + endgeLines - i);
			}

			System.out.println("");
		}

		printNoArvoreBinariaInternal(newNoArvoreBinarias, level + 1, maxLevel);
	}

	private static void printWhitespaces(int count) {
		for (int i = 0; i < count; i++)
			System.out.print(" ");
	}

	private static <T> int maxLevel(NoArvoreBinaria<T> NoArvoreBinaria) {
		if (NoArvoreBinaria == null)
			return 0;

		return Math.max(BTreePrinter.maxLevel(NoArvoreBinaria.getEsq()),
				BTreePrinter.maxLevel(NoArvoreBinaria.getDir())) + 1;
	}

	private static <T> boolean isAllElementsNull(List<T> list) {
		for (Object object : list) {
			if (object != null)
				return false;
		}

		return true;
	}

}
