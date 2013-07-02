/*******************************************************************************
 * Copyright 2013 Google Inc. All Rights Reserved.
 *
 * All rights reserved. This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *******************************************************************************/
package com.google.appengine.eclipse.wtp.wizards;

import com.google.appengine.eclipse.core.properties.ui.DeployComponent;
import com.google.appengine.eclipse.wtp.AppEnginePlugin;
import com.google.appengine.eclipse.wtp.facet.IGaeFacetConstants;
import com.google.appengine.eclipse.wtp.utils.ProjectUtils;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wst.common.project.facet.ui.IFacetWizardPage;

/**
 * A {@link IFacetWizardPage} for configuring GAE Facet.
 */
@SuppressWarnings("restriction")
public final class GaeFacetWizardPage extends GaeFacetAbstractWizardPage {

  private static final String WIZARD_PAGE_NAME = IGaeFacetConstants.GAE_FACET_ID + ".install.page";
  private DeployComponent deployComponent = new DeployComponent();
  private Button shouldCreateSampleButton;
  private Text packageText;

  /**
   * Default ctor
   */
  public GaeFacetWizardPage() {
    super(WIZARD_PAGE_NAME);
  }

  @Override
  protected void addModificationListeners() {
    super.addModificationListeners();
    synchHelper.synchText(deployComponent.getAppIdTextControl(), GAE_PROPERTY_APP_ID, null);
    synchHelper.synchText(deployComponent.getVersionTextControl(), GAE_PROPERTY_APP_VERSION, null);
    synchHelper.synchText(packageText, GAE_PROPERTY_PACKAGE, null);
    deployComponent.setModifyListener(new ModifyListener() {
      @Override
      public void modifyText(ModifyEvent e) {
        // to make it check for app id
        deployComponent.setEnabled(true);
      }
    });
    synchHelper.synchCheckbox(shouldCreateSampleButton, GAE_PROPERTY_CREATE_SAMPLE, null);
  }

  @Override
  protected Composite createTopLevelComposite(Composite parent) {
    initializeDialogUnits(parent);
    Composite composite = new Composite(parent, SWT.NONE);
    composite.setLayout(new GridLayout());
    {
      Group projectGroup = new Group(composite, SWT.NONE);
      projectGroup.setText("Project");
      projectGroup.setLayout(new GridLayout(2, false));
      projectGroup.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
      Label packageLabel = new Label(projectGroup, SWT.NONE);
      packageLabel.setText("Package:");
      packageText = new Text(projectGroup, SWT.BORDER);
      packageText.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
    }
    {
      deployComponent.createContents(composite);
    }
    {
      createDeployOptionsComponent(composite);
    }
    {
      Group sampleGroup = new Group(composite, SWT.NONE);
      sampleGroup.setText("Sample Code");
      sampleGroup.setLayout(new GridLayout());
      sampleGroup.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
      shouldCreateSampleButton = new Button(sampleGroup, SWT.CHECK | SWT.LEFT);
      shouldCreateSampleButton.setText("Generate project sample code");
    }
    addModificationListeners();
    return composite;
  }

  @Override
  protected String[] getValidationPropertyNames() {
    return new String[] {GAE_PROPERTY_APP_ID, GAE_PROPERTY_APP_VERSION, GAE_PROPERTY_PACKAGE};
  }

  @Override
  protected void restoreDefaultSettings() {
    super.restoreDefaultSettings();
    IProject project = ProjectUtils.getProject(model);
    if (project != null) {
      try {
        model.setStringProperty(GAE_PROPERTY_APP_ID, ProjectUtils.getAppId(project));
        model.setStringProperty(GAE_PROPERTY_APP_VERSION, ProjectUtils.getAppVersion(project));
      } catch (CoreException e) {
        AppEnginePlugin.logMessage(e);
      }
    }
    model.setBooleanProperty(GAE_PROPERTY_CREATE_SAMPLE,
        (Boolean) model.getDefaultProperty(GAE_PROPERTY_CREATE_SAMPLE));
    synchHelper.synchAllUIWithModel();
  }
}
