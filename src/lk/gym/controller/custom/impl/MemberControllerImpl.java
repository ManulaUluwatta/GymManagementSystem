package lk.gym.controller.custom.impl;

import lk.gym.controller.custom.MemberController;
import lk.gym.dao.DAOFactory;
import lk.gym.dao.custom.MemberDAO;
import lk.gym.dto.MemberDTO;
import lk.gym.dto.SuperDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class MemberControllerImpl implements MemberController {
    private MemberDAO memberDAO;

    public MemberControllerImpl() {
        memberDAO = (MemberDAO) DAOFactory.getInstance().getDAO(DAOFactory.DOATypes.MEMBER);
    }


    @Override
    public boolean add(MemberDTO dto) throws ClassNotFoundException, SQLException {
        return memberDAO.add(dto);
    }

    @Override
    public boolean update(MemberDTO dto) throws ClassNotFoundException, SQLException {
        return memberDAO.update(dto);
    }

    @Override
    public boolean delete(String name) throws ClassNotFoundException, SQLException {
        return memberDAO.delete(name);
    }

    @Override
    public SuperDTO search(String name) throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public ArrayList<MemberDTO> view() throws ClassNotFoundException, SQLException {
        return memberDAO.view();
    }

    @Override
    public ArrayList<MemberDTO> getName(String name) throws ClassNotFoundException, SQLException {
        return memberDAO.getName(name);
    }

    @Override
    public ArrayList<MemberDTO> getID(String id) throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public boolean updateExDate(MemberDTO dto) throws ClassNotFoundException, SQLException {
        return memberDAO.updateExDate(dto);
    }

    @Override
    public ArrayList<MemberDTO> searchByID(String id) throws ClassNotFoundException, SQLException {
        return memberDAO.searchByID(id);
    }

    @Override
    public ArrayList<MemberDTO> getDetailByCode(String code) throws ClassNotFoundException, SQLException {
        return null;
    }
}
