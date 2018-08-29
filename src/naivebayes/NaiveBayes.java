package naivebayes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NaiveBayes {

    static List<Data> dataSet = new ArrayList<>();
    static List<Float> faktaYa = new ArrayList<>();
    static List<Float> faktaTidak = new ArrayList<>();
    static int total;
    static String cuaca, temperature, angin;
    static float Y = 0, T = 0;

    public static void main(String[] args) {
        readFile();
        printDataset();
        process();
    }

    private static void readFile() {
        BufferedReader br;
        String line;

        try {
            br = new BufferedReader(new FileReader("src\\resources\\NaiveBayes.csv"));
            while ((line = br.readLine()) != null) {

                String[] dataOlahraga = line.split(";");

                Data data = new Data();
                data.setCuaca(String.valueOf(dataOlahraga[0]));
                data.setTemperature(String.valueOf(dataOlahraga[1]));
                data.setAngin(String.valueOf(dataOlahraga[2]));
                data.setOlahraga(String.valueOf(dataOlahraga[3]));

                dataSet.add(data);
            }
//            dataSupervised.addAll(dataSet);
            total = dataSet.size();
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
    }

    private static void printDataset() {
        System.out.println("\nCuaca\tTemp\tAngin\tOlahraga?");
        System.out.println("--------------------------------");
        for (Data data : dataSet) {
            System.out.println(data.getCuaca() + "\t"
                    + data.getTemperature() + "\t"
                    + data.getAngin() + "\t"
                    + data.getOlahraga());
        }
    }

    private static void process() {

        for (Data data : dataSet) {
            if ("Ya".equals(data.getOlahraga())) {
                Y += 1;
            } else {
                T += 1;
            }
        }
        System.out.println("-------------------------\n");
        Scanner input = new Scanner(System.in);
        System.out.print("Cuaca : ");
        cuaca = input.nextLine();

        System.out.print("Temperature : ");
        temperature = input.nextLine();

        System.out.print("Kecepatan Angin : ");
        angin = input.nextLine();

        probabilitasYa();
        probabilitasTidak();
        perhitunganNB();

    }

    private static void probabilitasYa() {
        //ya
        System.out.println("\nFAKTA YA");
        if (!cuaca.isEmpty()) {
            float x1 = 0;

            for (Data data : dataSet) {
                if (data.getCuaca().equalsIgnoreCase(cuaca) && data.getOlahraga().equalsIgnoreCase("Ya")) {
                    x1 += 1;
                }
            }
            float tmp = (x1 / total) / (Y / total);
            faktaYa.add(tmp);
            System.out.println("Probabilitas Cuaca " + cuaca + " dengan fakta Ya adalah " + tmp);
        } else {
            float temp = 1;
            faktaYa.add(temp);
            //System.out.println("Probabilitas Cuaca " + cuaca + " dengan fakta Ya adalah " + temp);
        }
        //System.out.println("Cuaca : "+cuaca+"&"+"Fakta Ya "+tmp);
        if (!temperature.isEmpty()) {
            float x2 = 0;

            for (Data data : dataSet) {
                if (data.getTemperature().equalsIgnoreCase(temperature) && data.getOlahraga().equalsIgnoreCase("Ya")) {
                    x2 += 1;
                }
            }
            float tmp = (x2 / total) / (Y / total);
            System.out.println("Probabilitas Temperature " + temperature + " dengan fakta Ya adalah " + tmp);
            faktaYa.add(tmp);
        } else {
            float temp = 1;
            //System.out.println("Probabilitas Temperature " + temperature + " dengan fakta Ya adalah " + temp);
            faktaYa.add(temp);
        }
        if (!angin.isEmpty()) {
            float x3 = 0;

            for (Data data : dataSet) {
                if (data.getAngin().equalsIgnoreCase(angin) && data.getOlahraga().equalsIgnoreCase("Ya")) {
                    x3 += 1;
                }
            }
            float tmp = (x3 / total) / (Y / total);
            System.out.println("Probabilitas Kecepatan Angin " + angin + " dengan fakta Ya adalah " + tmp);
            faktaYa.add(tmp);
        } else {
            float temp = 1;
            //System.out.println("Probabilitas Kecepatan Angin " + angin + " dengan fakta Ya adalah " + temp);
            faktaYa.add(temp);
        }
    }

    private static void probabilitasTidak() {
        //tidak
        System.out.println("\nFAKTA TIDAK");
        if (!cuaca.isEmpty()) {
            float x1 = 0;

            for (Data data : dataSet) {
                if (data.getCuaca().equalsIgnoreCase(cuaca) && data.getOlahraga().equalsIgnoreCase("Tidak")) {
                    x1 += 1;
                }
            }
            float tmp = (x1 / total) / (T / total);
            System.out.println("Probabilitas Kecepatan Angin " + angin + " dengan fakta Tidak adalah " + tmp);
            faktaTidak.add(tmp);
        } else {
            float temp = 1;
            //System.out.println("Probabilitas Kecepatan Angin " + angin + " dengan fakta Tidak adalah " + temp);
            faktaTidak.add(temp);
        }
        if (!temperature.isEmpty()) {
            float x2 = 0;

            for (Data data : dataSet) {
                if (data.getTemperature().equalsIgnoreCase(temperature) && data.getOlahraga().equalsIgnoreCase("Tidak")) {
                    x2 += 1;
                }
            }
            float tmp = (x2 / total) / (T / total);
            System.out.println("Probabilitas Temperature " + temperature + " dengan fakta Tidak adalah " + tmp);
            faktaTidak.add(tmp);
        } else {
            float temp = 1;
            //System.out.println("Probabilitas Temperature " + temperature + " dengan fakta Tidak adalah " + temp);
            faktaTidak.add(temp);
        }
        if (!angin.isEmpty()) {
            float x3 = 0;

            for (Data data : dataSet) {
                if (data.getAngin().equalsIgnoreCase(angin) && data.getOlahraga().equalsIgnoreCase("Tidak")) {
                    x3 += 1;
                }
            }
            float tmp = (x3 / total) / (T / total);
            System.out.println("Probabilitas Temperature " + angin + " dengan fakta Tidak adalah " + tmp);
            faktaTidak.add(tmp);
        } else {
            float temp = 1;
            //System.out.println("Probabilitas Temperature " + angin + " dengan fakta Tidak adalah " + temp);
            faktaTidak.add(temp);
        }
    }

    private static void perhitunganNB() {
        float ya = 0, tidak = 0;
        ya = faktaYa.get(0) * faktaYa.get(1) * faktaYa.get(2) * (Y / dataSet.size());
        tidak = faktaTidak.get(0) * faktaTidak.get(1) * faktaTidak.get(2) * (T / dataSet.size());
        if (cuaca.isEmpty() && temperature.isEmpty() && angin.isEmpty()) {
            ya = 0;
            tidak = 0;
        }
        System.out.println("\nHasil Perhitungan Naive Bayes");
        System.out.println("Probalitas Cuaca : "+cuaca+", Temperature : "+temperature+", Kecepatan Angin :"+angin+" dengan fakta YA adalah "+ya 
                + "\n" + 
                "Probalitas Cuaca : "+cuaca+", Temperature : "+temperature+", Kecepatan Angin :"+angin+" dengan fakta Tidak adalah "+tidak);
        //Hasil probabilitas
        if (ya > tidak) {
            System.out.println("\nOlahraga ? Ya");
        } else if (ya < tidak) {
            System.out.println("\nOlahraga ? Tidak");
        } else {
            System.out.println("\nKemungkinan Ya Kemungkinan Tidak, karena nilai probabilitas keduanya sama");
        }
    }
}
