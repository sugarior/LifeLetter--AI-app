# README.md

## 运行指南

在 Android Studio 中运行本项目前，请确保已安装较新版本的 Android Studio，并配置好 Java 和 Kotlin 开发环境。同时，需将项目 SDK 路径更改为本地路径。编译完成后，可于 `release` 文件夹中获取可执行的 APK 文件。

## APP 使用说明

  1. 在安卓设备上下载并安装 APK 文件（在release中），打开应用后将进入登录界面，点击登录按钮即可跳转至课表界面。
  2. 在课表界面点击记事本图标，可进入待办事项界面；点击添加按钮，进入添加事项界面。
  3. 将包含时间、地点和事件信息的文字粘贴至指定位置，点击确认键等待跳转。若操作成功，将跳转至记事本界面并显示所添加事项；若添加失败，请检查是否已授予本应用访问 Wi-Fi 或移动数据的权限。
  4. 点击日程可对其进行修改或删除操作。

**注意事项：**
若应用无法正常运行，可能是由于 API 密钥过期所致。可参考 “工程文件详解” 中的说明，前往 [Kimi Moonshot AI 平台](https://platform.moonshot.cn/console/context-caching)申请新的密钥并替换原有密钥。

**当前存在的问题：**
(1) 极小概率会出现 AI 调用超时，此时将返回空日程，重试即可解决。
(2) 对包含多个事项的文字段分析能力较弱。
(3) 对过短输入的响应能力有待提升。

## 工程文件详解

  1. **主要 UI 界面** ：采用 Activity 与单独 xml 文件相结合的方式，每个 Activity 对应一个 Java 文件和一个 xml 文件。
     * `MainActivity` ：主界面，即应用启动时显示的登录界面。
     * `LessonActivity` ：实现课表界面布局，并负责处理向记事本及记事本添加页面的跳转逻辑。
     * `AskActivity` ：提供向 AI 输入文本的界面布局。
     * `TodoActivity` ：实现记事本界面，展示数据库中的内容。
     * `UpdateActivity` ：用于修改记事本中的日程信息。
     * `listview_item.xml` ：定义单个日程的布局样式。

  2. **主要代码文件**
     * `HttpHelper.kt` ：负责与 Kimi Moonshot AI 进行网络通信。当前使用的 API 密钥为小组申请的密钥，可能会因时间推移而失效。如需复现功能，请自行申请密钥并替换原有密钥。注意：该文件使用 Kotlin 编写，编译时需安装相关 Kotlin 插件。
     * `myDataHelpler.java` ：继承自 `SQLiteOpenHelper`，实现数据库的创建及增删改查操作。Android Studio 自带 SQLite 数据库，无需额外下载。需注意该类名称结尾为 “Helpler” 而非 “Helper”，这是创建时的拼写错误。
     * `TodoList.java` ：待办（日程）类，用于封装待办事项数据。
     * `ListViewAdapter.java` ：列表显示类，依据 `listview_item.xml` 定义的布局，在记事本的 ListView 中更新并展示日程数据。

## 学习参考资料

  1. **AS 快速入门** ：[手把手教你用 Android Studio 写一个 APP_哔哩哔哩_bilibili](https://www.bilibili.com/video/BV1MK411p7dp/?spm_id_from=333.1007.top_right_bar_window_custom_collection.content.click&vd_source=fad7878d9b72a2d2207f3a5dc50d9f12)
  2. **AS 深入学习** ：[【天哥】Android 开发视频教程最新版 Android Studio 开发_哔哩哔哩_bilibili](https://www.bilibili.com/video/BV1Rt411e76H/?spm_id_from=333.337.search-card.all.click&vd_source=fad7878d9b72a2d2207f3a5dc50d9f12)
  3. **KIMI AI API 接口实现介绍** ：[第一章 使用 Android 完成 KIMI API 简单使用_android 接入 kimy-CSDN 博客](https://blog.csdn.net/alishaoxiong/article/details/140174357)
  4. **数据库快速入门** ：[【SQL 数据库】SQL 两小时半快速入门到精通 建议反复观看 学不会自我反省_哔哩哔哩_bilibili](https://www.bilibili.com/video/BV1yw4m1S7Be/?spm_id_from=333.1007.top_right_bar_window_custom_collection.content.click)
  5. **android-sqlite 数据库基本操作** ：[android-sqlite 数据库基本操作|快速入门|增删改查|基础_哔哩哔哩_bilibili](https://www.bilibili.com/video/BV1YY4y1M77G/?spm_id_from=333.999.0.0&vd_source=fad7878d9b72a2d2207f3a5dc50d9f12)
  6. **ListView 快速上手** ：[android 入门必看 ListView 快速上手 | 基础知识_哔哩哔哩_bilibili](https://www.bilibili.com/video/BV1LD4y1t7E5/?spm_id_from=333.999.0.0&vd_source=fad7878d9b72a2d2207f3a5dc50d9f12)
