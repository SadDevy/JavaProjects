package Utilities;

import DAO.TestDAO;

public class Remover {
    private final TestDAO testContext = new TestDAO();

    public void removeTestFromDb(int id) {
        testContext.remove(id);
    }
}
