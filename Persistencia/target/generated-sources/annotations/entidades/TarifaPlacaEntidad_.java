package entidades;

import entidades.PlacaEntidad;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-04-04T05:46:58", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(TarifaPlacaEntidad.class)
public class TarifaPlacaEntidad_ { 

    public static volatile SingularAttribute<TarifaPlacaEntidad, String> tipo;
    public static volatile SingularAttribute<TarifaPlacaEntidad, Float> costo;
    public static volatile SingularAttribute<TarifaPlacaEntidad, Long> id;
    public static volatile ListAttribute<TarifaPlacaEntidad, PlacaEntidad> placas;

}