package src;

public interface Container {
    double load(double volumeToAdd);
    double unLoad(double volumeToRemove);
    double getAvailableVolume();
}
