/**
 * Модуль солержит класс поиска по количеству записей
 */
package com.example.vet_clinic;

import javafx.collections.transformation.FilteredList;
/**
 * Поиск по количеству записей
 */
public class SearchByNumberRecords implements Searchable {
    /**
     * Фильтрует по количество записей
     * @param filteredList фильтруемый лист
     * @param searchText строка
     */
    @Override
    public void execute(FilteredList<Pet> filteredList, String searchText) {
        try {
            int numRecords = Integer.parseInt(searchText);
            filteredList.setPredicate(pet -> pet.getRecords().size() == numRecords);
        } catch (NumberFormatException e) {
            // Некорректный формат числа, не выполняем фильтрацию
            filteredList.setPredicate(null);
        }
    }
}
