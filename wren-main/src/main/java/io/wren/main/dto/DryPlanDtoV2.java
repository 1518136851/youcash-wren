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

package io.wren.main.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class DryPlanDtoV2
{
    @NotNull(message = "mdl不允许为空")
    private  String manifestStr;
    @NotNull(message = "标准sql不允许为空")
    private  String sql;
    @NotNull(message = "请求id不允许为空")
    private String requestId;
    @NotNull(message = "查询id不允许为空")
    private String queryId;
    @NotNull(message = "系统id不允许为空")
    private String systemId;

}
