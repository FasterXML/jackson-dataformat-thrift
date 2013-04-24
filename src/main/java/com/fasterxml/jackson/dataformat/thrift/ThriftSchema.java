package com.fasterxml.jackson.dataformat.thrift;

import com.fasterxml.jackson.core.FormatSchema;

public class ThriftSchema implements FormatSchema
{
    public final static String FORMAT_NAME = "thrift";

    protected ThriftSchema() { }
    
    public static ThriftSchema construct() {
        return new ThriftSchema();
    }
    
    @Override
    public String getSchemaType() {
        return FORMAT_NAME;
    }
}