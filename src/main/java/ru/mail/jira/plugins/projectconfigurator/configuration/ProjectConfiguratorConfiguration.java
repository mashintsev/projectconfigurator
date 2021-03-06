package ru.mail.jira.plugins.projectconfigurator.configuration;

import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.fields.screen.FieldScreenScheme;
import com.atlassian.jira.issue.issuetype.IssueType;
import com.atlassian.jira.notification.NotificationScheme;
import com.atlassian.jira.permission.PermissionScheme;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.user.ApplicationUser;
import com.atlassian.jira.web.action.JiraWebActionSupport;
import com.atlassian.jira.workflow.JiraWorkflow;
import java.util.List;

public class ProjectConfiguratorConfiguration extends JiraWebActionSupport {

  private final PluginData pluginData;
  private final ProjectConfiguratorManager projectConfiguratorManager;
  private List<String> workflowNames;
  private List<String> issueTypeIds;
  private List<String> screenSchemeIds;
  private List<String> permissionSchemeIds;
  private List<String> notificationSchemeIds;
  private String adminUserKey;
  private String projectId;
  private String issueTypeId;
  private String projectConfigurationCfId;

  public ProjectConfiguratorConfiguration(
      PluginData pluginData, ProjectConfiguratorManager projectConfiguratorManager) {
    this.pluginData = pluginData;
    this.projectConfiguratorManager = projectConfiguratorManager;
  }

  @Override
  public String doExecute() {
    workflowNames = pluginData.getWorkflowNames();
    issueTypeIds = pluginData.getIssueTypeIds();
    screenSchemeIds = pluginData.getScreenSchemeIds();
    permissionSchemeIds = pluginData.getPermissionSchemeIds();
    notificationSchemeIds = pluginData.getNotificationSchemeIds();
    adminUserKey = pluginData.getAdminUserKey();

    projectId = pluginData.getProjectId();
    issueTypeId = pluginData.getIssueTypeId();
    projectConfigurationCfId = pluginData.getProjectConfigurationCfId();

    return SUCCESS;
  }

  @SuppressWarnings("unused")
  public List<JiraWorkflow> getWorkflows() {
    return projectConfiguratorManager.getWorkflows(this.workflowNames);
  }

  @SuppressWarnings("unused")
  public List<IssueType> getIssueTypes() {
    return projectConfiguratorManager.getIssueTypes(this.issueTypeIds);
  }

  @SuppressWarnings("unused")
  public List<FieldScreenScheme> getScreenSchemes() {
    return projectConfiguratorManager.getScreenSchemes(this.screenSchemeIds);
  }

  @SuppressWarnings("unused")
  public List<PermissionScheme> getPermissionSchemes() {
    return projectConfiguratorManager.getPermissionSchemes(this.permissionSchemeIds);
  }

  @SuppressWarnings("unused")
  public List<NotificationScheme> getNotificationSchemes() {
    return projectConfiguratorManager.getNotificationSchemes(this.notificationSchemeIds);
  }

  @SuppressWarnings("unused")
  public ApplicationUser getAdminUser() {
    return projectConfiguratorManager.getUser(this.adminUserKey);
  }

  @SuppressWarnings("unused")
  public String getContextPath() {
    return getHttpRequest().getContextPath();
  }

  @SuppressWarnings("unused")
  public Project getProject() {
    return projectConfiguratorManager.getProject(this.projectId);
  }

  @SuppressWarnings("unused")
  public IssueType getIssueType() {
    return projectConfiguratorManager.getIssueType(this.issueTypeId);
  }

  @SuppressWarnings("unused")
  public CustomField getProjectConfigurationCf() {
    return projectConfiguratorManager.getCustomField(this.projectConfigurationCfId);
  }
}
