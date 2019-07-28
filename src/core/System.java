package core;

import hardwareComponents.Hardware;
import softwareComponents.Software;

import java.util.LinkedHashMap;
import java.util.Map;

public class System {
    private Map<String, Hardware> hardwareComponents;

    public System() {
        this.hardwareComponents = new LinkedHashMap<>();
    }


    public void addHardware(Hardware hardware) {

        this.hardwareComponents.putIfAbsent(hardware.getName(), hardware);
    }

    public void addSoftwareComponent(String hardwareName, Software software) {
        if (this.hardwareComponents.containsKey(hardwareName)) {
            this.hardwareComponents.get(hardwareName).addSoftware(software);
        }
    }

    public void releaseSoftware(String hardwareName, String softwareName) {
        if (this.hardwareComponents.containsKey(hardwareName)) {
            this.hardwareComponents.get(hardwareName).releaseSoftware(softwareName);
        }
    }


    public String analyze() {

        String separator = java.lang.System.lineSeparator();
        StringBuilder builder = new StringBuilder("System Analysis");
        builder.append(separator);
        int softwareComponentsCount = 0;
        int capacityTotal = 0;
        int capacityInUse = 0;
        int memoryTotal = 0;
        int memoryInUse = 0;


        for (String name : hardwareComponents.keySet()) {
            softwareComponentsCount
                    += this.hardwareComponents
                    .get(name)
                    .getSoftwareComponentsCount();

            memoryInUse += this.hardwareComponents.get(name).getUsedMemory();
            memoryTotal += this.hardwareComponents.get(name).getMaxMemory();
            capacityInUse += this.hardwareComponents.get(name).getUsedCapacity();
            capacityTotal += this.hardwareComponents.get(name).getMaxCapacity();
        }
        builder.append("Hardware Components: ")
                .append(this.hardwareComponents.size())
                .append(separator)
                .append("Software Components: ")
                .append(softwareComponentsCount)
                .append(separator)
                .append("Total Operational Memory: ")
                .append(memoryInUse)
                .append(" / ")
                .append(memoryTotal)
                .append(separator)
                .append("Total Capacity Taken: ")
                .append(capacityInUse)
                .append(" / ")
                .append(capacityTotal)
        ;
        return builder.toString();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        this.hardwareComponents.entrySet()
                .stream()
                .filter(e -> e.getValue().getType().equals("Power"))
                .forEach(e -> builder
                        .append(e.getValue().toString())
                        .append(java.lang.System.lineSeparator()));


        this.hardwareComponents.entrySet()
                .stream()
                .filter(e -> e.getValue().getType().equals("Heavy"))
                .forEach(e -> builder
                        .append(e.getValue().toString())
                        .append(java.lang.System.lineSeparator()));


        return builder.toString();
    }
}
