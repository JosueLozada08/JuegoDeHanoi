public class Factorial {
    public int Factorial(int n){
        int fact = 1;
        for (int i = 1; i <= n; i++) {
            fact = fact * i;
        }
        return fact;
    }

    public int FactorialRecursivo(int n){
        if (n == 0) {
            return 1;
        } else {
            int resultado = n * FactorialRecursivo(n - 1);
            return resultado;
        }
    }
}
