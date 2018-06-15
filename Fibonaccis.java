import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Fibonaccis {
    
    private static List<Long> fib = new LinkedList<> ();
    private static Long[] fArray;
    public static Long[] buildFibonaccisArray( ) {
        try {
            fib.add(1L);
            fib.add(1L);
            int i = 2;
            while(true) {
                long f = fib.get(i-2) + fib.get(i-1);
                fib.add(f);
                i++;
                if (f > Long.MAX_VALUE / 2) {
                    break;
                }
            }
        } catch (Exception ex) {
            System.err.println(ex);
        }
        return fib.toArray(new Long[fib.size()]);
    }
    
    // find initial closest matching fibo value using
    // https://www.burgundywall.com/post/fibonacci-vs-exponential-growth
    public static String printFibonacciEncodedNumber(long n) {
        Deque<Integer> codes = new ArrayDeque<>();
        codes.addLast(1);
        boolean done = false;
        // find closest match fibo value
        int i=1;
        for (; i<fArray.length; i++) {
            if (n == fArray[i]) {
                codes.addFirst(1);
                for (int j=1; j<i; j++) {
                    codes.addFirst(0);
                }
                done = true;
                break;
            } else if (n < fArray[i]) {
                i--;
                break;
            }
        }

        while(!done) {
            codes.addFirst(1);
            n -= fArray[i];
            if (--i <= 1) {
                done = true;
                codes.addFirst(1);
            } else {
                while (n < fArray[i]) {
                    codes.addFirst(0);
                    i--;
                }
                if (n == fArray[i]) {
                    codes.addFirst(1);
                    for (int j=1; j<i; j++) {
                        codes.addFirst(0);
                    }
                    done = true;
                    break;
                }
            }
        }
        
        codes.addFirst(0);
        return codes.toString();
    }
    public static void main(String[] args) {
        fArray = buildFibonaccisArray();
        for (int i = 1; i< 100000; i++) {
            System.out.println("Number: "+ i +" - "+printFibonacciEncodedNumber(i));
        }
    }
}
