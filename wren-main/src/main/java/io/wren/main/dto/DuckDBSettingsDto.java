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

import com.fasterxml.jackson.annotation.JsonProperty;

public class DuckDBSettingsDto
{
    private String initSQL;

    private String sessionSQL;

    @JsonProperty
    public String getInitSQL()
    {
        return initSQL;
    }

    public void setInitSQL(String initSQL)
    {
        this.initSQL = initSQL;
    }

    @JsonProperty
    public String getSessionSQL()
    {
        return sessionSQL;
    }

    public void setSessionSQL(String sessionSQL)
    {
        this.sessionSQL = sessionSQL;
    }
}
