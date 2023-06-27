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

    public void registerTeam(String name, int studentId) {
        try {
            teamDAO.registerTeam(name, studentId);

        }catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Team> findAll(){
        return teamDAO.findAll();
    }






}
