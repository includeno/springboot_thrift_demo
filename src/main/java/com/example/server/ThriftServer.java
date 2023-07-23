package com.example.server;

import javax.servlet.Servlet;

import com.example.config.Consts;
import com.example.service.CalculatorService;
import com.example.thrift.tutorial.Calculator;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.TServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ThriftServer {
    public static final String uri = Consts.uri;
    public static final int port = Consts.port;

    public static void main(String[] args) {
        //设置端口
        System.getProperties().put("server.port", port);
        SpringApplication.run(ThriftServer.class, args);
    }

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        TProcessor processor = new Calculator.Processor(new CalculatorService());
        TProtocolFactory protoFactory = new TJSONProtocol.Factory();
        Servlet calculatorServlet = new TServlet(processor, protoFactory);
        return new ServletRegistrationBean(calculatorServlet, "/" + uri + "/*");
    }
}
