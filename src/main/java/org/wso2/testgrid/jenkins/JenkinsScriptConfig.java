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
import hudson.util.ListBoxModel;
import org.kohsuke.stapler.DataBoundConstructor;

import javax.annotation.Nonnull;
import java.io.IOException;

public class JenkinsScriptConfig implements Describable<JenkinsScriptConfig>{

    @Extension
    public static final DescriptorImpl DESCRIPTOR = new DescriptorImpl();
    public String name;
    public String repository;
    public String description;
    public String parameters;
    public String iacProvider;

    @DataBoundConstructor
    public JenkinsScriptConfig(String name, String repository, String description, String iacProvider, String parameters) {
        this.name = name;
        this.repository = repository;
        this.description = description;
        this.iacProvider = iacProvider;
        this.parameters = parameters;
    }

    @Override
    public Descriptor<JenkinsScriptConfig> getDescriptor() {
        return DESCRIPTOR;
    }


    public static class DescriptorImpl extends Descriptor<JenkinsScriptConfig>{

        public ListBoxModel doFillIacProviderItems() throws IOException {
            ListBoxModel model = new ListBoxModel();
            model.add(new ListBoxModel.Option("CLOUDFORMATION"));
            model.add(new ListBoxModel.Option("SHELL"));
            return model;
        }

        @Nonnull
        @Override
        public String getDisplayName() {
            return "Scripts";
        }
    }
}
