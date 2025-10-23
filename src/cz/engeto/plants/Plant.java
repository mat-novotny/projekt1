package cz.engeto.plants;

import java.time.LocalDate;

public class Plant {
    private String name;
    private String notes;
    private LocalDate planted;
    private LocalDate lastWatered;
    private int wateringFrequency;

    public Plant(String name, String notes, LocalDate planted, LocalDate lastWatered, int wateringFrequency) {
        this.name = name;
        this.notes = notes;
        this.planted = planted;
        this.lastWatered = lastWatered;
        this.wateringFrequency = wateringFrequency;
    }

    public Plant(String name, int wateringFrequency) {
       this(name, "", LocalDate.now(), LocalDate.now(), wateringFrequency);
    }
    public Plant(String name) {
        this(name, "", LocalDate.now(), LocalDate.now(), 7);
    }

}
