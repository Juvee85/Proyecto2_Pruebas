package entidades;

import entidades.PlacaEntidad;
import entidades.RelacionVehiculoPersona;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-04-04T05:46:58", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(VehiculoEntidad.class)
public abstract class VehiculoEntidad_ { 

    public static volatile SingularAttribute<VehiculoEntidad, String> numeroSerie;
    public static volatile SingularAttribute<VehiculoEntidad, Boolean> usado;
    public static volatile SingularAttribute<VehiculoEntidad, Long> id;
    public static volatile ListAttribute<VehiculoEntidad, PlacaEntidad> placas;
    public static volatile ListAttribute<VehiculoEntidad, RelacionVehiculoPersona> detalle;

}