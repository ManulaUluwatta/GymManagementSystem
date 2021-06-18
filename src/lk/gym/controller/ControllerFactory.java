package lk.gym.controller;

import lk.gym.controller.custom.impl.MemberControllerImpl;
import lk.gym.controller.custom.impl.PackageControllerImpl;
import lk.gym.controller.custom.impl.PaymentControllerImpl;
import lk.gym.controller.custom.impl.WeightControllerImpl;

public class ControllerFactory {
    private static ControllerFactory controllerFactory;

    public enum controllerType{
        MEMBER,PAYMENT,WEIGHT,PACKAGE;
    }

    public ControllerFactory() {
    }

    public static ControllerFactory getInstance(){
        if(controllerFactory==null){
            controllerFactory=new ControllerFactory();
        }
        return controllerFactory;
    }
    public SuperController getController(controllerType type){
        switch (type){
            case MEMBER:
                return (SuperController) new MemberControllerImpl();
            case PAYMENT:
                return (SuperController) new PaymentControllerImpl();
            case WEIGHT:
                return (SuperController) new WeightControllerImpl();
            case PACKAGE:
                return (SuperController) new PackageControllerImpl();
            default:
                return null;
        }

    }
}
