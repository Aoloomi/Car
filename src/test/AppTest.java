package test;

import javafx.application.Application;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j;

import java.sql.DriverManager;


@Log4j
public class AppTest extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
            DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe",
                    "javase",
                    "java123"
            );

            primaryStage.show();
            log.info("Test Passed....");
        }
}
