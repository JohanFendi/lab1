import java.security.PublicKey;
import java.util.ArrayList;

public class Container<T> {

    private double maxVolume;
    private double currentVolume = 0;

    public Container(double maxVolume) {
        this.maxVolume = maxVolume;
    }




    public double loadVolume(T obj, double volumeToAdd){
        double overflow = 0;
        if (this.currentVolume + volumeToAdd > this.maxVolume){
            this.currentVolume = maxVolume;
            overflow = this.currentVolume + volumeToAdd - this.maxVolume;
        }
        return overflow;
    }

    public double unloadVolume(T obj, double volumeToUnload) {
        double unloadedVolume = volumeToUnload;
        if (this.currentVolume >= volumeToUnload) {
            unloadedVolume = this.currentVolume;
            this.currentVolume = 0;
        }
        return unloadedVolume;
    }
}
