/*
 * Copyright (c) 2018, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.wso2.testgrid.jenkins;

import hudson.Extension;
import hudson.model.Job;
import hudson.model.JobProperty;
import hudson.model.JobPropertyDescriptor;
import net.sf.json.JSONObject;
import org.jenkinsci.Symbol;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.StaplerRequest;

import java.util.List;
import javax.annotation.Nonnull;

public final class TestgridJobProperty extends JobProperty<Job<?, ?>> {

    private final List<JenkinsInfrastructureConfig> infrastructureConfigs;


    private final String testgridYaml;

    @DataBoundConstructor
    public TestgridJobProperty(List<JenkinsInfrastructureConfig> infrastructureConfigs) {
        this.infrastructureConfigs = infrastructureConfigs;

        // PoC testgrid yaml content. This is not the actual format.
        StringBuilder testgridYamlSB = new StringBuilder("infrastructureConfig:\n");
        testgridYamlSB.append("  provisioners:\n");
        for (JenkinsInfrastructureConfig infrastructureConfig : infrastructureConfigs) {
            testgridYamlSB.append("    - repository: ").append(infrastructureConfig.gitURL).append('\n');
            testgridYamlSB.append("      repositoryBranch: ").append(infrastructureConfig.gitBranch).append('\n');
            testgridYamlSB.append("      file: ").append(infrastructureConfig.file).append('\n');
//            testgridYamlSB.append("      type: ").append(infrastructureConfig.iacProvider).append('\n');
        }
        this.testgridYaml = testgridYamlSB.toString();
    }

    public List<JenkinsInfrastructureConfig> getInfrastructureConfigs() {
        return infrastructureConfigs;
    }

    public String getTestgridYaml() {
        return testgridYaml;
    }

    @Symbol("testgrid")
    @Extension
    public static class PropertyImpl extends JobPropertyDescriptor {

        @Override
        public boolean isApplicable(Class<? extends Job> jobType) {
            //TODO applicable only in pipeline projects
            return true;
        }

        @Override
        public JobProperty<?> newInstance(StaplerRequest req, JSONObject formData) throws FormException {
            return super.newInstance(req, formData);
        }

        @Nonnull
        @Override
        public String getDisplayName() {
            return "TestGrid";
        }
    }

}
