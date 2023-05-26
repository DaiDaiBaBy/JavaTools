package com.zhoufu.springbootaop;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootAopApplicationTests {

    @Test
    void contextLoads() {
    }

    public static void main(String[] args) {
        String head = "{\"is_online\":\"0\",\"total_bill_amount\":\"0.00\",\"small_amount\":\"0.00\",\"remark\":\"\",\"receive_no\":\"SK202305240003\",\"order_pay_status\":\"3\",\"receive_amount\":\"50.00\",\"is_outside\":\"0\",\"date\":\"2023-05-24\",\"verification_id\":\"\",\"order_no\":\"\",\"pay_status\":\"1\",\"cm_source_code\":\"K030198\",\"cm_code\":\"K030198\",\"id\":\"5895250678438089779\",\"prepare_write_off_amount\":\"0.00\",\"details\":[],\"pay_type\":\"0\",\"write_off_amount\":\"0.00\",\"refund_status\":\"0\",\"use_prepare\":\"0.00\",\"cm_name\":\"桐乡中都皮草有限公司\",\"total_amount\":\"50.00\",\"emp_code\":\"19967412825\",\"account_form_str\":\"现金\",\"accountDetails\":[{\"amount\":50.00,\"account_form\":10,\"master_id\":5895250678438089779,\"account\":10,\"account_form_name\":\"现金\",\"account_name\":\"现金\"}],\"print_count\":\"\",\"confirm_reason\":\"\",\"total_write_off_amount\":\"0.00\",\"is_enable\":\"1\",\"bill_check_status\":\"0\",\"account_name\":\"现金\",\"emp_source_code\":\"19967412825\",\"exts\":[{\"ext_key\":\"收款单位\",\"ext_value\":\"0007-杭州\"}],\"emp_name\":\"19967412825\",\"receive_type\":\"预收款\",\"add_prepare_amount\":\"50.00\",\"account_str\":\"现金\",\"confirm_status\":\"1\"}";
        JSONObject datajson = JSONObject.parseObject(head);
        String jsonString = datajson.toJSONString();
        int count = processData("msgId","receive","dataId","dataVersion","dataFormat","dataSource", "billcode","",jsonString, "timestamp", "digest","1", "statusTime","tenantId");
    }

    private static int processData(String msgId, String dataType, String dataId, String dataVersion, String dataFormat, String dataSource, String billcode, String billstatus, String data, String timestamp, String digest, String status, String statusTime, String tenantId) {

        StringBuffer str = new StringBuffer();
        str.append(" insert into bd_waiqin_data_log(msgid , datatype , dataId , dataVersion , dataFormat,dataSource,billcode,billstatus,data,data_x,digest,status, statustime,tenantid ,ts) 	");
        str.append("	values('"+msgId+"' , '"+dataType+"', '"+dataId+"', '"+dataVersion+"', '"+dataFormat+"', '"+dataSource+"', '");
        str.append(billcode+"', '");
        str.append(billstatus+"', '");

        str.append(data.substring(0, data.length() > 3000 ? 3000 : data.length())+"', '");
        if(data.length()>3000){
            str.append(data.substring(3000).replace("\"", "\\\"")+"', '");
        }else{
            str.append("', '");
        }

        //		str.append(data.substring(0, data.length() > 3000 ? 3000 : data.length())+"', '");
//		str.append(clob+"', '");
//		if(data.length()>3000){
//			str.append(data.substring(3000).replace("\"", "\\\"")+"', '");
//		}else{
//			str.append("', '");
//		}

        str.append(digest+"', '"+status+"' , '"+statusTime+"', '"+tenantId+"' , to_char(sysdate,'YYYY-MM-DD HH24:MI:SS') )");
//		int count = getBaseDAO().executeUpdate(str.toString());
//		return count;
        String toString = str.toString();
        System.out.println(toString);
        return 0;
    }


}
