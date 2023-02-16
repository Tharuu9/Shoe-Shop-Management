package lk.ijse.NiharaShoe.entity;

public class Customer {
    private String custID;
    private String custName;
    private String custPhoneNo;
    private String custAddress;
    private String custNic;

    public Customer(String custID, String custName, String custPhoneNo, String custAddress, String custNic) {
        this.custID = custID;
        this.custName = custName;
        this.custPhoneNo = custPhoneNo;
        this.custAddress = custAddress;
        this.custNic = custNic;
    }

    public Customer() {
    }

    public String getCustID() {
        return custID;
    }

    public void setCustID(String custID) {
        this.custID = custID;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustPhoneNo() {
        return custPhoneNo;
    }

    public void setCustPhoneNo(String custPhoneNo) {
        this.custPhoneNo = custPhoneNo;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    public String getCustNic() {
        return custNic;
    }

    public void setCustNic(String custNic) {
        this.custNic = custNic;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "custID='" + custID + '\'' +
                ", custName='" + custName + '\'' +
                ", custPhoneNo='" + custPhoneNo + '\'' +
                ", custAddress='" + custAddress + '\'' +
                ", custNic='" + custNic + '\'' +
                '}';
    }
}
