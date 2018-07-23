package com.hhzh.strategy;

import com.hhzh.service.IFutureTradeService;
import org.apache.http.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class XRPStrategy {

    @Autowired
    private IFutureTradeService futureTradeService;

    public void executeStrategy() throws IOException, HttpException {
        String userInfo = futureTradeService.futureUserInfo();
    }

}
