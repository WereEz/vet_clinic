package com.example.vet_clinic;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
/**
 Контроллер приложения ветеринарной клиники.
 */
public class Controller implements Initializable {
    static ObservableList<Pet> petList = FXCollections.observableArrayList();
    FilteredList<Pet> filteredList = new FilteredList<>(petList, p -> true);
    private Pet selectedPet;
    @FXML
    private Label birthdayLabel, breedLabel, genderLabel, nameLabel, ownerLabel;
    @FXML
    private ListView <Pet> listViewPets;
    @FXML
    private TableView<Record> tableRecords;
    @FXML
    private TableColumn<Pet, String> diseaseTablet, dateTablet, treatmentTablet;
    @FXML
    private Pane newPanePet, newPaneRecord, startPane, labelsPetPane;
    @FXML
    private TextField newBreed, newGender, newName, newOwner, newDisease, newTreatment, fieldSearch;
    @FXML
    private DatePicker newBirthday, newDate;
    @FXML
    private Button buttonSavePet, buttonSaveRecord, buttonSaveEditPet, buttonSaveEditRecord, buttonNotSave;
    /**
     * Отображение формы создания нового питомца.
     */
    @FXML
    void createPet() {
        clearNodes(newPanePet);
        switchViewOnPet();
    }
    /**
     * Отображение формы создания новой записи о лечении
     */
    @FXML
    void createRecord() {
        clearNodes(newPaneRecord);
        switchViewOnRecord();
    }
    /**
     * Удаление питомца
     */
    @FXML
    void deletePet() {
        selectedPet = listViewPets.getSelectionModel().getSelectedItem();
        System.out.println(selectedPet);
        if (selectedPet != null) {
            petList.remove(selectedPet);
            tableRecords.getItems().clear();
            startPane.getChildren().stream().filter(node -> node instanceof Label)
                    .map(node -> (Label) node).forEach(label -> label.setText(""));
            listViewPets.getSelectionModel().select(-1);
        }
    }
    /**
     * Удаление записи о лечении
     */
    @FXML
    void deleteRecord() {
        int ind_record = tableRecords.getSelectionModel().getSelectedIndex();
        if (ind_record != -1) {
            selectedPet.getRecords().remove(ind_record);
            tableRecords.getItems().remove(ind_record);
            tableRecords.getItems().clear();
            tableRecords.getItems().addAll(selectedPet.getRecords());
        }
    }
    /**
     * Сохранение новой записи.
     */
    @FXML
    void SaveRecord() {
        Record record = new Record(newDate.getPromptText(), newDisease.getText(), newTreatment.getText());
        selectedPet.addRecord(record);
        tableRecords.getItems().add(record);
        switchViewOnStartDisplay();
    }
    /**
     * Не сохранять
     */
    @FXML
    void notSave() {
        switchViewOnStartDisplay();
    }
    /**
     * Сохранение нового питомца.
     */
    @FXML
    void SavePet() {
        Pet pet = new Pet(newName.getText(), newBreed.getText(),
                newGender.getText(), newBirthday.getValue().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")), newOwner.getText());
        petList.add(pet);
        switchViewOnStartDisplay();
    }
    /**
     * Редактирование питомца.
     */
    @FXML
    void editPet() {
        selectedPet = listViewPets.getSelectionModel().getSelectedItem();
        if (selectedPet != null) {
            clearNodes(newPanePet);
            switchViewOnPet();
            buttonSavePet.setVisible(false);
            buttonSaveEditPet.setVisible(true);
            newName.setText(String.valueOf(selectedPet.getName()));
            newGender.setText(String.valueOf(selectedPet.getGender()));
            newBreed.setText(String.valueOf(selectedPet.getBreed()));
            newBirthday.setPromptText(String.valueOf(selectedPet.getBirthday()));
            newOwner.setText(String.valueOf(selectedPet.getOwner()));
            startPane.setVisible(false);
        }
    }
    /**
     * Редактирование записи о лечении.
     */
    @FXML
    void editRecord() {
        selectedPet = listViewPets.getSelectionModel().getSelectedItem();
        if (selectedPet == null) {
            return;
        }
        int ind_record = tableRecords.getSelectionModel().getSelectedIndex();
        if (ind_record != -1) {
            clearNodes(newPaneRecord);
            switchViewOnRecord();
            buttonSaveRecord.setVisible(false);
            buttonSaveEditRecord.setVisible(true);
            newDate.setPromptText(String.valueOf(selectedPet.getRecords().get(ind_record).date));
            newDisease.setText(String.valueOf(selectedPet.getRecords().get(ind_record).disease));
            newTreatment.setText(String.valueOf(selectedPet.getRecords().get(ind_record).treatment));
        }
    }
    /**
     * Обработка клика на лист питомцев
     */
    @FXML
    void clickOnViewPets() {
        selectedPet = listViewPets.getSelectionModel().getSelectedItem();
        if (selectedPet != null) {
            tableRecords.getItems().clear();
            nameLabel.setText(String.valueOf(selectedPet.getName()));
            genderLabel.setText(String.valueOf(selectedPet.getGender()));
            breedLabel.setText(String.valueOf(selectedPet.getBreed()));
            birthdayLabel.setText(String.valueOf(selectedPet.getBirthday()));
            ownerLabel.setText(String.valueOf(selectedPet.getOwner()));
            tableRecords.getItems().addAll(selectedPet.getRecords());
        }
    }
    /**
     * Сохранение отредактированного питомца.
     */
    @FXML
    void saveEditPet() {
        Pet pet = new Pet(newName.getText(), newBreed.getText(),
                newGender.getText(), newBirthday.getValue().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")), newOwner.getText());
        pet.setRecords(selectedPet.getRecords());
        int selectedIndex = petList.indexOf(selectedPet);
        petList.set(selectedIndex, pet);
        switchViewOnStartDisplay();
        buttonSavePet.setVisible(true);
        buttonSaveEditPet.setVisible(false);
        startPane.setVisible(true);
        nameLabel.setText(String.valueOf(petList.get(selectedIndex).getName()));
        genderLabel.setText(String.valueOf(petList.get(selectedIndex).getGender()));
        breedLabel.setText(String.valueOf(petList.get(selectedIndex).getBreed()));
        birthdayLabel.setText(String.valueOf(petList.get(selectedIndex).getBirthday()));
        ownerLabel.setText(String.valueOf(petList.get(selectedIndex).getOwner()));
    }
    /**
     * Сохранение отредактированной записи о лечении.
     */
    @FXML
    void saveEditRecord() {
        int ind_record = tableRecords.getSelectionModel().getSelectedIndex();
        Record record = new Record(newDate.getValue().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")), newDisease.getText(), newTreatment.getText());
        selectedPet.getRecords().set(ind_record, record);
        tableRecords.getItems().set(ind_record, record);
        switchViewOnStartDisplay();
        buttonSaveRecord.setVisible(true);
        buttonSaveEditRecord.setVisible(false);

    }
    /**
     * Переключение на окошко редактирование/добавление питомца.
     */
    private void switchViewOnPet() {
        newPanePet.setVisible(true);
        startPane.setVisible(false);
        buttonSavePet.setVisible(true);
        buttonNotSave.setVisible(true);
    }
    /**
     * Переключение на начальный экран.
     */
    private void switchViewOnStartDisplay() {
        newPaneRecord.setVisible(false);
        newPanePet.setVisible(false);
        buttonNotSave.setVisible(false);
        startPane.setVisible(true);
        labelsPetPane.setVisible(true);
        buttonSaveEditPet.setVisible(false);
        buttonSavePet.setVisible(true);
    }
    /**
     * Переключение на окошко редактирование/добавление записи о лечении.
     */
    private void switchViewOnRecord() {
        newPaneRecord.setVisible(true);
        labelsPetPane.setVisible(false);
        startPane.setVisible(false);
        buttonNotSave.setVisible(true);
    }
    /**
     * Установка фабрики ячеек для списка питомцев.
     */
    private void setPetListCellFactory() {
        listViewPets.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Pet pet, boolean empty) {
                super.updateItem(pet, empty);
                if (empty || pet == null) {
                    setText(null);
                } else {
                    setText(pet.getName());
                }
            }
        });
    }
    /**
     * Очистка полей ввода в указанном контейнере.
     * @param pane контейнер, содержащий поля ввода
     */
    private void clearNodes(Pane pane) {
        pane.getChildren().stream().filter(node -> node instanceof TextField)
                .map(node -> (TextField) node).forEach(TextField::clear);
        pane.getChildren().stream().filter(node -> node instanceof DatePicker)
                .map(node -> (DatePicker) node).forEach(datePicker -> datePicker.setPromptText(""));
    }
    /**
     * Инициализирует контроллер при загрузке интерфейса.
     * @param location URL-адрес расположения корневого объекта или null, если местоположение неизвестно
     * @param resources ресурсы, связанные с контроллером, или null, если ресурсы не доступны
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        diseaseTablet.setCellValueFactory(new PropertyValueFactory<>("disease"));
        dateTablet.setCellValueFactory(new PropertyValueFactory<>("date"));
        treatmentTablet.setCellValueFactory(new PropertyValueFactory<>("treatment"));
        filteredList = new FilteredList<>(petList);
        PetFilter petFilter = new PetFilter(filteredList);
        listViewPets.setItems(filteredList);
        fieldSearch.textProperty().addListener((observable, oldValue, newValue) -> petFilter.applyFilter(newValue));
        setPetListCellFactory();
    }
}
