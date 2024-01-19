package models;

public class Manager extends Guest {


    private double salary;
    private double bankAccountBalance;
    String employeeId;

    public Manager(String nationalCode,
                   String firstName,
                   String lastName,
                   String email,
                   String password,
                   double salary,
                   double bankAccountBalance,
                   String employeeId) {
        super(nationalCode, firstName, lastName, email, password);
        this.salary = salary;
        this.bankAccountBalance = bankAccountBalance;
        this.employeeId = employeeId;
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

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }


}



