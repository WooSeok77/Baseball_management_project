package service;

import dao.OutPlayerDAO;
import dao.PlayerDAO;
import model.OutPlayer;
import java.sql.SQLException;

public class OutPlayerService {
    private OutPlayerDAO outPlayerDAO;
    private PlayerDAO playerDAO;

    public OutPlayerService(OutPlayerDAO outPlayerDAO,PlayerDAO playerDAO){
        this.outPlayerDAO=outPlayerDAO;
        this.playerDAO=playerDAO;
    }
    public String registerOutPlayer(int playerId, String reason){
        try {
            // 선수 등록
            outPlayerDAO.registerOutPlayer(playerId, reason);

            return "퇴출 선수 등록이 성공적으로 완료되었습니다.";
        } catch (SQLException e) {
            return "선수 등록 중 오류가 발생했습니다: " + e.getMessage();
        }
    }
}
