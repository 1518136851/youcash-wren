package io.wren.main.service.sql;

import org.apache.calcite.avatica.util.Casing;
import org.apache.calcite.config.NullCollation;
import org.apache.calcite.rel.type.RelDataTypeSystemImpl;
import org.apache.calcite.sql.SqlDialect;
import org.apache.calcite.sql.SqlNode;
import org.apache.calcite.sql.parser.SqlParseException;
import org.apache.calcite.sql.parser.SqlParser;
import org.apache.calcite.sql.validate.SqlConformanceEnum;
import org.springframework.stereotype.Service;

@Service
public class MysqlConverter implements SqlConverter{
    @Override
    public String convert(String sql) throws SqlParseException {
        SqlParser.Config config = SqlParser.config();
        // 创建解析器
        SqlParser parser = SqlParser.create(sql, config);
        SqlNode sqlNode = parser.parseStmt();
        SqlDialect.Context context = SqlDialect.EMPTY_CONTEXT
                .withDatabaseProduct(SqlDialect.DatabaseProduct.MYSQL)
                .withNullCollation(NullCollation.HIGH)
                .withUnquotedCasing(Casing.TO_UPPER)
                .withQuotedCasing(Casing.UNCHANGED)
                .withCaseSensitive(false)
                .withDataTypeSystem(RelDataTypeSystemImpl.DEFAULT)
                .withConformance(SqlConformanceEnum.DEFAULT);
        // 创建 SqlDialect 实例
        SqlDialect sqlDialect = new SqlDialect(context);
        return sqlNode.toSqlString(sqlDialect).getSql();
    }
}
