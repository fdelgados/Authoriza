package com.authoriza.shared.infrastructure.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.AbstractSingleColumnStandardBasicType;
import org.hibernate.type.PostgresUUIDType;
import org.hibernate.type.descriptor.java.JavaTypeDescriptor;
import org.hibernate.type.descriptor.java.UUIDTypeDescriptor;
import org.hibernate.type.descriptor.sql.SqlTypeDescriptor;
import org.hibernate.type.descriptor.sql.VarcharTypeDescriptor;

import java.io.IOException;
import java.util.Properties;

public class UuidType extends AbstractSingleColumnStandardBasicType {

    public UuidType(SqlTypeDescriptor sqlTypeDescriptor, JavaTypeDescriptor javaTypeDescriptor) {
        super(sqlTypeDescriptor, javaTypeDescriptor);
    }

    private static final SqlTypeDescriptor SQL_DESCRIPTOR;
    private static final JavaTypeDescriptor TYPE_DESCRIPTOR;

    static {
        Properties properties = new Properties();
        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            properties.load(loader.getResourceAsStream("database.properties"));
        } catch (IOException e) {
            throw new RuntimeException("Could not load properties!", e);
        }

        String dialect = properties.getProperty("dialect");
        if(dialect.equals("org.hibernate.dialect.PostgreSQLDialect")) {
            SQL_DESCRIPTOR = PostgresUUIDType.PostgresUUIDSqlTypeDescriptor.INSTANCE;
        } else if(dialect.equals("org.hibernate.dialect.H2Dialect")) {
            SQL_DESCRIPTOR = VarcharTypeDescriptor.INSTANCE;
        } else {
            throw new UnsupportedOperationException("Unsupported database!");
        }

        TYPE_DESCRIPTOR = UUIDTypeDescriptor.INSTANCE;
    }

    @Override
    public String getName() {
        return "custom-uuid";
    }

    @Override
    public Object resolve(Object value, SharedSessionContractImplementor session, Object owner, Boolean overridingEager) throws HibernateException {
        return super.resolve(value, session, owner, overridingEager);
    }
}
