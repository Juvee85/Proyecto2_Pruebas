package entidades;

import entidades.TarifaPlacaEntidad;
import entidades.VehiculoEntidad;
import java.util.Calendar;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-04-04T05:46:58", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(PlacaEntidad.class)
public class PlacaEntidad_ extends TramiteEntidad_ {

    public static volatile SingularAttribute<PlacaEntidad, TarifaPlacaEntidad> tarifa;
    public static volatile SingularAttribute<PlacaEntidad, String> numero;
    public static volatile SingularAttribute<PlacaEntidad, VehiculoEntidad> vehiculo;
    public static volatile SingularAttribute<PlacaEntidad, Calendar> fechaRecepcion;

}