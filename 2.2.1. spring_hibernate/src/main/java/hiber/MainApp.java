package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("John", "Conor", "JohnConor@mail.com");
      User user2 = new User("Sam", "Cook", "SamCook@mail.com");
      User user3 = new User("Frank", "Sinatra", "FrankSinatra@mail.com");
      User user4 = new User("Neil", "Armstrong", "NeilNeil@mail.com");

      user1.setCar(new Car("Ford-mustang", 500));
      user2.setCar(new Car("Chevrolet - impala", 67));
      user3.setCar(new Car("Cadillac - Eldorado", 700));
      user4.setCar(new Car("Apollo", 11));

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = " + user.getCar());
         System.out.println();
      }

      System.out.println(userService.getUsersCar("Ford-mustang", 500));

      context.close();
   }
}
