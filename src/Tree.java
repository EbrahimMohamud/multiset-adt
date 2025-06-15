import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/* A recursive tree data structure, which provides services required of the MultiSet ADT.

 *   === Representation Invariants ===
 *   If root is None then subtrees is an empty list.
 *   This setting of attributes represents an empty tree. */

public class Tree {
	// === Private Attributes ===
	private Integer root;  // Wrapper Class Integer (root = null)
	private ArrayList<Tree> subtree;  // ArrayList of TreeClass

	/** Initialize a new Tree with the given root value and subtrees.
	 * If <root> is None, the tree is empty.
	 * Precondition: if <root> is None, then <subtrees> is empty.
	 *
	 * @param root the node value
	 * @param subtree the set of children trees
	 */

	// -------------------------------------------------------------------------
	//                             Constructor
	// -------------------------------------------------------------------------

	public Tree(Integer root, ArrayList<Tree> subtree) {
		this.root = root;

		if (subtree == null) {
			this.subtree = new ArrayList<>();
		} else {
			this.subtree = subtree;
		}
	}
	public Tree(Integer root) {
		this.root = root;
		this.subtree = new ArrayList<>();
	}

	// -------------------------------------------------------------------------
	//                             Non-Mutating methods
	// -------------------------------------------------------------------------

	public boolean isEmpty() {return this.root == null;}

	public int len() {
		if (this.isEmpty()) {
			return 0;
		} else {
			int count = 0;
			for (Tree sub : this.subtree) {
				count += 1 + sub.len();
			}
			return count;
		}
	}

	public int count(int item) {
		if (this.isEmpty()) {
			return 0;
		} else {
			int count = 0;
			if (this.root == item) {
				count += 1;
			}
			for (Tree sub : this.subtree) {
				count += sub.count(item);
			}
			return count;
		}
	}

	@Override
	public String toString() {
		return this.strIndented(0);
	}

	private String strIndented(int depth) {
		if (this.isEmpty()) {
			return "";
		} else {
			StringBuilder s = new StringBuilder(" ".repeat(depth) + root + "\n");
			for (Tree subtree: this.subtree) {
				s.append(subtree.strIndented(depth + 1));
			}
			return s.toString();
		}
	}

	public double average() {
		// Return the average of all the values in this tree.
		int total = this.averageHelp()[0];
		int num = this.averageHelp()[1];

		if (num == 0) {
			return 0.0;
		} else {
			return ((double) total /num);
		}
	}

	private Integer[] averageHelp() {
		// Return the total count and num count.
		if (this.isEmpty()) {
			return new Integer[]{0, 0};
		} else {
			int total = this.root;
			int num = 1;

			for (Tree sub : this.subtree) {
				total += sub.averageHelp()[0];
				num += sub.averageHelp()[1];
			}
			return new Integer[]{total, num};
		}
	}

	public boolean equals(Tree object) {
		if (this.isEmpty() && object.isEmpty()) {
			return true;
		} else if (this.isEmpty() || object.isEmpty()) {
			return false;
		}
		else {
			if (this.len() != object.len()) {
				return false;
			}
			if (this.root != object.root) {
				return false;
			}
			for (int i = 0; i < this.len(); i++) {
				return this.subtree.get(i).equals(object.subtree.get(i));
			}
			return true;
		}
	}

	public boolean contains(int item) {
		if (this.isEmpty()) {
			return false;
		} else if (this.root == item) {
			return true;
		} else {
			for (Tree sub : this.subtree) {
				if (sub.contains(item)) {
					return true;
				}
			}
			return false;
		}
	}

	public ArrayList<Integer> leaves() {
		if (this.isEmpty()) {
			return new ArrayList<>();
		} else if (this.subtree.equals(new ArrayList<>())) {
			return new ArrayList<>(this.root);
		} else {
			ArrayList<Integer> value = new ArrayList<>();
			for (Tree sub : this.subtree) {
				value.addAll(sub.leaves());
			}
			return value;
		}
	}

	// -------------------------------------------------------------------------
	//                             Mutating methods
	// -------------------------------------------------------------------------

	public boolean delete(int item) {
		if (this.isEmpty()) {
			return false;
		} else if (this.root == item) {
			this.deleteRoot();
			return true;
		} else {
			for (Tree subtree : this.subtree) {
				boolean deleted = subtree.delete(item);
				if  (deleted && subtree.isEmpty()) {
					this.subtree.remove(subtree);
					return true;
				} else if (deleted) {
					return true;
				} else {}
			}
			return false;
		}
	}

	public void deleteRoot() {
		if (this.subtree.size() == 0) {
			this.root = null;
		} else {
			Tree chosenTree = this.subtree.remove(this.subtree.size() - 1);
			this.root = chosenTree.root;
			this.subtree.addAll(chosenTree.subtree);
		}
	}
	public int extractLeaf() {
		if (this.subtree.size() == 0) {
			Integer oldRoot = this.root;
			this.root = null;
			return oldRoot;
		} else {
			int leaf = this.subtree.get(0).extractLeaf();
			if (this.subtree.get(0).isEmpty()) {
				this.subtree.remove(0);
			}
			return leaf;
		}
	}

	public void insert(int item) {
		if (this.isEmpty()) {
			this.root = item;
		} else if (this.subtree.size() == 0) {
			this.subtree = new ArrayList<>(Arrays.asList(new Tree(item, new ArrayList<>())));
		} else {
			if (ThreadLocalRandom.current().nextInt(1, 4) == 3) {
				this.subtree.add(new Tree(item, new ArrayList<>()));
			} else {
				int subtreeIndex = ThreadLocalRandom.current().nextInt(0, this.subtree.size() - 1);
				this.subtree.get(subtreeIndex).insert(item);
			}
		}
	}

	public boolean insertChild(int item, int parent) {
		if (this.isEmpty()) {
			return false;
		} else if (this.root == parent) {
			this.subtree.add(new Tree(item, new ArrayList<>()));
			return true;
		} else {
			for (Tree subtree : this.subtree) {
				if (subtree.insertChild(item, parent)) {
					return true;
				}
			}
			return false;
		}
	}
}




