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
        setLastWatered(lastWatered);
        setWateringFrequency(wateringFrequency);
    }

    public Plant(String name, int wateringFrequency) {
       this(name, "", LocalDate.now(), LocalDate.now(), wateringFrequency);
    }
    public Plant(String name) {
        this(name, "", LocalDate.now(), LocalDate.now(), 7);
    }

    public String getWateringInfo()
    {
        return name + ", naposledy zalitá " + lastWatered.toString() + ", další zalití doporučeno " + lastWatered.plusDays(wateringFrequency).toString();
    }

    public void doWateringNow()
    {
        setLastWatered(LocalDate.now());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDate getPlanted() {
        return planted;
    }

    public void setPlanted(LocalDate planted) {
        this.planted = planted;
    }

    public LocalDate getLastWatered() {
        return lastWatered;
    }

    public void setLastWatered(LocalDate lastWatered) {
        if(lastWatered.isBefore(planted))
        {
            throw new PlantException("Datum zalití nesmí být starší než datum zasazení! Zadali jste " + lastWatered.toString());
        }
        this.lastWatered = lastWatered;
    }

    public int getWateringFrequency() {
        return wateringFrequency;
    }

    public void setWateringFrequency(int wateringFrequency) {
        if(wateringFrequency<=0)
        {
            throw new PlantException("Frekvence zalévání musí být kladné číslo! Zadali jste " + wateringFrequency);
        }
        this.wateringFrequency = wateringFrequency;
    }
}
