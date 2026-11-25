/**
 * Módulo principal del videojuego VolteaRoja.
 *
 * Integra las clases del modelo, la lógica del juego,
 * el sistema de combate y la interfaz gráfica.
 *
 * Utiliza JavaFX para la representación visual
 * y la reproducción de recursos multimedia.
 */
module Volteadoja {
    requires javafx.controls;
    requires javafx.media;
    exports volteadoja.vista to javafx.graphics;
}