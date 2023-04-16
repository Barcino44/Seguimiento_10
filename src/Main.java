import Exceptions.NotTypeofMedalException;
import model.Country;
import model.OlimpicGames;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    static OlimpicGames olimpicGames = new OlimpicGames();
    public static void main(String[] args) throws IOException {
        Scanner reader = new Scanner(System.in);
        olimpicGames.persist();
        while (true) {
            System.out.println("****************************");
            System.out.println("Juegos Olimpicos Paris 2024");
            System.out.println("****************************");
            System.out.println("1. Ingresar un pais\n2. Mostrar medalleria\n3. Mostrar total de medallas\n4. Mostrar paises\n5. Salir");
            int option = 0;
            try {
                option = Integer.parseInt(reader.nextLine());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            switch (option) {
                case 1:
                    System.out.println("Ingrese el pais en el formato, Nombre::Tipo de medalla obtenida::Cantidad de medallas obtenidas");
                    String input = reader.nextLine();
                    String[] data = input.split("::");
                    try {
                        if (data[1].equalsIgnoreCase("ORO")) {
                            olimpicGames.addCountry(new Country(data[0], Integer.parseInt(data[2]), 0, 0));
                        } else if (data[1].equalsIgnoreCase("PLATA")) {
                            olimpicGames.addCountry(new Country(data[0], 0, Integer.parseInt(data[2]), 0));
                        } else if (data[1].equalsIgnoreCase("BRONCE")) {
                            olimpicGames.addCountry(new Country(data[0], 0, 0, Integer.parseInt(data[2])));
                        } else {
                            try {
                                throw new NotTypeofMedalException();
                            } catch (NotTypeofMedalException e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                        e.printStackTrace();
                    }
                    olimpicGames.save();
                    break;
                case 2:
                    olimpicGames.medalTable();
                    break;
                case 3:
                    olimpicGames.totalMedals();
                    break;
                case 4:
                    olimpicGames.printCountries();
                    break;
                case 5:
                    System.exit(0);
            }
        }
    }
}