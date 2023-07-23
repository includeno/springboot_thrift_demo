package com.example.service;

import com.example.thrift.shared.SharedStruct;
import com.example.thrift.tutorial.InvalidOperation;
import com.example.thrift.tutorial.Work;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CalculatorService implements com.example.thrift.tutorial.Calculator.Iface {
    @Override
    public SharedStruct getStruct(int key) throws TException {
        return new SharedStruct(key, "test");
    }

    @Override
    public void ping() throws TException {
        log.info("ping");
    }

    @Override
    public int add(int num1, int num2) throws TException {
        log.info("add:" + num1 + "+" + num2);
        return num1 + num2;
    }

    @Override
    public int calculate(int logid, Work w) throws InvalidOperation, TException {
        log.info("calculate:" + logid + ", " + w);
        return 0;
    }

    @Override
    public void zip() throws TException {
        log.info("zip");
    }
}
