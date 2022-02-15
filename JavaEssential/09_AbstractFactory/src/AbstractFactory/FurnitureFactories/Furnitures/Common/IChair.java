package AbstractFactory.FurnitureFactories.Furnitures.Common;

public interface IChair {
    boolean hasLegs();
    void setHasLegs(boolean value);

    boolean sitOn();
    void setSitOn(boolean value);
}
