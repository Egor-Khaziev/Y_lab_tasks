import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class FibonacciMath {

    public Map<Integer, BigInteger> fibMap;

    public FibonacciMath(){
        fibMap = new HashMap<>();
        fibMap.put(0,BigInteger.ZERO);
    }

    public static void main(String[] args) {
        FibonacciMath fibonacciMath = new FibonacciMath();

        fibonacciMath.fib(20);
        System.out.println(fibonacciMath.fibMap.values());
    }

    public BigInteger fib(Integer i){

        if (fibMap.containsKey(i)) { return fibMap.get(i); }

        if (i<=0) { return BigInteger.valueOf(-i); }

        BigInteger num = fib(i-1).add(fib(i-2));
        fibMap.put(i, num);
        return num;

    }
}


