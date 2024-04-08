/*
 * IRelacionVehiculoPersonaDAO.java
 */
package daos;

import entidades.RelacionVehiculoPersona;

/**
 * Interfaz que proporciona los métodos para acceder y manipular datos
 * relacionados con detalle (la relación entre vehículos y personas) en la base
 * de datos.
 *
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
public interface IRelacionVehiculoPersonaDAO {

    /**
     * Método que devuelve verdadero si ya existe una relación entre el vehículo
     * y la persona, devuelve falso si no la hay.
     *
     * @param rvp Relación que hay entre un vehículo y una persona.
     * @return Verdadero si ya existe una relación entre el vehículo y la
     * persona, devuelve falso si no la hay.
     */
    public boolean existeDetalle(RelacionVehiculoPersona rvp);

    /**
     * Método que inserta una relación entre un vehículo y una persona en la
     * base de datos.
     *
     * @param rvp Relación entre un vehículo y una persona.
     */
    public void insertarDetalle(RelacionVehiculoPersona rvp);
}
