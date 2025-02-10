import java.security.PublicKey;
import java.util.ArrayList;

public class Container {

    private double maxVolume;
    private double currentVolume = 0;

    public Container(double maxVolume) {
        this.maxVolume = maxVolume;
    }

    public double loadVolume(double volumeToAdd){
        double overflow = 0;
        if (this.currentVolume + volumeToAdd > this.maxVolume){
            this.currentVolume = maxVolume;
            overflow = this.currentVolume + volumeToAdd - this.maxVolume;
        }
        return overflow;
    }

    public double unloadVolume(double volumeToUnload) {
        double unloadedVolume = volumeToUnload;
        if (this.currentVolume >= volumeToUnload) {
            unloadedVolume = this.currentVolume;
            this.currentVolume = 0;
        }
        return unloadedVolume;
    }
}
