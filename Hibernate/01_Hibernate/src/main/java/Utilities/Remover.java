package Utilities;

import DAO.StudentTestDAO;
import org.hibernate.SessionFactory;

public class Remover {
    private StudentTestDAO testContext;

    public Remover(SessionFactory factory) {
        testContext = new StudentTestDAO(factory);
    }

    public void removeTestFromDb(int id) {
        testContext.remove(id);
    }
}
