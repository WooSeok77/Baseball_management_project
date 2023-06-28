import dao.PlayerDAO;
import db.DBConnection;
import model.Player;
import service.PlayerService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class BaseBallApp {
    public static void main(String[] args) throws SQLException {
        Connection connection = DBConnection.getInstance();
        PlayerDAO playerDAO =new PlayerDAO(connection);
        PlayerService playerService = new PlayerService(playerDAO);

        System.out.println("어떤 기능을 요청 하시겠습니까?");
        Scanner sc =new Scanner(System.in);
        String input = sc.nextLine();

        // 입력값 파싱하여 기능과 필요한 정보 추출
        String[] parsedInput = input.split("\\?"); //선수등록?teamId=1&name=이대호&position=1루수
        String function = parsedInput[0]; // 기능 Ex.선수등록
        String params = parsedInput[1]; // 파라미터 Ex.teamId=1&name=이대호&position=1루수

        if (function.equals("선수등록")) {
            String[] paramArray = params.split("&"); //paramArray={teamId=1,name=이대호,position=1루수}
            int teamId = Integer.parseInt(paramArray[0].split("=")[1]); //1
            String name = paramArray[1].split("=")[1]; //이대호
            String position = paramArray[2].split("=")[1]; // 1루수

            String player = playerService.registerPlayer(teamId, name, position);

            System.out.println(player);

        } else if (function.equals("선수목록")) {
            int teamId = Integer.parseInt(parsedInput[1].split("=")[1]); //1

            List<Player> players = playerService.getPlayersByTeamId(teamId);

            System.out.println(players);

        } else {
            System.out.println("지원하지 않는 기능입니다.");
        }

        sc.close();
    }
}



