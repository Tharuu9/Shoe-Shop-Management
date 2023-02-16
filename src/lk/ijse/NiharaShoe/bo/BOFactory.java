package lk.ijse.NiharaShoe.bo;

import lk.ijse.NiharaShoe.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }
    public static BOFactory getBoFactory(){
        if(boFactory==null){
            boFactory=new BOFactory();
        }
        return boFactory;
    }
    public Object getBO(BOType boType) {
        switch (boType) {
            case CUSTOMER_CONTROLLER:
                return new CustomerBOImpl();

            case ITEM_CONTROLLER:
                return new ItemBOImpl();

                case EMPLOYEE_CONTROLLER:
                return new EmployeeBOImpl();

            case SUPPLIER_CONTROLLER:
                return new SupplierBOImpl();

            case PLACE_ORDER_CONTROLLER:
                return new PlaceOrderBOImpl();

            default:
                return null;

        }
    }
    public enum BOType{
        CUSTOMER_CONTROLLER,ITEM_CONTROLLER,EMPLOYEE_CONTROLLER,SUPPLIER_CONTROLLER,PLACE_ORDER_CONTROLLER
    }
}
