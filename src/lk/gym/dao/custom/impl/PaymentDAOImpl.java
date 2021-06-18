package lk.gym.dao.custom.impl;

import lk.gym.dao.custom.PaymentDAO;
import lk.gym.db.ConnectionFactory;
import lk.gym.dto.PaymentDTO;
import lk.gym.dto.SuperDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public boolean add(PaymentDTO dto) throws ClassNotFoundException, SQLException {
        String sql = "insert into payment(memberID,paymentFee,paymentDate,expireDate) values(?,?,?,?)";
        Connection connection = ConnectionFactory.getInstance().getConnection();
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setObject(1,dto.getMemberID());
        stm.setObject(2,dto.getPaymentFee());
        stm.setObject(3,dto.getPaymentDate());
        stm.setObject(4,dto.getExpireDate());


        int res = stm.executeUpdate();
        if(res > 0){
            return true;
        }else {
            return false;
        }
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
}
