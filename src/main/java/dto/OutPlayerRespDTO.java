package dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Setter
@Getter
@ToString
public class OutPlayerRespDTO {
    private int id;
    private String name;
    private String position;
    private String reason;
    private Timestamp createdAt;

    @Builder
    public OutPlayerRespDTO(String reason, Timestamp createdAt) {
        this.reason = reason;
        this.createdAt = createdAt;
    }
}
