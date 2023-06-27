package dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class PlayerRespDTO {
    private int id;
    private int teamId;
    private String name;
    private String position;
    private Timestamp createdAt;

    @Override
    public String toString() {
        return "OutPlayerRespDTO{" +
                "id=" + id +
                ", playerName='" + name + '\'' +
                ", position='" + position + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
