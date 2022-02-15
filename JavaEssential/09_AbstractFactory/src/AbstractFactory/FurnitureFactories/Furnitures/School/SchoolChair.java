package AbstractFactory.FurnitureFactories.Furnitures.School;

import AbstractFactory.FurnitureFactories.Furnitures.Common.IChair;
import AbstractFactory.FurnitureFactories.Furnitures.Common.IFurniture;

public class SchoolChair implements IChair, IFurniture<SchoolFurniture> {
    private boolean hasLegs;

    @Override
    public boolean hasLegs() {
        return hasLegs;
    }

    @Override
    public void setHasLegs(boolean value) {
        hasLegs = value;
    }

    private boolean sitOn;
    @Override
    public boolean sitOn() {
        return sitOn;
    }

    @Override
    public void setSitOn(boolean value) {
        sitOn = value;
    }

    @Override
    public String toString() {
        return "SchoolChair";
    }
}
