package com.wosummer.generator;

import lombok.extern.slf4j.Slf4j;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Task;

/**
 *
 * @author yunqisong
 * @date 2018/9/29
 * FOR: TODO:
 */

@Slf4j
@SuppressWarnings("ALL")
public class EntityXmlMapperPlugin implements Plugin<Project> {

  @Override
  public void apply(Project project) {
    // 加载EditorConfig插件
    project.getPlugins().apply("org.ec4j.editorconfig");
    Task efTask = project.getTasks().findByName("editorconfigFormat");

    // 加载生成文件Task
    Task justGennerator = project.getTasks().create("justGennerator", GenneratorTask.class);

    efTask.mustRunAfter(justGennerator);

    // 创建并生成文件
    project.task("genneratorAndFormat").dependsOn(efTask, justGennerator);

  }
}

