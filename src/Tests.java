public class Tests {
    public static void main(String[] args) {
        testDynamicArray();
        testLinkedList();
        testMinHeap();
        System.out.println("ALL TESTS PASSED SUCCESSFULLY!");
    }

    private static void testDynamicArray() {
        DynamicArray<Integer> da = new DynamicArray<>();
        da.add(10);
        da.add(20);
        da.add(0, 5);
        assert da.get(0) == 5;
        assert da.get(1) == 10;
        assert da.get(2) == 20;
        assert da.remove(1) == 10;
        assert da.contains(20);
        assert !da.contains(100);
    }

    private static void testLinkedList() {
        LinkedList<Integer> ll = new LinkedList<>();
        ll.add(10);
        ll.add(20);
        ll.add(0, 5);
        assert ll.get(0) == 5;
        assert ll.get(1) == 10;
        assert ll.get(2) == 20;
        assert ll.remove(1) == 10;
        assert ll.contains(20);
        assert !ll.contains(100);
    }

    private static void testMinHeap() {
        MinHeap heap = new MinHeap();
        int[] vals = {5, 3, 8, 1, 2};
        for (int v : vals) heap.insert(v);

        assert heap.peekMin() == 1;
        assert heap.extractMin() == 1;
        assert heap.extractMin() == 2;
        assert heap.extractMin() == 3;
        assert heap.extractMin() == 5;
        assert heap.extractMin() == 8;
    }
}