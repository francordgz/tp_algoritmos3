package src.main;




public class Main  {

    public static void Main(String args[]){

        Juego juego = new Juego();

        Controller controlador = new Controller(juego);


        while(!juego.terminado()){

           controlador.Menu();

        }

    }





}