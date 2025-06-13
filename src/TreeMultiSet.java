public class TreeMultiSet extends MultiSet {
    private Tree tree;
    /** Add the given item to this multiset.
     * @param item the item to add
     */
    public TreeMultiSet() {
        this.tree = new Tree(null);
    }
    @Override
    void add(Integer item) {
        this.tree.insert(item);
    }
    @Override
    void remove(Integer item) {
        this.tree.delete(item);
    }
    @Override
    boolean contains(Integer item) {
        return this.tree.contains(item);
    }
    @Override
    boolean isEmpty() {
        return this.tree.isEmpty();
    }
    @Override
    int count(Integer item) {
        return this.tree.count(item);
    }
    @Override
    int size() {
        return this.tree.len();
    }
}
