#Google Plugin for Eclipse (GPE)
The Google Plugin for Eclipse repository. 

##Repository manifest 
* [common/](common/) - `com.google.gdt.googleapi.core` common library.
* [eclipse/](eclipse/) - Eclipse settings resources such as code style imports.
* [features/](features/) - Eclipse features for plugin by Eclipse platform version.
* [plugins/](plugins/) - Eclipse plugins.
* [tools/](tools/) - Third party tools to for working on this project.


##Configuring the Eclipse Workspace
This file contains instructions on how to setup the Eclipse environment to
work with the source code for the Google Plugin for Eclipse.

Configuring the Eclipse workspace to develop on the Google Eclipse plugin is very
much like getting set up to develop for GWT. Most of the same style rules apply, and
Eclipse projects are provided for import. The "settings" directory
mentioned below is under "eclipse", in your checkout of plugin.

* *Macintosh users*: Note that on the Macintosh version of Eclipse, "Preferences"
is under the "Eclipse" menu, not under "Window".

Default Eclipse settings will give you out of memory errors in many test and compile steps.
Modify the eclipse.ini file in your root Eclipse directory in the following ways:

* Change -Xmx512m to at least -Xmx1024m

##Environment Variables
Update your .bashrc (or equivalent) file with the following Environment Variables.
For Eclipse to pick up these variables, it may be required to run it from a terminal
window instead of from the launcher.

* export GWT_TOOLS=```<local path>/gwt/tools```
* export GWT_ROOT=```<local path>/gwt/trunk```
* export GWT_VERSION=```<version number>``` i.e. 2.6.1
* export GWT_HOME=```<path to GWT SDK>``` i.e. /opt/gwt-2.6.1
* export GAE_HOME=```<path to GAE SDK>``` i.e. /opt/appengine-java-sdk-1.9.6
* export JDK_HOME=```<path to JDK>``` i.e. /usr/lib/jvm/java-7-oracle
* export JAVA_HOME=```<path to JDK>``` i.e. /usr/lib/jvm/java-7-oracle

##Eclipse Plug-in Development Settings

* Open Window->Preferences->Plug-in Development
* Verify that the Workspace location defaults are appropriate
* For new Eclipse Applications, ```${workspace_loc}/../runtime-``` with "Append
launch configuration name" should work.
* For JUnit Plug-in Tests, ```${workspace_loc}/../junit-workspace``` with
"Use as workspace location" should work.
* Select Plug-in Development->API Baselines and verify that you have a default baseline.
* If you do not, select "Add Baseline..." and select the location of the Eclipse installation
that you want to be used in your launches. Give it an appropriate name.

Some projects will not compile without an Eclipse API Baseline defined.

##Dependent Plugins

* Restart Eclipse to detect the new plugins.

###All Eclipse versions

* The `com.google.gdt.eclipse.maven` plugin requires m2e (Maven Integration for Eclipse) to be installed. 
Install m2e from your Eclipse version's default update site. i.e. http://download.eclipse.org/releases/kepler
* The `com.google.gdt.eclipse.gph.hge` project requires MercurialEclipse to be 
installed. You can either close the project, or install MercurialEclipse: http://cbes.javaforge.com/update
* The `com.google.gdt.eclipse.gph.subclipse` project requires Subclipse to be 
installed. You can either close the project, or install Subclipse: http://subclipse.tigris.org/update_1.6.x
* The `com.google.gdt.eclipse.gph.subversive` project requires Subversive to 
be installed. You can either close the project, or install Subversive: http://download.eclipse.org/releases/helios

###Eclipse 3.3, 3.4

* Copy the plugin JARs from `tools/swtbot/3.3` into your Eclipse's `/dropins` 
directory (on 3.4) or `/plugins` directory (on 3.3).

###Eclipse 3.5

* Copy the plugin JARs from `tools/swtbot/3.5` into your Eclipse's `/dropins` 
directory.

###Eclipse 3.7

####Mylyn

1. Go to Help->Install New Software
2. From the drop down list, select Google Internal for Eclipse 3.7
* If you are not using the internal version of Eclipse, install 
Mylyn Commons from the Indigo Update site.
3. Uncheck Group Items by Category
4. Install Mylyn Commons

###Eclipse 4.3
For Eclipse version 4.3 (Kepler).

####SWTBot
Install SWTBot for automated testing.

1. Goto [SWTBot for testing Eclipse Applications](http://www.vogella.com/tutorials/SWTBot/article.html)
2. Goto Install new sofware: http://download.eclipse.org/technology/swtbot/releases/latest/
3. Select all and install. Sources are optional.

####Dali
Install Dali for the JPA persistence plugins.

1. Goto Install New Software.
2. Filter `Dali`.
3. Select all the `Dali Java Persistence Tools - *` and install them.


##Formatting Preferences

###Text Editors
1. Window->Preferences->General->Editors->Text Editors
2. Make sure that "Displayed Tab Width" is set to 2
3. Enable "Insert Spaces for Tabs"
4. Enable "Show Print Margin" and set "Print Margin Column" to 80

###XML Files
1. Window->Preferences->Web and XML->XML Files->Source, 
(or Window->Preferences->XML->XML Files->Editor, if you can't find it there)
2. Set "Line Width" 80
3. Enable "Split Multiple Attributes Each of a New Line"
4. Enable "Indent Using Spaces" with an Indentation Size of 4

###Ant Build Files
1. Window->Preferences->Ant->Editor->Formatter
2. Set "Tab Size" to 4
3. Disable "Use Tabs Instead of Spaces"
4. Set "Maximum Line Width" to 80
5. Enable "Wrap Long Element Tags"

###Spelling
1. Window->Preferences->General->Editors->Text Editors->Spelling
2. Enable spell checking
3. Use "settings/english.dictionary".


##Project Preferences

###Output Filtering
1. Window->Preferences->Java->Compiler->Building
2. Make sure "Filtered Resources" includes ".svn/"

###Code Templates
1. Window->Preferences->Java->Code Style->Code Templates
2. Comments->Files template should look like this:

   ```
   /*
   * Copyright ${year} Google Inc.
   * 
   * Licensed under the Apache License, Version 2.0 (the "License"); you may not
   * use this file except in compliance with the License. You may obtain a copy of
   * the License at
   * 
   * http://www.apache.org/licenses/LICENSE-2.0
   * 
   * Unless required by applicable law or agreed to in writing, software
   * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
   * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
   * License for the specific language governing permissions and limitations under
   * the License.
   */
   ```

3. Comments->Types template should look like this:

   ```
   /**
    *
    * ${tags}
    */
   ```

###Save Actions
1. Window->Preferences->Java->Editor->Save Actions
2. Enable "Perform the Selected Actions on Save"
3. Enable "Format Source Code"
4. Enable "Organize Imports"
5. Enable "Additional Actions"
6. Click "Configure", and make sure that all actions are disabled except "Sort 
Members" and "Ignore fields and enum constants"

##Code style/formatting
1. Window->Preferences->Java->Code Style->Formatter->Import...
2. Import `settings/code-style/gwt-format.xml`

##Import organization
1. Window->Preferences->Java->Code Style->Organize Imports->Import...
2. Import `settings/code-style/gwt.importorder`

##Member sort order
1. Window->Preferences->Java->Appearance->Members Sort Order
2. There is no import here, so make your settings match:
<img src="eclipse/settings/code-style/gwt-sort-order.png" />

###Sort members
First, members should be sorted by category.

1. `Types`
2. `Static Fields`
3. `Static Initializers`
4. `Static Methods`
5. `Fields`
6. `Initializers`
7. `Constructors`
8. `Methods`

###Sort visibility
Second, members in the same category should be sorted by visibility.

1. `Public`
2. `Protected`
3. `Default`
4. `Private`

* Third, within a category/visibility combination, members should be sorted
alphabetically.


##Checkstyle
Checkstyle is used to enforce good programming style.
Currently Checkstyle configuring is being revisted and the new policies are TBA.
The GWT team upgraded to Checkstyle 5.7 and modified their policies recently.
Do not try to enable the Checkstyle plugin at this time as the code base will
fail about 700+ checks.

##Dependent Projects
The Google Plugin for Eclipse depends on the gwt-dev and gwt-user projects
which must imported into your workspace and successfully building.

See the GWT [README.txt](https://gwt.googlesource.com/gwt/+/master/eclipse/README.txt) page for more details.

##Importing the Google Plugin projects

Having set up your workspace appropriately, you can now import the appropriate
projects.

File -> Import -> General -> Existing Projects into Workspace

Select your checkout of the trunk of google-plugin-for-eclipse to see all of the
Eclipse projects for you to import. You should only import the projects that
correspond to the version of Eclipse that you are using for development and
the platform you are running on. 

For example, if you have Eclipse 4.3, do not import a project that has "e33" in its name.

##Launching the Plugin

* Once your projects have been imported, go to the Package Explorer and
right-click on the "`com.google.gdt.eclipse.suite`" project. 
* Go to "Run As" -> "Eclipse Application".
* Another instance of Eclipse should launch, running the GPE!

##Running JUnit Tests
The following packages are tested by Run As->JUnit Test:

* com.google.gdt.eclipse.drive.test
* com.google.gdt.googleapi.core.test
* com.google.gwt.eclipse.oophm.test

##Running SWTBot Unit Tests
The following packages are tested by Run As->SWTBot Test:

* com.google.appengine.eclipse.core.test.swtbot
* com.google.gdt.eclipse.login.test.swtbot
* com.google.gdt.eclipse.suite.test.swtbot
* com.google.gwt.eclipse.core.test.swtbot

Running SWTBot unit tests individually can take more memory.

Use larger memory configurations
* Debug Configurations -> Arguments -> Vm Arguments > `-Xmx1024M -XX:MaxPermSize=128m`

##Running JUnit Plug-in Tests
The following packages are tested by Run As->JUnit Plug-in Test:

* com.google.appengine.eclipse.core.test
* com.google.gdt.eclipse.core.test
* com.google.gdt.eclipse.suite.test
* com.google.gwt.eclipse.core.test

