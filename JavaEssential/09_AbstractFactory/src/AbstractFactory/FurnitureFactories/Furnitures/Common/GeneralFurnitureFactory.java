package AbstractFactory.FurnitureFactories.Furnitures.Common;

public class GeneralFurnitureFactory<TFurniture extends IFurniture<TFurniture>,
        TChair extends IChair & IFurniture<TFurniture>,
        TSofa extends ISofa & IFurniture<TFurniture>>
        implements IFurnitureFactory {

    private Class<TChair> chairClass;
    private Class<TSofa> sofaClass;

    public GeneralFurnitureFactory(Class<TChair> chairClass, Class<TSofa> sofaClass) {
        this.chairClass = chairClass;
        this.sofaClass = sofaClass;
    }

    @Override
    public IChair createChair(boolean hasLegs, boolean sitOn)
            throws InstantiationException, IllegalAccessException {
        TChair chair = chairClass.newInstance();
        chair.setHasLegs(hasLegs);
        chair.setSitOn(sitOn);

        return chair;
    }

    @Override
    public ISofa createSofa(boolean hasLegs, boolean hasSidePanels)
            throws InstantiationException, IllegalAccessException {
        TSofa sofa = sofaClass.newInstance();
        sofa.setHasLegs(hasLegs);
        sofa.setHasSidePanels(hasSidePanels);

        return sofa;
    }
}
