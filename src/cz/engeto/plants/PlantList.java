package cz.engeto.plants;

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

}
