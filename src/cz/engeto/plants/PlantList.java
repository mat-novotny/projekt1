package cz.engeto.plants;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.*;

public class PlantList {
    private List<Plant> plants = new ArrayList<>();

    public void addPlant(Plant plant)
    {
        plants.add(plant);
    }

    public Plant getPlant(int index)
    {
        return plants.get(index);
    }

    public void removePlant(int index)
    {
        plants.remove(index);
    }
    public void removePlant(Plant plant)
    {
        plants.remove(plant);
    }

    public List<Plant> getListCopy()
    {
        return new ArrayList<Plant>(plants);
    }

    public void clearList()
    {
        plants.clear();
    }

    public void sortName()
    {
        plants.sort(null);
    }
    public void sortLastWatered()
    {
        plants.sort(Comparator.comparing(Plant::getLastWatered));
    }
    public List<Plant> getListToWater()
    {
        List<Plant> toWater = new ArrayList();
        for (Plant plant : plants)
        {
            if(plant.getLastWatered().plusDays(plant.getWateringFrequency()).isBefore(LocalDate.now()))
            {
                toWater.add(plant);
            }
        }
        return toWater;
    }
    public void loadFile(String path, String delimiter)
    {
        final int NUMBER_OF_PARTS_ON_LINE = 5;
        int lineNumber = 0;
        plants.clear();
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(path)))) {
            while (scanner.hasNextLine()) {
                lineNumber++;
                String line = scanner.nextLine();
                String[] parts = line.split(delimiter);
                if (parts.length != NUMBER_OF_PARTS_ON_LINE) {
                    throw new PlantException(
                            "Nesprávný počet položek na řádku číslo: " +lineNumber+
                                    "\n"+ line+
                                    "\nOčekáváno: "+ NUMBER_OF_PARTS_ON_LINE +
                                    ", skutečně: " + parts.length);
                }
                Plant plant = parseLine(parts, lineNumber, line);
                addPlant(plant);
            }
        } catch (FileNotFoundException e) {
            throw new PlantException(
                    "Soubor "+path+" nebyl nalezen!\n"+e.getLocalizedMessage());
        }
    }
    private Plant parseLine(String[] parts, int lineNumber, String line)
            throws PlantException {
        try {
            String name = parts[0];
            String notes = parts[1];
            LocalDate planted = LocalDate.parse(parts[4]);
            LocalDate lastWatered = LocalDate.parse(parts[3]);
            int wateringFrequency = Integer.parseInt(parts[2]);
            return new Plant(name,notes,planted,lastWatered,wateringFrequency);
        } catch (NumberFormatException e) {
            throw new PlantException(
                    "Chybný formát čísla na řádku číslo: " + lineNumber +
                            "\n" + line +
                            "\n" + e.getLocalizedMessage());
        } catch (DateTimeParseException e) {
            throw new PlantException(
                    "Chybný formát data na řádku číslo: " + lineNumber +
                            "\n" + line +
                            "\n" + e.getLocalizedMessage());

        }
        catch (Exception e) {
            throw new PlantException(
                    "Chyba při zpracování řádku číslo: " + lineNumber +
                            "\n"+ line +
                            "\n" + e.getLocalizedMessage());
        }
    }
    public void saveContentToFile(String path, String delimiter) throws PlantException
    {
        try(PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(path)))){
            for(Plant plant : plants)
            {
                String line = plant.getName() + delimiter + plant.getNotes() + delimiter + plant.getWateringFrequency() + delimiter
                        + plant.getLastWatered() + plant.getPlanted();
            }
        } catch (IOException e) {
            throw new PlantException(
                    "Chyba při ukládání do souboru " + path + "!\n" + e.getLocalizedMessage()
            );
        }
    }

}
