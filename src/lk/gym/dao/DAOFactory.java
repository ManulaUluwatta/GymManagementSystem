package lk.gym.dao;

import lk.gym.dao.custom.PackageDAO;
import lk.gym.dao.custom.impl.MemberDAOImpl;
import lk.gym.dao.custom.impl.PackageDOAImpl;
import lk.gym.dao.custom.impl.PaymentDAOImpl;
import lk.gym.dao.custom.impl.WeightDAOImpl;

public class DAOFactory {

    public enum DOATypes{
        MEMBER,PAYMENT,WEIGHT,PACKAGE;
    }
    private static DAOFactory daoFactory;

    public DAOFactory() {

    }
    public static DAOFactory getInstance(){
        if (daoFactory==null){
            daoFactory=new DAOFactory();
        }
        return daoFactory;
    }
    public SuperDAO getDAO(DOATypes types) {
        switch (types) {
            case MEMBER:
                return (SuperDAO) new MemberDAOImpl();
            case PAYMENT:
                return (SuperDAO) new PaymentDAOImpl();
            case WEIGHT:
                return (SuperDAO) new WeightDAOImpl();
            case PACKAGE:
                return (SuperDAO) new PackageDOAImpl();
            default:
                return null;
        }
    }

}
