/**
 * Модуль солержит класс поиска по имени
 */
package com.example.vet_clinic;

import javafx.collections.transformation.FilteredList;

/**
 * Поиск по имени
 */
public class SearchByName implements Searchable {

    /**
     * Фильтрует по имени
     * @param filteredList фильтруемый лист
     * @param searchText имя
     */
    @Override
    public void execute(FilteredList<Pet> filteredList, String searchText) {
        filteredList.setPredicate(pet -> {
            if (searchText == null || searchText.isEmpty()) {
                return true;
            }

            String lowercaseSearchText = searchText.toLowerCase();
            return pet.getName().toLowerCase().startsWith(lowercaseSearchText);
        });
    }
}