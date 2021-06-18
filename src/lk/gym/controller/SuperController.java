package lk.gym.controller;

import lk.gym.dto.SuperDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SuperController<T extends SuperDTO> {
    public boolean add(T dto)throws ClassNotFoundException, SQLException;
    public boolean update(T dto)throws ClassNotFoundException,SQLException;
    public boolean delete(String name)throws ClassNotFoundException,SQLException;
    public SuperDTO search(String name)throws ClassNotFoundException,SQLException;
    public ArrayList<T> view()throws ClassNotFoundException,SQLException;
    public ArrayList<T> getName(String name)throws ClassNotFoundException,SQLException;
    public ArrayList<T> getID(String id)throws ClassNotFoundException,SQLException;
    public boolean updateExDate(T dto)throws ClassNotFoundException,SQLException;
    public ArrayList<T> searchByID(String id) throws ClassNotFoundException, SQLException;
    public ArrayList<T> getDetailByCode(String code)throws ClassNotFoundException,SQLException;

}
