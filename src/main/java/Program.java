import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.hibernate.instance.IHazelcastInstanceFactory;

import java.net.UnknownHostException;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Program {
    static  HazelcastInstance hazelcastInstance;
    static Scanner scanner;
    static Random r;

    static void addNew(){
        Map<Long,LigaSportowa> ligaSportowaMap = hazelcastInstance.getMap("ligaSportowa");
        LigaSportowa ligaSportowa = new LigaSportowa();
        System.out.println("Podaj nazwe dyscypliny: ");
        ligaSportowa.setNameDyscypline(scanner.nextLine());

        System.out.println("Podaj liczbę klas rogrywkowych: ");
        ligaSportowa.setNumberClassDivisions(scanner.nextInt());

        System.out.println("Podaj liczbę drużyn: ");
        ligaSportowa.setNumberTema(scanner.nextInt());

        System.out.println("Podaj nazwę glównego organu prowadzącego: ");
        ligaSportowa.setNameLeadingLeadBody(scanner.nextLine());

        Long key = r.nextLong();
        ligaSportowaMap.put(key,ligaSportowa);
    }

    static void getAll(){
        Map<Long,LigaSportowa> ligaSportowaMap = hazelcastInstance.getMap("ligaSportowa");
        System.out.println("Wszystkie ligi sportowe: ");
        for(Map.Entry<Long,LigaSportowa> e: ligaSportowaMap.entrySet()){
            System.out.println(e.getKey() + " => " + e.getValue().toString());
        }
    }

    static void getOneByNameDyscypline(){
        Map<Long,LigaSportowa> ligaSportowaMap = hazelcastInstance.getMap("ligaSportowa");
        System.out.println("Podaj nawe ligi sportowej: ");
        String nazwa = scanner.nextLine();

        for (Map.Entry<Long,LigaSportowa> e: ligaSportowaMap.entrySet()){
            if (e.getValue().getNameDyscypline().equals(nazwa)==true){
                System.out.println(e.getKey() + " => " + e.getValue().toString());
            }
        }
    }

    static  void update(){
        Map<Long,LigaSportowa> ligaSportowaMap = hazelcastInstance.getMap("ligaSportowa");
        System.out.println("Podaj klucz: ");
        Long key = scanner.nextLong();
        for (Map.Entry<Long,LigaSportowa> e: ligaSportowaMap.entrySet()){
            if (e.getKey()==key){
                System.out.println(e.getKey() + " => " + e.getValue().toString());

                System.out.println("Zmien nazwe dyscypliny: ");
                System.out.println("Wpisz Q aby pominąć!!!");
                String nazwaD = scanner.nextLine();
                if(nazwaD.equals("Q")==false){
                    e.getValue().setNameDyscypline(nazwaD);
                }

                System.out.println("Zmień liczbe klas rozgrywkowych: ");
                System.out.println("Wpisz Q aby pominąć!!!");
                String liczbaRor = scanner.nextLine();
                if(liczbaRor.equals("Q")==false){
                    e.getValue().setNumberClassDivisions(Integer.parseInt(liczbaRor));
                }

                System.out.println("Zmień liczbę drużyn: ");
                System.out.println("Wpisz Q aby pominąć!!!");
                String liczbaDr = scanner.nextLine();
                if(liczbaDr.equals("Q")==false){
                    e.getValue().setNumberTema(Integer.parseInt(liczbaRor));
                }

                System.out.println("Zmień nazwę glównego organu prowadzącego: ");
                System.out.println("Wpisz Q aby pominąć!!!");
            }
        }
    }


    public static void main(String[] args) throws UnknownHostException{
        hazelcastInstance = Hazelcast.newHazelcastInstance(HConfig.getConfig());
        scanner = new Scanner(System.in);
        r = new Random();

        getAll();

        addNew();

        getAll();

        scanner.nextInt();
    }
}
