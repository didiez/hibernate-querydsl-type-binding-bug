package es.didiez.hibernatequerydslbug;

import java.io.Serializable;
import org.hibernate.dialect.Dialect;
import org.hibernate.type.AbstractSingleColumnStandardBasicType;
import org.hibernate.type.DiscriminatorType;
import org.hibernate.type.PrimitiveType;
import org.hibernate.type.StringType;
import org.hibernate.type.descriptor.java.BooleanTypeDescriptor;
import org.hibernate.type.descriptor.sql.CharTypeDescriptor;

public class ActiveInactiveType extends AbstractSingleColumnStandardBasicType<Boolean> implements PrimitiveType<Boolean>, DiscriminatorType<Boolean> {

    public static final ActiveInactiveType INSTANCE = new ActiveInactiveType();

    public ActiveInactiveType() {
        super(CharTypeDescriptor.INSTANCE, new BooleanTypeDescriptor('A', 'I'));
    }

    @Override
    public String getName() {
        return "A_I";
    }

    @Override
    public Class getPrimitiveClass() {
        return boolean.class;
    }

    @Override
    public Boolean stringToObject(String xml) throws Exception {
        return fromString(xml);
    }

    @Override
    public Serializable getDefaultValue() {
        return Boolean.TRUE;
    }

    @Override
    public String objectToSQLString(Boolean value, Dialect dialect) throws Exception {
        return StringType.INSTANCE.objectToSQLString(value ? "A" : "I", dialect);
    }
}
