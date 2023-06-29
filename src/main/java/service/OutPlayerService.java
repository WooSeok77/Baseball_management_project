package service;

import dao.OutPlayerDAO;
import dao.PlayerDAO;
import dto.OutPlayerRespDTO;
import model.OutPlayer;

import java.sql.SQLException;
import java.util.List;

public class OutPlayerService {
    private OutPlayerDAO outPlayerDAO;
    private PlayerDAO playerDAO;
    private OutPlayer outPlayer;


    public OutPlayerService(OutPlayerDAO outPlayerDAO,PlayerDAO playerDAO){
        this.outPlayerDAO=outPlayerDAO;
        this.playerDAO=playerDAO;
    }
    public String registerOutPlayer(int playerId, String reason) throws SQLException {
        // 해당 선수의 team_id를 null로 업데이트
        int updateRowCount = playerDAO.updatePlayer(playerId);
        if(updateRowCount==0){
            System.out.println("실패");
        }
        int rs = outPlayerDAO.registerOutPlayer(playerId,reason);
        if (rs >0) {
            return "성공";
        }
        return "실패";
    }

    //퇴출 선수 목록 조회
    public List<OutPlayerRespDTO.OutPlayerSelectDTO> findAllOutPlayers() throws SQLException {
        try {
            return outPlayerDAO.findAllOutPlayers();
        } catch (SQLException e) {
            System.err.println("퇴출 선수 목록 조회 중 오류가 발생했습니다: " + e.getMessage());
            return null;
        }
    }
}
