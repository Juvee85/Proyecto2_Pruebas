package entidades;

import entidades.TarifaPlacasEntidad;
import entidades.VehiculoEntidad;
import java.util.Calendar;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-11-01T23:54:53", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(PlacasEntidad.class)
public class PlacasEntidad_ extends TramiteEntidad_ {

    public static volatile SingularAttribute<PlacasEntidad, TarifaPlacasEntidad> tarifa;
    public static volatile SingularAttribute<PlacasEntidad, String> numero;
    public static volatile SingularAttribute<PlacasEntidad, VehiculoEntidad> vehiculo;
    public static volatile SingularAttribute<PlacasEntidad, Calendar> fechaRecepcion;

}