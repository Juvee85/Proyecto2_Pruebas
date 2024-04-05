/**
 * IConexion.java
 */
package conexion;

import javax.persistence.EntityManager;

/**
 * Interfaz con los métodos necesarios para crear conexiones con la base de datos. 
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
public interface IConexion {
    
    /**
     * Método para crear una conexión con la base de datos.
     * @return Un objeto del tipo EntityManager.
     */
    public EntityManager crearConexion();
}
