/**
 * Модуль солержит класс поиска по владельцу
 */
package com.example.vet_clinic;

import javafx.collections.transformation.FilteredList;
/**
 * Поиск по владельцу
 */
public class SearchByOwner implements Searchable {

    /**
     * Фильтрует по владельцу
     * @param filteredList фильтруемый лист
     * @param searchText владелец
     */
    @Override
    public void execute(FilteredList<Pet> filteredList, String searchText) {
        filteredList.setPredicate(pet -> {
            if (searchText == null || searchText.isEmpty()) {
                return true;
            }
            String lowercaseSearchText = searchText.toLowerCase();
            return pet.getOwner().toLowerCase().startsWith(lowercaseSearchText);
        });
    }
}