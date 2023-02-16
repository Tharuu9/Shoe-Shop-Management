package lk.ijse.NiharaShoe.entity;

public class Supplier {
    private String supplierID;
    private String supplierName;
    private String supplierAddress;
    private String supplierPhoneNo;
    private String supplierEmail;

    public Supplier(String supplierID, String supplierName, String supplierAddress, String supplierPhoneNo, String supplierEmail) {
        this.supplierID = supplierID;
        this.supplierName = supplierName;
        this.supplierAddress = supplierAddress;
        this.supplierPhoneNo = supplierPhoneNo;
        this.supplierEmail = supplierEmail;
    }

    public Supplier() {
    }

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierAddress() {
        return supplierAddress;
    }

    public void setSupplierAddress(String supplierAddress) {
        this.supplierAddress = supplierAddress;
    }

    public String getSupplierPhoneNo() {
        return supplierPhoneNo;
    }

    public void setSupplierPhoneNo(String supplierPhoneNo) {
        this.supplierPhoneNo = supplierPhoneNo;
    }

    public String getSupplierEmail() {
        return supplierEmail;
    }

    public void setSupplierEmail(String supplierEmail) {
        this.supplierEmail = supplierEmail;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "supplierID='" + supplierID + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", supplierAddress='" + supplierAddress + '\'' +
                ", supplierPhoneNo='" + supplierPhoneNo + '\'' +
                ", supplierEmail='" + supplierEmail + '\'' +
                '}';
    }
}
