package dao;

import model.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAO {
    private Connection connection;

    private static PlayerDAO instance;

    private PlayerDAO(){};

    public static PlayerDAO getInstance() {
        if(instance == null) {
            instance = new PlayerDAO();
        }
        return instance;
    }

    public PlayerDAO(Connection connection) {
        this.connection = connection;
    }

    // 선수 등록
    public int registerPlayer(int teamId, String name, String position) throws SQLException {
        String query = "INSERT INTO player (team_id, name, position) VALUES (?, ?, ?)";
        String checkquery = "SELECT COUNT(*) FROM player WHERE team_id =? AND position =?"; //position 중복 체크 쿼리

        try (PreparedStatement statement = connection.prepareStatement(query);
             PreparedStatement checkStatement = connection.prepareStatement(checkquery)) {
             checkStatement.setInt(1,teamId);
             checkStatement.setString(2,position);
            //중복 체크
            try (ResultSet resultSet = checkStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    if (count > 0) {
                        throw new SQLException("팀 내에서 같은 포지션의 선수가 이미 등록되어 있습니다.");
                    }
                }
            }
            //선수 등록
            statement.setInt(1, teamId);
            statement.setString(2, name);
            statement.setString(3, position);
            int rowCount = statement.executeUpdate();

            return rowCount;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return -1;
    }


    //선수 목록 업데이트(퇴출선수 생길 시)
    public int updatePlayer(int playerId){
        String updatequery = "UPDATE player SET team_id =NULL WHERE id = ?";
        try(PreparedStatement updatestatement = connection.prepareStatement(updatequery)){
            updatestatement.setInt(1,playerId);
            return updatestatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();;
            return 0;
        }
    }




    // 팀별 선수 조회
    public List<Player> findAllPlayer(int teamId) throws SQLException {
        List<Player> playerList = new ArrayList<>();
        String query = "SELECT id, name, position, created_at FROM player WHERE teamID = ?"; //team_id는 출력 X

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, teamId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Player player = Player.builder()
                            .id(resultSet.getInt("id"))
                            .name(resultSet.getString("name"))
                            .position(resultSet.getString("position"))
                            .createdAt(resultSet.getTimestamp("created_at"))
                            .build();
                    playerList.add(player);
                }
            }
        }

        return playerList;
    }
}
