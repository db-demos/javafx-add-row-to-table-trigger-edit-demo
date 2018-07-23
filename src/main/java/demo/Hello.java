package demo;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static javafx.collections.FXCollections.observableArrayList;

public class Hello extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello");
        VBox root = new VBox() {{
            getChildren().add(createTable());
            getChildren().add(createAddButton());
        }};
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }

    private final ObservableList<Person> data = observableArrayList(
            new Person("AAA", 23, observableArrayList("Apple", "Banana")),
            new Person("BBB", 11, observableArrayList("Pear")),
            new Person("DDD", 34, observableArrayList("Orange"))
    );

    private Button createAddButton() {
        return new Button("Add Data") {{
            setOnAction(event -> data.add(new Person("NEW", new Random().nextInt(100), Arrays.asList("date", "watermelon"))));
        }};
    }

    private TableView<Person> createTable() {
        return new TableView<Person>() {{
            getColumns().add(new TableColumn<Person, String>("Name") {{
                setCellValueFactory(new PropertyValueFactory<>("name"));
            }});
            getColumns().add(new TableColumn<Person, Integer>("Number") {{
                setCellValueFactory(new PropertyValueFactory<>("number"));
            }});
            getColumns().add(new TableColumn<Person, List<String>>("Fruits") {{
                setCellValueFactory(new PropertyValueFactory<>("fruits"));
            }});
            setItems(data);
        }};
    }
}
