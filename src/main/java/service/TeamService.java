package service;

import dao.TeamDAO;
import model.Team;

import java.sql.SQLException;
import java.util.List;

public class TeamService {

    private final TeamDAO teamDAO;

    public TeamService(TeamDAO teamDAO) {
        this.teamDAO = teamDAO;
    }

    public String registerTeam(int stadiumId, String name ) {
        try {
           int rs =  teamDAO.registerTeam(stadiumId, name);
           if (rs >0) {
               return "성공";
           } else {
              return "실패";
           }

        }catch(SQLException e) {
            e.printStackTrace();
            return "예외실패";
        }
    }

    public List<Team> findAll(){
        List<Team> teamList = teamDAO.findAll();
        return teamList;
    }






}
