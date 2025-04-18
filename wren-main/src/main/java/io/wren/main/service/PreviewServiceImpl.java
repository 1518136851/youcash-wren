/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package io.wren.main.service;

import io.wren.base.AnalyzedMDL;
import io.wren.base.SessionContext;
import io.wren.base.WrenMDL;
import io.wren.base.sqlrewrite.WrenPlanner;
import io.wren.main.service.impl.PreviewService;
import io.wren.main.service.sql.SqlConverter;
import org.apache.calcite.sql.parser.SqlParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PreviewServiceImpl implements PreviewService
{

    @Autowired
    @Qualifier("mysqlConverter")
    private SqlConverter sqlConverter;

    @Value("${youcash.experimental.enable.dynamic.fields}")
    private Boolean enableDynamicFields;


    @Autowired
    public String dryPlan(WrenMDL mdl, String sql, boolean isModelingOnly) throws SqlParseException {

        // 构建会话上下文
        SessionContext sessionContext = SessionContext.builder()
                .setCatalog(mdl.getCatalog())
                .setSchema(mdl.getSchema())
                .setEnableDynamic(enableDynamicFields)
                .build();

        // 重写 SQL
        String planned = WrenPlanner.rewrite(sql, sessionContext, new AnalyzedMDL(mdl, null));

        if (isModelingOnly) {

//            log.info("Planned SQL: {}", planned);
            return planned;
        }

        // 转换 SQL
        return sqlConverter.convert(planned);
    }

}
