public abstract class Person {

	// Attributes
	private String firstName;
	private String lastName;
	private String streetAddress;
	private String city;
	private String state;
	private String zip;
	private String email;
	private String phoneNumber;
	private String title;

	// Constructor
	public Person(
			String firstName,
			String lastName,
			String streetAddress,
			String city,
			String state,
			String zip,
			String email,
			String phoneNumber,
			String title) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.title = title;
	}

	// Getters and Setters
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	// toString method
	@Override
	public String toString() {
		return ("Person{" +
				"firstName='" +
				firstName +
				'\'' +
				", lastName='" +
				lastName +
				'\'' +
				", streetAddress='" +
				streetAddress +
				'\'' +
				", city='" +
				city +
				'\'' +
				", state='" +
				state +
				'\'' +
				", zip='" +
				zip +
				'\'' +
				", email='" +
				email +
				'\'' +
				", phoneNumber='" +
				phoneNumber +
				'\'' +
				", title='" +
				title +
				'\'' +
				'}');
	}
}
