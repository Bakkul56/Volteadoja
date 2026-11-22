package volteadoja.logica;

public enum Resultado {
    INVALIDA,   // carta eliminada o ya descubierta
    ESPERANDO,  // hay dos cartas erroneas a la vista
    PRIMERA,    // Primera carta volteada
    PAREJA,     // segunda carta: hacen pareja
    NO_PAREJA,  // segunda carta: no hacen pareja
    JOKER       // una de las dos es el Joker
}