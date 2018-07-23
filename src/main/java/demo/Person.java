package demo;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Person {
    private final SimpleStringProperty name;
    private final SimpleIntegerProperty number;

    Person(String name, Integer number) {
        this.name = new SimpleStringProperty(name);
        this.number = new SimpleIntegerProperty(number);
    }

    public String getName() {
        return this.name.get();
    }

    public int getNumber() {
        return number.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setNumber(int number) {
        this.number.set(number);
    }
}
