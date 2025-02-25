package src.model;

public interface Container {
    double load(double volumeToAdd);
    double unload(double volumeToUnload);
    double getAvailableVolume();

}
