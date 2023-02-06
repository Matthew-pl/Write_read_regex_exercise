import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringParser {

    public static void initiateShopping () throws IOException {

        Person p = new Person(null, null);

        System.out.println("Please type \"car\" or \"bike\"");
        Scanner scannerOfConsoleInput = new Scanner(System.in);
        String vehicleType = scannerOfConsoleInput.nextLine();

        System.out.println("Please provide your vehicle model");
        String vehicleName = scannerOfConsoleInput.nextLine();

        Vehicle customerVehicle = null;

        if (vehicleType.equals("car")) {
            Car car = new Car(vehicleName);
            customerVehicle = car;
        }
        else if (vehicleName.equals("bike")) {
            Bike bike = new Bike(vehicleName);
            customerVehicle = bike;
        }

        File customerNames = new File("src\\Customer_names.txt");
        Scanner scannerFromFile = new Scanner(customerNames);

        while (scannerFromFile.hasNext()) {
            Pattern pattern = Pattern.compile("([a-zA-Z]+)\\s+([a-zA-Z]+)");
            Matcher matcher = pattern.matcher(scannerFromFile.nextLine());
            matcher.find();
            p.setName(matcher.group(1));
            p.setSurname(matcher.group(2));
        }

        File shoppingHistory = new File("src\\Shopping_history.txt");

        BufferedReader reader = new BufferedReader(new FileReader(shoppingHistory));
        FileWriter fw = new FileWriter(shoppingHistory, true);
        PrintWriter writer = new PrintWriter(fw);

        writer.printf("%s %s %s has bought %s of model %s \n", System.currentTimeMillis(), p.getName(), p.getSurname(), customerVehicle.getClass(), customerVehicle.getName());
        writer.close();

        System.out.printf("%s %s has bought %s of model %s", p.getName(), p.getSurname(), customerVehicle.getClass(), customerVehicle.getName());

    }

}
