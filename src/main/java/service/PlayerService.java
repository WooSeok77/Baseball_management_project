package service;

import dao.PlayerDAO;
import model.Player;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class PlayerService {
    private PlayerDAO playerDAO;

    public PlayerService(PlayerDAO playerDAO){
        this.playerDAO=playerDAO;
    }
    public String registerPlayer(int teamId, String name, String position) {
        try {
            // 선수 등록
            int rs = playerDAO.registerPlayer(teamId, name, position);
            if (rs >0) {
                return "성공";
            } else {
                return "실패";
            }
        } catch(SQLException | RuntimeException e){
            e.printStackTrace();
            return "예외실패";
        }
    }


    public List<Player> getPlayersByTeamId(int teamId) {
        try {
            // 팀별 선수 목록 조회
            return playerDAO.findAllPlayer(teamId);
        } catch (SQLException e) {
            // 오류 발생 시 빈 리스트 반환
            System.err.println("팀별 선수 목록 조회 중 오류가 발생했습니다: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
