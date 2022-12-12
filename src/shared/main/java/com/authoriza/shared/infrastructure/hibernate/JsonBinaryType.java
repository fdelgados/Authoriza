package com.authoriza.shared.infrastructure.hibernate;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Objects;
import java.util.Properties;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonBinaryType implements UserType, Serializable {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public int[] sqlTypes() {
        return new int[] {Types.JAVA_OBJECT};
    }

    @Override
    public Class<Object> returnedClass() {
        return Object.class;
    }

    @Override
    public boolean equals(Object x, Object y) throws HibernateException {
        return Objects.equals(x, y);
    }

    @Override
    public int hashCode(Object x) throws HibernateException {
        return Objects.hashCode(x);
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner)
            throws HibernateException, SQLException {

        final String cellContent = rs.getString(names[0]);
        if (cellContent == null) {
            return null;
        }
        try {
            return MAPPER.readValue(cellContent.getBytes("UTF-8"), returnedClass());
        } catch (final Exception exp) {
            throw new RuntimeException("Failed to convert json type: " + exp.getMessage(), exp);
        }
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session)
            throws HibernateException, SQLException {

        if (value == null) {
            st.setNull(index, Types.OTHER);
            return;
        }
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final StringWriter writer = new StringWriter();
            mapper.writeValue(writer, value);
            writer.flush();
            st.setObject(index, writer.toString(), Types.OTHER);
        } catch (final Exception exp) {
            throw new RuntimeException("Failed to convert json type: " + exp.getMessage(), exp);
        }

    }

    @Override
    public Object deepCopy(Object value) throws HibernateException {
        try {
            return (value == null) ? null : MAPPER.readValue(MAPPER.writeValueAsString(value), returnedClass());
        } catch (IOException e) {
            throw new HibernateException("unable to deep copy object", e);
        }
    }

    @Override
    public boolean isMutable() {
        return true;
    }

    @Override
    public Serializable disassemble(Object value) throws HibernateException {
        try {
            return MAPPER.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new HibernateException("unable to disassemble object", e);
        }
    }

    @Override
    public Object assemble(Serializable cached, Object owner) throws HibernateException {
        return deepCopy(cached);
    }

    @Override
    public Object replace(Object original, Object target, Object owner) throws HibernateException {
        return deepCopy(original);
    }
}
