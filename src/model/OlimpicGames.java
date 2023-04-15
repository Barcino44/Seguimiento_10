package model;

import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class OlimpicGames {
    ArrayList<Country> countries;
    static String folder ="data";
    static String path = "data/data.txt";

    public OlimpicGames() {
        countries = new ArrayList<>();
    }

    public ArrayList<Country> getCountries() {
        return countries;
    }
    public void addCountry(Country country) {
        if (!countries.contains(country)){
            countries.add(country);
        }
        else {
            int index = countries.indexOf(country);
            countries.get(index).addGold(country.getGold());
            countries.get(index).addPlate(country.getPlate());
            countries.get(index).addBronze(country.getBronze());
        }
    }
    public void persist() throws IOException {
        File file = new File(path); //Me genera la persistencia (hace que los archivos no se borren)
        if (file.exists()) {
            FileInputStream fis = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String content = "";
            String line = "";
            while ((line = reader.readLine()) != null) {
                content += line + "\n";
            }
            System.out.println(content);
            Gson gson = new Gson();
            Country[] array = gson.fromJson(content, Country[].class); //Almaceno los datos como arreglo.
            countries.addAll(Arrays.asList(array));
            fis.close();//Cierro el lector.
        }else {
            File f = new File(folder);
            if (!f.exists()) {
                f.mkdirs();
            }
            file.createNewFile();
        }
    }

    public void save() throws IOException {  //Con esto guardo los archivos que importé y los que construya
        File file = new File(path);
        FileOutputStream fos = new FileOutputStream(file); //Escribir// Fileoutput strings (corriente de salida)

        Gson gson = new Gson();//Creo un archivo tipo Json.
        String data = gson.toJson(countries); //Se guardan en Json.

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
        writer.write(data); //Escribir (guardar informacion)
        writer.flush(); //Vaciar el buffer (vaciar informacion en el archivo destino)
        fos.close(); //Cerrar el archivo (Se debe cerrar)
    }
    public void medalTable(){
        Collections.sort(countries);
        countries.forEach(country ->{
            System.out.println("PAIS: "+ country.getName()+", OROS: "+country.getGold()+", PLATAS: "+country.getPlate()+", BRONCES: "+ country.getBronze()+ ", CANTIDAD DE MEDALLAS: "+ country.getTotalMedals());
        });
    }
    public void totalMedals(){
        Collections.sort(countries, (a, b) -> {
            return b.getTotalMedals() - a.getTotalMedals();
        });
        countries.forEach(country ->{
            System.out.println("PAIS: "+ country.getName()+", CANTIDAD DE MEDALLAS: "+country.getTotalMedals()+", OROS: "+country.getGold()+", PLATAS: "+country.getPlate()+", BRONCES: "+ country.getBronze());
        });
    }
    public Object[] orderByCountry(){
        Object[] array = countries.toArray();
            Country previous;
            Country current;
            for (int i=0; i< array.length-1; i++){ //Me recorre todo el arreglo.
                for (int j=1; j< array.length-i;j++) { //Me va recorriendo el arreglo, como voy aumentando i, voy reduciendo el recorrido de j.
                    if (array[j - 1] instanceof Country && array[j] instanceof Country) {
                        if (((Country) array[j - 1]).getName().compareTo(((Country) array[j]).getName()) > 0) { //Si el arreglo en la pos anterior es mayor que el arreglo en la pos actual.
                            previous = (Country) array[j - 1]; //A la variable temporal le añado la posicion menor.
                            current = (Country) array[j];//A la variable temporal le añado la posicion mayor.
                            array[j - 1] = current;//Organizo el arreglo.
                            array[j] = previous;
                        }
                    }
                }
            }
            return array;
        }
    public void printCountries(){
        Object[] countriesOrder= orderByCountry();
        for (int i=0; i<countriesOrder.length;i++) {
            if (countriesOrder[i] instanceof Country) {
                System.out.println("PAIS:" + ((Country) countriesOrder[i]).getName() + ", CANTIDAD DE MEDALLAS: " + ((Country) countriesOrder[i]).getTotalMedals() + ", OROS: " + ((Country) countriesOrder[i]).getGold() + ", PLATAS: " + ((Country) countriesOrder[i]).getPlate() + ", BRONCES: " + ((Country) countriesOrder[i]).getBronze());
            }
        }
    }
}
