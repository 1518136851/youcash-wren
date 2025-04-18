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

package io.wren.main.controller;

import io.wren.base.WrenMDL;
import io.wren.main.enums.ResponseCodeEnum;
import io.wren.main.dto.DryPlanDtoV2;
import io.wren.main.service.impl.PreviewService;
import io.wren.main.vo.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Base64;
import java.util.Optional;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Objects.requireNonNull;

@RestController
@RequestMapping("/v2/mdl")
@Slf4j
public class MDLResourceController {

    @Autowired
    private PreviewService previewService;

    @GetMapping("/dry-plan")
    public ApiResult dryPlan(@Validated @RequestBody DryPlanDtoV2 dryPlanDto)
    {
        try {
            log.info("请求入参为:{}",dryPlanDto);
            // 检查 manifestStr 是否为空
            String manifestStr = Optional.ofNullable(dryPlanDto.getManifestStr())
                    .orElseThrow(() -> new IllegalArgumentException("Manifest is required"));

            String mdlStr = new String(Base64.getDecoder().decode(manifestStr), UTF_8);
            log.info("解码后的mdl字符串为:{}",mdlStr);
            // 对 Base64 编码的 manifestStr 进行解码并解析为 WrenMDL 对象
            WrenMDL mdl = WrenMDL.fromJson(mdlStr);

            // 调用 previewService 的 dryPlan 方法进行处理
            String dialect = previewService.dryPlan(mdl, dryPlanDto.getSql(), true);

            log.info("响应数据为:{}",dialect);
            // 返回成功响应
            return ApiResult.success(ResponseCodeEnum.SUCCESS,dialect);
        } catch (Exception e) {
            log.error("处理异常:{}",e.getMessage(),e);
            // 处理异常
            return ApiResult.fail(ResponseCodeEnum.ERROR);
        }
    }
}
