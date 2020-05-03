import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.hibernate.instance.IHazelcastInstanceFactory;

import java.net.UnknownHostException;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Program {
    static HazelcastInstance hazelcastInstance;
    static Scanner scanner;
    static Random r;
    static boolean koniec = false;

    static void completeData() {
        Map<Long, LigaSportowa> ligaSportowaMap = hazelcastInstance.getMap("ligaSportowa");
        LigaSportowa ligaSportowa = new LigaSportowa("Piłka nożna mężczyzn", 8, 5600, "Ekstraklasa SA");

        LigaSportowa ligaSportowa2 = new LigaSportowa("Piłka nożna kobiet", 3, 60, "Polski Związek Piłki Nożnej");

        LigaSportowa ligaSportowa3 = new LigaSportowa("Sport żużlowy", 3, 21, "Polski Związek Motorowy");

        LigaSportowa ligaSportowa4 = new LigaSportowa("Futbol amerykański", 4, 38, "Polski Związek Futbolu Amerykańskiego");

        LigaSportowa ligaSportowa5 = new LigaSportowa("Futsal", 3, 60, "Wydział Futsalu PZPN");

        LigaSportowa ligaSportowa6 = new LigaSportowa("Piłka wodna", 1, 6, "Polski Związek Pływacki");

        ligaSportowaMap.put(r.nextLong(), ligaSportowa);
        ligaSportowaMap.put(r.nextLong(), ligaSportowa2);
        ligaSportowaMap.put(r.nextLong(), ligaSportowa3);
        ligaSportowaMap.put(r.nextLong(), ligaSportowa4);
        ligaSportowaMap.put(r.nextLong(), ligaSportowa5);
        ligaSportowaMap.put(r.nextLong(), ligaSportowa6);
    }

    static void addNew() {
        Map<Long, LigaSportowa> ligaSportowaMap = hazelcastInstance.getMap("ligaSportowa");
        LigaSportowa ligaSportowa = new LigaSportowa();
        System.out.println("Podaj nazwe dyscypliny: ");
        ligaSportowa.setNameDyscypline(scanner.nextLine());

        System.out.println("Podaj liczbę klas rogrywkowych: ");
        ligaSportowa.setNumberClassDivisions(Integer.parseInt(scanner.nextLine()));

        System.out.println("Podaj liczbę drużyn: ");
        ligaSportowa.setNumberTema(Integer.parseInt(scanner.nextLine()));

        System.out.println("Podaj nazwę glównego organu prowadzącego: ");
        ligaSportowa.setNameLeadingLeadBody(scanner.nextLine());

        Long key = r.nextLong();
        ligaSportowaMap.put(key, ligaSportowa);
    }

    static void getAll() {
        Map<Long, LigaSportowa> ligaSportowaMap = hazelcastInstance.getMap("ligaSportowa");
        System.out.println("Wszystkie ligi sportowe: ");
        for (Map.Entry<Long, LigaSportowa> e : ligaSportowaMap.entrySet()) {
            System.out.println(e.getKey() + " => " + e.getValue().toString());
        }
    }

    static void getOneByNameDyscypline() {
        Map<Long, LigaSportowa> ligaSportowaMap = hazelcastInstance.getMap("ligaSportowa");
        System.out.println("Podaj nawe ligi sportowej: ");
        String nazwa = scanner.nextLine();

        for (Map.Entry<Long, LigaSportowa> e : ligaSportowaMap.entrySet()) {
            if (e.getValue().getNameDyscypline().equals(nazwa) == true) {
                System.out.println(e.getKey() + " => " + e.getValue().toString());
            }
        }
    }

    static void update() {
        Map<Long, LigaSportowa> ligaSportowaMap = hazelcastInstance.getMap("ligaSportowa");
        System.out.println("Podaj klucz: ");
        Long key = Long.parseLong(scanner.nextLine());
        for (Map.Entry<Long, LigaSportowa> e : ligaSportowaMap.entrySet()) {
            if (key == e.getKey().longValue()) {
                System.out.println(e.getKey() + " => " + e.getValue().toString());

                System.out.println("Zmien nazwe dyscypliny: ");
                System.out.println("Wpisz Q aby pominąć!!!");
                String nazwaD = scanner.nextLine();
                if (nazwaD.equals("Q") == false) {
                    e.getValue().setNameDyscypline(nazwaD);
                }

                System.out.println("Zmień liczbe klas rozgrywkowych: ");
                System.out.println("Wpisz Q aby pominąć!!!");
                String liczbaRor = scanner.nextLine();
                if (liczbaRor.equals("Q") == false) {
                    e.getValue().setNumberClassDivisions(Integer.parseInt(liczbaRor));
                }

                System.out.println("Zmień liczbę drużyn: ");
                System.out.println("Wpisz Q aby pominąć!!!");
                String liczbaDr = scanner.nextLine();
                if (liczbaDr.equals("Q") == false) {
                    e.getValue().setNumberTema(Integer.parseInt(liczbaRor));
                }

                System.out.println("Zmień nazwę glównego organu prowadzącego: ");
                System.out.println("Wpisz Q aby pominąć!!!");
                String nazwaOrg = scanner.nextLine();
                if (nazwaOrg.equals("Q") == false) {
                    e.getValue().setNameLeadingLeadBody(nazwaOrg);
                }
            }
        }
    }

    static void remove() {
        Map<Long, LigaSportowa> ligaSportowaMap = hazelcastInstance.getMap("ligaSportowa");
        System.out.println("Podaj klucz: ");
        Long key = Long.parseLong(scanner.nextLine());

        ligaSportowaMap.remove(key);
    }

    static void getClause() {
        Map<Long, LigaSportowa> ligaSportowaMap = hazelcastInstance.getMap("ligaSportowa");
        System.out.println("1 - większe od wartości liczby drużyn");
        System.out.println("2 - mniejsze od wartości liczby drużyn");
        String choose = scanner.nextLine();

        System.out.println("Podaj liczbe do warunku:");
        int liczba = Integer.parseInt(scanner.nextLine());

        for (Map.Entry<Long, LigaSportowa> e : ligaSportowaMap.entrySet()) {
            if (choose.equals("1") == true) {
                if (e.getValue().getNumberTema() > liczba) {
                    System.out.println(e.getKey() + " => " + e.getValue().toString());
                }
            } else {
                if (e.getValue().getNumberTema() < liczba) {
                    System.out.println(e.getKey() + " => " + e.getValue().toString());
                }
            }
        }
    }

    static private void showMenu() {
        System.out.println("\n**************  MENU:  ***************");
        System.out.println("1 - Dodaj nowy element ");
        System.out.println("2 - Wyświetl wszystkie elementy ");
        System.out.println("3 - Wyświetl elemnet po jego nazwie ");
        System.out.println("4 - Zaaktualizuj element");
        System.out.println("5 - Usuń element");
        System.out.println("6 - Zapytanie warunkowe");
        System.out.println("0 - WYJŚCIE\n");
    }

    static private int getWyborMenu() {
        int choice = -1;
        do {
            System.out.println("Podaj wybór:");
            choice = Integer.parseInt(scanner.nextLine());
            if (choice < 0 || choice > 6) {
                System.out.println("Brak takiej opcji!");
            }
        } while (choice < 0 || choice > 6);
        return choice;
    }

    static private void akcja(int wybor){
        switch(wybor){
            case 1:
                addNew();
                break;
            case 2:
                getAll();
                break;
            case 3:
                getOneByNameDyscypline();
                break;
            case 4:
                update();
                break;
            case 5:
                remove();
                break;
            case 6:
                getClause();
                break;
            case 0:
                koniec = true;
                break;
            default:
                System.out.println("BŁĄD");
        }
    }

    static void menu() {
        while (!koniec) {
            showMenu();
            int wybor = getWyborMenu();
            akcja(wybor);
        }
    }

    public static void main(String[] args) throws UnknownHostException {
        hazelcastInstance = Hazelcast.newHazelcastInstance(HConfig.getConfig());
        scanner = new Scanner(System.in);
        r = new Random();
        completeData();
        menu();
        scanner.nextInt();
    }
}
