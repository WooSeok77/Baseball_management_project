package service;

import dao.StadiumDAO;
import model.Stadium;

import java.sql.SQLException;
import java.util.List;

public class StadiumService {
    private final StadiumDAO stadiumDAO;

    public StadiumService(StadiumDAO stadiumDAO) {
        this.stadiumDAO = stadiumDAO;
    }

    public void createStadium(String name) {
        try {
            stadiumDAO.createStadium(name);
        }catch(SQLException | RuntimeException e){
            e.printStackTrace();
        }
    }

    public List<Stadium> findAll() {
        return stadiumDAO.findAll();
    }


}
