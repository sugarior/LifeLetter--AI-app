##运行
完整安卓代码若在Android Studio中运行报错，请保证下载好可以运行java以及kotlin项目的较新版AS，并修改项目SDK路径为本地路径。
##可执行apk文件在release中。
##app使用：
在安卓手机中下载apk并安装，打开界面，点击登录，跳转到课表界面，点击记事本可进入待办界面，点击添加进入添加事项界面，将含有时间地点事件的文字粘贴到指定位置，点击确认键等待跳转即可。若不能运行即为api密钥过期，参考4.工程文件详解第二条。跳转到记事本界面即为成功添加。点击显示的日程可以进行修改和删除。
如果添加失败请检查是否允许本app访问wifi或流量
目前仍存在的bug：
            (1)极小概率会出现AI调用超时，此时返回空日程。遇到这种情况重试即可。
            (2)目前APP对含有多个事项的文字段分析能力较差。
            (3)目前APP对过短的输入的响应能力也较差。
##工程文件详解
    #(1)主要UI界面,分为Activity和单独的xml，每个Activity对应一个Java文件和一个xml
        MainActivity：主界面，为打开app即可见的登录界面。
        LessonActivity：实现了课表界面布局，以及向记事本和记事本添加页面的跳转
        AskActivity：实现向AI提供文本的写入界面布局。
        TodoActivity：实现记事本界面，以及数据库的内容输出展示
        UpdateActivity：实现修改记事本中的日程的修改界面。
        listview_item.xml：自定义的单个日程的布局设计
    #(2)主要代码文件，包括Java和kotlin文件
        HttpHelper.kt：实现和Kimi Moonshot AI通信的网络帮助类，该API的申请地址如下
                     https://platform.moonshot.cn/console/context-caching
                     类中的密钥(API_KEY)目前使用的是本小组申请的密钥，时间久了之后可能失效，若要复现请自己申请
                     需要注意，该类若用Java编写则有可能报错，编译该文件需要下载相关Kotlin插件。
        myDataHelpler.java：继承自SQLiteOpenHelper的数据库操作帮助类，实现数据库的创建以及增删查改。AS中有自带的SQLite数据库，无需下载，需要注意的是该类名称结尾不是Helper而是Helpler，这是创建时误打错了。
        TodoList.java：待办(日程)类，将待办集成为一个类。
        ListViewAdapter.java：列表显示类，实现在记事本的ListView中以listview_item.xml定义的布局更新并显示日程。
##学习参考资料如下：
    (1)AS快速入门：https://www.bilibili.com/video/BV1MK411p7dp/?spm_id_from=333.1007.top_right_bar_window_custom_collection.content.click&vd_source=fad7878d9b72a2d2207f3a5dc50d9f12
    (2)AS深入学习：https://www.bilibili.com/video/BV1Rt411e76H/?spm_id_from=333.337.search-card.all.click&vd_source=fad7878d9b72a2d2207f3a5dc50d9f12
    (3)KIMI AI API接口实现介绍：https://blog.csdn.net/alishaoxiong/article/details/140174357
    (4)数据库快速入门：https://www.bilibili.com/video/BV1yw4m1S7Be/?spm_id_from=333.1007.top_right_bar_window_custom_collection.content.click
    (5)android-sqlite数据库基本操作：https://www.bilibili.com/video/BV1YY4y1M77G/?spm_id_from=333.999.0.0&vd_source=fad7878d9b72a2d2207f3a5dc50d9f12
    (6)ListView快速上手：https://www.bilibili.com/video/BV1LD4y1t7E5/?spm_id_from=333.999.0.0&vd_source=fad7878d9b72a2d2207f3a5dc50d9f12

