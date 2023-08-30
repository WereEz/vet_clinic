/**
 * Модуль солержит интерфейс поиска
 */
package com.example.vet_clinic;

import javafx.collections.transformation.FilteredList;

/**
 * Интерфейс поиска по полю в листе
 */
public interface Searchable {
    /**
     * Применяет фильтр к листу
     * @param filteredList фильтруемый лист
     * @param searchText строка-фильтр
     */
    void execute(FilteredList<Pet> filteredList, String searchText);
}
