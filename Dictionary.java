import java.util.Scanner;

public class Dictionary {

	private TreeNode root; // TreeNode stores Person objects

	public Dictionary() {
		this.root = null;
	}

	// insertion method
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
			throw new IllegalArgumentException("Duplicate person cannot be inserted. BST requires unique keys.");
		}
	}

	// method to compare person types (implementation provided)
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

	// deletion method
	public boolean delete(String title, String firstName, String lastName) {
		TreeNode parentNode = null;
		TreeNode nodeToDelete = root;

		// Find the node to delete and its parent based on title, firstName, and
		// lastName
		while (nodeToDelete != null &&
				!(nodeToDelete.getValue().getTitle().equals(title) &&
						nodeToDelete.getValue().getFirstName().equals(firstName) &&
						nodeToDelete.getValue().getLastName().equals(lastName))) {
			parentNode = nodeToDelete;
			if (title.compareTo(nodeToDelete.getValue().getTitle()) < 0 ||
					(title.equals(nodeToDelete.getValue().getTitle()) &&
							(firstName.compareTo(nodeToDelete.getValue().getFirstName()) < 0 ||
									(firstName.equals(nodeToDelete.getValue().getFirstName()) &&
											lastName.compareTo(nodeToDelete.getValue().getLastName()) < 0)))) {
				nodeToDelete = nodeToDelete.getLeftNode();
			} else {
				nodeToDelete = nodeToDelete.getRightNode();
			}
		}

		// Node not found
		if (nodeToDelete == null) {
			return false;
		}

		// Handle deletion based on node type (leaf, one child, two children)
		deleteHelper(parentNode, nodeToDelete);
		return true;
	}

	private void deleteHelper(TreeNode parentNode, TreeNode nodeToDelete) {
		// Leaf node (no children)
		if (nodeToDelete.getLeftNode() == null && nodeToDelete.getRightNode() == null) {
			if (parentNode == null) {
				root = null; // Deleting the root (empty tree)
			} else if (parentNode.getLeftNode() == nodeToDelete) {
				parentNode.setLeftNode(null);
			} else {
				parentNode.setRightNode(null);
			}
			return;
		}

		// Node with one child
		if (nodeToDelete.getLeftNode() == null) {
			// Promote the right child
			if (parentNode == null) {
				root = nodeToDelete.getRightNode();
			} else if (parentNode.getLeftNode() == nodeToDelete) {
				parentNode.setLeftNode(nodeToDelete.getRightNode());
			} else {
				parentNode.setRightNode(nodeToDelete.getRightNode());
			}
		} else if (nodeToDelete.getRightNode() == null) {
			// Promote the left child
			if (parentNode == null) {
				root = nodeToDelete.getLeftNode();
			} else if (parentNode.getLeftNode() == nodeToDelete) {
				parentNode.setLeftNode(nodeToDelete.getLeftNode());
			} else {
				parentNode.setRightNode(nodeToDelete.getLeftNode());
			}
		} else {
			// Node with two children (find in-order successor)
			TreeNode successor = findInOrderSuccessor(nodeToDelete);
			if (successor.getValue().equals(nodeToDelete.getValue())) {
				throw new IllegalStateException("Cannot delete node with duplicates. Maintain BST property.");
			}
		}
	}

	private TreeNode findInOrderSuccessor(TreeNode node) {
		TreeNode successor = node.getRightNode();
		while (successor != null && successor.getLeftNode() != null) {
			successor = successor.getLeftNode();
		}
		return successor;
	}

	// modify method
	public boolean modify(Person personToModify, Scanner scanner) {
		// Find the person to modify in the BST by searching based on personToModify's
		// details
		TreeNode nodeToModify = searchHelper(root, personToModify.getTitle(), personToModify.getFirstName(),
				personToModify.getLastName());

		// Person not found
		if (nodeToModify == null) {
			System.out.println("Person not found.");
			return false;
		}

		// Display current information of the person
		System.out.println("\nCurrent information:");
		System.out.println(nodeToModify.getValue());

		do {
			// prompt user for the field to modify
			System.out.println("\nWhat information would you like to modify?");
			System.out.println("0. Exit");
			System.out.println("1. Title");
			System.out.println("2. First Name");
			System.out.println("3. Last Name");
			System.out.println("4. Street Address");
			System.out.println("5. City");
			System.out.println("6. State");
			System.out.println("7. Zip");
			System.out.println("8. Email");
			System.out.println("9. Phone Number");
			System.out.print("Enter your choice (0-9): ");

			int choice = Integer.parseInt(scanner.nextLine());

			// Get new value based on user's choice
			String newValue;
			switch (choice) {
				case 0:
					System.out.println("Exiting modification.");
					return false; // Exit if user chooses 0
				case 1:
					System.out.print("Enter new title: ");
					newValue = scanner.nextLine();
					nodeToModify.getValue().setTitle(newValue);
					break;
				case 2:
					System.out.print("Enter new first name: ");
					newValue = scanner.nextLine();
					nodeToModify.getValue().setFirstName(newValue);
					break;
				case 3:
					System.out.print("Enter new last name: ");
					newValue = scanner.nextLine();
					nodeToModify.getValue().setLastName(newValue);
					break;
				case 4:
					System.out.print("Enter new street address: ");
					newValue = scanner.nextLine();
					nodeToModify.getValue().setStreetAddress(newValue);
					break;
				case 5:
					System.out.print("Enter new city: ");
					newValue = scanner.nextLine();
					nodeToModify.getValue().setCity(newValue);
					break;
				case 6:
					System.out.print("Enter new state: ");
					newValue = scanner.nextLine();
					nodeToModify.getValue().setState(newValue);
					break;
				case 7:
					System.out.print("Enter new zip: ");
					newValue = scanner.nextLine();
					nodeToModify.getValue().setZip(newValue);
					break;
				case 8:
					System.out.print("Enter new email: ");
					newValue = scanner.nextLine();
					nodeToModify.getValue().setEmail(newValue);
					break;
				case 9:
					System.out.print("Enter new phone number: ");
					newValue = scanner.nextLine();
					nodeToModify.getValue().setPhoneNumber(newValue);
					break;
				default:
					System.out.println("Invalid choice.");
					break;
			}

			// Ask if user wants to modify another field
			System.out.print("Do you want to modify another field (y/n)? ");
		} while (scanner.nextLine().equalsIgnoreCase("y"));

		System.out.println("Entry successfully modified.");
		return true;
	}

	// Get the parent of a given Person object
	public Person getParent(Person person) {
		TreeNode parentNode = getParentNode(root, person);
		return parentNode == null ? null : parentNode.getValue();
	}

	private TreeNode getParentNode(TreeNode node, Person person) {
		if (node == null) {
			return null; // Person not found or empty tree
		}

		if (node.getLeftNode() != null && node.getLeftNode().getValue().equals(person)) {
			return node; // Person is the left child of node
		}

		if (node.getRightNode() != null && node.getRightNode().getValue().equals(person)) {
			return node; // Person is the right child of node
		}

		// Recursively search for the person in the subtrees
		TreeNode parentInLeft = getParentNode(node.getLeftNode(), person);
		TreeNode parentInRight = getParentNode(node.getRightNode(), person);
		return parentInLeft != null ? parentInLeft : parentInRight;
	}

	// searching method
	public Person search(String title, String firstName, String lastName) {
		return searchHelper(root, title, firstName, lastName);
	}

	public TreeNode searchHelper(TreeNode root, String title, String firstName, String lastName) {
		if (root == null || (title.compareTo(root.getValue().getTitle()) == 0 &&
				firstName.compareTo(root.getValue().getFirstName()) == 0 &&
				lastName.compareTo(root.getValue().getLastName()) == 0)) {
			return root;
		}

		if (title.compareTo(root.getValue().getTitle()) < 0) {
			return searchHelper(root.getLeftNode(), title, firstName, lastName);
		} else {
			return searchHelper(root.getRightNode(), title, firstName, lastName);
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

		// public void setValue(Person value) {
		// 	this.value = value;
		// }

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
