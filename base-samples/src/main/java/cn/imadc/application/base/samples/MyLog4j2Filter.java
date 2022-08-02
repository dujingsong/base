//package cn.imadc.application.base.samples;
//
//import com.alibaba.druid.filter.FilterEventAdapter;
//import com.alibaba.druid.proxy.jdbc.ResultSetProxy;
//import com.alibaba.druid.proxy.jdbc.StatementProxy;
//import org.springframework.stereotype.Component;
//
///**
// * <p>
// *
// * </p>
// *
// * @author 杜劲松
// * @since 2022-08-01
// */
//@Component
//public class MyLog4j2Filter extends FilterEventAdapter {
//    /**
//     * insert update delete 执行后进入
//     *
//     * @param statement
//     * @param sql
//     * @param updateCount
//     */
//    @Override
//    protected void statementExecuteUpdateAfter(StatementProxy statement, String sql, int updateCount) {
//        System.out.println(sql);
//        super.statementExecuteUpdateAfter(statement, sql, updateCount);
//    }
//
//    /**
//     * select 执行后进入
//     *
//     * @param statement
//     * @param sql
//     * @param resultSet
//     */
//    @Override
//    protected void statementExecuteQueryAfter(StatementProxy statement, String sql, ResultSetProxy resultSet) {
//        System.out.println(sql);
//        super.statementExecuteQueryAfter(statement, sql, resultSet);
//    }
//
//    /**
//     * sql 执行错误后进入
//     *
//     * @param statement
//     * @param sql
//     * @param error
//     */
//    @Override
//    protected void statement_executeErrorAfter(StatementProxy statement, String sql, Throwable error) {
//        System.out.println(sql);
//        super.statement_executeErrorAfter(statement, sql, error);
//    }
//
//
//}
