package com.ass.dingshopping.net.response;


import java.util.List;

/**
 * Created by Administrator on 2017/6/9 0009.
 */

public class RespRulList{
    public RespRulList() {

    }

    public String PageSize;
    public String PageCount;
    public String RecordCount;
    public String PageIndex;
    public String HasNextPage;

    public List<Cars> PageResults;

    public class Cars {
        public String CarNumber;
        public String CarFrameNumber;
        public String CarEngineNumber;
        public String CarType;
        public String CarUsage;
        public String LastUpdateTime;

    }


}
