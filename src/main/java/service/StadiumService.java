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

    public String createStadium(String name) {
        try {
           int rs = stadiumDAO.createStadium(name);
           if (rs >0){
               stadiumDAO.createStadium(name);
               return "성공";
           } else {
               return "실패";
           }
        }catch(SQLException | RuntimeException e){
            e.printStackTrace();
            return "예외실패";
        }
    }

    public List<Stadium> findAll() {
        List<Stadium> stadiumList = stadiumDAO.findAll();

        return stadiumList;
    }


}
