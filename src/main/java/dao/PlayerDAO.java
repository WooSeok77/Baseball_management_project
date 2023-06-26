package dao;

import model.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAO {
    private Connection connection;

    public PlayerDAO(Connection connection) {
        this.connection = connection;
    }

    // 선수 등록
    public void registerPlayer(int teamId, String name, String position, Timestamp createdAt) throws SQLException {
        String query = "INSERT INTO player (team_id, name, position, created_at) VALUES (?, ?, ?, ?)";
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
            statement.setTimestamp(4, createdAt);
            statement.executeUpdate();
        }
    }

    // 모든 선수 조회
    public List<Player> findAllPlayer() throws SQLException {
        List<Player> playerList = new ArrayList<>();
        String query = "SELECT id, name, position, created_at FROM player"; //team_id는 출력 X

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
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

        return playerList;
    }
}
