package demo;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import static javafx.collections.FXCollections.observableArrayList;

public class Hello extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello");
        VBox root = new VBox() {{
            TableView<Person> table = createTable();
            getChildren().add(table);
            getChildren().add(createAddButton(table));
        }};
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }

    private Node createAddButton(TableView<Person> table) {
        return new Button("Add") {{
            setOnAction(event -> {
                data.add(new Person());
                triggerEditOnNewRow(table);
            });
        }};
    }

    private void triggerEditOnNewRow(TableView<Person> table) {
        table.edit(data.size() - 1, table.getColumns().get(0));
    }

    private final ObservableList<Person> data = observableArrayList(
            new Person("AAA", 23),
            new Person("BBB", 11),
            new Person("CCC", 78),
            new Person("DDD", 34)
    );

    private TableView<Person> createTable() {
        return new TableView<Person>() {{
            getColumns().add(new TableColumn<Person, String>("Name") {{
                setCellValueFactory(new PropertyValueFactory<>("name"));
                setCellFactory(TextFieldTableCell.forTableColumn());
                setOnEditCommit(event -> getPerson(event).setName(event.getNewValue()));
            }});
            getColumns().add(new TableColumn<Person, Integer>("Number") {{
                setCellValueFactory(new PropertyValueFactory<>("number"));
                setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter() {
                    @Override
                    public Integer fromString(String value) {
                        try {
                            return super.fromString(value);
                        } catch (NumberFormatException e) {
                            new Alert(Alert.AlertType.ERROR, "Invalid number: " + value).showAndWait();
                            throw e;
                        }
                    }
                }));
                setOnEditCommit(event -> getPerson(event).setNumber(event.getNewValue()));
            }});
            setItems(data);
            setEditable(true);
        }};
    }

    private <T> Person getPerson(TableColumn.CellEditEvent<Person, T> event) {
        return event.getTableView().getItems().get(event.getTablePosition().getRow());
    }
}
