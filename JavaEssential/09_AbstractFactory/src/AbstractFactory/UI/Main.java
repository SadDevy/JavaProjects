package AbstractFactory.UI;

import AbstractFactory.FurnitureFactories.Furnitures.Common.GeneralFurnitureFactory;
import AbstractFactory.FurnitureFactories.Furnitures.Common.IFurnitureFactory;
import AbstractFactory.FurnitureFactories.Furnitures.Office.OfficeChair;
import AbstractFactory.FurnitureFactories.Furnitures.Office.OfficeFurniture;
import AbstractFactory.FurnitureFactories.Furnitures.Office.OfficeSofa;
import AbstractFactory.FurnitureFactories.Furnitures.School.SchoolChair;
import AbstractFactory.FurnitureFactories.Furnitures.School.SchoolFurniture;
import AbstractFactory.FurnitureFactories.Furnitures.School.SchoolFurnitureFactory;
import AbstractFactory.FurnitureFactories.Furnitures.School.SchoolSofa;

public class Main {
    public static void main(String[] args)
            throws InstantiationException, IllegalAccessException {
        IFurnitureFactory[] factories = createFactories();
        for (IFurnitureFactory factory : factories) {
            Client client = new Client(factory);
            System.out.println(client);
        }
    }

    private static IFurnitureFactory[] createFactories() {
        return  new IFurnitureFactory[] {
                new SchoolFurnitureFactory(SchoolChair.class, SchoolSofa.class),
                new GeneralFurnitureFactory<SchoolFurniture, SchoolChair, SchoolSofa>(SchoolChair.class, SchoolSofa.class),
                new GeneralFurnitureFactory<OfficeFurniture, OfficeChair, OfficeSofa>(OfficeChair.class, OfficeSofa.class)
        };
    }
}

