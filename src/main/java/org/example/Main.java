package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class ApplianceManager {
    private List<Appliance> appliances;

    public ApplianceManager() {
        appliances = new ArrayList<>();
    }

    public void addAppliance(Appliance appliance) {
        appliances.add(appliance);
    }

    public void turnOnAppliance(int index) {
        if (index >= 0 && index < appliances.size()) {
            appliances.get(index).turnOn();
        }
    }

    public int calculateTotalPower() {
        return appliances.stream()
                .filter(Appliance::isOn)
                .mapToInt(Appliance::getPower)
                .sum();
    }

    public void sortAppliancesByPower() {
        appliances.sort(Comparator.comparingInt(Appliance::getPower));
    }

    public List<Appliance> findAppliancesByEmRange(int minRange, int maxRange) {
        return appliances.stream()
                .filter(appliance -> appliance.getEmRange() >= minRange && appliance.getEmRange() <= maxRange)
                .collect(Collectors.toList());
    }

    public void displayAppliances() {
        appliances.forEach(System.out::println);
    }
}
class Appliance {
    private String name;
    private int power; // Потужність у ватах
    private boolean isOn;
    private int emRange; // ЕМ випромінювання в умовних одиницях

    public Appliance(String name, int power, int emRange) {
        this.name = name;
        this.power = power;
        this.emRange = emRange;
        this.isOn = false;
    }

    public String getName() {
        return name;
    }

    public int getPower() {
        return power;
    }

    public boolean isOn() {
        return isOn;
    }

    public int getEmRange() {
        return emRange;
    }

    public void turnOn() {
        isOn = true;
    }

    public void turnOff() {
        isOn = false;
    }

    @Override
    public String toString() {
        return "Прилад - " + name + ". Потужність = " + power +
                ", isOn = " + isOn + ", eлектромагнітнe випромінювання = " + emRange;
    }
}
public class Main {
    public static void main(String[] args) {
        ApplianceManager manager = new ApplianceManager();

        // Додавання приладів
        manager.addAppliance(new Appliance("Холодильник", 200, 50));
        manager.addAppliance(new Appliance("Телевізор", 150, 70));
        manager.addAppliance(new Appliance("Комп'ютер", 300, 100));
        manager.addAppliance(new Appliance("Мікрохвильовка", 1000, 150));

        // Увімкнення деяких приладів
        manager.turnOnAppliance(0); // Увімкнути холодильник
        manager.turnOnAppliance(2); // Увімкнути комп'ютер

        // Підрахунок потужності
        System.out.println("Загальна потужність: " + manager.calculateTotalPower() + " Вт");

        // Сортування приладів за потужністю
        manager.sortAppliancesByPower();
        System.out.println("Прилади після сортування:");
        manager.displayAppliances();

        // Пошук приладу за діапазоном випромінювання
        int minRange = 50, maxRange = 100;
        List<Appliance> foundAppliances = manager.findAppliancesByEmRange(minRange, maxRange);
        System.out.println("Прилади у діапазоні випромінювання " + minRange + "-" + maxRange + ":");
        foundAppliances.forEach(System.out::println);
    }
}
