package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
public class TeamRespDTO {

    private int id;
    private int stadiumId;
    private String name;
    private Timestamp createdAt;

    @Override
    public String toString() {
        return "TeamRespDTO{" +
                "id=" + id +
                ", stadiumId=" + stadiumId +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
