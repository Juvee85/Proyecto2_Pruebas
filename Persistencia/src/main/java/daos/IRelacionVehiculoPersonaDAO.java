
package daos;

import entidades.RelacionVehiculoPersona;

/**
 *
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
public interface IRelacionVehiculoPersonaDAO {

    public boolean existeDetalle(RelacionVehiculoPersona rvp);

    public void insertarDetalle(RelacionVehiculoPersona rvp);

}
