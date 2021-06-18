package lk.gym.dao.custom.impl;

import lk.gym.dao.custom.WeightDAO;
import lk.gym.db.ConnectionFactory;
import lk.gym.dto.SuperDTO;
import lk.gym.dto.WeightDTO;

import java.sql.*;
import java.util.ArrayList;

public class WeightDAOImpl implements WeightDAO {
    @Override
    public boolean add(WeightDTO w) throws ClassNotFoundException, SQLException {
        String sql = "insert into weight values(?,?,?,?)";
        Connection connection = ConnectionFactory.getInstance().getConnection();
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setObject(1,w.getWeightID());
        stm.setObject(2,w.getMemberID());
        stm.setObject(3,w.getWeight());
        stm.setObject(4,w.getDate());


        int res = stm.executeUpdate();
        if(res > 0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean update(WeightDTO dto) throws ClassNotFoundException, SQLException {
        String sql="update weight set memberID=?,weight=?, date=? where weightID=?";
        Connection connection=ConnectionFactory.getInstance().getConnection();
        PreparedStatement stm=connection.prepareStatement(sql);
        stm.setObject(1,dto.getMemberID());
        stm.setObject(2,dto.getWeight());
        stm.setObject(3,dto.getDate());
        stm.setObject(4,dto.getWeightID());
        int res=stm.executeUpdate();
        if(res>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean delete(String id) throws ClassNotFoundException, SQLException {
        Connection connection=ConnectionFactory.getInstance().getConnection();
        Statement stm=connection.createStatement();
        String sql="delete from weight where weightID='"+id+"' ";
        int res=stm.executeUpdate(sql);
        if(res>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public SuperDTO search(String name) throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public ArrayList<WeightDTO> view() throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public ArrayList<WeightDTO> getName(String name) throws ClassNotFoundException, SQLException {
        Connection connection=ConnectionFactory.getInstance().getConnection();
        Statement stm=connection.createStatement();
        String sql="select * from weight where memberID = '"+name+"'";
        ResultSet rst=stm.executeQuery(sql);
        ArrayList<WeightDTO> weightDTOS = new ArrayList<>();
        while (rst.next()){
            WeightDTO w=new WeightDTO(
                    rst.getString(1),
                    rst.getString(4),
                    Double.parseDouble(rst.getString(3))
            );
            weightDTOS.add(w);
        }
        return weightDTOS;
    }

    @Override
    public ArrayList<WeightDTO> getID(String id) throws ClassNotFoundException, SQLException {
        Connection connection=ConnectionFactory.getInstance().getConnection();
        Statement stm=connection.createStatement();
        String sql="select * from weight where weightID ='"+id+"'";
        ResultSet rst=stm.executeQuery(sql);
        ArrayList<WeightDTO> weightDTOS = new ArrayList<>();
        while (rst.next()){
            WeightDTO w=new WeightDTO(
                    rst.getString(2)
            );
            weightDTOS.add(w);
        }
        return weightDTOS;
    }

    @Override
    public boolean updateExDate(WeightDTO dto) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public ArrayList<WeightDTO> searchByID(String id) throws ClassNotFoundException, SQLException {
        return null;
    }
}
