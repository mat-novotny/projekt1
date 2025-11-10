import cz.engeto.plants.Plant;
import cz.engeto.plants.PlantException;
import cz.engeto.plants.PlantList;

import java.time.LocalDate;
import java.util.Comparator;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        PlantList list = new PlantList();
        try {
            list.loadFile("resources/kvetiny.txt","\t");
        } catch (PlantException e)
        {
            list.clearList();
            System.err.println("Chyba při načítání souboru: " + e.getMessage());
        }
        for(Plant plant:list.getListCopy())
        {
            System.out.println(plant.getWateringInfo());
        }
        list.addPlant(new Plant("Tulipán na prodej 0",14));

        for (int i = 1; i < 11; i++) {
            list.addPlant(new Plant("Tulipán na prodej " + i,14));
        }
        list.removePlant(2);
        list.saveContentToFile("resources/kvetiny-output.txt","\t");
        try {
            list.loadFile("resources/kvetiny-output.txt","\t");
        } catch (PlantException e)
        {
            list.clearList();
            System.err.println("Chyba při načítání souboru: " + e.getMessage());
        }
        for(Plant plant:list.getListCopy())
        {
            System.out.println(plant.getWateringInfo());
        }
        list.sortByLastWatered();
        for(Plant plant:list.getListCopy())
        {
            System.out.println(plant.getWateringInfo());
        }
        list.sortList(Comparator.comparing(Plant::getPlanted));
        for(Plant plant:list.getListCopy())
        {
            System.out.println(plant.getName() + ", zasazena " + plant.getPlanted());
        }
    }
}
