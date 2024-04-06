package entidades;

import entidades.PersonaEntidad;
import java.util.Calendar;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-04-06T05:37:18", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(TramiteEntidad.class)
public abstract class TramiteEntidad_ { 

    public static volatile SingularAttribute<TramiteEntidad, PersonaEntidad> persona;
    public static volatile SingularAttribute<TramiteEntidad, Float> costo;
    public static volatile SingularAttribute<TramiteEntidad, Calendar> fechaEmision;
    public static volatile SingularAttribute<TramiteEntidad, Long> id;
    public static volatile SingularAttribute<TramiteEntidad, Boolean> activa;

}