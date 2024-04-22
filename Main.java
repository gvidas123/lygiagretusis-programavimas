import java.util.Random;
public class Main {
    public static void main(String[] args) {
        System.out.println("Programa pradeda darba");
        chess_thread.pradeti();
        System.out.println("Programa baigia darba.");
    }
}

class chess_thread extends Thread
{

    chess_matches bendras;


    public chess_thread(chess_matches bendras)
    {
        this.bendras = bendras;
    }

    public void run()
    {
        System.out.println("Gija " + this + " paleista");

        for (int i = 0; i < 100; i++)
        {
            synchronized(bendras)
            {

                System.out.println("Gija " + this + " pries macha");
                bendras.machas();
                System.out.println("dabartinis rezultatas = " + bendras.rezultatas);
                bendras.padidinti();
                System.out.println("Gija " + this + " atliko macha teisingai");
            }
        }
        System.out.println("Gija " + this + " baigia darbą");
    }
    public static void pradeti()
    {
        chess_matches bendras = new chess_matches();
        bendras.suzaisti_zaidimai = 0;
        bendras.rezultatas = 0;
        try
        {

            Thread t1 = new chess_thread(bendras);
            t1.start();

            Thread t2 = new chess_thread(bendras);
            t2.start();

            t1.join();  t2.join();

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
    int suzaisti_zaidimai;
    int rezultatas;
    public void padidinti()
    {
        int nn = suzaisti_zaidimai;
        nn++;
        suzaisti_zaidimai = nn;
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
