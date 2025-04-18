package io.wren.main.service.impl;

import io.wren.base.WrenMDL;
import io.wren.main.dto.QueryResultDto;
import org.apache.calcite.sql.parser.SqlParseException;

import java.util.concurrent.CompletableFuture;

public interface PreviewService {
    String dryPlan(WrenMDL mdl, String sql, boolean isModelingOnly) throws SqlParseException;
}
