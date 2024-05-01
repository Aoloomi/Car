package model.da;
import model.entity.Car;
import model.entity.enums.Color;
import model.tools.JDBC;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDa implements AutoCloseable {
    private Connection connection;
    private PreparedStatement preparedStatement;

    public CarDa() throws SQLException {
        connection = JDBC.getJdbc().getConnection();
    }

    public void save(Car car) throws Exception {
        preparedStatement = connection.prepareStatement(
                "INSERT INTO CAR (ID, NAME, COLOR, MAN_DATE) VALUES (CAR_SEQ.nextval,?,?,?)"
        );
        preparedStatement.setString(1, car.getName());
        preparedStatement.setString(2, car.getColor().name());
        preparedStatement.setDate(3, Date.valueOf(car.getManDate()));
        preparedStatement.execute();
    }

    public void edit(Car car) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE CAR SET NAME=?, COLOR=?, MAN_DATE=? WHERE ID=?"
        );
        preparedStatement.setString(1, car.getName());
        preparedStatement.setString(2, car.getColor().name());
        preparedStatement.setDate(3, Date.valueOf(car.getManDate()));
        preparedStatement.setInt(4, car.getId());
        preparedStatement.execute();
    }

    public void remove(int id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "DELETE FROM CAR WHERE ID=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    public List<Car> findAll() throws Exception{
        preparedStatement = connection.prepareStatement("SELECT * FROM CAR ORDER BY ID, MAN_DATE, NAME");
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Car> carList = new ArrayList<>();

        while (resultSet.next()) {
            Car car = Car
                    .builder()
                    .id(resultSet.getInt("ID"))
                    .name(resultSet.getString("NAME"))
                    .color(Color.valueOf(resultSet.getString("COLOR")))
                    .manDate(resultSet.getDate("MAN_DATE").toLocalDate())
                    .build();
            carList.add(car);
        }
        return carList;
    }

    public Car findById(int id) throws Exception{
        preparedStatement = connection.prepareStatement("SELECT * FROM CAR WHERE ID=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        Car car = null;
        if (resultSet.next()) {
            car = Car
                    .builder()
                    .id(resultSet.getInt("ID"))
                    .name(resultSet.getString("NAME"))
                    .color(Color.valueOf(resultSet.getString("COLOR")))
                    .manDate(resultSet.getDate("MAN_DATE").toLocalDate())
                    .build();
        }
        return car;
    }

    public List<Car> findByName(String name) throws Exception{
        preparedStatement = connection.prepareStatement("SELECT * FROM CAR WHERE NAME LIKE ?");
        preparedStatement.setString(1, name+"%");
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Car> carList = new ArrayList<>();

        while (resultSet.next()) {
            Car car = Car
                    .builder()
                    .id(resultSet.getInt("ID"))
                    .name(resultSet.getString("NAME"))
                    .color(Color.valueOf(resultSet.getString("COLOR")))
                    .manDate(resultSet.getDate("MAN_DATE").toLocalDate())
                    .build();
            carList.add(car);
        }
        return carList;
    }


    public List<Car> findByColor(String color) throws Exception{
        preparedStatement = connection.prepareStatement("SELECT * FROM CAR WHERE COLOR LIKE ?");
        preparedStatement.setString(1, color+"%");
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Car> carList = new ArrayList<>();

        while (resultSet.next()) {
            Car car = Car
                    .builder()
                    .id(resultSet.getInt("ID"))
                    .name(resultSet.getString("NAME"))
                    .color(Color.valueOf(resultSet.getString("COLOR")))
                    .manDate(resultSet.getDate("MAN_DATE").toLocalDate())
                    .build();
            carList.add(car);
        }
        return carList;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}