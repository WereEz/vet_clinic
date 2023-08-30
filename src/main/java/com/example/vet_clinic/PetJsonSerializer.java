/**
 * Модуль солержит класс записи в json
 */
package com.example.vet_clinic;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Класс сериализации и десериализации json
 */
public class PetJsonSerializer {
    /**
     * Путь к файлу json
     */
    private static final String FILE_PATH = "pets.json";
    /**
     * Сохранение питомцев в файл
     * @param petList лист питомцев
     */
    public static void savePetsToFile(List<Pet> petList) {
        try (Writer writer = new FileWriter(FILE_PATH)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(petList, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Загрузка питомцев из файла
     * @return список питомцев
     */
    public static List<Pet> loadPetsFromFile() {
        try (Reader reader = new FileReader(FILE_PATH)) {
            Gson gson = new Gson();
            Type petListType = new TypeToken<List<Pet>>() {}.getType();
            return gson.fromJson(reader, petListType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

