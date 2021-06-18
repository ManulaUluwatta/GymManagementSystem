package lk.gym.controller.custom.impl;

import lk.gym.controller.custom.WeightController;
import lk.gym.dao.DAOFactory;
import lk.gym.dao.custom.WeightDAO;
import lk.gym.dto.SuperDTO;
import lk.gym.dto.WeightDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class WeightControllerImpl implements WeightController {
    private WeightDAO weightDAO;

    public WeightControllerImpl() {
        weightDAO = (WeightDAO) DAOFactory.getInstance().getDAO(DAOFactory.DOATypes.WEIGHT);
    }

    @Override
    public boolean add(WeightDTO dto) throws ClassNotFoundException, SQLException {
        return weightDAO.add(dto);
    }

    @Override
    public boolean update(WeightDTO dto) throws ClassNotFoundException, SQLException {
        return weightDAO.update(dto);
    }

    @Override
    public boolean delete(String name) throws ClassNotFoundException, SQLException {
        return weightDAO.delete(name);
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
        return weightDAO.getName(name);
    }

    @Override
    public ArrayList<WeightDTO> getID(String id) throws ClassNotFoundException, SQLException {
        return weightDAO.getID(id);
    }

    @Override
    public boolean updateExDate(WeightDTO dto) throws ClassNotFoundException, SQLException {
        return false;
    }


    @Override
    public ArrayList<WeightDTO> searchByID(String id) throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public ArrayList<WeightDTO> getDetailByCode(String code) throws ClassNotFoundException, SQLException {
        return null;
    }
}
