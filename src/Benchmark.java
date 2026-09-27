import java.util.Random;

public class Benchmark {
    private static final int[] N_VALUES = {100, 1000, 10000, 100000};
    private static final int REPETITIONS = 5;

    public static void main(String[] args) {
        System.out.println("=== BENCHMARK STARTING ===");
        runWorkload1();
        runWorkload2();
        runWorkload3();
        runWorkload4();
        System.out.println("=== BENCHMARK COMPLETE ===");
    }

    private static void runWorkload1() {
        System.out.println("\n--- Workload 1: Random Access (10,000 get operations) ---");
        for (int n : N_VALUES) {
            long totalTimeDA = 0;
            long totalTimeLL = 0;

            for (int r = 0; r < REPETITIONS; r++) {
                Random rng = new Random(42 + r);
                DynamicArray<Integer> da = new DynamicArray<>();
                LinkedList<Integer> ll = new LinkedList<>();
                for (int i = 0; i < n; i++) {
                    int val = rng.nextInt();
                    da.add(val);
                    ll.add(val);
                }

                int[] indices = new int[10000];
                for (int i = 0; i < 10000; i++) {
                    indices[i] = rng.nextInt(n);
                }

                // Dynamic Array Test
                long startDA = System.nanoTime();
                for (int idx : indices) {
                    da.get(idx);
                }
                totalTimeDA += (System.nanoTime() - startDA);

                // Linked List Test
                long startLL = System.nanoTime();
                for (int idx : indices) {
                    ll.get(idx);
                }
                totalTimeLL += (System.nanoTime() - startLL);
            }

            System.out.printf("N = %-7d | DA Avg Time: %-10.2f ms | LL Avg Time: %-10.2f ms%n",
                    n, (totalTimeDA / (double) REPETITIONS) / 1e6, (totalTimeLL / (double) REPETITIONS) / 1e6);
        }
    }

    private static void runWorkload2() {
        System.out.println("\n--- Workload 2: Search (1,000 contains operations) ---");
        for (int n : N_VALUES) {
            long totalTimeDA = 0;
            long totalTimeLL = 0;

            for (int r = 0; r < REPETITIONS; r++) {
                Random rng = new Random(42 + r);
                DynamicArray<Integer> da = new DynamicArray<>();
                LinkedList<Integer> ll = new LinkedList<>();
                for (int i = 0; i < n; i++) {
                    int val = rng.nextInt(n * 2);
                    da.add(val);
                    ll.add(val);
                }

                int[] searchVals = new int[1000];
                for (int i = 0; i < 1000; i++) {
                    searchVals[i] = rng.nextInt(n * 2);
                }

                long startDA = System.nanoTime();
                for (int val : searchVals) {
                    da.contains(val);
                }
                totalTimeDA += (System.nanoTime() - startDA);

                long startLL = System.nanoTime();
                for (int val : searchVals) {
                    ll.contains(val);
                }
                totalTimeLL += (System.nanoTime() - startLL);
            }

            System.out.printf("N = %-7d | DA Avg Time: %-10.2f ms | LL Avg Time: %-10.2f ms%n",
                    n, (totalTimeDA / (double) REPETITIONS) / 1e6, (totalTimeLL / (double) REPETITIONS) / 1e6);
        }
    }

    private static void runWorkload3() {
        System.out.println("\n--- Workload 3: Insert & Remove (1,000 ops at index 0 & N/2) ---");
        for (int n : N_VALUES) {
            long timeDA_add0 = 0, timeLL_add0 = 0;
            long timeDA_addMid = 0, timeLL_addMid = 0;

            for (int r = 0; r < REPETITIONS; r++) {
                DynamicArray<Integer> da = new DynamicArray<>();
                LinkedList<Integer> ll = new LinkedList<>();
                for (int i = 0; i < n; i++) {
                    da.add(i);
                    ll.add(i);
                }

                // Insert index 0
                long start = System.nanoTime();
                for (int i = 0; i < 1000; i++) da.add(0, -1);
                timeDA_add0 += System.nanoTime() - start;

                start = System.nanoTime();
                for (int i = 0; i < 1000; i++) ll.add(0, -1);
                timeLL_add0 += System.nanoTime() - start;

                // Insert index N/2
                start = System.nanoTime();
                for (int i = 0; i < 1000; i++) da.add(n / 2, -1);
                timeDA_addMid += System.nanoTime() - start;

                start = System.nanoTime();
                for (int i = 0; i < 1000; i++) ll.add(n / 2, -1);
                timeLL_addMid += System.nanoTime() - start;
            }

            System.out.printf("N = %-7d | Add(0) DA: %.2f ms, LL: %.2f ms | Add(N/2) DA: %.2f ms, LL: %.2f ms%n",
                    n,
                    (timeDA_add0 / (double) REPETITIONS) / 1e6, (timeLL_add0 / (double) REPETITIONS) / 1e6,
                    (timeDA_addMid / (double) REPETITIONS) / 1e6, (timeLL_addMid / (double) REPETITIONS) / 1e6);
        }
    }

    private static void runWorkload4() {
        System.out.println("\n--- Workload 4: Priority Processing (MinHeap) ---");
        for (int n : N_VALUES) {
            long totalInsertTime = 0;
            long totalExtractTime = 0;

            for (int r = 0; r < REPETITIONS; r++) {
                Random rng = new Random(42 + r);
                int[] data = new int[n];
                for (int i = 0; i < n; i++) data[i] = rng.nextInt();

                MinHeap heap = new MinHeap();

                long startInsert = System.nanoTime();
                for (int val : data) heap.insert(val);
                totalInsertTime += System.nanoTime() - startInsert;

                long startExtract = System.nanoTime();
                for (int i = 0; i < n; i++) heap.extractMin();
                totalExtractTime += System.nanoTime() - startExtract;
            }

            System.out.printf("N = %-7d | Insert Total Time: %-10.2f ms | Extract Total Time: %-10.2f ms%n",
                    n, (totalInsertTime / (double) REPETITIONS) / 1e6, (totalExtractTime / (double) REPETITIONS) / 1e6);
        }
    }
}