package volteadoja.vista;

import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.image.Image;

import volteadoja.modelo.Heroe;
import volteadoja.modelo.Enemigo;

import javafx.application.Application;

import volteadoja.logica.Juego;
import volteadoja.modelo.Carta;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;


import javafx.scene.layout.BorderPane;
import javafx.geometry.Insets;


import javafx.scene.layout.GridPane;

import volteadoja.logica.Resultado;
import volteadoja.logica.EstadoPartida;

import javafx.animation.PauseTransition;
import javafx.util.Duration;

import javafx.scene.Parent;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

/**
 * Gestiona la interfaz gráfica del juego VolteaRoja mediante JavaFX.
 *
 * Administra las escenas del menú, los ajustes, el combate y el resultado
 * final. También coordina los recursos de audio, las imágenes, los estilos
 * y la actualización visual del tablero y de los personajes.
 *
 * @see Juego
 * @see Application
 */
public class Ventana extends Application {

    /**
     * Ventana principal utilizada para cambiar las escenas de la aplicación.
     */
    private Stage stage;
    /**
     * Efecto sonoro reproducido al seleccionar una carta válida.
     */
    private AudioClip sonidoVoltear;
    /**
     * Música de fondo del menú principal.
     */
    private AudioClip sonidoMenu;
    /**
     * Música de fondo durante la partida.
     */
    private AudioClip sonidoJuego;

    /**
     * Inicializa la aplicación gráfica de JavaFX.
     *
     * Configura el icono, carga los recursos de audio, establece el título
     * y muestra la escena del menú principal.
     *
     * @param stage ventana principal proporcionada por JavaFX.
     */
    @Override
    public void start(Stage stage) {

        this.stage = stage;
        stage.getIcons().add(
                new Image(getClass().getResource("img/grgl_01.PNG").toExternalForm()));

        sonidoVoltear = new AudioClip(
                getClass().getResource("sonidos/Flip Card_2.wav").toExternalForm());
        sonidoMenu = new AudioClip(
                getClass().getResource("sonidos/Wasteland Ruins.mp3").toExternalForm());
        sonidoJuego = new AudioClip(
                getClass().getResource("sonidos/Deceitful Castle.mp3").toExternalForm());

        stage.setTitle("Volteadoja");
        stage.setScene(crearMenu());
        stage.show();
    }

    /**
     * Crea la escena del menú principal.
     *
     * Reproduce la música del menú y configura los botones para comenzar
     * una partida o acceder a la pantalla de ajustes.
     *
     * @return escena del menú principal.
     */
    private Scene crearMenu() {
        sonidoMenu.setCycleCount(AudioClip.INDEFINITE);
        sonidoMenu.setVolume(0.1);
        sonidoMenu.play();


        ImageView icono;
        Label titulo = new Label("Volteadoja");
        titulo.getStyleClass().add("titulo");

        Button btnStart = new Button("Comenzar");
        btnStart.getStyleClass().add("btn");
        icono = new ImageView(getClass().getResource("img/grgl_01.PNG").toExternalForm());
        icono.setFitWidth(64);
        icono.setPreserveRatio(true);
        btnStart.setGraphic(icono);
        btnStart.setOnAction(e -> {
            sonidoMenu.stop();
            stage.setScene(crearJuego());
        });

        Button btnAjustes = new Button("Ajustes");
        btnAjustes.getStyleClass().add("btn");
        icono = new ImageView(getClass().getResource("img/CasualUI_6_5.png").toExternalForm());
        icono.setFitWidth(48);
        icono.setPreserveRatio(true);
        btnAjustes.setGraphic(icono);
        btnAjustes.setOnAction(e -> stage.setScene(crearMenuAjustes()));

        VBox menu = new VBox(20, titulo, btnStart, btnAjustes);

        menu.setAlignment(Pos.CENTER);

        return nuevaEscena(menu);
    }

    /**
     * Crea la escena del menú de ajustes.
     *
     * Actualmente contiene un botón para regresar al menú principal.
     *
     * @return escena del menú de ajustes.
     */
    private Scene crearMenuAjustes() {

        ImageView icono;
        Button btnRegresar = new Button("Regresar");
        btnRegresar.getStyleClass().add("btn");
        icono = new ImageView(getClass().getResource("img/CasualUI_6_6.png").toExternalForm());
        icono.setFitWidth(48);
        icono.setPreserveRatio(true);
        btnRegresar.setGraphic(icono);
        btnRegresar.setOnAction(e -> {
            sonidoMenu.stop();
            stage.setScene(crearMenu());
            });
        VBox ajustes = new VBox(20, btnRegresar);
        ajustes.setAlignment(Pos.CENTER);

        return nuevaEscena(ajustes);
    }

    /**
     * Crea la escena principal de una nueva partida.
     *
     * Inicia la música del juego, crea una instancia de Juego y construye
     * el tablero gráfico de 5 por 5 botones junto con las estadísticas
     * del héroe y del enemigo.
     *
     * Cada botón comunica su selección a la lógica del juego. Cuando se
     * completa un turno, se espera un segundo antes de resolverlo y
     * actualizar el tablero, salvo si la partida ya ha terminado.
    *
     * @return escena gráfica de la partida.
     */
    private Scene crearJuego() {
        sonidoJuego.setVolume(0.05);
        sonidoJuego.setCycleCount(AudioClip.INDEFINITE);
        sonidoJuego.play();

        Juego partida = new Juego();

        Label lblEnemigo = new Label();
        Label lblHeroe = new Label();
        lblEnemigo.getStyleClass().add("personaje");
        lblHeroe.getStyleClass().add("personaje");

        actualizarCampo(partida, lblHeroe, lblEnemigo);


        BorderPane campo = new BorderPane();
        campo.setLeft(lblEnemigo);
        campo.setRight(lblHeroe);
        campo.setPadding(new Insets(20, 60, 20, 60));

        Button btnSalir = new Button("Menu");
        btnSalir.getStyleClass().add("btn");

        ImageView icono = new ImageView(getClass().getResource("img/CasualUI_6_8.png").toExternalForm());
        icono.setFitWidth(48);
        icono.setPreserveRatio(true);
        btnSalir.setGraphic(icono);

        btnSalir.setOnAction(e -> {
            sonidoJuego.stop();
            stage.setScene(crearMenu());
        });

    GridPane tablero = new GridPane();
    tablero.getStyleClass().add("tablero");
    tablero.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
    tablero.setHgap(6);
    tablero.setVgap(6);
    tablero.setAlignment(Pos.CENTER);
    tablero.setPadding(new Insets(6));

    /*
     * Construye una matriz de botones que corresponde a las posiciones del tablero lógico.
     */
    Button[][] botones = new Button[5][5];
    for (int fila = 0; fila < 5; fila++) {
        for (int columna = 0; columna < 5; columna++) {
            final int f = fila;
            final int c = columna;


            Button btnCarta = new Button("?");
            btnCarta.setPrefSize(54, 72);
            btnCarta.getStyleClass().add("carta");
            btnCarta.setOnAction(e -> {
                /*
                 * Comunica la selección a la lógica de la partida y obtiene su resultado.
                 */
                Resultado r = partida.seleccionar(f, c);
                if (r != Resultado.INVALIDA && r != Resultado.ESPERANDO) {
                    sonidoVoltear.play();
                }
                actualizarTablero(partida, botones);
                actualizarCampo(partida, lblHeroe, lblEnemigo);

                /*
                 * Comprueba si el combate terminó antes de programar la resolución del turno.
                 */
                EstadoPartida estado = partida.getEstado();

                if (estado == EstadoPartida.VICTORIA || estado == EstadoPartida.DERROTA) {
                    stage.setScene(crearFin(estado));
                    sonidoJuego.stop();
                    return;
                }
                if (r == Resultado.NO_PAREJA || r == Resultado.PAREJA || r == Resultado.JOKER) {
                    /*
                     * Mantiene las cartas visibles durante un segundo antes de resolver el turno.
                     */
                    PauseTransition pausa = new PauseTransition(Duration.seconds(1));
                    pausa.setOnFinished(ev -> {
                         partida.resolverTurno();
                        actualizarTablero(partida, botones);
                    });
                    pausa.play();
                }
            });

            tablero.add(btnCarta, columna, fila);
            botones[fila][columna] = btnCarta;

        }
    }

    BorderPane juego = new BorderPane();
    campo.getStyleClass().add("campo");
    juego.setCenter(campo);

    StackPane panelCartas = new StackPane(tablero, btnSalir);
    panelCartas.getStyleClass().add("panel");
    StackPane.setAlignment(btnSalir, Pos.BOTTOM_RIGHT);
    StackPane.setMargin(btnSalir, new Insets(0,10,10,0));
    juego.setBottom(panelCartas);

    return nuevaEscena(juego);
}
    
    /**
     * Actualiza la representación gráfica de todas las cartas del tablero.
     *
     * Las cartas eliminadas se deshabilitan, las descubiertas muestran
     * su nombre y las ocultas muestran un signo de interrogación.
     *
     * @param partida partida de la que se consulta el estado de las cartas.
     * @param botones matriz de botones que representa el tablero.
     */
    private void actualizarTablero(Juego partida, Button[][] botones) {
        for (int fila = 0; fila < 5; fila++) {
            for (int columna = 0; columna < 5; columna++) {
                Carta carta = partida.getCarta(fila, columna);
                Button boton = botones[fila][columna];

                /*
                 * Retira el estilo anterior antes de aplicar el correspondiente al estado actual.
                 */
                boton.getStyleClass().remove("descubierta");

                if (carta.isEliminada()) {
                    boton.setText("");
                    boton.setDisable(true);
                } else if (carta.isDescubierta()) {
                    boton.setText(carta.getNombre());
                    boton.setDisable(false);
                    boton.getStyleClass().add("descubierta");
                } else {
                    boton.setText("?");
                    boton.setDisable(false);
                }
            }
        }
    }
    
    /**
     * Actualiza las estadísticas visibles del héroe y del enemigo.
     *
     * Muestra el nombre, la vida actual y máxima, el ataque y la defensa
     * de cada personaje.
     *
     * @param partida partida que contiene los personajes del combate.
     * @param lblHeroe etiqueta que muestra las estadísticas del héroe.
     * @param lblEnemigo etiqueta que muestra las estadísticas del enemigo.
     */
    private void actualizarCampo(Juego partida, Label lblHeroe, Label lblEnemigo) {
        Heroe h = partida.getHeroe();
        Enemigo en = partida.getEnemigo();

        lblHeroe.setText(h.getNombre()
                + "\nHP " + h.getVidaActual() + "/" + h.getVidaMaxima()
                + "\nATQ " + h.getAtaque()
                + "\nDEF " + h.getDefensa());

        lblEnemigo.setText(en.getNombre()
                + "\nHP " + en.getVidaActual() + "/" + en.getVidaMaxima()
                + "\nATQ " + en.getAtaque()
                + "\nDEF " + en.getDefensa());
    }

    /**
     * Crea la pantalla final de victoria o derrota.
     *
     * Selecciona el mensaje y la imagen correspondientes al resultado
     * de la partida e incorpora un botón para volver al menú principal.
     *
     * @param estado resultado final de la partida.
     * @return escena que presenta el resultado final.
     * @throws IllegalArgumentException si el estado no es VICTORIA ni DERROTA.
     */
    private Scene crearFin(EstadoPartida estado) {

        ImageView icono;
        Label texto_final;

        if (estado == EstadoPartida.VICTORIA) {
            texto_final = new Label("Victoria! El enemigo fue derrotado.");
            icono = new ImageView(getClass().getResource("img/CasualUI_9_2.png").toExternalForm());
            } else if (estado == EstadoPartida.DERROTA) {
            texto_final = new Label("Derrota...");
            icono = new ImageView(getClass().getResource("img/icon_02.png").toExternalForm());
            } else {
                throw new IllegalArgumentException("crearFin solo acepta VICTORIA o DERROTA, no " + estado);
            }

            texto_final.getStyleClass().add("titulo");
            texto_final.getStyleClass().add("final");


            icono.setFitWidth(64);
            icono.setPreserveRatio(true);
            texto_final.setGraphic(icono);

            Button btnMenu = new Button("Volver al menu");
            btnMenu.getStyleClass().add("btn");
            btnMenu.getStyleClass().add("regresar");
            icono = new ImageView(getClass().getResource("img/strl_01.png").toExternalForm());
            icono.setFitWidth(48);
            icono.setPreserveRatio(true);
            btnMenu.setGraphic(icono);
            btnMenu.setOnAction(e -> stage.setScene(crearMenu()));

            VBox fin = new VBox(20, texto_final, btnMenu);
            fin.setAlignment(Pos.CENTER);

            return nuevaEscena(fin);
        }

    /**
     * Crea una escena con las dimensiones y estilos del juego.
     *
     * Establece un tamaño inicial de 720 por 900 píxeles y aplica
     * la hoja de estilos estilos.css.
     *
     * @param raiz nodo raíz de la escena.
     * @return escena creada con la hoja de estilos aplicada.
     */
    private Scene nuevaEscena(Parent raiz) {
        Scene escena = new Scene(raiz, 720, 900);
        escena.getStylesheets().add(
                getClass().getResource("estilos.css").toExternalForm());
        return escena;
    }

}
