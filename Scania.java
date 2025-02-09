import java.awt.*;

public class Scania extends Vehicle {

    private static final int NR_DOORS = 2;
    private static final int ENGINE_POWER = 50;
    private static final String MODEL_NAME = "Scania";
    private static final double ANGLE_MAX = 70;
    private static final double ANGLE_MIN = 0;

    private double rampAngle = 0.0;

    // TODO "Raw use" - måste man sätta typ direkt?
    Container container;


    public Scania(Color color, double containerVolume) {
        super(Scania.NR_DOORS, color, Scania.ENGINE_POWER, Scania.MODEL_NAME);

        this.container = new Container<>(containerVolume);
    }

    @Override
    protected double speedFactor() {
        return getEnginePower() * 0.01;
    }


    private boolean isRampAdjustable() {
        return this.getCurrentSpeed() == 0;
    }

    private boolean isScaniaMovable() {
        return rampAngle == 0;
    }

    protected boolean adjustRamp(double angle) {
        if (this.isRampAdjustable()) {
            rampAngle = clamp(ANGLE_MIN, ANGLE_MAX, angle);
            return true;
        } else {
            System.out.println("Scania cannot adjust ramp while moving");
            return false;
        }
    }

    public void move() {
        if (isScaniaMovable()) {
            Position position = this.getPosition();
            Vector vector = this.getVector();
            position.setX(position.getX() + this.getCurrentSpeed() * vector.getX());
            position.setY(position.getY() + this.getCurrentSpeed() * vector.getY());
            this.setPosition(position);
        }
    }

    @Override
    public void gas(double amount) {
        if (amount < 0 || amount > 1 || !isScaniaMovable()) {
            throw new IllegalArgumentException("Gas-amount must be between 0 and 1 and ramp cannot be lowered");
        }
        incrementSpeed(amount);
        setCurrentSpeed(clamp(0.0, this.getEnginePower(), this.getCurrentSpeed()));
    }


    public double getRampAngle() {
        return rampAngle;
    }
}
