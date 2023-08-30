/**
 * Модуль солержит класс вызовов фильтров
 */
package com.example.vet_clinic;

import javafx.collections.transformation.FilteredList;

/**
 * Класс выбора нужного фильтра
 */
public class PetFilter {
    private FilteredList<Pet> filteredList;
    /**
     * Конструктор класса Record.
     * @param filteredList фильтруемый лист
     */
    public PetFilter(FilteredList<Pet> filteredList) {
        this.filteredList = filteredList;
    }
    /**
     * Выбор фильтра для применения
     * @param value значение, введеное пользователем
     */
    public void applyFilter(String value) {
        Searchable currentSearchState;
        String searchText = value;
        if (searchText.startsWith("o:")) {
            currentSearchState = new SearchByOwner();
            searchText = searchText.substring(2).trim();
        } else if (value.startsWith("n:")) {
            currentSearchState = new SearchByName();
            searchText = searchText.substring(2).trim();
        } else if (value.startsWith("r:")) {
            currentSearchState = new SearchByNumberRecords();
            searchText = searchText.substring(2).trim();
        } else {
            currentSearchState = new SearchByName();
        }
        currentSearchState.execute(filteredList, searchText);
    }
}
