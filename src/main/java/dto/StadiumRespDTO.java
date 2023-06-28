package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
@Getter
@Setter
@Builder
public class StadiumRespDTO {

    private int id;
    private String name;
    private Timestamp createdAt;

    @Override
    public String toString() {
        return "StadiumRespDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
