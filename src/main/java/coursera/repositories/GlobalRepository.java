package coursera.repositories;

import coursera.DBManager;

import java.sql.Connection;

public interface GlobalRepository{
     Connection CONNECTION = DBManager.INSTANCE.getConnection();

}
