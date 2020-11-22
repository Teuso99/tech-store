package dao;

import java.sql.*;

public class Dao
{
    private Connection connection;
    private String erro = "";
    private PreparedStatement statement;

    public Connection getConnection()
    {
        return connection;
    }

    public String getErro()
    {
        return erro;
    }

    public PreparedStatement getStatement()
    {
        return statement;
    }
    
    public boolean connect()
    {
        try
        {
            erro = "";
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tech_store?useTimeZone=true&serverTimezone=UTC&autoReconnect=true&useSSL=false","root","adm123");
            return true;
        }
        catch(Exception e)
        {
            erro = e.getMessage();
            return false;
        }
    }
    
    public PreparedStatement createPreparedStatement(String sql) throws SQLException
    {
        if(connection != null)
        {
            statement = connection.prepareStatement(sql);
            return statement;
        }
        else
        {
            return null;
        }
    }
    
    public void setString(int index, String value) throws SQLException
    {
        if(statement != null)
        {
            statement.setString(index, value);
        }
    }
    
    public void execute() throws SQLException
    {
        if(statement != null)
        {
            statement.execute();
        }
    }
    
    public ResultSet executeQuery() throws SQLException
    {
        if(statement != null)
        {
            return statement.executeQuery();
        }
        else
        {
            return null;
        }
    }
    
    public void close() throws SQLException
    {
        if(statement != null)
        {
            statement.close();
        }
        
        if(connection != null)
        {
            connection.close();
        }
    }
}