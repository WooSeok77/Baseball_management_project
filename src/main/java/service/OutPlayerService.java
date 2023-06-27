package service;

import dao.OutPlayerDAO;
import dao.PlayerDAO;
import model.OutPlayer;
import java.sql.SQLException;

public class OutPlayerService {
    private OutPlayerDAO outPlayerDAO;
    private PlayerDAO playerDAO;
    private OutPlayer outPlayer;


    public OutPlayerService(OutPlayerDAO outPlayerDAO,PlayerDAO playerDAO){
        this.outPlayerDAO=outPlayerDAO;
        this.playerDAO=playerDAO;
    }
    public String registerOutPlayer(int playerId, String reason){
        try {
            // 퇴출 선수 등록(insert)
            outPlayerDAO.registerOutPlayer(playerId, reason);
            // 해당 선수의 team_id를 null로 업데이트
            playerDAO.updatePlayer(outPlayer.getPlayerId());

            return "퇴출 선수 등록이 성공적으로 완료되었습니다.";
        } catch (SQLException e) {
            return "선수 등록 중 오류가 발생했습니다: " + e.getMessage();
        }
    }
}
