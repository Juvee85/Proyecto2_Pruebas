package entidades;

import entidades.LicenciaEntidad;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-04-09T22:23:48", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(TarifaLicenciaEntidad.class)
public class TarifaLicenciaEntidad_ { 

    public static volatile SingularAttribute<TarifaLicenciaEntidad, String> vigencia;
    public static volatile SingularAttribute<TarifaLicenciaEntidad, Float> costoDiscapacitado;
    public static volatile SingularAttribute<TarifaLicenciaEntidad, Float> costoNormal;
    public static volatile SingularAttribute<TarifaLicenciaEntidad, Long> id;
    public static volatile ListAttribute<TarifaLicenciaEntidad, LicenciaEntidad> licencias;

}