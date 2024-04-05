/*
 * IncercionMasivaBO.java
 */
package negocio;

import daos.PersonaDAO;
import excepciones.NegocioException;
import java.util.logging.Level;
import java.util.logging.Logger;
import daos.IPersonaDAO;
import entidades.PersonaEntidad;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import utilidades.Encriptador;

/**
 * Clase con la implementación de la interfaz IInsercionMasivaBO para realizar
 * la inserción masiva.
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
public class InsercionMasivaBO implements IInsercionMasivaBO {

    private IPersonaDAO personaDAO = new PersonaDAO();
    private static final Logger logger = Logger.getLogger(InsercionMasivaBO.class.getName());

    /**
     * Método para llevar a cabo la inserción masiva.
     * @throws NegocioException si ya se realizó la inserción masiva.
     */
    @Override
    public void insertarPersonas() throws NegocioException {
        // Si ya hay personas registradas, lanzamos una excepción.
        if (personaDAO.hayPersonas()) {
            // Mandamos un mensaje a la consola de que se interrumpió la inserción
            // masiva.
            logger.log(Level.WARNING, "Inserción masiva interrumpida.");
            // Lanzamos una excepción que indique que ya se realizó la inserción masiva.
            throw new NegocioException("Ya se realizó la inserción masiva.");
        }

        // Creamos una lista con las personas que se insertarán.
        List<PersonaEntidad> listaPersonas = crearPersonas();

        // Iteramos sobre la lista de personas.
        for (PersonaEntidad persona : listaPersonas) {
            // Mandamos a insertar a la persona.
            personaDAO.insertarPersona(persona);
        }
        // Mandamos un mensaje a la consola de que se realizó la inserción masiva.
        logger.log(Level.INFO, "Inserción masiva completada.");
    }

    /**
     * Método que crea las personas de la inserción masiva.
     * @return Una lista de personas.
     * @throws NegocioException si ocurre un error durante la encriptación.
     */
    @Override
    public List<PersonaEntidad> crearPersonas() throws NegocioException {
        try {
            // Creamos una instancia del encriptador de datos.
            Encriptador e = new Encriptador();

            // Creamos la lista donde se almacenarán las personas.
            List<PersonaEntidad> listaPersonas = new ArrayList<>();
            // Creamos las personas ya con sus nombres encriptados y las agregamos
            // a la lista.
            listaPersonas.add(new PersonaEntidad(e.encriptar("Diego"), e.encriptar("Valenzuela"), e.encriptar("Parra"), "VAPD040603HSRLRGA6", "VAPD040603UP0", "6441123456", false, new GregorianCalendar(2004, 5, 3)));
            listaPersonas.add(new PersonaEntidad(e.encriptar("Juventino"), e.encriptar("López"), e.encriptar("García"), "LOGJ040318HSRPRVA4", "LOGJ040318AN7", "6441987654", false, new GregorianCalendar(2004, 5, 3)));
            listaPersonas.add(new PersonaEntidad(e.encriptar("Elena"), e.encriptar("López"), e.encriptar("Martínez"), "LOME090720TJCNNA01", "LOME090720CT8", "6441234567", true, new GregorianCalendar(2008, 6, 20))); // Menor de edad
            listaPersonas.add(new PersonaEntidad(e.encriptar("Javier"), e.encriptar("Gómez"), e.encriptar("Ruiz"), "GORJ950512MDFGJM92", null, "6448965130", false, new GregorianCalendar(1995, 4, 12))); // Sin RFC
            listaPersonas.add(new PersonaEntidad(e.encriptar("Sofía"), e.encriptar("Martínez"), e.encriptar("González"), "MAGO000101TJCNNA45", "MAGO000101GF5", null, true, new GregorianCalendar(2000, 0, 1))); // Sin número de teléfono
            listaPersonas.add(new PersonaEntidad(e.encriptar("Luis"), e.encriptar("Hernández"), e.encriptar("García"), "HEGL850428MTSRRA00", "HEGL850428PL8", "5634567890", true, new GregorianCalendar(1985, 3, 28)));
            listaPersonas.add(new PersonaEntidad(e.encriptar("Martha"), e.encriptar("Gómez"), e.encriptar("Díaz"), "GODM730702MDFMRT98", "GODM730702QT9", "5645678901", false, new GregorianCalendar(1973, 6, 2)));
            listaPersonas.add(new PersonaEntidad(e.encriptar("Roberto"), e.encriptar("Pérez"), e.encriptar("López"), "PELR601201HDFBRT04", "PELR601201BV0", "5656789012", true, new GregorianCalendar(1960, 11, 1)));
            listaPersonas.add(new PersonaEntidad(e.encriptar("Ana"), e.encriptar("Martínez"), e.encriptar("Fernández"), "MAFA540920GDLNTN07", "MAFA540920NQ1", "5667890123", false, new GregorianCalendar(1954, 8, 20)));
            listaPersonas.add(new PersonaEntidad(e.encriptar("Pedro"), e.encriptar("González"), e.encriptar("Sánchez"), "GOSP870701MTSDRP05", "GOSP870701YY2", "5678901234", true, new GregorianCalendar(1987, 6, 1)));
            listaPersonas.add(new PersonaEntidad(e.encriptar("Elena"), e.encriptar("Rodríguez"), e.encriptar("Ramírez"), "RORR960316HDFLNA09", "RORR960316XL3", "5689012345", false, new GregorianCalendar(1996, 2, 16)));
            listaPersonas.add(new PersonaEntidad(e.encriptar("Francisco"), e.encriptar("Díaz"), e.encriptar("Gómez"), "DIGF700812MTSFNC08", "DIGF700812ZZ4", "5690123456", true, new GregorianCalendar(1970, 7, 12)));
            listaPersonas.add(new PersonaEntidad(e.encriptar("Laura"), e.encriptar("Sánchez"), e.encriptar("Martínez"), "SAML650525HDFLRA06", "SAML650525KH5", "5701234567", false, new GregorianCalendar(1965, 4, 25)));
            listaPersonas.add(new PersonaEntidad(e.encriptar("Miguel"), e.encriptar("López"), e.encriptar("González"), "LOGM060419GDLPLG00", "LOGM280419HG6", "5712345678", true, new GregorianCalendar(2006, 3, 19)));
            listaPersonas.add(new PersonaEntidad(e.encriptar("Isabel"), e.encriptar("Fernández"), e.encriptar("Pérez"), "FEPI910601MTSLZB01", "FEPI910601WÑ7", "5723456789", false, new GregorianCalendar(1991, 5, 1)));
            listaPersonas.add(new PersonaEntidad(e.encriptar("Alejandro"), e.encriptar("Martínez"), e.encriptar("Gómez"), "MAGO720730GDLTLX03", "MAGO720730MF8", "5734567890", true, new GregorianCalendar(1972, 6, 30)));
            listaPersonas.add(new PersonaEntidad(e.encriptar("Carmen"), e.encriptar("García"), e.encriptar("Hernández"), "GAHC840511MDFCRM04", "GAHC840511TI9", "5745678901", false, new GregorianCalendar(1984, 4, 11)));
            listaPersonas.add(new PersonaEntidad(e.encriptar("José"), e.encriptar("Díaz"), e.encriptar("López"), "DILJ800922MTSJSP05", "DILJ800922OE0", "5756789012", true, new GregorianCalendar(1980, 8, 22)));
            listaPersonas.add(new PersonaEntidad(e.encriptar("Fernanda"), e.encriptar("Ramírez"), e.encriptar("Martínez"), "RAMF790130GDLRND06", "RAMF790130RT1", "5767890123", false, new GregorianCalendar(1979, 0, 30)));
            listaPersonas.add(new PersonaEntidad(e.encriptar("Javier"), e.encriptar("González"), e.encriptar("Sánchez"), "GOSJ861205MTSJKR07", "GOSJ861205JC2", "5778901234", true, new GregorianCalendar(1986, 11, 5)));
            
            // Devolvemos la lista de personas.
            return listaPersonas;
        } catch (Exception ex) {
            //Mandamos un mensaje a la consola de que se interrumpió la inserción masiva.
            logger.log(Level.WARNING, "Inserción masiva interrumpida.");
            // Mandamos un mensaje a la consola de que hubo un error en la encriptación.
            logger.log(Level.SEVERE, "Hubo un error durante la encriptación", ex);
            // Lanzamos una excepción que indique que hubo un error.
            throw new NegocioException("Lo sentimos, ocurrió un error. Inténtelo más tarde.");
        }
    }
}
