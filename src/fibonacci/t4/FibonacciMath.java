package fibonacci.t4;

import java.math.BigInteger;
import java.util.*;

public class FibonacciMath {

    public Map<Integer, BigInteger> fibMap;

    public static void main(String[] args) {
        FibonacciMath fibonacciMath = new FibonacciMath();

        fibonacciMath.fib(15);

    }


    public void fib(Integer i){
        fibMap = new HashMap<>();
        fibMap.put(0,BigInteger.ZERO);

        fibMethod(i-1);
        System.out.println(fibMap.values());
    }


    public BigInteger fibMethod(Integer i){

        if (fibMap.containsKey(i)) {
            return fibMap.get(i);
        }

        if (i<=0) {
            return BigInteger.valueOf(-i);
        }

        BigInteger num = fibMethod(i-1).add(fibMethod(i-2));
        fibMap.put(i, num);
        return num;

    }
}


