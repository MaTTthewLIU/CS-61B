package timing;

import edu.princeton.cs.algs4.Stopwatch;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

import java.util.ArrayList;
import java.util.List;

public class Experiments {

    private static void printTimingTable(TimingData data) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.println("------------------------------------------------------------");
        for (int i = 0; i < data.getNs().size(); i += 1) {
            int N = data.getNs().get(i);
            double time = data.getTimes().get(i);
            int opCount = data.getOpCounts().get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    /** Computes the nth Fibonacci number using a slow naive recursive strategy.*/
    private static int fib(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fib(n - 1) + fib(n - 2);
    }

    public static TimingData exampleFibonacciExperiment() {
        List<Integer> Ns = new ArrayList<>();
        List<Double> times = new ArrayList<>();
        List<Integer> opCounts = new ArrayList<>();

        // We're computing each fibonacci number 100 times to get a more stable number
        int ops = 100;

        for (int N = 10; N < 31; N++) {
            Ns.add(N);
            opCounts.add(ops);
            Stopwatch sw = new Stopwatch();
            for (int j = 0; j < ops; j++) {
                int fib = fib(N);
            }
            times.add(sw.elapsedTime());
        }

        return new TimingData(Ns, times, opCounts);
    }

    public static TimingData timeAListConstruction() {
        List<Integer> Ns = new ArrayList<>();
        List<Double> times = new ArrayList<>();
        List<Integer> opCounts = new ArrayList<>();

        AList arr;

        int ops = 100;

        for(int N = 1000;N <= 128000;N*=2){
            arr = new AList<>();
            Ns.add(N);
            opCounts.add(ops);

            Stopwatch sw = new Stopwatch();
            for(int i = N; i >= 1; i--){
                arr.addLast(i);
            }
            times.add(sw.elapsedTime());

        }

        return new TimingData(Ns, times, opCounts);
    }


    public static TimingData timeSLListGetLast() {
        List<Integer> Ns = new ArrayList<>();
        List<Double> times = new ArrayList<>();
        List<Integer> opCounts = new ArrayList<>();

        SLList ls;
        int N = 1000;
        int M = 10000;
        for(int NN = 1000; NN <= 128000; NN *= 2) {
            ls = new SLList();
            Ns.add(NN);
            for (int i = NN; i >0 ;i--) {
                ls.addFirst(i);
            }
            Stopwatch sw = new Stopwatch();
            for (int j = M; j > 0; j--) {
                ls.getLast();
                opCounts.add(j);
            }

            times.add(sw.elapsedTime());
        }

        return new TimingData(Ns, times, opCounts);

    }



    public static void main(String[] args) {
        // TODO: Modify the following line to change the experiment you're running
        TimingData td = timeSLListGetLast();
        // Modify this line to make the chart title make sense
        String title = "Naive Recursive Fibonacci";

        // Convert "times" (in seconds) and "opCounts" to nanoseconds / op
        List<Double> timesUsPerOp = new ArrayList<>();
        for (int i = 0; i < td.getTimes().size(); i++) {
            timesUsPerOp.add(td.getTimes().get(i) / td.getOpCounts().get(i) * 1e6);
        }

        printTimingTable(td);

        XYChart chart = QuickChart.getChart(title, "N", "time (us per op)", "Time", td.getNs(), timesUsPerOp);
        new SwingWrapper(chart).displayChart();
    }
}