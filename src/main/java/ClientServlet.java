import java.sql.Connection;
import java.sql.Date;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kursach.Agreement;
import kursach.Contragent;
import kursach.Provider;

import java.io.IOException;
import javax.servlet.ServletException;

public class ClientServlet extends HttpServlet
{
    private static String URL = "jdbc:mysql://localhost:3306/?user=root/java_lab4";
    private static String USERNAME = "root";
    private static String PASSWORD = "!16832619";

    private static String submit;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        request.setCharacterEncoding("UTF-8");

        try
        {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }

        try(Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD); Statement statement=connection.createStatement())
        {
            ArrayList<Agreement> database = new ArrayList<Agreement>();
            ResultSet databaseResult = statement.executeQuery("SELECT * from java_kursach.agreement;");
            ArrayList<Contragent> databaseContragent = new ArrayList<Contragent>();
            ArrayList<Provider> databaseProvider = new ArrayList<Provider>();

            while (databaseResult.next())
            {
                database.add(new Agreement(Integer.parseInt(databaseResult.getString("id")), 
                databaseResult.getString("name"), 
                databaseResult.getString("provider"), 
                databaseResult.getString("counterparty"),
                Date.valueOf(databaseResult.getString("date_begin")), 
                Date.valueOf(databaseResult.getString("date_end")), 
                databaseResult.getString("requirements"), 
                Double.parseDouble(databaseResult.getString("price")),
                databaseResult.getString("type")));
            }

            databaseResult=statement.executeQuery("SELECT * from java_kursach.contragents;");

            while (databaseResult.next())
            {
                databaseContragent.add(new Contragent(Integer.parseInt(databaseResult.getString("id")), 
                databaseResult.getString("name"), 
                Date.valueOf(databaseResult.getString("birthday")),
                Double.parseDouble(databaseResult.getString("state"))));
            }

            databaseResult=statement.executeQuery("SELECT * from java_kursach.providers;");

            while (databaseResult.next())
            {
                databaseProvider.add(new Provider(Integer.parseInt(databaseResult.getString("id")),
                databaseResult.getString("name"), 
                databaseResult.getString("industry"), 
                Double.parseDouble(databaseResult.getString("profit"))));
            }

            for (int i=0; i<database.size(); i++)
            {
                Agreement temp=database.get(i);
                temp.setCounterparty(databaseContragent.get(Integer.parseInt(database.get(i).getCounterparty())).getName());
                temp.setProvider(databaseProvider.get(Integer.parseInt(database.get(i).getProvider())).getName());
                database.set(i, temp);
            }

            ArrayList<String> status = new ArrayList<String>();
            for (int i=0; i<database.size(); i++)
            {
                java.util.Date today = new java.util.Date();
                int res=database.get(i).getDataEnd().compareTo(today);

                if (res>=0) 
                {
                    Calendar calendar=Calendar.getInstance();
                    calendar.setTime(today);
                    calendar.add(Calendar.DAY_OF_MONTH, -3);
                    java.util.Date threeDays=calendar.getTime();
                    if (database.get(i).getDataEnd().compareTo(threeDays)>=0)
                    {
                        String notification="Договор " + database.get(i).getName() + " скоро будет выполнен";
                        HttpSession session = request.getSession();
                        session.setAttribute("notification", notification);
                    }

                    status.add("Заказ выполняется");
                }
                else 
                {
                    status.add("Заказ выполнен");
                }    
            }

            submit = request.getParameter("display");

            switch (submit)
            {
                // Договоры
                case "addAgreement":
                request.setAttribute("databaseContragent", databaseContragent);
                request.setAttribute("databaseProvider", databaseProvider);
                request.getRequestDispatcher("addAgreement.jsp").forward(request, response); 
                break;

                case "updateAgreement":  
                request.setAttribute("database", database);
                request.setAttribute("databaseContragent", databaseContragent);
                request.setAttribute("databaseProvider", databaseProvider);
                request.getRequestDispatcher("updateAgreement.jsp").forward(request, response);
                break;


                case "deleteAgreement": 
                request.setAttribute("database", database);
                request.getRequestDispatcher("deleteAgreement.jsp").forward(request, response);
                break;

                case "checkAgreement": 
                request.setAttribute("database", database);
                request.setAttribute("status", status);
                request.getRequestDispatcher("checkAgreement.jsp").forward(request, response); 
                break;

                // Контрагенты
                case "addContragent": request.getRequestDispatcher("addContragent.jsp").forward(request, response); break;

                case "updateContragent":  
                request.setAttribute("database", databaseContragent);
                request.getRequestDispatcher("updateContragent.jsp").forward(request, response);
                break;

                case "deleteContragent":  
                request.setAttribute("database", databaseContragent);
                request.getRequestDispatcher("deleteContragent.jsp").forward(request, response);
                break;

                case "checkContragent":
                request.setAttribute("database", databaseContragent);
                request.getRequestDispatcher("checkContragent.jsp").forward(request, response);
                break;

                // Поставщики
                case "addProvider": request.getRequestDispatcher("addProvider.jsp").forward(request, response); break;

                case "updateProvider":  
                request.setAttribute("database", databaseProvider);
                request.getRequestDispatcher("updateProvider.jsp").forward(request, response);
                break;

                case "deleteProvider":  
                request.setAttribute("database", databaseProvider);
                request.getRequestDispatcher("deleteProvider.jsp").forward(request, response);
                break;

                case "checkProvider":
                request.setAttribute("database", databaseProvider);
                request.getRequestDispatcher("checkProvider.jsp").forward(request, response);
                break;
            }

            statement.close();
            connection.close();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        try
        {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }

        try(Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD); Statement statement=connection.createStatement())
        {
            request.setCharacterEncoding("UTF-8");

            int count=0;
            int elementsDeleted=0;
            ResultSet result;
            Agreement attributes;
            Contragent attributesContragent;
            Provider attributesProvider;
            int contragentId=0;
            int providerId=0;
            
            switch (submit)
            {
                // Договоры
                case "addAgreement":
                result=statement.executeQuery("SELECT COUNT(*) as count from java_kursach.agreement;");
                if (result.next()) count=Integer.parseInt(result.getString("count"));

                if (ValidateSum(request.getParameter("getPrice")) && ValidateDate(Date.valueOf(request.getParameter("dateBegin")), Date.valueOf(request.getParameter("dateEnd"))))
                {
                    
                attributes = new Agreement(count, 
                request.getParameter("getName"), 
                request.getParameter("getProvider"), request.getParameter("getContragent"),
                Date.valueOf(request.getParameter("dateBegin")), 
                Date.valueOf(request.getParameter("dateEnd")), request.getParameter("getRequirements"), 
                Double.parseDouble(request.getParameter("getPrice")),
                request.getParameter("getType"));

                result=statement.executeQuery("SELECT id from java_kursach.contragents where name=\""+attributes.getCounterparty()+"\";");
                if (result.next()) contragentId=Integer.parseInt(result.getString("id"));
                result=statement.executeQuery("SELECT id from java_kursach.providers where name=\""+attributes.getProvider()+"\";");
                if (result.next()) providerId=Integer.parseInt(result.getString("id"));

                statement.executeUpdate("INSERT INTO java_kursach.agreement (id, name, provider, counterparty, date_begin, date_end, requirements, price, type) VALUES(" + attributes.toAdd(providerId, contragentId) + ")");
                
                request.setAttribute("message", "Данные успешно добавлены!");
                request.getRequestDispatcher("message.jsp").forward(request, response);
                }
                else
                {
                    request.setAttribute("message", "Неверные входные данные");
                    request.getRequestDispatcher("message.jsp").forward(request, response);
                }

                break;

                case "updateAgreement":
                result=statement.executeQuery("select count(*) as count from java_kursach.agreement;");
                if (result.next()) count=Integer.parseInt(result.getString("count"));
                for (int i=0; i<count; i++)
                {
                    if (ValidateSum(request.getParameter("getPrice"+String.valueOf(i))) && ValidateDate(Date.valueOf(request.getParameter("dateBegin"+String.valueOf(i))), Date.valueOf(request.getParameter("dateEnd"+String.valueOf(i)))))
                    {
                    attributes = new Agreement(i, 
                    request.getParameter("getName"+String.valueOf(i)), 
                    request.getParameter("getProvider"+String.valueOf(i)), 
                    request.getParameter("getContragent"+String.valueOf(i)),
                    Date.valueOf(request.getParameter("dateBegin"+String.valueOf(i))), 
                    Date.valueOf(request.getParameter("dateEnd"+String.valueOf(i))), 
                    request.getParameter("getRequirements"+String.valueOf(i)), 
                    Double.parseDouble(request.getParameter("getPrice"+String.valueOf(i))),
                    request.getParameter("getType"+String.valueOf(i)));

                    result=statement.executeQuery("SELECT id from java_kursach.contragents where name=\""+attributes.getCounterparty()+"\";");
                    if (result.next()) contragentId=Integer.parseInt(result.getString("id"));
                    result=statement.executeQuery("SELECT id from java_kursach.providers where name=\""+attributes.getProvider()+"\";");
                    if (result.next()) providerId=Integer.parseInt(result.getString("id"));
                
                    statement.executeUpdate("update java_kursach.agreement set " + attributes.toUpdate(providerId, contragentId) + " where id = " + i + ";");
                    }
                    else
                    {
                        request.setAttribute("message", "Неверные входные данные");
                        request.getRequestDispatcher("message.jsp").forward(request, response);
                        break;
                    }
                }

                request.setAttribute("message", "Данные успешно обновлены!");
                request.getRequestDispatcher("message.jsp").forward(request, response);
                break;

                case "deleteAgreement":
                result=statement.executeQuery("select count(*) as count from java_kursach.agreement;");
                if (result.next()) count=Integer.parseInt(result.getString("count"));
                elementsDeleted=0;
                for (int i=0; i<count; i++)
                {
                    String deleter=request.getParameter("deleted"+String.valueOf(i));
                    String id=request.getParameter("hiddenId"+String.valueOf(i));
                    if (deleter!=null)
                    {
                        statement.executeUpdate("delete from java_kursach.agreement where id = " + String.valueOf(Integer.parseInt(id)-elementsDeleted) + " limit 1;");
                            
                        for (int j=Integer.parseInt(id)+1-elementsDeleted; j<=count-elementsDeleted; j++)
                        {
                            statement.executeUpdate("update java_kursach.agreement set id = " + String.valueOf(j-1) + " where id = " + String.valueOf(j) +";");
                        }
                        elementsDeleted++;
                    }
                }

                request.setAttribute("message", "Данные успешно удалены!");
                request.getRequestDispatcher("message.jsp").forward(request, response);
                break;

                // Контрагенты
                case "addContragent":
                result=statement.executeQuery("SELECT COUNT(*) as count from java_kursach.contragents;");
                if (result.next()) count=Integer.parseInt(result.getString("count"));

                if (ValidateSum(request.getParameter("getState")))
                {
                attributesContragent = new Contragent(count, 
                request.getParameter("getName"),
                Date.valueOf(request.getParameter("getBirthday")), 
                Double.parseDouble(request.getParameter("getState")));

                statement.executeUpdate("INSERT INTO java_kursach.contragents (id, name, birthday, state) VALUES(" + attributesContragent.toAdd() + ")");
                
                request.setAttribute("message", "Данные успешно добавлены!");
                request.getRequestDispatcher("message.jsp").forward(request, response);
                }
                else 
                {
                    request.setAttribute("message", "Неверные входные данные!");
                    request.getRequestDispatcher("message.jsp").forward(request, response);
                    break;
                }
                break;

                case "updateContragent":
                result=statement.executeQuery("select count(*) as count from java_kursach.contragents;");
                if (result.next()) count=Integer.parseInt(result.getString("count"));
                for (int i=0; i<count; i++)
                {
                    if (!ValidateSum(request.getParameter("getState"+String.valueOf(i))))
                    {
                        request.setAttribute("message", "Неверные входные данные!");
                        request.getRequestDispatcher("message.jsp").forward(request, response);
                        break;
                    }
                    attributesContragent = new Contragent(i, 
                    request.getParameter("getName"+String.valueOf(i)),
                    Date.valueOf(request.getParameter("getBirthday"+String.valueOf(i))), 
                    Double.parseDouble(request.getParameter("getState"+String.valueOf(i))));

                    statement.executeUpdate("update java_kursach.contragents set " + attributesContragent.toUpdate() + " where id = " + i + ";");
                }

                request.setAttribute("message", "Данные успешно обновлены!");
                request.getRequestDispatcher("message.jsp").forward(request, response);
                break;

                case "deleteContragent":
                result=statement.executeQuery("select count(*) as count from java_kursach.contragents;");
                if (result.next()) count=Integer.parseInt(result.getString("count"));
                else count=0;
                elementsDeleted=0;
                for (int i=0; i<count; i++)
                {
                    String deleter=request.getParameter("deleted"+String.valueOf(i));
                    String id=request.getParameter("hiddenId"+String.valueOf(i));
                    if (deleter!=null)
                    {
                        statement.executeUpdate("delete from java_kursach.contragents where id = " + String.valueOf(Integer.parseInt(id)-elementsDeleted) + " limit 1;");
                            
                        for (int j=Integer.parseInt(id)+1-elementsDeleted; j<=count-elementsDeleted; j++)
                        {
                            statement.executeUpdate("update java_kursach.contragents set id = " + String.valueOf(j-1) + " where id = " + String.valueOf(j) +";");
                        }
                        elementsDeleted++;
                    }
                }

                request.setAttribute("message", "Данные успешно удалены!");
                request.getRequestDispatcher("message.jsp").forward(request, response);
                break;

                // Поставщики
                case "addProvider":
                result=statement.executeQuery("SELECT COUNT(*) as count from java_kursach.providers;");
                if (result.next()) count=Integer.parseInt(result.getString("count"));

                if (!ValidateSum(request.getParameter("getProfit")))
                {
                    request.setAttribute("message", "Неверные входные данные!");
                    request.getRequestDispatcher("message.jsp").forward(request, response);
                    break;
                }
                attributesProvider = new Provider(count, 
                request.getParameter("getName"),
                request.getParameter("getIndustry"), 
                Double.parseDouble(request.getParameter("getProfit")));

                statement.executeUpdate("INSERT INTO java_kursach.providers (id, name, industry, profit) VALUES(" + attributesProvider.toAdd() + ")");
                request.setAttribute("message", "Данные успешно добавлены!");
                request.getRequestDispatcher("message.jsp").forward(request, response);
                break;

                case "updateProvider":
                result=statement.executeQuery("select count(*) as count from java_kursach.providers;");
                if (result.next()) count=Integer.parseInt(result.getString("count"));
                for (int i=0; i<count; i++)
                {
                    if (!ValidateSum(request.getParameter("getProfit"+String.valueOf(i))))
                    {
                        request.setAttribute("message", "Неверные входные данные!");
                        request.getRequestDispatcher("message.jsp").forward(request, response);
                        break;
                    }

                    attributesProvider = new Provider(i, 
                    request.getParameter("getName"+String.valueOf(i)),
                    request.getParameter("getIndustry"+String.valueOf(i)), 
                    Double.parseDouble(request.getParameter("getProfit"+String.valueOf(i))));

                    statement.executeUpdate("update java_kursach.providers set " + attributesProvider.toUpdate() + " where id = " + i + ";");
                }

                request.setAttribute("message", "Данные успешно обновлены!");
                request.getRequestDispatcher("message.jsp").forward(request, response);
                break;

                case "deleteProvider":
                result=statement.executeQuery("select count(*) as count from java_kursach.providers;");
                if (result.next()) count=Integer.parseInt(result.getString("count"));
                elementsDeleted=0;
                for (int i=0; i<count; i++)
                {
                    String deleter=request.getParameter("deleted"+String.valueOf(i));
                    String id=request.getParameter("hiddenId"+String.valueOf(i));
                    if (deleter!=null)
                    {
                        statement.executeUpdate("delete from java_kursach.providers where id = " + String.valueOf(Integer.parseInt(id)-elementsDeleted) + " limit 1;");
                            
                        for (int j=Integer.parseInt(id)+1-elementsDeleted; j<=count-elementsDeleted; j++)
                        {
                            statement.executeUpdate("update java_kursach.providers set id = " + String.valueOf(j-1) + " where id = " + String.valueOf(j) +";");
                        }
                        elementsDeleted++;
                    }
                }

                request.setAttribute("message", "Данные успешно удалены!");
                request.getRequestDispatcher("message.jsp").forward(request, response); 
                break;
            }

            statement.close();
            connection.close();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    private static boolean ValidateSum(String label)
    {
        try
        {
            Double.parseDouble(label);
        }
        catch (Exception ex)
        {
            return false;
        }

        if (Double.parseDouble(label)<0) return false;

        return true;
    }

    private static boolean ValidateDate(Date dateBegin, Date dateEnd)
    {
        return dateEnd.compareTo(dateBegin)>0;
    }
}

