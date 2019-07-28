package softwareComponents;

public class ExpressSoftware extends Software {
    private static final String TYPE = "Express";

    public ExpressSoftware(String name,  int capacity, int memory) {
        super(name, ExpressSoftware.TYPE, capacity, memory);
    }


    @Override
    protected void setMemoryConsumption(int memoryConsumption) {
        super.setMemoryConsumption(memoryConsumption * 2);
    }


}
