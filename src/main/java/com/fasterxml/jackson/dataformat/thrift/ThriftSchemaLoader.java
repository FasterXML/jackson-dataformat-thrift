package com.fasterxml.jackson.dataformat.thrift;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

import com.facebook.swift.parser.ThriftIdlParser;
import com.facebook.swift.parser.model.Document;

import com.google.common.io.InputSupplier;

public class ThriftSchemaLoader
{
    private final static Charset UTF8 = Charset.forName("UTF-8");

    public final static ThriftSchemaLoader DEFAULT_INSTANCE = new ThriftSchemaLoader();
    
    public ThriftSchemaLoader() { }

    /*
    /**********************************************************
    /* Public API
    /**********************************************************
     */

    public ThriftSchema load(URL url) throws IOException {
        return ThriftSchema.construct(parseNative(_readAll(url)));
    }
    
    public ThriftSchema load(File f) throws IOException {
        return ThriftSchema.construct(parseNative(_readAll(f)));
    }

    public ThriftSchema load(Reader r) throws IOException {
        return ThriftSchema.construct(parseNative(_readAll(r)));
    }

    public ThriftSchema parse(String schema) throws IOException {
        return ThriftSchema.construct(parseNative(schema));
    }
    
    /*
    /**********************************************************
    /* Loading native schema instances
    /**********************************************************
     */

    public NativeThriftSchema parseNative(String schema) throws IOException {
        return NativeThriftSchema.construct(ThriftIdlParser.parseThriftIdl(
                new StringInputSupplier(schema)));
    }
    
    /*
    /**********************************************************
    /* Helper methods
    /**********************************************************
     */

    protected String _readAll(URL url) throws IOException {
        return _readAll(url.openStream());
    }
    
    protected String _readAll(File f) throws IOException {
        return _readAll(new FileInputStream(f));
    }

    protected String _readAll(InputStream in) throws IOException {
        return _readAll(new InputStreamReader(in, UTF8));
    }
    
    protected String _readAll(Reader r) throws IOException
    {
        try {
            StringBuilder sb = new StringBuilder(1000);
            char[] buffer = new char[1000];
            int count;
            
            while ((count = r.read(buffer)) > 0) {
                sb.append(buffer, 0, count);
            }
            return sb.toString();
        } finally {
            try {
                r.close();
            } catch  (IOException e) { }
        }
    }
    
    public void _throw(Exception e0) throws IOException
    {
        // First, peel it
        Throwable e = e0;
        while (e.getCause() != null) {
            e = e.getCause();
        }
        if (e instanceof RuntimeException) {
            throw (RuntimeException) e;
        }
        if (e instanceof IOException){ 
            throw (IOException) e;
        }
        throw new IOException(e.getMessage(), e);
    }
    
    /*
    /**********************************************************
    /* Helper classes
    /**********************************************************
     */

    //InputSupplier<? extends Reader>

    protected static class StringInputSupplier implements InputSupplier<Reader> {
        protected final String _input;
        public StringInputSupplier(String input) { _input = input; }
        @Override public Reader getInput() {
            return new StringReader(_input);
        }
    }
}
