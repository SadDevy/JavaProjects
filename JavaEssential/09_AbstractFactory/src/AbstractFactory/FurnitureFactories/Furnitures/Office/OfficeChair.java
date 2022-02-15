package AbstractFactory.FurnitureFactories.Furnitures.Office;

import AbstractFactory.FurnitureFactories.Furnitures.Common.IChair;
import AbstractFactory.FurnitureFactories.Furnitures.Common.IFurniture;

public class OfficeChair implements IChair, IFurniture<OfficeFurniture> {
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
        value = sitOn;
    }

    @Override
    public String toString() {
        return "OfficeChair";
    }
}
