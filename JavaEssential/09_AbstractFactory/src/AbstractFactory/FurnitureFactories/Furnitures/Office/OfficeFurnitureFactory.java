package AbstractFactory.FurnitureFactories.Furnitures.Office;

import AbstractFactory.FurnitureFactories.Furnitures.Common.GeneralFurnitureFactory;

public class OfficeFurnitureFactory
        extends GeneralFurnitureFactory<OfficeFurniture, OfficeChair, OfficeSofa> {
    public OfficeFurnitureFactory(Class<OfficeChair> officeChairClass, Class<OfficeSofa> officeSofaClass) {
        super(officeChairClass, officeSofaClass);
    }
}
