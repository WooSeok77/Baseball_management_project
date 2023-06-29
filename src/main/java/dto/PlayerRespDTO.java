package dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerRespDTO {
    private int teamId;
    private String name;
    private String position;
}
