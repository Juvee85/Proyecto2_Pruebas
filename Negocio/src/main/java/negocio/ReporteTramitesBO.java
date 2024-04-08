/**
 * ReporteTramitesBO.java
 */
package negocio;

import daos.ITramiteDAO;
import daos.TramiteDAO;
import dtos.ReporteDTO;
import entidades.LicenciaEntidad;
import entidades.PlacasEntidad;
import entidades.TramiteEntidad;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import java.awt.HeadlessException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import utilidades.Encriptador;
import utilidades.Paginacion;
import utilidades.TiposTramite;
import static utilidades.TiposTramite.LICENCIA;
import static utilidades.TiposTramite.PLACAS;

/**
 * Clase con la implementación de la interfaz IReporteTramitesBO para realizar
 * el reporte de trámites.
 *
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
public class ReporteTramitesBO implements IReporteTramitesBO {

    private ITramiteDAO tramiteDAO = new TramiteDAO();
    private static final Logger logger = Logger.getLogger(ReporteTramitesBO.class.getName());
    private int numeroElementos;
    private Encriptador encriptador = new Encriptador();

    /**
     * Constructor que inicializa el atributo número de elementos.
     *
     * @param numeroElementos Número de elementos a mostrar por página.
     */
    public ReporteTramitesBO(int numeroElementos) {
        this.numeroElementos = numeroElementos;
    }

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
    @Override
    public List<ReporteDTO> obtenerReporteTramites(List<TiposTramite> tramitesSeleccionados,
            String nombre, Calendar fechaInicial, Calendar fechaFinal, int pagina) throws NegocioException {
        if (nombre.isBlank()) {
            nombre = null;
        }
        try {
            List<Class> tipos = tiposSeleccionados(tramitesSeleccionados);
            // Obtenemos la lista de trámites.
            List<TramiteEntidad> tramiteEntidades = tramiteDAO.obtenerReporte(nombre, fechaInicial, fechaFinal, tipos,
                    numeroElementos, Paginacion.obtenerOffset(numeroElementos, pagina));
            // La convertimos a DTO.
            List<ReporteDTO> reporteTramites = new ArrayList<>();
            for (TramiteEntidad tramiteEntidad : tramiteEntidades) {
                reporteTramites.add(new ReporteDTO(tramiteEntidad));
            }
            // Retornamos la lista.
            return reporteTramites;
        } catch (PersistenciaException pe) {
            // Mandamos un mensaje a la consola de que no se encontró ninguna persona.
            logger.log(Level.WARNING, pe.getMessage());
            // Lanzamos una exceción indicando que no se encontró ningun trámite.
            throw new NegocioException(pe.getMessage());
        }
    }

    /**
     * Método que genera un reporte en PDF.
     *
     * @param tramitesSeleccionados Tipos de trámite seleccionados.
     * @param nombre Nombre que se busca coincidir.
     * @param fechaInicial Fecha inicial del periodo de búsqueda.
     * @param fechaFinal Fecha final del periodo de búsqueda.
     */
    @Override
    public void generarReporte(List<TiposTramite> tramitesSeleccionados, String nombre, Calendar fechaInicial, Calendar fechaFinal) {
        if (nombre.isBlank()) {
            nombre = null;
        }
        try {
            List<Class> tipos = tiposSeleccionados(tramitesSeleccionados);
            List<TramiteEntidad> tramiteEntidades = tramiteDAO.obtenerReporte(nombre, fechaInicial, fechaFinal, tipos);
            List<ReporteDTO> reporteTramites = new ArrayList<>();
            for (TramiteEntidad tramiteEntidad : tramiteEntidades) {
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                String fecha = formatter.format(tramiteEntidad.getFechaEmision().getTime());
                reporteTramites.add(new ReporteDTO(tramiteEntidad));
            }
            // Crear un JRBeanCollectionDataSource con la lista de ReporteDTO
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reporteTramites);

            // Parámetros para el reporte mapeado
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("ds", dataSource);

            // Configuración del JFileChooser para la seleccion de ubicación y nombre para el archivo
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Guardar Reporte");
            fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos PDF", "pdf"));

            // Se muestra el diálogo para guardar el archivo
            int userSelection = fileChooser.showSaveDialog(null);

            // Cuando el usuario selecciona guardar
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                java.io.File fileToSave = fileChooser.getSelectedFile();
                String filePath = fileToSave.getAbsolutePath();

                // Asegurar que la extensión del archivo sea .pdf
                if (!filePath.endsWith(".pdf")) {
                    filePath += ".pdf";
                }

                // Exportar el reporte a un archivo PDF
                try (InputStream input = getClass().getResourceAsStream("/ReporteTramites.jrxml")) {
                    JasperDesign jasperDesign = JRXmlLoader.load(input);
                    JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
                    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

                    JasperExportManager.exportReportToPdfFile(jasperPrint, filePath);
                    JOptionPane.showMessageDialog(null, "Reporte generado con éxito.");
                } catch (Exception ex) {
                    // Manejar la excepción cuando ocurre algún error al generar el reporte
                    JOptionPane.showMessageDialog(null, "Error al generar el reporte PDF: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    logger.log(Level.WARNING, "No se obtuvo ningun tramite");
                }
            } else if (userSelection == JFileChooser.CANCEL_OPTION) {
                // Si el usuario cancela la operación.
                JOptionPane.showMessageDialog(null, "El usuario canceló la operación.", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (PersistenciaException | HeadlessException ex) {
            // Manejar cualquier excepción que pueda ocurrir.
            JOptionPane.showMessageDialog(null, "Error al generar el reporte PDF: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Método que devuelve una lista con los tipos de trámite seleccionados
     * para el reporte.
     *
     * @param tramitesSeleccionados Tipos de trámite seleccionados.
     * @return Una lista con los tipos de trámite.
     */
    @Override
    public List<Class> tiposSeleccionados(List<TiposTramite> tramitesSeleccionados) {
        List<Class> tipos = new ArrayList<>();
        for (TiposTramite tramiteSeleccionado : tramitesSeleccionados) {
            switch (tramiteSeleccionado) {
                case LICENCIA ->
                    tipos.add(LicenciaEntidad.class);
                case PLACAS ->
                    tipos.add(PlacasEntidad.class);
            }
        }
        return tipos;
    }
}
