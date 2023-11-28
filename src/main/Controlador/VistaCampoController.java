package src.main.Controlador;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.util.Duration;
import src.main.Controlador.Eventos.EligeHabilidadEvento;
import src.main.Controlador.Eventos.RendirseEvento;
import src.main.Controlador.Eventos.VerMochilaEvento;
import src.main.Controlador.Eventos.VerPokemonesEvento;
import src.main.Modelo.Constant;
import src.main.Modelo.Entrenador;
import src.main.Modelo.Pokemon;

import java.io.InputStream;

public class VistaCampoController {
    public ImageView background;
    public Label estadosRival;
    public Label estadosActual;
    public Label cantidadPokemonesActual;
    public Label cantidadPokemonesRival;
    private Scene escena;
    @FXML
    public Label rivalNombre;
    @FXML
    public ProgressBar rivalVidaPorcentaje;
    @FXML
    public Label jugadorNombre;
    @FXML
    public ProgressBar jugadorVidaPorcentaje;
    @FXML
    public Label jugadorVidaNumero;
    @FXML
    public Text dialogo;
    @FXML
    public Label rivalNivel;
    @FXML
    private Label jugadorNivel;
    @FXML
    private ImageView rivalImagen;
    @FXML
    private ImageView jugadorImagen;
    @FXML
    private Button botonRendirse;

    @FXML
    private Button botonPokemones;

    @FXML
    private Button botonMochila;

    @FXML
    private MenuItem botonHabilidad1;

    @FXML
    private MenuItem botonHabilidad2;

    @FXML
    private MenuItem botonHabilidad3;

    @FXML
    private MenuItem botonHabilidad4;


    @FXML
    private MenuItem botonHabilidad5;

    @FXML
    private MenuItem botonHabilidad6;

    public void setEscena(Scene escena) {
        this.escena = escena;
    }

    public Scene getEscena() {
        return escena;
    }




    @FXML
    public void initialize() {
        botonRendirse.setOnAction(e -> botonRendirse.fireEvent(new RendirseEvento()));
        botonPokemones.setOnAction(e -> botonPokemones.fireEvent(new VerPokemonesEvento()));
        botonMochila.setOnAction(e -> botonMochila.fireEvent(new VerMochilaEvento()));

        MenuItem[] habilidadButtons = {
                botonHabilidad1, botonHabilidad2, botonHabilidad3,
                botonHabilidad4, botonHabilidad5, botonHabilidad6
        };
        for (int i = 0; i < habilidadButtons.length; i++) {
            int opcion = i;
            habilidadButtons[i].setOnAction(e -> habilidad(opcion));
        }
    }


    private void habilidad(int habilidad) {
        botonRendirse.fireEvent(new EligeHabilidadEvento(habilidad));
    }

    public void setDialogo(String mensaje) {
        dialogo.setText(mensaje);
    }

    public void setDatos(Entrenador actual, Entrenador rival) {
        setDatosPokemonActual(actual.obtenerPokemonActual());
        setDatosPokemonRival(rival.obtenerPokemonActual());

        this.cantidadPokemonesActual.setText("◓".repeat(actual.obtenerCantidadDePokemones()));
        this.cantidadPokemonesRival.setText("◓".repeat(rival.obtenerCantidadDePokemones()));
    }

    private void setDatosPokemonActual(Pokemon pokemon) {
        String nombre = pokemon.obtenerNombre();
        this.jugadorImagen.setImage(getImagenPokemon(nombre, true));
        this.jugadorNombre.setText(nombre);

        if (pokemon.estaNormal()) this.estadosActual.setText("");
        else this.estadosActual.setText(pokemon.obtenerEstados() + "");

        this.dialogo.setText("Que debe hacer " + nombre + "?");

        this.jugadorNivel.setText("Nv " + pokemon.obtenerNivel());
        setJugadorVida(pokemon.obtenerVidaActual(), pokemon.obtenerVidaMaxima());

        MenuItem[] habilidadButtons = {
                botonHabilidad1, botonHabilidad2, botonHabilidad3,
                botonHabilidad4, botonHabilidad5, botonHabilidad6
        };
        for (int i = 0; i < habilidadButtons.length; i++)
            habilidadButtons[i].setText(pokemon.habilidades(i).obtenerNombre());
    }

    private void setDatosPokemonRival(Pokemon pokemon) {
        String nombre = pokemon.obtenerNombre();
        this.rivalImagen.setImage(getImagenPokemon(nombre, false));
        this.rivalNombre.setText(nombre);
        this.rivalNivel.setText("Nv " + pokemon.obtenerNivel());

        if (pokemon.estaNormal()) this.estadosRival.setText("");
        else this.estadosRival.setText(pokemon.obtenerEstados() + "");

        setRivalVida(pokemon.obtenerVidaActual(), pokemon.obtenerVidaMaxima());
    }


    public void setClima(String clima) {
        this.background.setImage(getImagenClima(clima));
    }

    private void setJugadorVida(int vidaActual, int vidaMaxima) {
        this.jugadorVidaNumero.setText(vidaActual + "/" + vidaMaxima);

        double porcentaje = (double) vidaActual/vidaMaxima;
        this.jugadorVidaPorcentaje.setProgress(porcentaje);

        String style = String.format("-fx-accent: %s;", getColor(porcentaje));
        jugadorVidaPorcentaje.setStyle(style);
    }

    private void setRivalVida(int vidaActual, int vidaMaxima) {
        double porcentaje = (double) vidaActual/vidaMaxima;
        this.rivalVidaPorcentaje.setProgress(porcentaje);

        String style = String.format("-fx-accent: %s;", getColor(porcentaje));
        rivalVidaPorcentaje.setStyle(style);
    }

    public static String getColor(double percentage) {
        if (percentage < 0.1) return "#FF0000";  // Red
        if (percentage < 0.25) return "#FF4500"; // Orange-Red
        if (percentage < 0.5) return "#FFFF66";  // Yellow
        if (percentage < 0.75) return "#ADFF2F"; // Green-Yellow
        return "#00FF00"; // Green
    }

    private Image getImagenPokemon(String nombre, boolean dorso) {
        String path = "/Imagenes/pokemon/";
        if (dorso) path = path + "dorso";
        else path = path + "frente";
        path = path + "_gif/" + nombre + ".gif";
        InputStream imagen = getClass().getResourceAsStream(path);
        assert imagen != null;
        return new Image(imagen);
    }

    private Image getImagenClima(String nombre) {
        String path = "/Imagenes/bgs/climas/" + nombre + ".png";
        InputStream imagen = getClass().getResourceAsStream(path);
        assert imagen != null;
        return new Image(imagen);
    }

    public void titilarRival(int ciclos) {
        ImageView imagen = this.rivalImagen;

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(imagen.opacityProperty(), 1.0)),
                new KeyFrame(Duration.seconds(0.2), new KeyValue(imagen.opacityProperty(), 0.0)),
                new KeyFrame(Duration.seconds(0.5), new KeyValue(imagen.opacityProperty(), 1.0))
        );

        timeline.setCycleCount(ciclos);
        timeline.play();
    }

    public void mostrarEfectividad(double efectividad) {
        this.setDialogo("El ataque ha sido" + getEfectividad(efectividad) + " efectivo!");
        if(efectividad != Constant.NULA) titilarRival((int) (efectividad * 2));
    }
    private String getEfectividad(double efectividad) {
        if (efectividad == Constant.NULA) return " CERO";
        if (efectividad == Constant.MEDIA) return " POCO";
        if (efectividad == Constant.SIMPLE) return "";
        if (efectividad == Constant.DOBLE) return " MUY";
        else throw new RuntimeException("Error Efectividades");
    }

}




