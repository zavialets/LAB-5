package org.example;

import java.sql.*;

public class createDB {

    private final Connection connection;

    createDB() throws SQLException {
        connection = new DataSource().getConnection();
    }


    private void createLibraryTable() throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("CREATE TABLE label (" +
                "id_library int PRIMARY KEY, " +
                "name varchar(45) unique, " +
                "address varchar(50) not null);");

        stmt.executeUpdate();
    }

    private void createBookTable() throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("CREATE TABLE if not exists album (" +
                "book_id int not null, " +
                "FOREIGN KEY (library_id) REFERENCES label(id_library), " +
                "title varchar(50) not null, " +
                "author_id int not null, " +
                "FOREIGN KEY (author_id) REFERENCES author(author_id), " +
                "release_date DATE not null, " +
                "genre varchar(30), " +
                ");");

        stmt.executeUpdate();
    }

    private void createAuthor() throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("CREATE TABLE if not exists artist (" +
                "author_id int PRIMARY KEY, " +
                "author_name varchar(50) not null, " +
                "birthday DATE not null, " +
                "pseudonym varchar(50) not null);");

        stmt.executeUpdate();
    }

    public void createStructure() throws SQLException {
        createLibraryTable();
        createBookTable();
        createAuthor();
    }
}
