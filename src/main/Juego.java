package src.main;




public class Juego {



    Entrenador entrenador1;
    Entrenador entrenador2;
    Entrenador entrenadorActual = null;
    administradorTurno administrador = new administradorTurno();
    Boolean terminado;

    public Juego(){

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

    public void rendirse(){

        this.terminado = true;
    }


    public boolean terminado(){


        return this.terminado;

    }




    
}
