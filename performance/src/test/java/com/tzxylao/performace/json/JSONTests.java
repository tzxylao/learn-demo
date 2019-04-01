package com.tzxylao.performace.json;

import com.alibaba.fastjson.JSON;
import com.dslplatform.json.DslJson;
import com.jsoniter.JsonIterator;
import com.jsoniter.output.JsonStream;
import com.tzxylao.performace.report.RequestReport;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

/**
 * @author laoliangliang
 * @date 2019/3/22 16:10
 */
@Slf4j
public class JSONTests {

    private final DslJson<Object> dslJson = new DslJson<>();
    private final String JSON_STR = "{\"body\":{\"credit_points\":100,\"hire_flag\":0},\"header\":{\"re_code\":\"0000\",\"re_msg\":\"查询成功\",\"request_seq\":\"sn2019032201052861800000000000000000005\",\"sign\":\"1dbeb7e6235b4a054830202d04f6b006\"}}";

    @Test
    public void testDsljson() throws IOException {

        RequestReport map = JSON.parseObject(JSON_STR, RequestReport.class);
        log.info("credit_points:{}",map.getHeader().get("re_msg"));
        String s = JSON.toJSONString(map);
        log.info("序列化对象:{}",s);
        log.info("size:{}",s.getBytes().length);

        long start = System.nanoTime();
        Map<String, Object> reqParams = (Map<String, Object>) map.getBody();
        log.info("hire_flag:{}",reqParams.get("hire_flag"));

        long end = System.nanoTime();
        log.info("fastjson  time:{}ns",(end-start));

        RequestReport requestReport = JsonIterator.deserialize(JSON_STR,RequestReport.class);
        log.info("credit_points:{}",requestReport.getHeader().get("re_msg"));
        String serialize = JsonStream.serialize(requestReport);
        log.info("序列化对象:{}",serialize);
        log.info("size:{}",serialize.getBytes().length);
        requestReport = JsonIterator.deserialize(serialize,RequestReport.class);
        log.info("credit_points:{}",requestReport.getHeader().get("re_msg"));

        long start2 = System.nanoTime();
        Map<String,Object> deserialize = (Map<String, Object>) requestReport.getBody();
        log.info("hire_flag:{}",deserialize.get("hire_flag"));

        long end2 = System.nanoTime();
        log.info("jsoniter  time:{}ns",(end2-start2));

       /* byte[] buff = JSON_STR.getBytes("UTF-8");
//        JsonReader<RequestReport2> requestReportJsonReader = dslJson.newReader(buff);
//        RequestReport2 context = requestReportJsonReader.next(RequestReport2.class);
        RequestReport2 context = dslJson.deserialize(RequestReport2.class, buff, buff.length);
        log.info("credit_points:{}",context.getBody().get("credit_points"));
        long end3 = System.currentTimeMillis();
        log.info("dls-json  time:{}ms",(end3-end2));*/

    }
}
