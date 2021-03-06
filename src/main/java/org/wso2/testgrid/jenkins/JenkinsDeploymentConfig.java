/*
* Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
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
* KIND, either express or implied. See the License for the
* specific language governing permissions and limitations
* under the License.
*/
package org.wso2.testgrid.jenkins;

import hudson.Extension;
import hudson.model.Describable;
import hudson.model.Descriptor;
import org.kohsuke.stapler.DataBoundConstructor;

import javax.annotation.Nonnull;

public class JenkinsDeploymentConfig implements Describable<JenkinsDeploymentConfig>{

    private String repoUrl;

    @DataBoundConstructor
    public JenkinsDeploymentConfig(String repoUrl) {
        this.repoUrl = repoUrl;
    }

    @Override
    public Descriptor<JenkinsDeploymentConfig> getDescriptor() {
        return DESCRIPTOR;
    }

//    @Extension
    public static final DescriptorImpl DESCRIPTOR = new DescriptorImpl();

    public static class DescriptorImpl extends Descriptor<JenkinsDeploymentConfig>{

        @Nonnull
        @Override
        public String getDisplayName() {
            return "DeploymentConfig";
        }
    }
}
