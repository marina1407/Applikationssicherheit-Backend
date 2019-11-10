package ch.common.model.po;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SemesterPo.class)
public abstract class SemesterPo_ {

	public static volatile SingularAttribute<SemesterPo, String> bezeichnung;
	public static volatile SingularAttribute<SemesterPo, String> schultyp;
	public static volatile SingularAttribute<SemesterPo, BenutzerPo> benutzer;
	public static volatile SingularAttribute<SemesterPo, Long> id;

	public static final String BEZEICHNUNG = "bezeichnung";
	public static final String SCHULTYP = "schultyp";
	public static final String BENUTZER = "benutzer";
	public static final String ID = "id";

}

