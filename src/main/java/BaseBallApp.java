import dao.StadiumDAO;
import dao.TeamDAO;
import db.DBConnection;
import model.Stadium;

import java.sql.Connection;
import java.util.List;

public class BaseBallApp {
    public static void main(String[] args) {
        Connection connection = DBConnection.getInstance();
        StadiumDAO stadiumDAO = new StadiumDAO(connection);
        TeamDAO teamDAO = new TeamDAO(connection);




    }
}
