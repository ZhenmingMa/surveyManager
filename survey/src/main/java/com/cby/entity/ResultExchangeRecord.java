package com.cby.entity;

/**
 * Created by Ma on 2017/7/4.
 */
public class ResultExchangeRecord {
    private ExchangeRecord exchangeRecord;
    private Goods goods;

    public ResultExchangeRecord() {
    }

    public ExchangeRecord getExchangeRecord() {
        return exchangeRecord;
    }

    public void setExchangeRecord(ExchangeRecord exchangeRecord) {
        this.exchangeRecord = exchangeRecord;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }
}
