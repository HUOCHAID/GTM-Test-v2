GTM / Firebase 首次启动测试工程

包名:
  com.quicksupport.market.apk

已放入:
  app/google-services.json
  app/src/main/assets/containers/GTM-W24Z5Z2X.json

测试逻辑:
  1. APK 安装后第一次打开。
  2. Firebase Analytics 发送事件 b_first_start。
  3. GTM 容器匹配 Event Name = b_first_start。
  4. GTM 调用 com.quicksupport.market.apk.FirstStartTag。
  5. FirstStartTag 在 Logcat 输出:
     GTM_TEST: FirstStartTag executed

依赖:
  Firebase Android BoM 34.19.0
  Google services Gradle plugin 4.5.0
  Google Tag Manager play-services-tagmanager 18.3.0

编译:
  建议使用当前稳定版 Android Studio。
  本工程采用 AGP 9.4.0 / Gradle 9.6 / Java 17。
  打开工程后等待 Gradle Sync，安装 Android SDK 36，然后 Build > Build APK(s)。

测试:
  1. 安装 APK。
  2. 第一次打开应用。
  3. Firebase Console > Analytics > DebugView 查看 b_first_start。
  4. Android 调试环境开启:
     adb shell setprop debug.firebase.analytics.app com.quicksupport.market.apk
     adb shell setprop log.tag.GoogleTagManager VERBOSE
  5. Logcat 搜索 GTM_TEST，应看到:
     FirstStartTag executed

再次测试首次启动:
  先卸载应用，再重新安装。SharedPreferences 会在卸载后清除。

注意:
  这个测试工程只验证 Firebase -> GTM -> FirstStartTag。
  它不包含自动下载、静默安装、短信/无障碍/设备管理等功能。

不安装 Android Studio 的编译方法（GitHub Actions）:
  1. 新建一个 GitHub 仓库。
  2. 把本工程所有文件上传到仓库根目录。
  3. 打开仓库 Actions。
  4. 选择 “Build Android APK”。
  5. 点击 “Run workflow”。
  6. 构建完成后，在该次运行底部 Artifacts 下载:
     GTM-FirstStart-Test-debug
  7. 解压即可得到 app-debug.apk。

工程已经包含:
  .github/workflows/build-apk.yml
