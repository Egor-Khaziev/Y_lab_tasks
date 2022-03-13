package fibonacci.t2;

public class Task2 {

    public static void main(String[] args) {
        for (int i = 0 ; i<15;i++){
            System.out.print(fib(i)+ " ");
        }
    }


    public static int fib(int n) {
        if(n == 0 || n == 1) {
            return n;
        }
        int n0 = 0, n1 = 1;
        int tempNthTerm;
        for (int i = 2; i <= n; i++) {
            tempNthTerm = n0 + n1;
            n0 = n1;
            n1 = tempNthTerm;
        }
        return n1;
    }
}
