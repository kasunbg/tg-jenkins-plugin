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
import hudson.model.Describable;
import hudson.model.Descriptor;
import hudson.util.ListBoxModel;
import jenkins.model.Jenkins;
import org.kohsuke.stapler.DataBoundConstructor;

import java.io.IOException;
import java.util.List;

public final class JenkinsInfrastructureConfig implements Describable<JenkinsInfrastructureConfig> {

    @Extension
    public static final DescriptorImpl DESCRIPTOR = new DescriptorImpl();
    public String gitURL;
    public String gitBranch;
    public String file;
    public List<IncludeParameter> includeParameters;
    public List<ExcludeParameter> excludeParameters;
    public List<JenkinsScriptConfig> scriptConfigs;

    @DataBoundConstructor
    public JenkinsInfrastructureConfig(String gitURL, String gitBranch, String file, List<IncludeParameter> includeParameters,
                                       List<ExcludeParameter> excludeParameters, List<JenkinsScriptConfig> scriptConfigs) {
        this.gitURL = gitURL;
        this.gitBranch = gitBranch;
        this.file = file;
        this.includeParameters = includeParameters;
        this.excludeParameters = excludeParameters;
        this.scriptConfigs = scriptConfigs;
    }


    @Override
    public Descriptor<JenkinsInfrastructureConfig> getDescriptor() {
        return DESCRIPTOR;
    }

    public List<JenkinsScriptConfig> getScriptConfigs() {
        return scriptConfigs;
    }

    public List<IncludeParameter> getIncludeParameters() {
        return includeParameters;
    }

    public List<ExcludeParameter> getExcludeParameters() {
        return excludeParameters;
    }

    public static class DescriptorImpl extends Descriptor<JenkinsInfrastructureConfig> {

        @Override
        public String getDisplayName() {
            return "Infrastructure provision configuration";
        }
    }



    public static class IncludeParameter implements Describable<IncludeParameter>{

        public String includeParameter;

        @DataBoundConstructor
        public IncludeParameter(String includeParameter) {
            this.includeParameter = includeParameter;
        }

        @Override
        public Descriptor<IncludeParameter> getDescriptor() {
            return Jenkins.getInstance().getDescriptor(getClass());
        }

        @Extension
        public static class ParameterDescriptorImpl extends Descriptor<IncludeParameter> {}
    }

    public static class ExcludeParameter implements Describable<ExcludeParameter>{

        public String excludeParameter;

        @DataBoundConstructor
        public ExcludeParameter(String excludeParameter) {
            this.excludeParameter = excludeParameter;
        }

        @Override
        public Descriptor<ExcludeParameter> getDescriptor() {
            return Jenkins.getInstance().getDescriptor(getClass());
        }

        @Extension
        public static class ParameterDescriptorImpl extends Descriptor<ExcludeParameter> {}
    }

}
