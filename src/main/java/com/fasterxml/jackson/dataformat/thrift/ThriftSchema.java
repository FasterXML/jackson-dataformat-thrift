package com.fasterxml.jackson.dataformat.thrift;

import com.fasterxml.jackson.core.FormatSchema;

public class ThriftSchema implements FormatSchema
{
    public final static String FORMAT_NAME = "thrift";

    protected final NativeThriftSchema _rawSchema;
    
    protected ThriftSchema(NativeThriftSchema raw) {
        _rawSchema = raw;
    }
    
    public static ThriftSchema construct(NativeThriftSchema rawSchema) {
        return new ThriftSchema(rawSchema);
    }
    
    @Override
    public String getSchemaType() {
        return FORMAT_NAME;
    }
}