package com.example.client;

import com.example.config.Consts;
import com.example.thrift.tutorial.Calculator;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.THttpClient;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransportException;

public class ThriftClient {
    public static final String uri = Consts.uri;
    public static final int port = Consts.port;
    public static final String host = "localhost";

    public TProtocol getBinaryProtocol() {
        return new TBinaryProtocol(new TSocket(host, Consts.port));
    }

    public TProtocol getJsonProtocol() {
        THttpClient httpClient = null;
        try {
            httpClient = new THttpClient("http://" + host + ":" + port + "/" + uri);
        } catch (TTransportException e) {
            throw new RuntimeException(e);
        }
        httpClient.setConnectTimeout(3000);
        TProtocol protocol = new TJSONProtocol(httpClient);
        return protocol;
    }

    public static void main(String[] args) {
        ThriftClient thriftClient = new ThriftClient();
        TProtocol protocol = thriftClient.getJsonProtocol();
        Calculator.Client client = new Calculator.Client(protocol);
        try {
            client.ping();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
