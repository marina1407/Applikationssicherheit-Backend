package ch.common.model.po;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(FachPo.class)
public abstract class FachPo_ {

	public static volatile SingularAttribute<FachPo, String> bezeichnung;
	public static volatile SingularAttribute<FachPo, String> notizen;
	public static volatile SingularAttribute<FachPo, Double> gewichtung;
	public static volatile SingularAttribute<FachPo, SemesterPo> semester;
	public static volatile SingularAttribute<FachPo, Long> id;

	public static final String BEZEICHNUNG = "bezeichnung";
	public static final String NOTIZEN = "notizen";
	public static final String GEWICHTUNG = "gewichtung";
	public static final String SEMESTER = "semester";
	public static final String ID = "id";

}

