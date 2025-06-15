import java.util.ArrayList;

public class ArrayListMultiSet extends MultiSet {
    private ArrayList<Integer> list;

    public ArrayListMultiSet() {
        this.list = new ArrayList<>();
    }
    @Override
    void add(Integer item) {
        this.list.add(item);
    }
    @Override
    void remove(Integer item) {
        if (this.list.contains(item)) {
            this.list.remove(item);
        }
    }
    @Override
    boolean contains(Integer item) {
        return this.list.contains(item);
    }
    @Override
    boolean isEmpty() {
        return this.list.size() == 0;
    }
    @Override
    int count(Integer item) {
        int count = 0;
        for (Integer i : this.list) {
            if (i == item) {
                count++;
            }
        }
        return count;
    }
    @Override
    int size() {
        return this.list.size();
    }
}

