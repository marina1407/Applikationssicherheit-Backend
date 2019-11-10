package ch.common.model.po;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BenutzerPo.class)
public abstract class BenutzerPo_ {

	public static volatile SingularAttribute<BenutzerPo, String> password;
	public static volatile SingularAttribute<BenutzerPo, String> vorname;
	public static volatile SingularAttribute<BenutzerPo, String> nachname;
	public static volatile SingularAttribute<BenutzerPo, Long> id;
	public static volatile SingularAttribute<BenutzerPo, String> email;

	public static final String PASSWORD = "password";
	public static final String VORNAME = "vorname";
	public static final String NACHNAME = "nachname";
	public static final String ID = "id";
	public static final String EMAIL = "email";

}

