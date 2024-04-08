/*
 * IReporteTramitesBO.java
 */
package negocio;

import dtos.ReporteDTO;
import excepciones.NegocioException;
import java.util.Calendar;
import java.util.List;
import utilidades.TiposTramite;

/**
 * Interfaz que define métodos para aplicar las reglas de negocio y llevar a
 * cabo el la generación de reportes de trámites.
 *
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
public interface IReporteTramitesBO {

    /**
     * Método que devuelve una lista de reportes.
     *
     * @param tramitesSeleccionados Tipo de trámites seleccionados.
     * @param nombre Nombre que se busca coincidir.
     * @param fechaInicial Fecha inicial del periodo de búsqueda.
     * @param fechaFinal Fecha final del periodo de búsqueda.
     * @param pagina Número de página de los resultados.
     * @return Una lista con los reportes encontrados.
     * @throws NegocioException Si no se encuentra ningún trámite.
     */
    public List<ReporteDTO> obtenerReporteTramites(List<TiposTramite> tramitesSeleccionados,
            String nombre, Calendar fechaInicial, Calendar fechaFinal, int pagina) throws NegocioException;

    /**
     * Método que genera un reporte en PDF.
     *
     * @param tramitesSeleccionados Tipos de trámite seleccionados.
     * @param nombre Nombre que se busca coincidir.
     * @param fechaInicial Fecha inicial del periodo de búsqueda.
     * @param fechaFinal Fecha final del periodo de búsqueda.
     */
    public void generarReporte(List<TiposTramite> tramitesSeleccionados, String nombre, Calendar fechaInicial, Calendar fechaFinal);

    /**
     * Método que devuelve una lista con los tipos de trámite seleccionados para
     * el reporte.
     *
     * @param tramitesSeleccionados Tipos de trámite seleccionados.
     * @return Una lista con los tipos de trámite.
     */
    public List<Class> tiposSeleccionados(List<TiposTramite> tramitesSeleccionados);
}
