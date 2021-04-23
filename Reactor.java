import java.util.Scanner;

public class Reactor
{

    int	level=30;
    int	max_level=100;
    int target = 80;
    int	min_level=0;
    //Scanner in = new Scanner(System.in);



    boolean	stopped=false;

    public int gr(int min) {
        return (int) ((Math.random() * (10 - min)) + min);
    }
    public int ran() {
        return (int) (Math.random() * 9);
    }
    public int cor() {
        return (int) (Math.random() * 5);
    }
    public int kl() {
        return (int) (Math.random() * 100);
    }
    /* public int[] k;
     for (int i = 0; i <10; i++)
         k[i]=kl()*3;
     /*public void kr(){
    for (int i = 0; i <10; i++){
         k[i]=kl()*3;
     }
     }*/
    //private ListSklad[] ls;
    public ListSklad[] ls = new ListSklad[10];
    class ListSklad {
        private int name;
        private int numbers;
        public ListSklad (int name, int numbers) {
            this.name = name;
            this.numbers = numbers;
        }

        public int getName() {
            return name;
        }

        public void setName(int name) {
            this.name = name;
        }

        public int getNumbers() {
            return numbers;
        }

        public void setNumbers(int numbers) {
            this.numbers = numbers;
        }
    }



    //ListSklad[] ls = new ListSklad[11];
    public Reactor()
    {  ListSklad[] ls = new ListSklad[11];
        int i;
        int j;
        for (i=0; i<10; i++){

            ls[i]= new  ListSklad(i, kl()*3 );

            System.out.println("Товар "+ ls[i].name + " имеется в количестве " + ls[i].numbers);
            //ls[i].name=i;
            // ls[i].numbers=kl()*3;
        }
        Thread post=new Thread() {
            public void run() {
                int s = 0;
                while (!stopped) {
                    for (int i = 0; i < 10; i++) {
                        s = s + ls[i].getNumbers();

                    }

                        try {
                            if (s <= 10000) {
                                sleep(1000);
                                int t = ran();
                                int m = gr(t);
                                int g;
                                int n;


                                synchronized (ls) {
                                    for (int i = t; i < m; i++) {
                                        g = ran();
                                        n = ls[g].getNumbers();
                                        // new ls[g](g, ls[g].numbers+kl());
                                        ls[g].setNumbers(n + kl());
                                        //k(g)=k(g)+kl();
                                    }
                                }
                            } else
                                System.out.println ("Склад заполнен");
                                sleep(10000);
                        }
                        catch (Exception pe) {
                            pe.printStackTrace();
//            System.out.println("Error post: " + pe);

                        }

                }
                }

            };

        Thread potr=new Thread()
        {	public void run() {
            while (!stopped) {
            try {
                sleep(1000);
                int t = ran();
                int m = gr(t);
                int g;
                int c;
                int n;
                synchronized (ls){
                    for (int i = t; i<m; i++){
                        g=ran();
                        c=kl();
                        //new ls[g](g, ls[g].numbers-c);
                        n= ls[g].getNumbers();
                        n=n-c;
                        if ( n<0){
                            ls[g].setNumbers(0);
                            System.out.println("Товар "+ ls[g].getName() + " закончился");
                       /* k[g]=k[g]-c;
                        if (k[g]<0){
                            k[g]=0;
                        System.out.println("Товар "+ k(g) + " закончился");*/
                        }
                    }

                }


            }
            catch(Exception pe)
            {
//            System.out.println("Error potr: " + pe);
                pe.printStackTrace();
            }
        }


            }

        };

        Thread monitor=new Thread(){
            public void run() {
                while (!stopped) {
                    try {
                        sleep(3000);
                        for (int i = 0; i < 10; i++) {
                            System.out.println("Товар " + ls[i].getName() + " имеется в количестве " + ls[i].getNumbers());
                            //System.out.println("Товар "+ i + " имеется в количестве "+  k[i]);

                        }
                    } catch (Exception pe) {
                        System.out.println("Error heigh: " + pe);

                    }
                }
            }
        };

        for (i=0; i<cor(); i++){
            new Thread (post).start();
        }
        for (i=0; i<cor(); i++){
            new Thread (potr).start();
        }
        monitor.start();
        // control.setPriority(Thread.NORM_PRIORITY);	//NORM_PRIORITY MIN_PRIORITY
        // low.setPriority(Thread.NORM_PRIORITY);
        //heigh.setPriority(Thread.NORM_PRIORITY);

      /*  control.start();
        heigh.start();
        low.start();
        monitor.start();
        usin.start();*/
        // heigh.start();

    }
    /**
     *
     */
    public static void main(String args[])
    {	System.out.println("Склад запущен");
        //   System.out.println(ran());
        // System.out.println(kl());

        new Reactor();

    }

}
