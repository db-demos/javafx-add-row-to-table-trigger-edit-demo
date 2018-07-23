package demo;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Person {
    private final SimpleStringProperty name = new SimpleStringProperty();
    private final SimpleIntegerProperty number = new SimpleIntegerProperty();

    Person(String name, Integer number) {
        setName(name);
        setNumber(number);
    }

    Person() {
        
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
