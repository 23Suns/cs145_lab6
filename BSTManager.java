import java.util.Scanner;

public class BSTManager {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Dictionary dictionary = new Dictionary();
		int choice = 0;

		// Create some Person objects with different details
		Person person1 = new Person(
				"John", "Doe", // First and last name
				"123 Main St", "Anytown", // Address (street, city)
				"CA", "12345", // State and zip code
				"john.doe@example.com", // Email
				"1234567890", // Phone number
				"Member" // Membership level
		);

		Person person2 = new Person(
				"Jane", "Smith", // First and last name
				"456 Elm St", "Anytown", // Address (street, city)
				"CA", "90210", // State and zip code
				"jane.smith@example.com", // Email
				"9876543210", // Phone number
				"Employee" // Membership level
		);

		// Third person object example:
		Person person3 = new Person(
				"Bob", "Johnson", // First and last name
				"789 Oak Ave", "Othertown", // Address (street, city)
				"NY", "12345", // State and zip code
				"bob.johnson@example.net", // Email
				"5551234567", // Phone number
				"Customer" // Membership level
		);

		// Insert Person objects into the dictionary (BST)
		dictionary.insert(person1);
		dictionary.insert(person2);
		dictionary.insert(person3);

		// Main program loop
		do {
			System.out.println("\nMenu:");
			System.out.println("1. Search for a person");
			System.out.println("2. Modify a person");
			System.out.println("3. Exit");
			System.out.print("Enter your choice: ");

			choice = Integer.parseInt(scanner.nextLine());

			switch (choice) {
				case 1:
					// Search for a person (prompt user for details, call searchHelper)
					break;
				case 2:
					// Modify a person (prompt user for details, call searchHelper and modify)
					break;
				case 3:
					System.out.println("Exiting program.");
					break;
				default:
					System.out.println("Invalid choice.");
			}
		} while (choice != 3);

		scanner.close();
	}
}
