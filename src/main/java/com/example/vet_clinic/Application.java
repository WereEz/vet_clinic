/**
 * Модуль солержит класс запуска ветериной клиники
 */
package com.example.vet_clinic;

import java.io.IOException;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

/**
 * Приложение
 */
public class Application extends javafx.application.Application {
    /**
     * Запуск программы.
     * @param stage основная сцена приложения
     * @throws IOException при возникновении ошибок загрузки FXML-файла
    */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new
                FXMLLoader(Application.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Vet clinic");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        stage.setOnCloseRequest(e -> {
            // Сохраняем данные в файл JSON перед закрытием
            PetJsonSerializer.savePetsToFile(Controller.petList);
            Platform.exit();
        });
    }
    /**
     * Точка входа в программу
     */
    public static void main(String[] args) {
        Controller.petList = FXCollections.observableArrayList(PetJsonSerializer.loadPetsFromFile());
        launch();
    }
}

