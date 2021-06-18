package lk.gym.controller.custom.impl;

import lk.gym.controller.custom.PackageController;
import lk.gym.dao.DAOFactory;
import lk.gym.dao.custom.PackageDAO;
import lk.gym.dto.PackageDTO;
import lk.gym.dto.SuperDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class PackageControllerImpl implements PackageController {
    private PackageDAO packageDAO;

    public PackageControllerImpl() {
        packageDAO = (PackageDAO) DAOFactory.getInstance().getDAO(DAOFactory.DOATypes.PACKAGE);
    }

    @Override
    public boolean add(PackageDTO dto) throws ClassNotFoundException, SQLException {
        return packageDAO.add(dto);
    }

    @Override
    public boolean update(PackageDTO dto) throws ClassNotFoundException, SQLException {
        return packageDAO.update(dto);
    }

    @Override
    public boolean delete(String name) throws ClassNotFoundException, SQLException {
        return packageDAO.delete(name);
    }

    @Override
    public SuperDTO search(String name) throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public ArrayList<PackageDTO> view() throws ClassNotFoundException, SQLException {
        return packageDAO.view();
    }

    @Override
    public ArrayList<PackageDTO> getName(String name) throws ClassNotFoundException, SQLException {
        return packageDAO.getName(name);
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

    @Override
    public ArrayList<PackageDTO> getDetailByCode(String code) throws ClassNotFoundException, SQLException {
        return null;
    }
}
