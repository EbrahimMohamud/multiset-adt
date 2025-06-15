import java.lang.classfile.constantpool.IntegerEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static void profileMultiSet(MultiSet myInput, int n) {
        ArrayList<Integer> itemAdded = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int x = ThreadLocalRandom.current().nextInt(0, 100);
            myInput.add(x);
            itemAdded.add(x);
        }
        assert itemAdded.size() == n;

        long start = System.currentTimeMillis();

        for (Integer item : itemAdded) {
            myInput.remove(item);
        }
        long end = System.currentTimeMillis();

        assert myInput.isEmpty();
    }

    public static void main(String[] args) {
        ArrayList<MultiSet> multiSet = new ArrayList<>(
                Arrays.asList(
                        new TreeMultiSet(),
                        new ArrayListMultiSet(),
                        new LinkedListMultiSet()
                )
        );
        int[] values = {500, 1000, 2000, 4000};

        for (MultiSet set : multiSet) {
            for (Integer n : values) {
                profileMultiSet(set, n);
            }
        }
    }
}
