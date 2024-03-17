import java.lang.reflect.Member;

public class Dictionary {

	private TreeNode root; // TreeNode stores Person objects

	public Dictionary() {
		this.root = null;
	}

	// methods for searching
	public Person search(String title, string firstName, string lastName) {
		return searchHelper(root, title, firstName, lastName);
	}

	private Person searchHelper(TreeNode node, String title, String firstName, String lastName) {
		if (node == null) {
			return null; // Person not found
		} else if (node.getValue().getTitle().equals(title) &&
				node.getValue().getFirstName().equals(firstName) &&
				node.getValue().getLastName().equals(lastName)) {
			return node.getValue(); // Person found with matching title, firstName, and lastName
		} else if (title.compareTo(node.getValue().getTitle()) < 0 ||
				(title.equals(node.getValue().getTitle()) &&
						(firstName.compareTo(node.getValue().getFirstName()) < 0 ||
								(firstName.equals(node.getValue().getFirstName()) &&
										lastName.compareTo(node.getValue().getLastName()) < 0)))) {
			return searchHelper(node.getLeftNode(), title, firstName, lastName); // Search left subtree
		} else {
			return searchHelper(node.getRightNode(), title, firstName, lastName); // Search right subtree
		}
	}

	// pre-order traversal
	public void preOrderTraversal() {
		preOrderHelper(root);
	}

	private void preOrderHelper(TreeNode node) {
		if (node == null) {
			return;
		}
		// Visit the root (process the Person object)
		System.out.println(node.getValue());
		preOrderHelper(node.getLeftNode()); // Recursively traverse left subtree
		preOrderHelper(node.getRightNode()); // Recursively traverse right subtree
	}

	// In-order traversal
	public void inOrderTraversal() {
		inOrderHelper(root);
	}

	private void inOrderHelper(TreeNode node) {
		if (node == null) {
			return;
		}
		inOrderHelper(node.getLeftNode()); // Recursively traverse left subtree
		// Visit the root (process the Person object)
		System.out.println(node.getValue());
		inOrderHelper(node.getRightNode()); // Recursively traverse right subtree
	}

	// Post-order traversal
	public void postOrderTraversal() {
		postOrderHelper(root);
	}

	private void postOrderHelper(TreeNode node) {
		if (node == null) {
			return;
		}
		postOrderHelper(node.getLeftNode()); // Recursively traverse left subtree
		postOrderHelper(node.getRightNode()); // Recursively traverse right subtree
		// Visit the root (process the Person object)
		System.out.println(node.getValue());
	}

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

		// Use compareTitle method for hierarchy comparison
		int comparison = compareTitle(person, node.getValue());

		if (comparison < 0) {
			// Lower value (person has lower hierarchy) goes to left subtree
			if (node.getLeftNode() == null) {
				node.setLeftNode(new TreeNode(person));
			} else {
				insertHelper(node.getLeftNode(), person); // recurse left for lower hierarchy
			}
		} else if (comparison > 0) {
			// Higher value (person has higher hierarchy) goes to right subtree
			if (node.getRightNode() == null) {
				node.setRightNode(new TreeNode(person));
			} else {
				insertHelper(node.getRightNode(), person); // recurse right for higher hierarchy
			}
		} else {
			// Handle true duplicates (logic for handling duplicates remains as-is)
			// - throw exception (maintain BST property)
			// - implement a separate list for duplicates
		}
	}

	// Helper method to compare person types (implementation provided)
	private int compareTitle(Person person1, Person person2) {
		String title1 = person1.getTitle();
		String title2 = person2.getTitle();

		// Basic hierarchy based on title prefixes (modify as needed)
		if (title1.startsWith("Customer")) {
			return -1; // Customer has lower hierarchy
		} else if (title1.startsWith("Member")) {
			if (title2.startsWith("Employee")) {
				return -1; // Member is between Customer and Employee
			} else {
				return 1; // Member has higher hierarchy than Customer
			}
		} else if (title1.startsWith("Employee")) {
			return 1; // Employee has highest hierarchy
		} else {
			// Handle unexpected title formats (optional)
			throw new IllegalArgumentException("Unsupported title format: " + title1);
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

		// Getter and setter methods
		public Person getValue() {
			return value;
		}

		public void setValue(Person value) {
			this.value = value;
		}

		public TreeNode getLeftNode() {
			return leftNode;
		}

		public void setLeftNode(TreeNode leftNode) {
			this.leftNode = leftNode;
		}

		public TreeNode getRightNode() {
			return rightNode;
		}

		public void setRightNode(TreeNode rightNode) {
			this.rightNode = rightNode;
		}
	}
}
