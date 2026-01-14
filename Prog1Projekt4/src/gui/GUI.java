package gui;

import controller.Controller;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Bestilling;
import model.Forestilling;
import model.Kunde;
import model.Plads;
import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class GUI extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Teater bestillinger");
        GridPane topPane = new GridPane();
        GridPane bottomPane = new GridPane();
        initContent(topPane, bottomPane);

        VBox root = new VBox(20);
        root.getChildren().addAll(topPane, bottomPane);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    ListView lvForestillinger = new ListView<>();
    ListView lvKunder = new ListView<>();
    ListView<Plads> lvPladser = new ListView<>();

    ObservableList<Forestilling> forestillinger = FXCollections.observableArrayList();
    ObservableList<Kunde> kunder = FXCollections.observableArrayList();


    //Bottom------------------------------------------
    TextField txfNavn = new TextField();
    DatePicker dpStartDato = new DatePicker();
    DatePicker dpSlutDato = new DatePicker();
    DatePicker dpDato = new DatePicker();
    TextField txfKundeNavn = new TextField();
    TextField txfKundeMobil = new TextField();

    private void initContent(GridPane topPane, GridPane bottomPane) {
        topPane.setGridLinesVisible(false);
        topPane.setPadding(new Insets(20));
        topPane.setHgap(10);
        topPane.setVgap(10);

        bottomPane.setGridLinesVisible(false);
        bottomPane.setPadding(new Insets(20));
        bottomPane.setHgap(10);
        bottomPane.setVgap(10);

        Label lblForestillinger = new Label("Forestillinger");
        topPane.add(lblForestillinger, 1, 1);
        Label lblKunder = new Label("Kunder");
        topPane.add(lblKunder, 2, 1);
        Label lblPladser = new Label("Pladser");
        topPane.add(lblPladser, 3, 1);

        topPane.add(lvForestillinger, 1, 2);
        topPane.add(lvKunder, 2, 2);
        topPane.add(lvPladser, 3, 2);

        //Bottom---------------------------------------------------------------------------------
        Label lblNavn = new Label("Navn");
        bottomPane.add(lblNavn, 1, 1);
        Label lblStartDato = new Label("Start Dato");
        bottomPane.add(lblStartDato, 1, 2);
        Label lblSlutDato = new Label("Slut Dato");
        bottomPane.add(lblSlutDato, 1, 3);
        Label lblKundeNavn = new Label("Kunde navn");
        bottomPane.add(lblKundeNavn, 3, 1);
        Label lblKundeMobil = new Label("Kunde Mobil");
        bottomPane.add(lblKundeMobil, 3, 2);
        Label lblDato = new Label("Dato");
        bottomPane.add(lblDato, 5, 1);

        bottomPane.add(txfNavn, 2, 1);
        bottomPane.add(dpStartDato, 2, 2);
        bottomPane.add(dpSlutDato, 2, 3);
        bottomPane.add(dpDato, 6, 1);
        bottomPane.add(txfKundeNavn, 4, 1);
        bottomPane.add(txfKundeMobil, 4, 2);

        Button btnOpretForestilling = new Button("Opret Forestilling");
        bottomPane.add(btnOpretForestilling, 2, 4);
        Button btnOpretKunde = new Button("Opret kunde");
        bottomPane.add(btnOpretKunde, 4, 3);
        Button btnOpretBestilling = new Button("Opret bestilling");
        bottomPane.add(btnOpretBestilling, 6, 2);

        btnOpretForestilling.setOnAction(event -> opretForestilling());
        btnOpretKunde.setOnAction(event -> opretKunde());
        btnOpretBestilling.setOnAction(event -> opretBestilling());


        for (int i = 0; i < Storage.getForestillinger().size(); i++)
            forestillinger.add(Storage.getForestillinger().get(i));
        for (int i = 0; i < Storage.getKunder().size(); i++)
            kunder.add(Storage.getKunder().get(i));

        lvPladser.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        lvForestillinger.setItems(forestillinger);
        lvKunder.setItems(kunder);
        lvPladser.getItems().setAll(Storage.getPladser());

    }

    private void opretBestilling() {
        if (lvForestillinger.getSelectionModel().getSelectedItem() == null) {
            showAlert("Mangler forestilling", "Vælg en forestilling");
            return;
        }
        if (lvKunder.getSelectionModel().getSelectedItem() == null) {
            showAlert("Mangler kunde", "Vælg en kunde");
            return;
        }
        if (lvPladser.getSelectionModel().getSelectedItem() == null) {
            showAlert("Mangler plads", "Vælg en plads");
            return;
        }
        if (dpDato.getValue() == null) {
            showAlert("Mangler Dato", "Vælg en dato");
            return;
        }

        ArrayList<Plads> al = new ArrayList<>();
        List<Plads> markerede = lvPladser.getSelectionModel().getSelectedItems();
        int markeredefælter = markerede.size();

        for (Plads markeret : markerede) {
            al.add(markeret);
        }
        Bestilling bestilling = Controller.opretBestillingMedPladser((Forestilling) lvForestillinger.getSelectionModel().getSelectedItem(), (Kunde) lvKunder.getSelectionModel().getSelectedItem(), dpDato.getValue(), al);
        //    for (int i = 0; i < al.size(); i++)
        //       System.out.println(al.get(i));

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Bekræftelse");
        alert.setHeaderText("Bestillingen er oprettet");
        alert.show();

        lvForestillinger.getSelectionModel().clearSelection();
        lvKunder.getSelectionModel().clearSelection();
        lvPladser.getSelectionModel().clearSelection();


    }

    private void opretForestilling() {

        LocalDate start = dpStartDato.getValue();
        LocalDate slut = dpSlutDato.getValue();

        if (txfNavn.getText().trim().isEmpty()) {
            showAlert("Manglende navn", "Angiv et navn");
            return;
        }
        if (start == null) {
            showAlert("Manglende start dato", "Angiv en start dato");
            return;
        }
        if (slut == null) {
            showAlert("Manglede slut dato", "Angiv en slut dato");
            return;
        }
        Controller.createForestilling(txfNavn.getText().trim(), start, slut);
        forestillinger.add(Storage.getForestillinger().getLast());
        txfNavn.setText("");
        dpStartDato.setValue(null);
        dpSlutDato.setValue(null);


    }

    private void opretKunde() {
        if (txfKundeNavn.getText().trim().isEmpty()) {
            showAlert("Manglende navn", "Angiv et navn");
            return;
        }
        if (txfKundeMobil.getText().trim().isEmpty()) {
            showAlert("Manglende telefonnummer", "Angiv dit telefonnummer");
            return;
        }

        Kunde kunde = Controller.createKunde(txfKundeNavn.getText().trim(), txfKundeMobil.getText().trim());
        lvKunder.getItems().add(kunde);
        txfKundeNavn.setText("");
        txfKundeMobil.setText("");
    }

    private void showAlert(String title, String header) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.show();
    }

}
