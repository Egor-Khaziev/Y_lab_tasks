package fibonacci.t3;

public class task3 {

    public static void main(String[] args) {

        for (int i = 0 ; i<15;i++){
            System.out.print(fib(i)+ " ");
        }

    }

    public static int fib(int n) {
        double squareRootOf5 = Math.sqrt(5);
        double phi = (1 + squareRootOf5)/2;
        int nthTerm = (int) ((Math.pow(phi, n) - Math.pow(-phi, -n))/squareRootOf5);
        return nthTerm;
    }
}
