/**
 * Модуль солержит класс записей о приёме.
 */
package com.example.vet_clinic;
    /**
     * Запись о приёме
     */
    public class Record {
        /**
         * Возвращает проведенное лечение
         * @return проведенное лечение
         */
        public String getTreatment() {
            return treatment;
        }
        /**
         * Возвращает дату приёма
         * @return дата приёма
         */
        public String getDate() {
            return date;
        }
        /**
         * Возвращает болезнь
         * @return болезнь
         */
        public String getDisease() {
            return disease;
        }
        /**
         * Дата приёма
         */
        public String date;

        /**
         * Болезнь
         */
        public String disease;
        /**
         * Лечение
         */
        public String treatment;
        /**
         * Конструктор класса Record.
         * @param date Дата записи
         * @param disease Болезнь
         * @param treatment Лечение
         */
        public Record(String date, String disease, String treatment ) {
            this.treatment = treatment;
            this.date = date;
            this.disease =disease;
        }
    }
