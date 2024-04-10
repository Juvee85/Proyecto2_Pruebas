package entidades;

import entidades.PersonaEntidad;
import entidades.VehiculoEntidad;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-04-09T22:23:48", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(RelacionVehiculoPersona.class)
public class RelacionVehiculoPersona_ { 

    public static volatile SingularAttribute<RelacionVehiculoPersona, PersonaEntidad> persona;
    public static volatile SingularAttribute<RelacionVehiculoPersona, Long> id;
    public static volatile SingularAttribute<RelacionVehiculoPersona, VehiculoEntidad> vehiculo;

}