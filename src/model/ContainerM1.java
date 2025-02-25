package src.model;

public class ContainerM1 implements Container {

    private static final String NEGATIVE_ARGUMENT_ERROR = "NegativeVolumeException: volume cannot be negative.";
    private final double maxVolume;
    private double currentVolume = 0;

    public ContainerM1(double maxVolume) {
        this.maxVolume = maxVolume;
    }

    @Override
    public double getAvailableVolume(){

        return this.maxVolume - this.currentVolume;
    }


    @Override
    public double load(double volumeToAdd){
        if (volumeToAdd < 0){
            throw new IllegalArgumentException(NEGATIVE_ARGUMENT_ERROR);
        }
        double overflow = 0;
        if (this.currentVolume + volumeToAdd > this.maxVolume){
            this.currentVolume = maxVolume;
            overflow = this.currentVolume + volumeToAdd - this.maxVolume;
        }
        return overflow;
    }
    @Override
    public double unload(double volumeToUnload) {
        if (volumeToUnload < 0){
            throw new IllegalArgumentException(NEGATIVE_ARGUMENT_ERROR);
        }
        double unloadedVolume = volumeToUnload;
        if (this.currentVolume >= volumeToUnload) {
            unloadedVolume = this.currentVolume;
            this.currentVolume = 0;
        }
        return unloadedVolume;
    }
}
