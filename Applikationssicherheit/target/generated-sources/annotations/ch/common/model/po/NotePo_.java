package ch.common.model.po;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(NotePo.class)
public abstract class NotePo_ {

	public static volatile SingularAttribute<NotePo, LocalDate> datum;
	public static volatile SingularAttribute<NotePo, Double> note;
	public static volatile SingularAttribute<NotePo, String> notiz;
	public static volatile SingularAttribute<NotePo, Double> gewichtung;
	public static volatile SingularAttribute<NotePo, Long> id;
	public static volatile SingularAttribute<NotePo, FachPo> fach;

	public static final String DATUM = "datum";
	public static final String NOTE = "note";
	public static final String NOTIZ = "notiz";
	public static final String GEWICHTUNG = "gewichtung";
	public static final String ID = "id";
	public static final String FACH = "fach";

}

