//package cn.imadc.application.base.samples;
//
//import com.p6spy.engine.common.P6Util;
//import com.p6spy.engine.spy.appender.MessageFormattingStrategy;
//
///**
// * <p>
// *
// * </p>
// *
// * @author 杜劲松
// * @since 2022-08-01
// */
//public class MySingleLineFormat implements MessageFormattingStrategy {
//
//    @Override
//    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {
////        return now + "|" + elapsed + "|" + category + "|connection " + connectionId + "|url " + url + "|" + P6Util.singleLine(prepared) + "|" + P6Util.singleLine(sql);
//        String message = "-------------------------------------SQL execution information-------------------------------------"
//                + "\n"
//                + "sql         :" + P6Util.singleLine(sql)
//                + "\n"
//                + "category    :" + category
//                + "\n"
//                + "connectionId:" + connectionId
//                + "\n"
//                + "took        :" + elapsed;
//        return message;
////        return "|" + elapsed + "|" + category + "|connection " + connectionId + "|" + P6Util.singleLine(sql);
//    }
//}
