/*
 * Paginacion.java
 */
package utilidades;

/**
 * Clase con método auxiliar para la paginación de consultas a tabalas de la
 * base de datos
 *
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
public class Paginacion {

    /**
     * Regresa el offset para usar en una consulta a la base de datos requerido
     * para obtener los elementos correctos basado en la cantidad de elementos a
     * obtener y la página actual
     *
     * @param limite Limite de elementos a obtener de la base de datos por
     * página
     * @param pagina Número de página actual
     * @return El número de offset para usar en una consulta a la base de datos
     */
    public static int obtenerOffset(int limite, int pagina) {
        if (pagina <= 1) {
            return 0;
        }

        if (pagina == 2) {
            return limite;
        }

        return ((int) (limite * (pagina - 1)));
    }
}
