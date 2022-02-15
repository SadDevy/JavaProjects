package AbstractFactory.FurnitureFactories.Furnitures.Office;

import AbstractFactory.FurnitureFactories.Furnitures.Common.IFurniture;
import AbstractFactory.FurnitureFactories.Furnitures.Common.ISofa;

public class OfficeSofa implements ISofa, IFurniture<OfficeFurniture> {
    private boolean hasLegs;
    @Override
    public boolean hasLegs() {
        return hasLegs;
    }

    @Override
    public void setHasLegs(boolean value) {
        hasLegs = value;
    }

    private boolean hasSidePanels;
    @Override
    public boolean hasSidePanels() {
        return hasSidePanels;
    }

    @Override
    public void setHasSidePanels(boolean value) {
        hasSidePanels = value;
    }

    @Override
    public String toString() {
        return "OfficeSofa";
    }
}
