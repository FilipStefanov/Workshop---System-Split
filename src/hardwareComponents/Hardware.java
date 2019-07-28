package hardwareComponents;

import softwareComponents.Software;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class Hardware {
    private String name;
    private String type;
    private int maxCapacity;
    private int maxMemory;
    private Map<String, Software> softwareComponents;
    private int usedCapacity;
    private int usedMemory;

    public Hardware(String name, String type, int maxCapacity, int maxMemory) {
        this.name = name;
        this.type = type;
        this.setMaxCapacity(maxCapacity);
        this.setMaxMemory(maxMemory);
        this.softwareComponents = new LinkedHashMap<>();
        this.usedCapacity = 0;
        this.usedMemory = 0;

    }

    public String getName() {
        return this.name;
    }

    protected void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    protected void setMaxMemory(int maxMemory) {
        this.maxMemory = maxMemory;
    }

    private boolean hasFreeCapacity(int capacity) {
        return (this.maxCapacity -
                (this.usedCapacity + capacity)) >= 0;
    }

    private boolean hasFreeMemory(int memory) {
        return (this.maxMemory -
                (this.usedMemory + memory)) >= 0;
    }

    public void addSoftware(Software software) {
        if (this.hasFreeCapacity(software.getCapacityConsumption()) &&
                this.hasFreeMemory(software.getMemoryConsumption())) {

            this.softwareComponents.put(software.getName(), software);
            this.usedCapacity += software.getCapacityConsumption();
            this.usedMemory += software.getMemoryConsumption();
        }
    }


    public void releaseSoftware(String softwareName) {
        if (this.softwareComponents.containsKey(softwareName)) {
            Software software = this.softwareComponents.remove(softwareName);
            this.usedCapacity -= software.getCapacityConsumption();
            this.usedMemory -= software.getMemoryConsumption();
        }


    }

    public int getSoftwareComponentsCount() {
        return this.softwareComponents.size();
    }

    public int getMaxCapacity() {
        return this.maxCapacity;
    }

    public int getMaxMemory() {
        return this.maxMemory;
    }

    public int getUsedCapacity() {
        return this.usedCapacity;
    }

    public int getUsedMemory() {
        return this.usedMemory;
    }

    public String getType() {
        return this.type;
    }

    @Override
    public String toString() {

        String separator = java.lang.System.lineSeparator();
        int expressCount = (int) this.softwareComponents.keySet()
                .stream()
                .filter(k -> this.softwareComponents.get(k).getType().equals("Express")
                )
                .count();

        int lightCount = this.softwareComponents.size()-expressCount;

        StringBuilder builder = new StringBuilder();

        builder.append("Hardware Component - ")
                .append(this.getName())
                .append(separator)
                .append("Express Software Components - ")
                .append(expressCount)
                .append(separator)
                .append("Light Software Components - ")
                .append(lightCount)
                .append(separator)
                .append("Memory Usage: ")
                .append(this.getUsedMemory())
                .append(" / ")
                .append(this.getMaxMemory())
                .append(separator)
                .append("Capacity Usage: ")
                .append(this.getUsedCapacity())
                .append(" / ")
                .append(this.getMaxCapacity())
                .append(separator)
                .append("Type: ")
                .append(this.getType())
                .append(separator)
                .append("Software Components: ");

        String[] values = this.softwareComponents.values()
                .stream()
                .map(Software::getName)
                .toArray(String[]::new);

        if (values.length == 0){
            builder.append("None");
        }else{
            builder.append(String.join(", ", values));
        }

        return builder.toString();
    }
}
