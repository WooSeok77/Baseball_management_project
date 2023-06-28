package dao;

import model.Stadium;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StadiumDAO {
    private Connection connection;


    public StadiumDAO(Connection connection) {
        this.connection = connection;
    }
    //구현해야될 기능 : 야구장 등록, 전체 야구장 목록보기

    //야구장 등록
    // @Param name
    // @throws SQLException
    public int createStadium(String name) throws SQLException {
        String query = "INSERT INTO stadium (name, created_at) VALUES (?, now())";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            int rowCount = statement.executeUpdate();
            return rowCount;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    //전체 야구장 목록보기
    // List<Stadium>
    // try catch SQLException

    public List<Stadium> findAll(){
        List<Stadium> stadiumList = new ArrayList<>();
        String query = "SELECT * FROM stadium";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                Stadium stadium = new Stadium(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getTimestamp("created_at")
                );
                stadiumList.add(stadium);
            }
            return stadiumList;

        }catch(SQLException e) {
            e.printStackTrace();
        }
        return stadiumList;
    }

}
