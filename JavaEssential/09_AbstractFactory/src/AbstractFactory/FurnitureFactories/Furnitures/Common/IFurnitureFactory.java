package AbstractFactory.FurnitureFactories.Furnitures.Common;

public interface IFurnitureFactory {
    IChair createChair(boolean hasLegs, boolean sitOn) throws InstantiationException, IllegalAccessException;
    ISofa createSofa(boolean hasLegs, boolean hasSidePanels) throws InstantiationException, IllegalAccessException;
}
