/**
 * Модуль солержит класс питомца
 */
package com.example.vet_clinic;

import java.util.ArrayList;

/**
 * Питомец
 */
public class Pet {
    /**
     * Имя
     */
    private String name;
    /**
     * Вид
     */
    private String breed;
    /**
     * Пол
     */
    private String gender;
    /**
     * День рождения
     */
    private String birthday;
    /**
     * Владелец
     */
    private String owner;
    /**
     * Записи приёма
     */
    private ArrayList<Record> records;

    /**
     * Конструктор класса Pet.
     * @param name Имя питомца
     * @param breed Порода
     * @param gender Пол
     * @param birthday Дата рождения
     * @param owner Владелец
     */
    public Pet(String name, String breed, String gender, String
            birthday, String owner)
    {
        this.name=name;
        this.breed = breed;
        this.gender =gender;
        this.birthday = birthday;
        this.owner = owner;
        this.records = new ArrayList<>();
    }

    /**
     * Возвращает вид
     * @return владелец
     */
    public String getBreed() {
        return breed;
    }

    /**
     * Возвращает владельца
     * @return владелец
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Возвращает дату рождения
     * @return дата рождения
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * Возвращает пол питомца
     * @return пол
     */
    public String getGender() {
        return gender;
    }

    /**
     * Возвращает имя
     * @return имя
     */
    public String getName() {
        return name;
    }

    /**
     * Возвращает записи о лечении
     * @return записи о лечении
     */
    public ArrayList<Record> getRecords() {
        return records;
    }

    /**
     * Устанавливает записи о лечении
     * @param records записи о лечении
     */
    public void setRecords(ArrayList<Record> records) {
        this.records = records;
    }

    /**
     * Добавляет запись о лечении.
     * @param record Запись о лечении
     */
    public void addRecord(Record record) {
        records.add(record);
    }
}