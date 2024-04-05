/*
 * IPersonaDAO.java
 */
package daos;

import entidades.PersonaEntidad;
import excepciones.PersistenciaException;

/**
 * Interfaz que define métodos para acceder y manipular datos relacionados con
 * personas en la base de datos.
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
public interface IPersonaDAO {
    
    /**
     * Método que indica si hay personas o no registradas en la base de datos.
     * @return Verdadero si ya hay registros en la base de datos, y false si
     * no los hay.
     */
    public boolean hayPersonas();
    
    /**
     * Método que inserta una persona en la base de datos.
     * @param persona Persona a la cual se va a insertar.
     */
    public void insertarPersona(PersonaEntidad persona);
    
    /**
     * Método que busca una persona dada una CURP.
     * @param curp CURP de la persona que se busca.
     * @return La persona encontrada.
     * @throws PersistenciaException si no se encontró a ninguna persona con
     * la CURP dada.
     */
    public PersonaEntidad buscarPorCurp(String curp) throws PersistenciaException;
    
}
