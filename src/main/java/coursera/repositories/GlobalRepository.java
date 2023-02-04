package coursera.repositories;

import coursera.DBManager;

import java.sql.Connection;

public interface GlobalRepository{
     Connection c = DBManager.INSTANCE.getConnection();

}
