import java.util.ArrayList;
import java.util.Objects;

public class Tree {
	
	private Integer root;
	private ArrayList<Tre	e> subtrees;
	
	/*
		RP-IV:
		- If root is None, then subtrees is an empty list.
	*/
	
	public Tree(Integer root, ArrayList<Tree> subtrees) {
		this.root = root;
		this.subtrees = new ArrayList<Tree>(subtrees);
	}
	
    public Tree() {
        this(null, new ArrayList<>());
    }
    
	public boolean is_empty() {
		return root == null;
	}
	
	public int size() {
		if (this.is_empty()) {
			return 0;
		}
		else {
			int size = 1;
			for(Tree subtree: subtrees) {
				size += subtree.size();
			} 
			return size;
		}
	}
	
	public int count(int item) {
		if (this.is_empty()) {
			return 0;
		} else {
			int num = 0;

			if (root == item) {
				num ++;
			}

			for (Tree subtree: subtrees) {
				num += subtree.count(item);
			}
			
			return num;
		}
	}
	
	public float average() {
		if (this.is_empty()) {
			return 0.0f;
		}
		int[] result = this.average_helper();
		return (float) result[0] / result[1];
	}
	
	public boolean contains(int item) {
		if (this.is_empty()) {
			return false;
		}
		
		if (item == root) {
			return true;
		} else {
			for (Tree subtree: subtrees) {
				if (subtree.contains(item)) {
					return true;
				}
			}
			return false;
		}
	}

	public ArrayList<Integer> leaves() {
    ArrayList<Integer> result = new ArrayList<>();
    
    if (this.is_empty()) {
        return result;
    }
    
    if (subtrees.isEmpty()) {
        result.add(root);
    } else {
        for (Tree subtree : subtrees) {
            result.addAll(subtree.leaves());
        	}
    	}
    return result;
	}
	
// Mutating methods,

	

// Inherited methods,
	
	@Override
	public String toString() {
		return this.str_indented(0);
	}
	
	@Override
	public boolean equals(Object obj) {
    	if (this == obj) {
        	return true;
    	}
    	
    	if (obj == null || getClass() != obj.getClass()) {
        	return false;
    	}
    	Tree other = (Tree) obj;
    	if (root == null ? other.root != null : !root.equals(other.root)) {
        	return false;
    	}
    	return subtrees.equals(other.subtrees);
    }

// Helpers,
	
	private String str_indented(int depth) {
		if (this.is_empty()) {
			return "";
		} else {
			StringBuilder s = new StringBuilder(" ".repeat(depth) + root + "\n");
			for (Tree subtree: subtrees) {
				s.append(subtree.str_indented(depth + 1));
			}
			return s.toString();
		}
	}
	
	private int[] average_helper() {
		if (this.is_empty()) {
			return new int[] {0, 0};
		} else {
			int total = root != null ? root : 0;
			int size = 1;
			for (Tree subtree: subtrees) {
				int[] subtreeResult = subtree.average_helper();
				total += subtreeResult[0];
				size += subtreeResult[1];
			}
			return new int[] {total, size}; 
		}
	}
}
