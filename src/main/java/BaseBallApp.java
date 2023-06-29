import dao.OutPlayerDAO;
import dao.PlayerDAO;
import dao.StadiumDAO;
import dao.TeamDAO;
import db.DBConnection;
import dto.OutPlayerRespDTO;
import dto.PositionRespDTO;
import model.Player;
import model.Stadium;
import model.Team;
import service.OutPlayerService;
import service.PlayerService;
import service.StadiumService;
import service.TeamService;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class BaseBallApp {
    public static void main(String[] args) {
        Connection connection = DBConnection.getInstance();
        StadiumDAO stadiumDAO = new StadiumDAO(connection);
        TeamDAO teamDAO = new TeamDAO(connection);
        StadiumService stadiumService = new StadiumService(stadiumDAO);
        TeamService teamService = new TeamService(teamDAO);
        PlayerDAO playerDAO =new PlayerDAO(connection);
        OutPlayerDAO outPlayerDAO =new OutPlayerDAO(connection);
        PlayerService playerService = new PlayerService(playerDAO);
        OutPlayerService outPlayerService =new OutPlayerService(outPlayerDAO,playerDAO);
        while (true) {
            System.out.println();
            System.out.println("어떤 기능을 요청 하시겠습니까?");
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();

            if(input.equals("종료")){
                break;
            }

            if (input.equals("야구장목록")) {
                List<Stadium> stadiumList = stadiumService.findAll();

                for (Stadium stadium : stadiumList) {
                    System.out.println(stadium.getId() + " " + stadium.getName() + " " + stadium.getCreatedAt());
                }
            }

            if (input.equals("팀목록")) {
                List<Team> teamList = teamService.findAll();
                for (Team team : teamList) {
                    System.out.println("TeamId : " + team.getId() + " StadiumId : " + team.getStadiumId() + " " + team.getName() + " " + team.getCreatedAt());
                }
            }

            String[] parsedInput = input.split("\\?");
            String function = parsedInput[0];

            if(function.equals("야구장등록")) {
                String params = parsedInput[1];
                String[] paramArray = params.split("=");
                String name = paramArray[1];

                String stadium = stadiumService.createStadium(name);

                System.out.println(stadium);

            }

            if(function.equals("팀등록")) {
                String params = parsedInput[1];
                String[] paramArray = params.split("&");
                int stadiumId = Integer.parseInt(paramArray[0].split("=")[1]);
                String name = paramArray[1].split("=")[1];

                String team = teamService.registerTeam(stadiumId, name);

                System.out.println(team);
            }


            if (function.equals("선수등록")) {
                String params = parsedInput[1];
                String[] paramArray = params.split("&"); //paramArray={teamId=1,name=이대호,position=1루수}
                int teamId = Integer.parseInt(paramArray[0].split("=")[1]); //1
                String name = paramArray[1].split("=")[1]; //이대호
                String position = paramArray[2].split("=")[1]; // 1루수

                String player = playerService.registerPlayer(teamId, name, position);

                System.out.println(player);

            }

            if (function.equals("선수목록")) {
                int teamId = Integer.parseInt(parsedInput[1].split("=")[1]); //1

                List<Player> players = playerService.getPlayersByTeamId(teamId);

                for(Player player :players){
                    System.out.println(player.getId() + " " + player.getName() + " " + player.getPosition() + " " + player.getCreatedAt());
                }
            }

            if (function.equals("퇴출 등록")) {
                String params = parsedInput[1];
                String[] paramArray = params.split("&"); //paramArray={playerId=1, reason=도박}
                int playerId = Integer.parseInt(parsedInput[0].split("=")[1]); //1
                String reason = paramArray[1].split("=")[1]; //도박

                String outplayer =outPlayerService.registerOutPlayer(playerId,reason);

                System.out.println(outplayer);

            }
            if (input.equals("퇴출목록")) {
                List<OutPlayerRespDTO> outplayers = outPlayerService.findAllOutPlayers( );
                for (OutPlayerRespDTO outPlayerRespDTO : outplayers) {
                    System.out.println("p.id : " + outPlayerRespDTO.getId() +
                            " p.name : " + outPlayerRespDTO.getName() +
                            " p.position : " + outPlayerRespDTO.getPosition() +
                            " o.reason " + outPlayerRespDTO.getReason() +
                            " o.day " + outPlayerRespDTO.getCreatedAt());
                }
            }

            if (input.equals("포지션별목록")) {
                //List<PositionRespDTO> positionRespDto = playerService.();

            }
        }
    }
}
