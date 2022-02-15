package AbstractFactory.FurnitureFactories.Furnitures.School;

import AbstractFactory.FurnitureFactories.Furnitures.Common.GeneralFurnitureFactory;

public class SchoolFurnitureFactory
        extends GeneralFurnitureFactory<SchoolFurniture, SchoolChair, SchoolSofa> {
    public SchoolFurnitureFactory(Class<SchoolChair> schoolChairClass, Class<SchoolSofa> schoolSofaClass) {
        super(schoolChairClass, schoolSofaClass);
    }
}
