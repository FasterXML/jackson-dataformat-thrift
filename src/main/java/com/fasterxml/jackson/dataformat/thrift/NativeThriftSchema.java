package com.fasterxml.jackson.dataformat.thrift;

import com.facebook.swift.parser.model.Document;

public class NativeThriftSchema
{
    protected final Document _raw;

    private NativeThriftSchema(Document doc) {
        _raw = doc;
    }

    public static NativeThriftSchema construct(Document doc) {
        return new NativeThriftSchema(doc);
    }
}
