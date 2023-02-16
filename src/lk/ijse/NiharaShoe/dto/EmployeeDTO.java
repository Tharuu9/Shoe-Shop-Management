package lk.ijse.NiharaShoe.dto;

public class EmployeeDTO {
    private String empID;
    private String empName;
    private String empContact;
    private String empAddress;
    private String empNic;
    private double empSalary;

    public EmployeeDTO(String empID, String empName, String empContact, String empAddress, String empNic, double empSalary) {
        this.empID = empID;
        this.empName = empName;
        this.empContact = empContact;
        this.empAddress = empAddress;
        this.empNic = empNic;
        this.empSalary = empSalary;
    }

    public EmployeeDTO() {

    }

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpContact() {
        return empContact;
    }

    public void setEmpContact(String empContact) {
        this.empContact = empContact;
    }

    public String getEmpAddress() {
        return empAddress;
    }

    public void setEmpAddress(String empAddress) {
        this.empAddress = empAddress;
    }

    public String getEmpNic() {
        return empNic;
    }

    public void setEmpNic(String empNic) {
        this.empNic = empNic;
    }

    public double getEmpSalary() {
        return empSalary;
    }

    public void setEmpSalary(double empSalary) {
        this.empSalary = empSalary;
    }

    @Override
    public String toString() {
        return "EmployeeModel{" +
                "empID='" + empID + '\'' +
                ", empName='" + empName + '\'' +
                ", empContact='" + empContact + '\'' +
                ", empAddress='" + empAddress + '\'' +
                ", empNic='" + empNic + '\'' +
                ", empSalary=" + empSalary +
                '}';
    }
}
