package dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Getter
@Setter
public class PositionRespDTO {
    private Position position;
    private Map<String, String> playersByTeam;

    public PositionRespDTO(Position position) {
        this.position = position;
        this.playersByTeam = new HashMap<>();
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    //팀별 선수이름 담기
    public Map<String, String> getPlayersByTeam() {
        return playersByTeam;
    }
    public void setPlayersByTeam(Map<String, String> playersByTeam) {
        this.playersByTeam = playersByTeam;
    }
}
