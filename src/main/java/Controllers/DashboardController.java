package Controllers;

import Database.DBConnector;
import Services.RezervimetService;
import Services.OrariLinjaveService;
import Services.TripManagementService;
import Services.SessionManager;
import javafx.collections.transformation.SortedList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import models.Dto.CreateRezervimetDto;
import models.Dto.UpdateRezervimetDto;
import models.Dto.CreateOrariLinjaveDto;
import models.Dto.UpdatedOrariLinjaveDto;
import models.OrariLinjave;
import models.Rezervimet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;
import javafx.collections.transformation.FilteredList;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class DashboardController implements Initializable {

    private final RezervimetService rezervimetService = new RezervimetService();
    private final OrariLinjaveService orariService = new OrariLinjaveService();
    private final TripManagementService tripService = new TripManagementService();
    public ImageView Bimagebtn;
    public Button BDeleteBtn;
    public Button BClearbtn;
    public Button BAddbtm;
    public Button BUpdatebtn;
    @FXML
    private TextField Search;

    @FXML
    private Label username;
    @FXML
    private Label ATotalTravels;
    @FXML
    private Label ATravelsMade;
    @FXML
    private Label ACancelledTrips;
    @FXML
    private TextField BUserID, BNumberOfTickets, BReservationID;
    @FXML
    private TextField BScheduleID;
    @FXML
    private DatePicker BTravelDate, BReservationDate;
    @FXML
    private TableView<Rezervimet> addReservationtableview;
    @FXML
    private TableColumn<Rezervimet, Integer> BTReservationID, BTUserID, BTScheduleID, BTNumberOfTickets;
    @FXML
    private TableColumn<Rezervimet, String> BTTravelDate, BTReservationDate;
    @FXML
    private Button close, minimize, logout, Homebtn, AddReservationbtn, travelbtnhome;
    @FXML
    private AnchorPane AForm, Bform, CForm;
    @FXML private TextField CScheduleID;
    @FXML private TextField CTrainID;
    @FXML private TextField CStartID;
    @FXML private TextField CArrivalID;
    @FXML private ComboBox<String> CDepartureTime;
    @FXML private ComboBox<String> CArrivalTime;
    @FXML private ComboBox<String> CDay;
    @FXML private TableView<OrariLinjave> Ctable;
    @FXML private TableColumn<OrariLinjave, Integer> CTScheduleID;
    @FXML private TableColumn<OrariLinjave, Integer> CTTrainID;
    @FXML private TableColumn<OrariLinjave, Integer> CTStartID;
    @FXML private TableColumn<OrariLinjave, Integer> CTArrivaID;
    @FXML private TableColumn<OrariLinjave, String> CTDepartureTime;
    @FXML private TableColumn<OrariLinjave, String> CTArrivalTime;
    @FXML private TableColumn<OrariLinjave, String> CTDay;
    @FXML private BarChart<String, Number> Achart;


    private double x = 0;
    private double y = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        displayUsername();
        showTotalTravels();
        showRealizedTrips();
        showCancelledTrips();
        fillScheduleComboBox();
        showReservations();
        addReservationSearch();
        fillTimeComboBoxes();
        BTReservationID.setCellValueFactory(new PropertyValueFactory<>("rezervimId"));
        BTUserID.setCellValueFactory(new PropertyValueFactory<>("perdoruesId"));
        BTScheduleID.setCellValueFactory(new PropertyValueFactory<>("orariId"));
        BTTravelDate.setCellValueFactory(new PropertyValueFactory<>("dataUdhetimit"));
        BTNumberOfTickets.setCellValueFactory(new PropertyValueFactory<>("nrBiletave"));
        BTReservationDate.setCellValueFactory(new PropertyValueFactory<>("dataRezervimit"));
        showOrariLinjave();
        CTScheduleID.setCellValueFactory(new PropertyValueFactory<>("orariId"));
        CTTrainID.setCellValueFactory(new PropertyValueFactory<>("trenId"));
        CTStartID.setCellValueFactory(new PropertyValueFactory<>("nisjaId"));
        CTArrivaID.setCellValueFactory(new PropertyValueFactory<>("mbrritjaId"));
        CTDepartureTime.setCellValueFactory(new PropertyValueFactory<>("kohaNisjes"));
        CTArrivalTime.setCellValueFactory(new PropertyValueFactory<>("kohaMbrritjes"));
        CTDay.setCellValueFactory(new PropertyValueFactory<>("dita"));
        homeChart();
    }

    @FXML
    public void close() {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void minimize() {
        Stage stage = (Stage) minimize.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    public void switchForm(javafx.event.ActionEvent event) {
        if (event.getSource() == Homebtn) {
            AForm.setVisible(true);
            Bform.setVisible(false);
            CForm.setVisible(false);
            showTotalTravels();
            showRealizedTrips();
            showCancelledTrips();
        } else if (event.getSource() == AddReservationbtn) {
            AForm.setVisible(false);
            Bform.setVisible(true);
            CForm.setVisible(false);
            showReservations();
        } else if (event.getSource() == travelbtnhome) {
            AForm.setVisible(false);
            Bform.setVisible(false);
            CForm.setVisible(true);
        }
    }

    @FXML
    public void logout() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to logout?");
        Optional<ButtonType> option = alert.showAndWait();

        if (option.isPresent() && option.get() == ButtonType.OK) {
            try {
                logout.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("/com/example/knk_2025/hello-view.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                root.setOnMousePressed((MouseEvent event) -> {
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent event) -> {
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);
                    stage.setOpacity(0.8);
                });

                root.setOnMouseReleased((MouseEvent event) -> stage.setOpacity(1));

                stage.setScene(scene);
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void displayUsername() {
        String user = SessionManager.getInstance().getCurrentUser().getEmriPerdoruesit();
        username.setText(user);
    }

    private void showTotalTravels() {
        int total = tripService.getTotalTrips();
        ATotalTravels.setText(String.valueOf(total));
    }

    private void showRealizedTrips() {
        int total = tripService.getRealizedTrips();
        ATravelsMade.setText(String.valueOf(total));
    }

    private void showCancelledTrips() {
        int total = tripService.getCancelledTrips();
        ACancelledTrips.setText(String.valueOf(total));
    }

    public void homeChart() {
        Achart.getData().clear();
        String query = "SELECT data_udhetimit, COUNT(udhetim_id) AS numri_udhetimeve " +
                "FROM Udhetime " +
                "GROUP BY data_udhetimit " +
                "ORDER BY data_udhetimit ASC " +
                "LIMIT 7;";
        Connection connect;
        connect = DBConnector.getConnection();

        try {
            XYChart.Series<String, Number> chart = new XYChart.Series<>();
            PreparedStatement prepare;
            prepare = connect.prepareStatement(query);
            ResultSet result;
            result = prepare.executeQuery();

            while (result.next()) {
                String data = result.getString("data_udhetimit");
                int numri = result.getInt("numri_udhetimeve");
                chart.getData().add(new XYChart.Data<>(data, numri));
            }

            Achart.getData().add(chart);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void fillScheduleComboBox() {
        List<OrariLinjave> oraret = orariService.getAllOraret();
        ObservableList<Integer> ids = FXCollections.observableArrayList(
                oraret.stream().map(OrariLinjave::getOrariId).collect(Collectors.toList())
        );
        if (!ids.isEmpty()) {
            BScheduleID.setText(String.valueOf(ids.get(0)));
        }

    }
    private void fillTimeComboBoxes() {
        ObservableList<String> times = FXCollections.observableArrayList(
                "08:00:00", "09:15:00", "10:00:00", "11:30:00",
                "13:00:00", "14:45:00", "16:00:00", "17:30:00"

        );
        ObservableList<String> days = FXCollections.observableArrayList(
                "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"
        );

        CDepartureTime.setItems(times);
        CArrivalTime.setItems(times);
        CDay.setItems(days);
    }
    private ObservableList<Rezervimet> obslist = FXCollections.observableArrayList();

    private void showReservations() {
        List<Rezervimet> list = rezervimetService.getAllRezervimet();
        obslist.setAll(list); // jo krijim i ri
        addReservationtableview.setItems(obslist);
    }

    private void showOrariLinjave() {
        List<OrariLinjave> oraret = orariService.getAllOraret();
        ObservableList<OrariLinjave> obsList = FXCollections.observableArrayList(oraret);

        Ctable.setItems(obsList);
    }


    @FXML
    public void addReservationAdd() {
        if (BUserID.getText().isEmpty() || BScheduleID.getText().isEmpty() ||
                BNumberOfTickets.getText().isEmpty() || BTravelDate.getValue() == null || BReservationDate.getValue() == null) {
            showErrorAlert("Please fill in all fields");
            return;
        }

        try {
            CreateRezervimetDto dto = new CreateRezervimetDto(
                    Integer.parseInt(BUserID.getText()),
                    Integer.parseInt(BScheduleID.getText()),
                    BTravelDate.getValue(),
                    Integer.parseInt(BNumberOfTickets.getText())
            );

            rezervimetService.createRezervim(dto);

            showReservations();
            clearFields();
            showSuccessAlert("Reservation added successfully");

        } catch (Exception e) {
            showErrorAlert("Error: " + e.getMessage());
        }
    }

    @FXML
    public void addReservationSelect() {
        Rezervimet selected = addReservationtableview.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        BReservationID.setText(String.valueOf(selected.getRezervimId()));
        BUserID.setText(String.valueOf(selected.getPerdoruesId()));
        BScheduleID.setText(String.valueOf(selected.getOrariId()));
        BTravelDate.setValue(selected.getDataUdhetimit());
        BNumberOfTickets.setText(String.valueOf(selected.getNrBiletave()));
        BReservationDate.setValue(selected.getDataRezervimit());
    }
    @FXML
    public void addReservationDelete() {
        if (BReservationID.getText().isEmpty()) {
            showErrorAlert("Please select a reservation to delete.");
            return;
        }

        int id = Integer.parseInt(BReservationID.getText());

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete reservation ID " + id + "?");
        Optional<ButtonType> option = alert.showAndWait();

        if (option.isPresent() && option.get() == ButtonType.OK) {
            try {
                rezervimetService.deleteRezervim(id);
                showSuccessAlert("Reservation deleted successfully.");
                showReservations();
                clearFields();
            } catch (Exception e) {
                showErrorAlert("Failed to delete reservation: " + e.getMessage());
            }
        }
    }


    @FXML
    public void addReservationReset() {
        BReservationID.clear();
        BUserID.clear();
        BScheduleID.clear();
        BTravelDate.setValue(null);
        BNumberOfTickets.clear();
        BReservationDate.setValue(null);
    }

    @FXML
    public void addReservationUpdate() {
        if (BReservationID.getText().isEmpty() || BUserID.getText().isEmpty() || BScheduleID.getText().isEmpty()
                || BTravelDate.getValue() == null || BNumberOfTickets.getText().isEmpty()
                || BReservationDate.getValue() == null) {
            showErrorAlert("Please fill in all fields before updating.");
            return;
        }

        try {
            UpdateRezervimetDto dto = new UpdateRezervimetDto(
                    Integer.parseInt(BReservationID.getText()),
                    Integer.parseInt(BUserID.getText()),
                    Integer.parseInt(BScheduleID.getText()),
                    BTravelDate.getValue(),
                    Integer.parseInt(BNumberOfTickets.getText()),
                    BReservationDate.getValue()
            );

            rezervimetService.updateRezervim(dto);
            showSuccessAlert("Reservation updated successfully.");
            showReservations();
            clearFields();

        } catch (Exception e) {
            showErrorAlert("Update failed: " + e.getMessage());
        }
    }


    public void addReservationSearch() {
        FilteredList<Rezervimet> filter = new FilteredList<>(obslist, e -> true);

        Search.textProperty().addListener((observable, oldValue, newValue) -> {
            filter.setPredicate(r -> {
                if (newValue == null || newValue.isEmpty()) return true;
                String lower = newValue.toLowerCase();

                return String.valueOf(r.getRezervimId()).contains(lower) ||
                        String.valueOf(r.getPerdoruesId()).contains(lower) ||
                        String.valueOf(r.getOrariId()).contains(lower) ||
                        String.valueOf(r.getNrBiletave()).contains(lower) ||
                        r.getDataUdhetimit().toString().contains(lower) ||
                        r.getDataRezervimit().toString().contains(lower);
            });

            SortedList<Rezervimet> sorted = new SortedList<>(filter);
            sorted.comparatorProperty().bind(addReservationtableview.comparatorProperty());
            addReservationtableview.setItems(sorted);

            System.out.println("Search triggered, filtered size: " + filter.size());
        });
        @FXML
        public void addOrariLinjaveReset() {
            CScheduleID.clear();
            CTrainID.clear();
            CStartID.clear();
            CArrivalID.clear();
            CDepartureTime.setValue(null);
            CArrivalTime.setValue(null);
            CDay.setValue(null);
        }

        @FXML
        public void addOrariLinjaveUpdate() {
            if (CScheduleID.getText().isEmpty()
                    || CTrainID.getText().isEmpty()
                    || CStartID.getText().isEmpty()
                    || CArrivalID.getText().isEmpty()
                    || CDepartureTime.getValue() == null
                    || CArrivalTime.getValue() == null
                    || CDay.getValue() == null) {

                showErrorAlert("Please fill in all fields before updating.");
                return;
            }

            try {
                UpdatedOrariLinjaveDto dto = new UpdatedOrariLinjaveDto(
                        Integer.parseInt(CScheduleID.getText()),
                        CDay.getValue()
                );

                orariService.updateOrari(dto);
                showSuccessAlert("Schedule updated successfully!");
                addOrariLinjaveReset();

            } catch (Exception e) {
                e.printStackTrace();
                showErrorAlert("Failed to update schedule: " + e.getMessage());
            }
        }




        @FXML
        public void addOrariLinjaveAdd() {
            try {
                if (CTrainID.getText().isEmpty() || CStartID.getText().isEmpty() || CArrivalID.getText().isEmpty()
                        || CDepartureTime.getValue() == null || CArrivalTime.getValue() == null || CDay.getValue() == null) {
                    showErrorAlert("Please fill all the fields to add a schedule");
                    return;
                }


                java.sql.Time kohaNisjes = java.sql.Time.valueOf(CDepartureTime.getValue());
                java.sql.Time kohaMbrritjes = java.sql.Time.valueOf(CArrivalTime.getValue());


                CreateOrariLinjaveDto dto = new CreateOrariLinjaveDto(
                        Integer.parseInt(CTrainID.getText()),
                        Integer.parseInt(CStartID.getText()),
                        Integer.parseInt(CArrivalID.getText()),
                        kohaNisjes,
                        kohaMbrritjes,
                        CDay.getValue()
                );

                orariService.createOrar(dto);
                showSuccessAlert("Schedule added successfully");
                addOrariLinjaveReset();
                showAllOraret();

            } catch (IllegalArgumentException e) {
                showErrorAlert("Time format is incorrect. Use the correct format HH:mm:ss.");
            } catch (Exception e) {
                showErrorAlert("Error: " + e.getMessage());
            }
        }


        private void showAllOraret() {
            List<OrariLinjave> lista = orariService.getAllOraret();
            ObservableList<OrariLinjave> observable = FXCollections.observableArrayList(lista);
            Ctable.setItems(observable);
        }


        @FXML
        public void addOrariLinjaveDelete() {
            try {
                if (CScheduleID.getText().isEmpty()) {
                    showErrorAlert("Please enter Schedule ID in order to delete");
                    return;
                }

                int id = Integer.parseInt(CScheduleID.getText());

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to delete the schedule " + id + "?");

                Optional<ButtonType> option = alert.showAndWait();
                if (option.isPresent() && option.get() == ButtonType.OK) {
                    boolean deleted = orariService.deleteOrar(id);
                    if (deleted) {
                        showSuccessAlert("Schedule deleted successfully.");
                        addOrariLinjaveReset();
                        showAllOraret();
                    }
                }

            } catch (Exception e) {
                showErrorAlert("Error:" + e.getMessage());
            }
        }


        @FXML
        public void addOrariLinjaveSelect() {
            OrariLinjave selected = Ctable.getSelectionModel().getSelectedItem();
            if (selected != null) {
                CScheduleID.setText(String.valueOf(selected.getOrariId()));
                CTrainID.setText(String.valueOf(selected.getTrenId()));
                CStartID.setText(String.valueOf(selected.getNisjaId()));
                CArrivalID.setText(String.valueOf(selected.getMbrritjaId()));
                CDepartureTime.setValue(selected.getKohaNisjes().toString());
                CArrivalTime.setValue(selected.getKohaMbrritjes().toString());
                CDay.setValue(selected.getDita());
            }
        }

        private void clearFields() {
            BReservationID.clear();
            BUserID.clear();
            BScheduleID.clear();
            BNumberOfTickets.clear();
            BTravelDate.setValue(null);
            BReservationDate.setValue(null);
        }

        private void showErrorAlert(String message) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        }

        private void showSuccessAlert(String message) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        }
    }

    }
