package src.main;




public class Main  {

    public static void main(String args[]){

        Juego juego = new Juego();


        Controller controlador = new Controller();

        while(!juego.terminado()){

            int opcion = Controller.menu();

            juego.jugarTurno(opcion);
        
            Controller.mostrarJuego(juego);


        }



    }





}