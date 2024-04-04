package entidades;

import entidades.RelacionVehiculoPersona;
import entidades.TramiteEntidad;
import java.util.Calendar;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-04-04T05:46:58", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(PersonaEntidad.class)
public class PersonaEntidad_ { 

    public static volatile SingularAttribute<PersonaEntidad, String> apellidoPaterno;
    public static volatile SingularAttribute<PersonaEntidad, Boolean> discapacitado;
    public static volatile SingularAttribute<PersonaEntidad, Calendar> fechaNacimiento;
    public static volatile ListAttribute<PersonaEntidad, TramiteEntidad> tramites;
    public static volatile SingularAttribute<PersonaEntidad, Long> id;
    public static volatile SingularAttribute<PersonaEntidad, String> telefono;
    public static volatile SingularAttribute<PersonaEntidad, String> nombre;
    public static volatile SingularAttribute<PersonaEntidad, String> curp;
    public static volatile SingularAttribute<PersonaEntidad, String> rfc;
    public static volatile SingularAttribute<PersonaEntidad, String> apellidoMaterno;
    public static volatile ListAttribute<PersonaEntidad, RelacionVehiculoPersona> detalle;

}