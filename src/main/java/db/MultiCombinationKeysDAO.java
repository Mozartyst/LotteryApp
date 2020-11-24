package db;

import dataSupport.ObjectStreamMaker;
import entity.MultiCombinationNumber;

import java.io.*;
import java.sql.*;

public class MultiCombinationKeysDAO {
    private static Connection connect;

    public MultiCombinationKeysDAO() {
        connect = DBConnector.connect();
    }

    public void createTable(String tableName) {
        try {
            connect.createStatement().execute("CREATE TABLE `" + tableName + "` (" +
                    "`ID` INT NOT NULL AUTO_INCREMENT," +
                    "`MultiCombinationKeys`  VARBINARY(10000) NOT NULL," +
                    "PRIMARY KEY (`ID`));");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void dropTable(String tableName) {
        try {
            connect.createStatement().execute("DROP TABLE `" + tableName + "`;");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public int addObject(Object o, String tableName) {
        try {
            PreparedStatement statement = connect.prepareStatement("INSERT INTO `" + tableName + "` " +
                    "(`ID`,`MultiCombinationKeys`)" +
                    "VALUES" +
                    "(NULL ,?);", Statement.RETURN_GENERATED_KEYS);
            statement.setBytes(1, ObjectStreamMaker.convertObjectToByte(o));
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    public Object getObjectById(int id, String tableName) {
        Object o = null;
        try {
            PreparedStatement preparedStatement = connect.prepareStatement("SELECT MultiCombinationKeys FROM " +
                    tableName + " WHERE ID=" + id + " ;");
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                byte[] bytes1 = resultSet.getBytes(1);
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes1);
                ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
                o = objectInputStream.readObject();
            }
        } catch (SQLException | IOException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return o;
    }

    public MultiCombinationNumber getObjectByCompareObjects(MultiCombinationNumber multiCombinationKeys) {
        addObject(multiCombinationKeys, "compar");
            MultiCombinationNumber multiCombinationKeys1 = null;
        try {
            PreparedStatement preparedStatement = connect.prepareStatement("SELECT MultiCombinationKeys FROM multiCombination WHERE multiCombination.MultiCombinationKeys = (SELECT MultiCombinationKeys FROM compar WHERE ID=1);");
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                byte[] bytes1 = resultSet.getBytes(1);

                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes1);
                ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
                multiCombinationKeys1 = (MultiCombinationNumber) objectInputStream.readObject();
            }
            connect.createStatement().execute("DELETE FROM compar WHERE ID>=1;");
        } catch (SQLException | ClassNotFoundException | IOException throwables) {
            throwables.printStackTrace();
        }
        return multiCombinationKeys1;
    }
}
