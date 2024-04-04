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
 *
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
public class InsercionMasivaBO implements IInsercionMasivaBO {
    
    private IPersonaDAO personaDAO = new PersonaDAO();
    private static final Logger logger = Logger.getLogger(InsercionMasivaBO.class.getName());
    
    /**
     * Método para realizar una inserción masiva de 20 personas.
     * @throws NegocioException si ya hay personas registradas en la base de datos.
     */
    @Override
    public void insertarPersonas() throws NegocioException {
        // Si ya hay personas registradas, lanzamos una excepción.
        if (personaDAO.hayPersonas()) {
            // Mandamos un mensaje a la consola de que hay personas registradas.
            logger.log(Level.WARNING, "Ya hay personas registradas.");
            // Lanzamos una excepción que indique que ya se realizó la inserción masiva.
            throw new NegocioException("Ya se realizó la inserción masiva.");
        }
        
        Encriptador e = new Encriptador();
        List<PersonaEntidad> listaPersonas = crearPersonas();
        
        try {
            for (PersonaEntidad persona : listaPersonas) {
                    persona.setNombre(e.encriptar(persona.getNombre()));
                    persona.setApellidoPaterno(e.encriptar(persona.getApellidoPaterno()));
                    persona.setApellidoMaterno(e.encriptar(persona.getApellidoMaterno()));
                    personaDAO.insertarPersona(persona);
            }
        } catch (Exception ex) {
            Logger.getLogger(InsercionMasivaBO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<PersonaEntidad> crearPersonas() {
        List<PersonaEntidad> listaPersonas = new ArrayList<>();
        listaPersonas.add(new PersonaEntidad("Diego", "Valenzuela", "Parra", "VAPD040603HSRLRGA6", "VAPD040603UP0", "6441123456", false, new GregorianCalendar(2004, 5, 3)));
        listaPersonas.add(new PersonaEntidad("Juventino", "López", "García", "LOGJ040318HSRPRVA4", "LOGJ040318AN7", "6441987654", false, new GregorianCalendar(2004, 5, 3)));
        listaPersonas.add(new PersonaEntidad("Elena", "López", "Martínez", "LOME090720TJCNNA01", "LOME090720CT8", "6441234567", true, new GregorianCalendar(2008, 6, 20))); // Menor de edad
        listaPersonas.add(new PersonaEntidad("Javier", "Gómez", "Ruiz", "GORJ950512MDFGJM92", null, "6448965130", false, new GregorianCalendar(1995, 4, 12))); // Sin RFC
        listaPersonas.add(new PersonaEntidad("Sofía", "Martínez", "González", "MAGO000101TJCNNA45", "MAGO000101GF5", null, true, new GregorianCalendar(2000, 0, 1))); // Sin número de teléfono
        listaPersonas.add(new PersonaEntidad("Luis", "Hernández", "García", "HEGL850428MTSRRA00", "HEGL850428PL8", "5634567890", true, new GregorianCalendar(1985, 3, 28)));
        listaPersonas.add(new PersonaEntidad("Martha", "Gómez", "Díaz", "GODM730702MDFMRT98", "GODM730702QT9", "5645678901", false, new GregorianCalendar(1973, 6, 2)));
        listaPersonas.add(new PersonaEntidad("Roberto", "Pérez", "López", "PELR601201HDFBRT04", "PELR601201BV0", "5656789012", true, new GregorianCalendar(1960, 11, 1)));
        listaPersonas.add(new PersonaEntidad("Ana", "Martínez", "Fernández", "MAFA540920GDLNTN07", "MAFA540920NQ1", "5667890123", false, new GregorianCalendar(1954, 8, 20)));
        listaPersonas.add(new PersonaEntidad("Pedro", "González", "Sánchez", "GOSP870701MTSDRP05", "GOSP870701YY2", "5678901234", true, new GregorianCalendar(1987, 6, 1)));
        listaPersonas.add(new PersonaEntidad("Elena", "Rodríguez", "Ramírez", "RORR960316HDFLNA09", "RORR960316XL3", "5689012345", false, new GregorianCalendar(1996, 2, 16)));
        listaPersonas.add(new PersonaEntidad("Francisco", "Díaz", "Gómez", "DIGF700812MTSFNC08", "DIGF700812ZZ4", "5690123456", true, new GregorianCalendar(1970, 7, 12)));
        listaPersonas.add(new PersonaEntidad("Laura", "Sánchez", "Martínez", "SAML650525HDFLRA06", "SAML650525KH5", "5701234567", false, new GregorianCalendar(1965, 4, 25)));
        listaPersonas.add(new PersonaEntidad("Miguel", "López", "González", "LOGM060419GDLPLG00", "LOGM280419HG6", "5712345678", true, new GregorianCalendar(2006, 3, 19)));
        listaPersonas.add(new PersonaEntidad("Isabel", "Fernández", "Pérez", "FEPI910601MTSLZB01", "FEPI910601WÑ7", "5723456789", false, new GregorianCalendar(1991, 5, 1)));
        listaPersonas.add(new PersonaEntidad("Alejandro", "Martínez", "Gómez", "MAGO720730GDLTLX03", "MAGO720730MF8", "5734567890", true, new GregorianCalendar(1972, 6, 30)));
        listaPersonas.add(new PersonaEntidad("Carmen", "García", "Hernández", "GAHC840511MDFCRM04", "GAHC840511TI9", "5745678901", false, new GregorianCalendar(1984, 4, 11)));
        listaPersonas.add(new PersonaEntidad("José", "Díaz", "López", "DILJ800922MTSJSP05", "DILJ800922OE0", "5756789012", true, new GregorianCalendar(1980, 8, 22)));
        listaPersonas.add(new PersonaEntidad("Fernanda", "Ramírez", "Martínez", "RAMF790130GDLRND06", "RAMF790130RT1", "5767890123", false, new GregorianCalendar(1979, 0, 30)));
        listaPersonas.add(new PersonaEntidad("Javier", "González", "Sánchez", "GOSJ861205MTSJKR07", "GOSJ861205JC2", "5778901234", true, new GregorianCalendar(1986, 11, 5)));
        
        return listaPersonas;
    }
}
