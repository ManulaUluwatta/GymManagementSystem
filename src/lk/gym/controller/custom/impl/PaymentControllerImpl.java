package lk.gym.controller.custom.impl;

import lk.gym.controller.custom.PaymentController;
import lk.gym.dao.DAOFactory;
import lk.gym.dao.custom.PackageDAO;
import lk.gym.dao.custom.PaymentDAO;
import lk.gym.dto.PaymentDTO;
import lk.gym.dto.SuperDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentControllerImpl implements PaymentController {
    private PaymentDAO paymentDAO;

    public PaymentControllerImpl() {
        paymentDAO = (PaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DOATypes.PAYMENT);
    }
    @Override
    public boolean add(PaymentDTO dto) throws ClassNotFoundException, SQLException {
        return paymentDAO.add(dto);
    }

    @Override
    public boolean update(PaymentDTO dto) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public boolean delete(String name) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public SuperDTO search(String name) throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public ArrayList<PaymentDTO> view() throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public ArrayList<PaymentDTO> getName(String name) throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public ArrayList<PaymentDTO> getID(String id) throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public boolean updateExDate(PaymentDTO dto) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public ArrayList<PaymentDTO> searchByID(String id) throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public ArrayList<PaymentDTO> getDetailByCode(String code) throws ClassNotFoundException, SQLException {
        return null;
    }
}
