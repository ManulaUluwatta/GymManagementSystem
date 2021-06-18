package lk.gym.dao.custom.impl;

import lk.gym.dao.custom.PackageDAO;
import lk.gym.db.ConnectionFactory;
import lk.gym.dto.MemberDTO;
import lk.gym.dto.PackageDTO;
import lk.gym.dto.SuperDTO;

import java.sql.*;
import java.util.ArrayList;

public class PackageDOAImpl implements PackageDAO {
    @Override
    public boolean add(PackageDTO dto) throws ClassNotFoundException, SQLException {
        String sql = "insert into package values(?,?,?,?)";
        Connection connection = ConnectionFactory.getInstance().getConnection();
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setObject(1,dto.getPackageID());
        stm.setObject(2,dto.getPackageType());
        stm.setObject(3,dto.getPackageFee());
        stm.setObject(4,dto.getMonths());


        int res = stm.executeUpdate();
        if(res > 0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean update(PackageDTO dto) throws ClassNotFoundException, SQLException {
        String sql="update package set packageType=?,packageFee=?, months=? where packageID=?";
        Connection connection=ConnectionFactory.getInstance().getConnection();
        PreparedStatement stm=connection.prepareStatement(sql);
        stm.setObject(1 ,dto.getPackageType());
        stm.setObject(2,dto.getPackageFee());
        stm.setObject(3,dto.getMonths());
        stm.setObject(4,dto.getPackageID());
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
        String sql="delete from package where packageID='"+id+"' ";
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
    public ArrayList<PackageDTO> view() throws ClassNotFoundException, SQLException {
        Connection connection=ConnectionFactory.getInstance().getConnection();
        Statement stm=connection.createStatement();
        String sql="select * from package";
        ResultSet rst=stm.executeQuery(sql);
        ArrayList<PackageDTO> packageDTOS = new ArrayList<>();
        while (rst.next()){
            PackageDTO c=new PackageDTO(
                    rst.getString(1),
                    rst.getString(2),
                    Double.parseDouble(rst.getString(3)),
                    rst.getInt(4)

            );
            packageDTOS.add(c);
        }
        return packageDTOS;
    }

    @Override
    public ArrayList<PackageDTO> getName(String name) throws ClassNotFoundException, SQLException {
        Connection connection=ConnectionFactory.getInstance().getConnection();
        Statement stm=connection.createStatement();
        String sql="select * from package where packageType like '%"+name+"%'";
        ResultSet rst=stm.executeQuery(sql);
        ArrayList<PackageDTO> packageDTOS = new ArrayList<>();
        while (rst.next()){
            PackageDTO c=new PackageDTO(
                    rst.getString(1),
                    rst.getString(2),
                    Double.parseDouble(rst.getString(3)),
                    rst.getInt(4)
            );
            packageDTOS.add(c);
        }
        return packageDTOS;
    }

    @Override
    public ArrayList<PackageDTO> getID(String id) throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public boolean updateExDate(PackageDTO dto) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public ArrayList<PackageDTO> searchByID(String id) throws ClassNotFoundException, SQLException {
        return null;
    }
}
