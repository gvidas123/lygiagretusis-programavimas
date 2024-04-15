import java.util.Random;
public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Programa pradeda darba");
        TestThread.pradeti();
        System.out.println("Programa baigia darba.");
    }
}

/*
   Naujai sukurta klase.
   Gijos klasė, turi būti išvesta iš Thread
*/
class TestThread extends Thread
{
    // Gijos objekto specifiniai duomenys
    chess_matches bendras;

    // Konstruktorius, skirtas perduoti duomenis gijos objektui
    public TestThread(chess_matches bendras)
    {
        this.bendras = bendras;
    }

    // Metodas, vykdomas paleidus giją
    // Thread.run()
    public void run()
    {
        System.out.println("Gija " + this + " paleista");

        // Ciklas (didesnis iteracijų skaičius padidina duomenų skaitymo
        // atnaujinimo konflikto galimybę
        for (int i = 0; i < 5; i++)
        {
            // Sinchronizuoto bloko pradžia
            // Bloko viduje galima saugiai skaityti/modifikuoti
            // Objekto bendras laukus
            // *** Užkomentavus turėtų atsirasti duomenų atnaujinimo konfliktai ***
            synchronized(bendras)
            {
                // Kontrolinis spausdinimas, kad įsitikinti vienalaikiu gijų veikimu
                System.out.println("Gija " + this + " pries macha");
                bendras.machas();
                System.out.println("dabartinis rezultatas = " + bendras.rezultatas);
                // Kviečiame metodą, kuris modifikuoja objekto lauko reikšmę
                // *** Konkrečiam taikymui metodo kvietinys/iai turi būti pakeistas/i***
                bendras.padidinti();

                // Kontrolinis spausdinimas, kad įsitikinti vienalaikiu gijų veikimu
                System.out.println("Gija " + this + " atliko macha teisingai");
            }
        }
        System.out.println("Gija " + this + " baigia darbą");
    }

    // Metodas paleidžiantis gijas darbui ir išvedantis rezultatą
    public static void pradeti()
    {
        // Sukuriame objektą, kurį bendrai naudos kelios gijos
        chess_matches bendras = new chess_matches();
        bendras.suzaisti_zaidimai = 0;
        bendras.rezultatas = 0;
        try
        {
            // Sukuriame ir startuojame pirmąją giją
            // perduodami kaip parametrą objektą "bendras"
            Thread t1 = new TestThread(bendras);
            t1.start();

            // Sukuriame ir startuojame pirmąją giją
            Thread t2 = new TestThread(bendras);
            t2.start();

            // Laukiame, kol abi gijos baigs darbą
            t1.join();  t2.join();

            // Išvedame galutinį rezultatą
            System.out.println("Rezultatas suzaisti_zaidimai = " + bendras.suzaisti_zaidimai + ". Turi buti 10");
            System.out.println("Rezultatas macho = " + bendras.rezultatas + " turėtu būti neigiamas");
        }
        catch (InterruptedException exc)
        {
            System.out.println("Ivyko klaida "+exc);
        }
    }
}

class chess_matches
{
    // Laukas, kurį skaitys modifikuos kelios gijos
    int suzaisti_zaidimai;
    int rezultatas;
    // Metodas modifikuojantis objekto turinį
    public void padidinti()
    {
        int nn = suzaisti_zaidimai; // Nuskaityti
        nn++;       // Apskaiciuoti
        suzaisti_zaidimai = nn;     // Isiminti objekto lauke
    }
    public void machas()
    {
        Random rand = new Random();
        int rand1 = rand.nextInt(50);
        int n = rezultatas;
        if (rand1 < 30) {
            n -=1;
        }
        else {
            n++;
        }
        rezultatas = n;

    }
}