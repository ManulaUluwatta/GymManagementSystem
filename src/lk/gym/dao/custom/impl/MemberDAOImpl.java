package lk.gym.dao.custom.impl;

import lk.gym.dao.custom.MemberDAO;
import lk.gym.db.ConnectionFactory;
import lk.gym.dto.MemberDTO;
import lk.gym.dto.SuperDTO;

import java.sql.*;
import java.util.ArrayList;

public class MemberDAOImpl implements MemberDAO {
    

    @Override
    public boolean add(MemberDTO member) throws ClassNotFoundException, SQLException {
        String sql = "insert into members values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Connection connection = ConnectionFactory.getInstance().getConnection();
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setObject(1,member.getMemberID());
        stm.setObject(2,member.getPackageID());
        stm.setObject(3,member.getFirstName());
        stm.setObject(4,member.getLastName());
        stm.setObject(5,member.getNic());
        stm.setObject(6,member.getAge());
        stm.setObject(7,member.getContact());
        stm.setObject(8,member.getAddress());
        stm.setObject(9,member.getWeight());
        stm.setObject(10,member.getInDate());
        stm.setObject(11,member.getPackageType());
        stm.setObject(12,member.getExpireDate());
        stm.setObject(13,member.getPath());

        int res = stm.executeUpdate();
        if(res > 0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean update(MemberDTO dto) throws ClassNotFoundException, SQLException {
        String sql="update members set packageID=?,firstName=?,lastName=?,nic=?,age=?,contact=?,address=?,weight=?,inDate=?,packageType=?, expireDate=?, pasth=? where memberID=?";
        Connection connection=ConnectionFactory.getInstance().getConnection();
        PreparedStatement stm=connection.prepareStatement(sql);
        stm.setObject(1,dto.getPackageID());
        stm.setObject(2,dto.getFirstName());
        stm.setObject(3,dto.getLastName());
        stm.setObject(4,dto.getNic());
        stm.setObject(5,dto.getAge());
        stm.setObject(6,dto.getContact());
        stm.setObject(7,dto.getAddress());
        stm.setObject(8,dto.getWeight());
        stm.setObject(9,dto.getInDate());
        stm.setObject(10,dto.getPackageType());
        stm.setObject(11,dto.getExpireDate());
        stm.setObject(12,dto.getPath());
        stm.setObject(13,dto.getMemberID());
        System.out.printf(dto.getExpireDate());
        int res=stm.executeUpdate();
        System.out.printf(res+"");
        if(res>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean delete(String name) throws ClassNotFoundException, SQLException {
        Connection connection=ConnectionFactory.getInstance().getConnection();
        Statement stm=connection.createStatement();
        String sql="delete from members where memberID='"+name+"' ";
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
    public ArrayList<MemberDTO> view() throws ClassNotFoundException, SQLException {
        Connection connection=ConnectionFactory.getInstance().getConnection();
        Statement stm=connection.createStatement();
        String sql="select * from members";
        ResultSet rst=stm.executeQuery(sql);
        ArrayList<MemberDTO> memberList = new ArrayList<>();
        while (rst.next()){
            MemberDTO c=new MemberDTO(
                    rst.getString(1),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getDouble(9),
                    rst.getString(10),
                    rst.getString(11),
                    rst.getString(12),
                    rst.getString(13)

            );
            memberList.add(c);
        }
        return memberList;
    }

    @Override
    public ArrayList<MemberDTO> getName(String name) throws ClassNotFoundException, SQLException {
        Connection connection=ConnectionFactory.getInstance().getConnection();
        Statement stm=connection.createStatement();
        String sql="select * from members where firstName like '%"+name+"%' || lastName like '%"+name+"%'" ;
        ResultSet rst=stm.executeQuery(sql);
        ArrayList<MemberDTO> memberList = new ArrayList<>();
        while (rst.next()){
            MemberDTO c=new MemberDTO(
                    rst.getString(1),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getDouble(9),
                    rst.getString(10),
                    rst.getString(11),
                    rst.getString(12),
                    rst.getString(13)
            );
            memberList.add(c);
        }
        return memberList;
    }

    @Override
    public ArrayList<MemberDTO> getID(String id) throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public boolean updateExDate(MemberDTO dto) throws ClassNotFoundException, SQLException {
        String sql="update members set expireDate=? where memberID=?";
        Connection connection=ConnectionFactory.getInstance().getConnection();
        PreparedStatement stm=connection.prepareStatement(sql);
        stm.setObject(1,dto.getExpireDate());
        stm.setObject(2,dto.getMemberID());
        System.out.printf(dto.getExpireDate());
        int res=stm.executeUpdate();
        System.out.printf(res+"");
        if(res>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public ArrayList<MemberDTO> searchByID(String id) throws ClassNotFoundException, SQLException {
        Connection connection=ConnectionFactory.getInstance().getConnection();
        Statement stm=connection.createStatement();
        String sql="select * from members where memberID like '%"+id+"%'";
        ResultSet rst=stm.executeQuery(sql);
        ArrayList<MemberDTO> memberList = new ArrayList<>();
        while (rst.next()){
            MemberDTO c=new MemberDTO(
                    rst.getString(1),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getDouble(9),
                    rst.getString(10),
                    rst.getString(11),
                    rst.getString(12),
                    rst.getString(13)

            );
            memberList.add(c);
        }
        return memberList;
    }
}
