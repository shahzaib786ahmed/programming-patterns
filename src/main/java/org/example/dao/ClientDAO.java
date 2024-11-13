package org.example.dao;

import org.example.model.Client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {

        public static final String BASE_PATH = "jdbc:sqlite:./src/main/resources/database/";
        public static final String DB_PATH = BASE_PATH +  "data.db";

        // sql statement
        public static final String CREATE_CLIENTS_TABLE_SQL = """
            CREATE TABLE IF NOT EXISTS clients (
                id INTEGER PRIMARY KEY,
                lName TEXT NOT NULL,
                fName TEXT NOT NULL,
                passportNumber TEXT NOT NULL,
                phoneNumber TEXT NOT NULL,
                emailAddress TEXT NOT NULL,
                age INTEGER,
                userName TEXT,
                password TEXT,
                loyaltyPoints INTEGER,
                )
            """;
//        public static final String ALTER_STUDENTS_TABLE_ADD_GENDER_COLUMN_SQL = """
//            ALTER TABLE s ADD COLUMN gender TEXT
//            """;
        public static final String DROP_CLIENTS_TABLE = """
            DROP TABLE IF EXISTS clients
            """;



        /**
         * connects to a local db file
         * @param path the path of a local db file
         * @return the connection to the local db file
         */
        public static Connection connect(String path) {
            Connection connection;
            try {
                connection = DriverManager.getConnection(path);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return connection;
        }

        /**
         * creates a new table in a local database
         * @param sql the sql statement to create the new table
         */
        public static void createTable(String sql) {
            if (!sql.toUpperCase().contains("CREATE")) {
                System.out.println("creating table failed: sql statement must contain keyword CREATE");
                return;
            }

            executeDdlAndDml(sql);
        }

        /**
         * alter an existing table in a local database
         * @param sql the sql statement to alter an existing table
         */
        public static void alterTable(String sql) {
            if (!sql.toUpperCase().contains("ALTER")) {
                System.out.println("altering table failed: sql statement must contain keyword ALTER");
                return;
            }

            executeDdlAndDml(sql);
        }

        /**
         * drop an existing table in a local database
         * @param sql the sql statement to drop an existing table
         */
        public static void dropTable(String sql) {
            if (!sql.toUpperCase().contains("DROP")) {
                System.out.println("dropping table failed: sql statement must contain keyword DROP");
                return;
            }

            executeDdlAndDml(sql);
        }

        /**
         * inserts record into an existing table in a local database
         * @param sql the sql statement to insert record to an existing table
         */
        public static void insertRecord(String sql) {
            if (!sql.toUpperCase().contains("INSERT INTO")) {
                System.out.println("inserting record failed: sql statement must contain keyword INSERT INTO");
                return;
            }

            executeDdlAndDml(sql);
        }

        /**
         * updates a record in an existing table in a local database
         * @param sql the sql statement to update a record in an existing table
         */
        public static void updateRecord(String sql) {
            if (!sql.toUpperCase().contains("UPDATE")) {
                System.out.println("updating record failed: sql statement must contain keyword UPDATE");
                return;
            }

            executeDdlAndDml(sql);
        }

        /**
         * deletes a record into an existing table in a local database
         * @param sql the sql statement to delete a record to an existing table
         */
        public static void deleteRecord(String sql) {
            if (!sql.toUpperCase().contains("DELETE")) {
                System.out.println("deleting record failed: sql statement must contain keyword DELETE");
                return;
            }

            executeDdlAndDml(sql);
        }

        private static void executeDdlAndDml(String sql) {
            try (Connection connection = connect(DB_PATH);
                 Statement statement = connection.createStatement()) {
                statement.executeUpdate(sql);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        /**
         * generates a sql statement for inserting a record into an existing table in a local database
         * @param tableName the name of the table to insert into
         * @param params the values of a record to insert
         * @return the sql statement for inserting
         */
        public static String generateInsertSql(String tableName, Object... params) {
            StringBuilder sb = new StringBuilder("INSERT INTO ");

            sb.append(tableName).append(" VALUES(");

            for (int i = 0; i < params.length; i++) {
                Object value = params[i];
                if (i < params.length - 1) {        // with ", "
                    if (value instanceof String) {
                        sb.append("'").append(value).append("', ");
                    } else {
                        sb.append(value).append(", ");
                    }
                } else {                            // without ", "
                    if (value instanceof String) {
                        sb.append("'").append(value).append("'");
                    } else {
                        sb.append(value);
                    }
                }
            }
            sb.append(")");
            return sb.toString();
        }

        /**
         * generates a sql statement for updating a record based on one column
         * @param tableName the name of the table to be updated
         * @param params the values of the record
         * @return the sql statement for updating
         */
    /*
    "name", "mike", "age", 21, "gender", "male", "id", 1
     */
        public static String generateUpdateRecordSql(String tableName, Object... params) {
            StringBuilder sb = new StringBuilder("UPDATE ");
            sb.append(tableName).append(" SET ");

            for (int i = 0; i < params.length; i++) {
                Object value = params[i];
                if (i < params.length - 4) {                // appending column=values with ", "
                    if (i % 2 == 0) {   // appending the column name
                        sb.append(value).append(" = ");
                    } else {    // appending the real value
                        if (value instanceof String) {
                            sb.append("'").append(value).append("', ");
                        } else {
                            sb.append(value).append(", ");
                        }
                    }
                } else if (i < params.length - 2) {         // appending the last column=values without ", "
                    if (i % 2 == 0) {   // appending the column name
                        sb.append(value).append(" = ");
                    } else {    // appending the real value
                        if (value instanceof String) {
                            sb.append("'").append(value).append("'");
                        } else {
                            sb.append(value);
                        }
                    }
                } else {                                    // appending the condition
                    if (i % 2 == 0) {   // appending the column name
                        sb.append(" WHERE ").append(value).append(" = ");
                    } else {    // appending the real value
                        if (value instanceof String) {
                            sb.append("'").append(value).append("'");
                        } else {
                            sb.append(value);
                        }
                    }
                }
            }

            return sb.toString();
        }

        /**
         * generates a sql statement for deleting a record based on one column
         * @param tableName the name of the table at where the record is to be deleted
         * @param params the values of the record
         * @return the sql statement for deleting a record
         */
        public static String generateDeleteRecordSql(String tableName, Object... params) {
            StringBuilder sb = new StringBuilder("DELETE FROM ");
            sb.append(tableName)
                    .append(" WHERE ")
                    .append(params[0])      // appending column name for the condition
                    .append(" = ");

            Object value = params[1];       // appending column value for the condition
            value = (value instanceof String) ?
                    "'" + value + "'" :
                    value;
            sb.append(value);

            return sb.toString();
        }

        /**
         * selects all values of a table
         * @param tableName the name of the table to be queried
         * @return a list of Student in this specific case
         */
        public static List<Client> selectAll(String tableName) {
            String sql = "SELECT * FROM " + tableName;

            List<Client> clients = new ArrayList<>();
            try (Connection connection = connect(DB_PATH);
                 Statement statement = connection.createStatement();
                 ResultSet rs = statement.executeQuery(sql)
            ) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String lName = rs.getString("lName");
                    String fName = rs.getString("fName");
                    String passportNumber = rs.getString("passportNumber");
                    String phoneNumber = rs.getString("phoneNumber");
                    String emailAddress = rs.getString("emailAddress");
                    int age = rs.getInt("age");
                    String userName = rs.getString("userName");
                    String password = rs.getString("password");
                    int loyaltyPoints = rs.getInt("loyaltyPoints");

                    clients.add(new Client( lName,  fName,  passportNumber,  phoneNumber, emailAddress,  age, userName, password));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            return clients;
        }
    }
