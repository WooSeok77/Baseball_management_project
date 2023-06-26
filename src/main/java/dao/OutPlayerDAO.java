package dao;

import model.OutPlayer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OutPlayerDAO {
    //선수 퇴출 등록, 선수 퇴출 목록
    private Connection connection;

    public OutPlayerDAO(Connection connection){
        this.connection =connection;
    }

    //톼출 선수 등록
    public void  registerOutPlayer(int playerId, String reason, Timestamp createdAt ) throws SQLException{
        String query = "insert into out_player(player_id, reason, created_at) values (?,?,?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, playerId);
            statement.setString(2, reason);
            statement.setTimestamp(3, createdAt);
            statement.executeUpdate();
        }
    }

    //퇴출 선수 목록 조회
    public List<OutPlayer> findAllOutPlayer() throws SQLException {
        List<OutPlayer> outPlayerList = new ArrayList<>();
        String query = "SELECT * FROM out_player";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)){
                while (resultSet.next()) {
                    OutPlayer outplayer = new OutPlayer(
                            resultSet.getInt("id"),
                            resultSet.getInt("playerId"),
                            resultSet.getString("reason"),
                            resultSet.getTimestamp("createdAt")
                            );
                    outPlayerList.add(outplayer);
                }
            }
        return outPlayerList;
    }
}

