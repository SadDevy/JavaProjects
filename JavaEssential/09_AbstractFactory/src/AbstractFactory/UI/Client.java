package AbstractFactory.UI;

import AbstractFactory.FurnitureFactories.Furnitures.Common.IChair;
import AbstractFactory.FurnitureFactories.Furnitures.Common.IFurnitureFactory;
import AbstractFactory.FurnitureFactories.Furnitures.Common.ISofa;

public class Client {
    private IChair chair;
    private ISofa sofa;

    public Client(IFurnitureFactory factory)
            throws InstantiationException, IllegalAccessException {
        chair = factory.createChair(true, true);
        sofa = factory.createSofa(true, true);
    }

    @Override
    public String toString() {
        return String.format("Кровать: %s, стул: %s", sofa, chair);
    }
}
