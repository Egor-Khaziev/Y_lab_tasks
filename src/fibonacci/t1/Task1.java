package fibonacci.t1;

public class Task1 {

    public static void main(String[] args) {

        for (int i = 0 ; i<15;i++){
            System.out.print(fib(i)+ " ");
        }
    }

    public static int fib(int n) {
        if (n == 1 || n == 0) {
            return n;
        }
        return fib(n-1) + fib(n-2);
    }
}
