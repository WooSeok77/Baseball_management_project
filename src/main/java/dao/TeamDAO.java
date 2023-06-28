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
    public int registerTeam(int stadiumId, String name) throws SQLException {
        String query = "INSERT INTO team (stadium_id, name, created_at) VALUES (?,?, now())";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, stadiumId);
            statement.setString(2, name);
            int rowCount = statement.executeUpdate();

            return rowCount;
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return -1;
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
                        resultSet.getInt("stadium_id"),
                        resultSet.getString("name"),
                        resultSet.getTimestamp("created_at")
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
