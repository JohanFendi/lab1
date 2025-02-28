package src.model;

public class ContainerM1 implements Container {

    private static final String NEGATIVE_ARGUMENT_ERROR = "NegativeVolumeException: volume cannot be negative.";
    private final double MAX_VOLUME;
    private double currentVolume = 0;

    public ContainerM1(double maxVolume) {
        this.MAX_VOLUME = maxVolume;
    }

    @Override
    public double getAvailableVolume(){

        return this.MAX_VOLUME - this.currentVolume;
    }


    @Override
    public double load(double volumeToAdd){
        if (volumeToAdd < 0){
            throw new IllegalArgumentException(NEGATIVE_ARGUMENT_ERROR);
        }
        double overflow = 0;
        if (this.currentVolume + volumeToAdd > this.MAX_VOLUME){
            this.currentVolume = MAX_VOLUME;
            overflow = this.currentVolume + volumeToAdd - this.MAX_VOLUME;
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
