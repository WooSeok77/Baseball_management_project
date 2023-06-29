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

    private static OutPlayerDAO instance;

    private OutPlayerDAO(){};

    public static OutPlayerDAO getInstance() {
        if(instance == null) {
            instance = new OutPlayerDAO();
        }
        return instance;
    }

    public OutPlayerDAO(Connection connection){
        this.connection =connection;
    }

    //퇴출 선수 등록
    public int  registerOutPlayer(int playerId, String reason ) throws SQLException{
        String checkQuery = "SELECT COUNT(*) FROM out_player WHERE player_id = ?";
        String query = "insert into out_player(player_id, reason, created_at) values (?,?, now())";

        try (PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
             PreparedStatement statement = connection.prepareStatement(query)) {
            //퇴출 정보가 있는지 확인
            checkStatement.setInt(1, playerId);
            //중복 체크
            try (ResultSet resultSet = checkStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    if (count > 0) {
                        throw new SQLException("중복 퇴출");
                    }
                }
            }

            statement.setInt(1, playerId);
            statement.setString(2, reason);
            int rowCount = statement.executeUpdate();

            return rowCount;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return -1;
    }

    //퇴출 선수 목록 조회
    public List<OutPlayerRespDTO.OutPlayerSelectDTO> findAllOutPlayers() throws SQLException {
        List<OutPlayerRespDTO.OutPlayerSelectDTO> OutPlayers = new ArrayList<>();

        // player와 out_player 테이블을 OuterJoin
        String outquery = "SELECT p.id, p.name, p.position, o.reason ,o.created_at FROM player p LEFT JOIN out_player o ON p.id = o.player_id";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(outquery)) {
            while (resultSet.next()) {
                OutPlayerRespDTO.OutPlayerSelectDTO outPlayerSelectDTO = buildOutPlayerFromResultSet(resultSet);
                OutPlayers.add(outPlayerSelectDTO);
            }
        }
        return OutPlayers;
    }
    private OutPlayerRespDTO.OutPlayerSelectDTO buildOutPlayerFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String position = resultSet.getString("position");
        String reason = resultSet.getString("reason");
        Timestamp outPlayerCreatedAt = resultSet.getTimestamp("created_at");
        return OutPlayerRespDTO.OutPlayerSelectDTO.builder()
                .id(id)
                .name(name)
                .position(position)
                .reason(reason)
                .createdAt(outPlayerCreatedAt)
                .build();
    }
}

