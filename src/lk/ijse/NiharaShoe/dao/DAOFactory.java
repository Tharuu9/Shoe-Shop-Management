package lk.ijse.NiharaShoe.dao;

import lk.ijse.NiharaShoe.dao.custom.OrderDetailsDAO;
import lk.ijse.NiharaShoe.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }
    public static DAOFactory getDaoFactory(){
        if(daoFactory==null){
            daoFactory=new DAOFactory();
        }
        return daoFactory;
    }
    public SuperDAO getDAO(DAOType daoType) {
        switch (daoType) {
            case CUSTOMER:
                return new CustomerDAOImpl();
            case ITEM:
                return new ItemDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();

            case SUPPLIER:
                return new SupplierDAOImpl();

            case ORDER:
                return new OrderDAOImpl();
            case ORDERDETAILS:
                return new OrderDetailsDAOImpl();

            default:
                return null;

        }
    }
    public enum DAOType{
        CUSTOMER,ITEM,EMPLOYEE,SUPPLIER,ORDER,ORDERDETAILS
    }
}
