import java.lang.reflect.Member;

public class Dictionary {

	private TreeNode root; // TreeNode stores Person objects

	public Dictionary() {
		this.root = null;
	}

	// methods for searching, deletion, and traversal (implementation not shown)

	// method to insert a person object into the BST based on hierarchy
	public void insert(Person person) {
		// base case (empty tree)
		if (root == null) {
			root = new TreeNode(person);
			return;
		}
		insertHelper(root, person);
	}

	private void insertHelper(TreeNode node, Person person) {
		if (node == null) {
			root = new TreeNode(person);
			return;
		}
		// comparison logic based on hierarchy (replace with your specific logic)
		int comparison = person.getClass().getName().compareTo(node.getValue().getClass().getName());

		if (comparison < 0) {
			// lower value (or same value for duplicates) goes to left subtree
			if (node.getLeftNode() == null) {
				node.setLeftNode(new TreeNode(person));
			} else {
				// check if the left Node has the same type (duplicate)
				if (node.getLeftNode().getValue().getClass() == person.getClass()) {
					// make the new person object the left Node (sequential insertion)
					node.setLeftNode(new TreeNode(person));
				} else {
					insertHelper(node.getLeftNode(), person); // not a duplicate, recurse left
				}
			}
		} else if (comparison > 0) {
			// higher value goes to right subtree
			if (node.getRightNode() == null) {
				node.setRightNode(new TreeNode(person));
			} else {
				insertHelper(node.getRightNode(), person);
			}
		} else {
			// handle true duplicates
			// - throw exception (maintain BST property)
			// - implement a separate list for duplicates
		}
	}

	// Helper method to compare person types (implementation provided)
	private int comparePersonTypes(String type1, String type2) {
		// Implement logic to compare person types based on your hierarchy
		if (type1.equals(Customer.class.getName())) {
			return -1; // Customer has lower hierarchy
		} else if (type1.equals(Member.class.getName())) {
			return type2.equals(Employee.class.getName()) ? -1 : 1; // Member is between Customer and Employee
		} else if (type1.equals(Employee.class.getName())) {
			return 1; // Employee has highest hierarchy
		} else {
			// Handle unexpected class types (optional)
			throw new IllegalArgumentException("Unsupported person type: " + type1);
		}
	}

	private class TreeNode {

		private Person value;
		private TreeNode leftNode;
		private TreeNode rightNode;

		public TreeNode(Person value) {
			this.value = value;
			this.leftNode = null;
			this.rightNode = null;
		}
		// Getter and setter methods for value, leftNode, rightNode (not shown)
	}
}
