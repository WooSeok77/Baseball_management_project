package dao;

import dto.OutPlayerRespDTO;
import dto.PlayerRespDTO;
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

    //퇴출 선수 등록
    public void  registerOutPlayer(int playerId, String reason ) throws SQLException{
        String query = "insert into out_player(player_id, reason) values (?,?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, playerId);
            statement.setString(2, reason);
            statement.executeUpdate();
        }
    }

    /*//퇴출 선수 목록 조회
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
    }*/

    //퇴출 선수 목록 조회
    public List<OutPlayerRespDTO> findAllOutPlayers() throws SQLException {
        List<OutPlayerRespDTO> OutPlayers = new ArrayList<>();

        // player와 out_player 테이블을 OuterJoin
        String outquery = "SELECT p.id, p.name, p.position, o.reason ,o.created_at FROM player p LEFT JOIN out_player o ON p.id = o.player_id WHERE o.player_id IS NOT NULL";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(outquery)) {
            while (resultSet.next()) {
                OutPlayerRespDTO outPlayer = new OutPlayerRespDTO();
                PlayerRespDTO playerRespDTO =new PlayerRespDTO();

                playerRespDTO.setId(resultSet.getInt("id"));
                playerRespDTO.setName(resultSet.getString("name"));
                playerRespDTO.setPosition(resultSet.getString("position"));
                outPlayer.setReason(resultSet.getString("reason"));
                outPlayer.setCreatedAt(resultSet.getTimestamp("created_at"));
                // 퇴출된 선수 정보를 OutPlayerRespDTO에 매핑하는 코드 작성
                // resultSet에서 필요한 칼럼들을 가져와서 OutPlayer에 설정
                OutPlayers.add(outPlayer);
            }
        }catch (SQLException e) {
            // 오류 처리
            e.printStackTrace();
        }
        return OutPlayers;
    }
}

