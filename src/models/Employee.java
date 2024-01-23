package models;

public class Employee {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String nationalCode;
    private double salary;
    private double bankAccountBalance;
    // Add more fields as needed

    // Constructors
    public Employee() {
    }

    public Employee(
            String id,
            String firstName,
            String lastName,
            String email,
            String password,
            String nationalCode,
            double salary,
            double bankAccountBalance) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.nationalCode = nationalCode;
        this.salary = salary;
        this.bankAccountBalance = bankAccountBalance;
    }

    // Getter and Setter methods
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getBankAccountBalance() {
        return bankAccountBalance;
    }

    public void setBankAccountBalance(double bankAccountBalance) {
        this.bankAccountBalance = bankAccountBalance;
    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }


    // You can add more methods as needed
}
