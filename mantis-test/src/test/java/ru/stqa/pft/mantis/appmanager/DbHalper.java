package ru.stqa.pft.mantis.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.mantis.model.Users;
import ru.stqa.pft.mantis.model.UsersData;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbHalper {

  private final SessionFactory sessionFactory;
  private final ApplicationManager app;


  public DbHalper(ApplicationManager app) {
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    this.app = app;
  }

  public Users users() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<UsersData> result = session.createQuery("from UserData").list();
    session.getTransaction().commit();
    session.close();
    return new Users(result);
  }

  public String getUserName() {
    Connection conn = null;
    List<String> result = new ArrayList<>();

    try {
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugtracker?serverTimezone=UTC&user=root&password=");
      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery("select username from mantis_user_table where username<>'administrator'");

      while (rs.next()) {
        result.add(rs.getString("username"));
      }
      rs.close();
      st.close();
      conn.close();
      return result.get(1);

    } catch (SQLException ex) {

      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
      return null;
    }

  }
}

/*  try {
    +      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?user=root&password=");
    +      Statement st = conn.createStatement();
    +      ResultSet rs = st.executeQuery("select group_id, group_name, group_header, group_footer from group_list");
    +      Groups groups =new Groups();
    +      while (rs.next()){
      +        groups.add(new GroupData().withId(rs.getInt("group_id")).withName(rs.getString("group_name"))
              +                .withHeader(rs.getString("group_header")).withFooter(rs.getString("group_footer")));
      +      }
    +      rs.close();
    +      st.close();
    +      conn.close();
    +
            +      System.out.println(groups);
    +
            +    } catch (SQLException ex) {
    +      System.out.println("SQLException: " + ex.getMessage());
    +      System.out.println("SQLState: " + ex.getSQLState());
    +      System.out.println("VendorError: " + ex.getErrorCode());
    +    }
+  }*/


