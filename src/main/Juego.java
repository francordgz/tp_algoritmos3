package src.main;




public class Juego {



    Entrenador entrenador1 = new Entrenador();
    Entrenador entrenador2 = new Entrenador();
    Entrenador entrenadorActual = null;
    administradorTurno administrador = new administradorTurno();
    Boolean terminado;

    public juego(){

        this.crearPokemons();
        this.crearHabilidades();
        this.crearItems();
        this.terminado = false;
    }


    public void crearPokemons(){


    }

    public void crearHabilidades(){

    }

    public void crearItems(){



    }

    public void jugarTurno(int opcion){

        ///Rendirse
        ///Ver campo
        ///Cambiar Pokemon 
        ///Item -> 4
        ///Usar Habilidad -> 4
        

    }

    public boolean terminado(){


        return this.terminado;

    }





    
}
