package dao;

import dto.Position;
import dto.PositionRespDTO;
import model.Player;
import model.Team;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PositionDAO {
    private static Connection connection;

    public PositionDAO(Connection connection) {
        this.connection = connection;
    }

    public List<PositionRespDTO> getPlayersFromDb() {
        List<PositionRespDTO> positionData = new ArrayList<>();

        String query = "SELECT p.position, t.name AS team_name, p.name AS player_name " +
                "FROM player p " +
                "JOIN team t ON p.team_id = t.id";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            Map<String, Map<String, String>> playersByPosition = new HashMap<>();

            while (resultSet.next()) {
                String positionString = resultSet.getString("position");
                String teamName = resultSet.getString("team_name");
                String playerName = resultSet.getString("player_name");

                Position position = getPositionEnum(positionString);
                if (position != null) {
                    if (!playersByPosition.containsKey(positionString)) {
                        playersByPosition.put(positionString, new HashMap<>());
                    }
                    playersByPosition.get(positionString).put(teamName, playerName);
                }
            }

            for (String positionString : playersByPosition.keySet()) {
                Position position = getPositionEnum(positionString);
                if (position != null) {
                    Map<String, String> playersByTeam = playersByPosition.get(positionString);

                    PositionRespDTO positionRespDTO = new PositionRespDTO(position);
                    positionRespDTO.setPlayersByTeam(playersByTeam);
                    positionData.add(positionRespDTO);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return positionData;
    }

    private Position getPositionEnum(String positionString) {
        for (Position position : Position.values()) {
            if (position.getDisplayName().equals(positionString)) {
                return position;
            }
        }
        System.out.println("Invalid position: " + positionString);
        return null;
    }
}