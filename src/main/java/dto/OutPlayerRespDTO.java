package dto;


import lombok.*;

import java.sql.Timestamp;


public class OutPlayerRespDTO {

    @Setter
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OutPlayerInsertDTO{
        private int playerId;
        private String reason;
    }

    @Setter
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OutPlayerSelectDTO{
        private int id;
        private String name;
        private String position;
        private String reason;
        private Timestamp createdAt;
    }
}
