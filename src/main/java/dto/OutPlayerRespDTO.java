package dto;


import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
public class OutPlayerRespDTO {
    private int id;
    private String playerName;
    private String reason;
    private Timestamp createdAt;

    @Override
    public String toString() {
        return "OutPlayerRespDTO{" +
                "id=" + id +
                ", playerName='" + playerName + '\'' +
                ", reason='" + reason + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
