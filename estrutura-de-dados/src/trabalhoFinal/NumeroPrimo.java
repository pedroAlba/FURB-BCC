package trabalhoFinal;

public class NumeroPrimo {

	public static int nextPrime(int num) {
        num++;
        for (int i = 2; i <num; i++) {
            if(num%i == 0) {
                num++;
                i=2;
            } else{
                continue;
            }
        }
        return num;
    }
}
