package dao;

import model.Stadium;
import model.Team;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeamDAO {

    private Connection connection;

    public TeamDAO(Connection connection) {

        this.connection = connection;
    }

    //구현해야될 기능 : 팀등록, 전체팀 목록

    //팀등록
    //@Param
    public void registerTeam(String name, int stadiumId) throws SQLException {
        String query = "INSERT INTO team (stadium_id, name) VALUES (?,?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.setInt(2, stadiumId);
            statement.executeUpdate();
        }
    }

    //전체팀목록조회
    //List<Team>
    //try catch SQLexception
    public List<Team> findAll() {
        List<Team> teamList = new ArrayList<>();
        String query = "SELECT * FROM team";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                Team team = new Team(
                        resultSet.getInt("id"),
                        resultSet.getInt("stadiumId"),
                        resultSet.getString("name"),
                        resultSet.getTimestamp("createdAt")
                );
                teamList.add(team);
            }
            return teamList;

        }catch(SQLException e) {
            e.printStackTrace();
        }
        return teamList;
    }




}
