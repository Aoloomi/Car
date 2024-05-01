package model.bl;

import controller.exceptions.NoCarFoundException;
import model.da.CarDa;
import model.entity.Car;

import java.util.List;

public class CarBl {
    public static Car save(Car car) throws Exception {
        try (CarDa carDa = new CarDa()) {
            carDa.save(car);
            return car;
        }
    }

    public static Car edit(Car car) throws Exception {
        try (CarDa carDa = new CarDa()) {
            if (carDa.findById(car.getId()) != null) {
                carDa.edit(car);
                return car;
            } else {
                throw new NoCarFoundException();
            }
        }
    }

    public static Car remove(int id) throws Exception {
        try (CarDa carDa = new CarDa()) {
            Car car = carDa.findById(id);
            if (car != null) {
                carDa.remove(id);
                return car;
            } else {
                throw new NoCarFoundException();
            }
        }
    }

    public static List<Car> findAll() throws Exception {
        try (CarDa carDa = new CarDa()) {
            List<Car> carList = carDa.findAll();
            if (!carList.isEmpty()) {
                return carList;
            } else {
                throw new NoCarFoundException();
            }
        }
    }

    public static Car findById(int id) throws Exception {
        try (CarDa carDa = new CarDa()) {
            Car car = carDa.findById(id);
            if (car != null) {
                return car;
            } else {
                throw new NoCarFoundException();
            }
        }
    }

    public static List<Car> findByName(String name) throws Exception {
        try (CarDa carDa = new CarDa()) {
            List<Car> carList = carDa.findByName(name);
            if (!carList.isEmpty()) {
                return carList;
            } else {
                throw new NoCarFoundException();
            }
        }
    }

    public static List<Car> findByColor(String color) throws Exception {
        try (CarDa carDa = new CarDa()) {
            List<Car> carList = carDa.findByColor(color);
            if (!carList.isEmpty()) {
                return carList;
            } else {
                throw new NoCarFoundException();
            }
        }
    }


}
