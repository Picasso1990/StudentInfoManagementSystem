# 1.开发登录界面

## 1.1.创建Android项目

在Androidstudio上面新建Android项目，命名为StudentInfoManagementSystem（学生信息管理系统），项目创建完成后树形文件如下：

![](https://github.com/linguangyu1996/StudentInfoManagementSystem/raw/master/images/treetype.PNG)

## 1.2.编写登录界面

编写登录界面，实现学号和密码登录，登录界面截图如下：

![](https://github.com/linguangyu1996/StudentInfoManagementSystem/raw/master/images/login.PNG)

## 1.3.登录用例表

| 用例编号         | 01                                                           |
| ---------------- | ------------------------------------------------------------ |
| 用例名           | 登录                                                         |
| 用例描述         | 学生使用教务系统的学号和密码进行登录                         |
| 参与者           | 学生、管理员                                                 |
| 普通用户、管理员 | 系统的用户登录页面正常运行，并学生的学号和密码存在于教务系统的数据库 |
| 后置条件         | 进入学生的教务查询界面                                       |
| 主事件流         | 1、学生进入系统的学生登录页面；2、学生输入需要登录的学号、密码；3、学生提交登录信息；4、系统对学生提交的学号、密码进行有效性检查；检查通过，系统跳转到学生教务信息查询页面。 |
| 备选事件流       | 1、学生输入密码或学号为空，提示不能为空；2、学生输入的学号或密码错误，提示学号或密码错误；3、点击返回按钮直接返回上一级界面。 |

# 2.创建后端服务Bmob

## 2.1.注册Bmob后端云

登录网站https://www.bmob.cn或者在百度上搜索Bmob用邮箱注册一个Bmob账号。

## 2.2.查看Bmob开发者文档

在Bmob网站上查看开发者文档，了解Bmob开发过程。

![](https://github.com/linguangyu1996/StudentInfoManagementSystem/raw/master/images/bmob1.PNG)



## 2.3.网站后台创建应用

登录账号进入bmob后台后，点击后台界面左上角“创建应用”，在弹出框输入你应用的名称，然后确认，你就拥有了一个等待开发的应用。

![](https://github.com/linguangyu1996/StudentInfoManagementSystem/raw/master/images/bmob2.png)



## 2.4.获取应用密钥和下载SDK

选择你要开发的应用，进入该应用，在跳转页面，进入设置/应用密钥，点击复制，即可得到Application ID 

![](https://github.com/linguangyu1996/StudentInfoManagementSystem/raw/master/images/bmob3.png)



## 2.5.AndroidStudio配置

在AndroidStudio中导入SDK，在Project的build.gradle文件中添加Bmob的maven仓库地址

```
allprojects {
    repositories {
        google()
        jcenter()
        //Bmob的maven仓库地址--必填
        maven { url "https://raw.github.com/bmob/bmob-android-sdk/master" }
    }
}
```

在app的build.gradle文件中添加compile依赖文件：

```
/**兼容Android6.0系统所需，如果这句话报错，可在dependencies标签下使用compile 'cn.bmob.android:http-legacy:1.0'**/
useLibrary 'org.apache.http.legacy'
```

```
//以下SDK开发者请根据需要自行选择
//bmob-sdk：Bmob的android sdk包，包含了Bmob的数据存储、文件等服务，以下是最新的bmob-sdk:
//3.5.5：请务必查看下面注释[1]
compile 'cn.bmob.android:bmob-sdk:3.6.2'

//bmob-push：Bmob的推送包
compile 'cn.bmob.android:bmob-push:0.8'

//bmob-im：Bmob的即时通讯包，注意每个版本的im依赖特定版本的bmob-sdk，具体的依赖关系可查看下面注释[2]
compile 'cn.bmob.android:bmob-im:2.1.0@aar'

//如果你想应用能够兼容Android6.0，请添加此依赖(org.apache.http.legacy.jar)
compile 'cn.bmob.android:http-legacy:1.0'
```

在项目中的AndroidManifest.xml文件中添加权限：

```
<!-- 允许联网 -->
<uses-permission android:name="android.permission.INTERNET" />
<!-- 获取GSM（2g）、WCDMA（联通3g）等网络状态的信息 -->
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<!-- 获取wifi网络状态的信息 -->
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
<!-- 保持CPU 运转，屏幕和键盘灯有可能是关闭的,用于文件上传和下载 -->
<uses-permission android:name="android.permission.WAKE_LOCK" />
<!-- 获取sd卡写的权限，用于文件上传和下载 -->
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<!-- 允许读取手机状态 用于创建BmobInstallation -->
<uses-permission android:name="android.permission.READ_PHONE_STATE" />
```

在项目的LoginActivity中初始化BmobSDK，在onCreate（）方法中添加如下代码：

```
Bmob.initialize(this,"841bd7888ab964137195382ee2420a5f");//后一个参数为APPID
```

# 3.项目开发

## 3.1.建立JavaBean对象

因为我们是要存入数据库的，所以对应的就是数据表的表名，我们对应创建了一个名为UserTable（学生用户表）的对象继承自BmobObject，就会在数据库中新建一个表名叫UserTable的数据表，然后给对象包括的属性创建get，set方法。 

```
/**
 * Created by 光裕 on 2018/5/30.
 * 用户表
 */

public class UserTable extends BmobObject {

    private String studentId;
    private String password;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
```

在Bmob后端服务的后台的UserTable表中添加测试学号和密码，模拟学生教务系统的学生账号和密码：

![](https://github.com/linguangyu1996/StudentInfoManagementSystem/raw/master/images/pBmobtable.PNG)

在LoginActivity中实现连接后端服务器精准学号和密码登录（核心代码）：

```
/**
 * 查询是否存在该学生
 */
mLogin.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String studentIdString = mStudentId.getText().toString();
        String passwordString =  mPassword.getText().toString();
        if (studentIdString.equals("")||passwordString.equals("")){
            Toast.makeText(LoginActivity.this,"学号或密码不能为空值",Toast.LENGTH_SHORT).show();
        }else {
            BmobQuery<UserTable> queryUser = new BmobQuery<UserTable>();
            queryUser.addWhereEqualTo("studentId",studentIdString);
            queryUser.addWhereEqualTo("password",passwordString);
            queryUser.findObjects(new FindListener<UserTable>() {
                @Override
                public void done(List<UserTable> list, BmobException e) {
                    if (e == null && (!list.isEmpty())){
                        Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, TestActivity.class);
                        startActivity(intent);
                    }else if (e == null && list.isEmpty()){
                        Toast.makeText(LoginActivity.this,"学号或密码错误",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(LoginActivity.this,"服务器连接错误",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
});
```

## 3.2.底部导航栏实现

### 3.2.1.资源图片的导入

实现底部导航栏，四个功能分别为：timetable（课程表）、grade（成绩）、test（考试）、me（我的），到阿里巴巴的图标库搜索自己想要的图片，网址为：http://www.iconfont.cn/，找到如下图片资源，每个分别找了灰色（不选中状态）、蓝色（选中状态）、绿色（选中状态，备选颜色，测试美观性）。

![](https://github.com/linguangyu1996/StudentInfoManagementSystem/raw/master/images/navigation_Icon.PNG)

将下载的图片资源复制到项目中的res文件夹下的mipmap-hdpi目录下，如图：

![](https://github.com/linguangyu1996/StudentInfoManagementSystem/raw/master/images/inputNaviIcom.PNG)

### 3.2.2.实现底部选项的一些资源文件 

实现底部导航栏图片选中的状态的变换，图片资源如tar_menu_timetable.xml（课程表）：

```
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">

    <item android:drawable="@mipmap/timetable" android:state_selected="true"/>
    <item android:drawable="@mipmap/timetableno"/>

</selector>
```

其他三个类似实现：

![](https://github.com/linguangyu1996/StudentInfoManagementSystem/raw/master/images/drawableTabMenu.PNG)



实现底部导航栏文字选中的状态的变换，文字资源如tab_menu_timetable_text.xml（所有的文字资源）：

```
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">

    <item android:color="@color/colorBlue" android:state_selected="true"/>
    <item android:color="@color/colortabno"/>

</selector>
```

### 3.2.3.页面布局的实现

#### 3.2.3.1.Title（头部）的实现：

使用RelativeLayout页面布局，分别使用ImageView和TextView实现title布局：

```
<RelativeLayout
    android:id="@+id/layout_title"
    android:layout_alignParentTop="true"
    android:background="@color/colorBlue"
    android:layout_width="match_parent"
    android:layout_height="40dp">

    <ImageView
        android:id="@+id/image_upward"
        android:src="@mipmap/upward"
        android:layout_centerVertical="true"
        android:layout_marginLeft="10dp"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />

    <TextView
        android:layout_toRightOf="@+id/image_upward"
        android:layout_centerVertical="true"
        android:text="上周"
        android:textColor="@color/colorWhite"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />

    <TextView
        android:layout_centerInParent="true"
        android:textColor="@color/colorWhite"
        android:textSize="16dp"
        android:text="课程表"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />

    <ImageView
        android:id="@+id/image_down"
        android:layout_alignParentRight="true"
        android:layout_centerVertical="true"
        android:layout_marginRight="10dp"
        android:src="@mipmap/down"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />

    <TextView
        android:layout_toLeftOf="@+id/image_down"
        android:layout_centerVertical="true"
        android:text="下周"
        android:textColor="@color/colorWhite"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />

</RelativeLayout>
```

#### 3.2.3.2.导航栏的实现

使用LinearLayout和RelativeLayout嵌套的方式，在RelativeLayout布局里使用ImageView（在上面）和TextView（在下面）的方式实现，如timetable（课程表）实现代码如下：

```
<RelativeLayout
    android:id="@+id/tab_menu_timetable"
    android:layout_width="0dp"
    android:layout_height="match_parent"
    android:layout_weight="1">

    <ImageView
        android:id="@+id/image_timetable"
        android:layout_centerHorizontal="true"
        android:layout_marginTop="5dp"
        android:src="@drawable/tar_menu_timetable"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />

    <TextView
        android:id="@+id/text_timetable"
        android:layout_below="@+id/image_timetable"
        android:layout_centerInParent="true"
        android:text="@string/timetable"
        android:textSize="12dp"
        android:textColor="@drawable/tab_menu_timetable_text"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />

</RelativeLayout>
```

#### 3.2.3.3.实现之后的页面布局

![](https://github.com/linguangyu1996/StudentInfoManagementSystem/raw/master/images/mainUI.PNG)

### 3.2.4.布局关联的Activity的实现（MainActivity）

#### 3.2.4.1.创建四个Fragment的简单布局与类： 

创建四个Fragment的简单布局用来测试，用来对应之后要实现的四个主要的Fragment，分别为：TimeTableFragment（课程表）、GradeFragment（成绩）、TestFragment（测试）、MeFragment（我的）。

例如timetable_fragment.xml布局代码如下：

```
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <TextView
        android:id="@+id/text2"
        android:text="timetable"
        android:layout_centerInParent="true"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />

</RelativeLayout>
```

例如TimeTableFragment.java（课程表）代码如下：

```
/**
 * Created by 光裕 on 2018/5/30.
 * 课程表Fragment
 */

public class TimeTableFragment extends Fragment {

    private String context;
    private TextView mTextView;

    public TimeTableFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.timetable_fragment,container,false);//加载timetable_fragment布局
//          mTextView = view.findViewById(R.id.text2);
//        mTextView.setText(context);
        return view;
    }
}
```

#### 3.2.4.2.MainActivity.java的实现

设置去除布局标题和设置状态栏和定义的title颜色一致：

```
supportRequestWindowFeature(Window.FEATURE_NO_TITLE);//设置去除标题
setContentView(R.layout.activity_main);
getWindow().setStatusBarColor(0xff1C86EE);//设置状态栏和title颜色一致，0xff表示透明度100% 加上颜色值就行了。如上：颜色值是: 0xff + 1C86EE，要求API最小是21。
```

先初始化控件后添加控件的点击事件，其中hideAllFragment()方法和selested()方法为自定义方法，具体作用看注释，MainActivity的主要代码实现为：

```
 /**
     * 控件的点击事件实现
     * @param v
     */
    @Override
    public void onClick(View v) {

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();//开启一个事务
        hideAllFrament(fragmentTransaction);//隐藏所有Fragment方法
        switch (v.getId()){
            case R.id.tab_menu_timetable:
                selected();//重置所有文本的选中状态的方法
                mImageTimetable.setSelected(true);
                mTextTimetable.setSelected(true);
//                mTextTimetable.setTextColor(getResources().getColor(R.color.colorBlue));
                if (timeTableFragment == null){
                    timeTableFragment = new TimeTableFragment();
                    fragmentTransaction.add(R.id.layout_content,timeTableFragment);//将Fragment添加到布局中
                }else{
                    fragmentTransaction.show(timeTableFragment);
                }
                break;
            case R.id.tab_menu_grade:
                selected();
                mImageGrade.setSelected(true);
                mTextGrade.setSelected(true);
//                mTextGrade.setTextColor(getResources().getColor(R.color.colorBlue));
                if (gradeFragment == null){
                    gradeFragment = new GradeFragment();
                    fragmentTransaction.add(R.id.layout_content,gradeFragment);
                }else {
                    fragmentTransaction.show(gradeFragment);
                }
                break;
            case R.id.tab_menu_test:
                selected();
                mImageTest.setSelected(true);
                mTextTest.setSelected(true);
//                mTextTest.setTextColor(getResources().getColor(R.color.colorBlue));
                if (testFragment == null){
                    testFragment = new TestFragment();
                    fragmentTransaction.add(R.id.layout_content,testFragment);
                }else {
                    fragmentTransaction.show(testFragment);
                }
                break;
            case R.id.tab_menu_me:
                selected();
                mImageMe.setSelected(true);
                mTextMe.setSelected(true);
//                mTextMe.setTextColor(getResources().getColor(R.color.colorBlue));
                if (meFragment == null){
                    meFragment = new MeFragment();
                    fragmentTransaction.add(R.id.layout_content,meFragment);
                }else {
                    fragmentTransaction.show(meFragment);
                }
                break;
        }
        fragmentTransaction.commit();//提交事务
    }
```

#### 3.2.4.3.导航栏实现页面布局

![](https://github.com/linguangyu1996/StudentInfoManagementSystem/raw/master/images/mainShiXian.png)





## 3.3.头部（title）布局的实现

### 3.3.1.布局的XML的实现

实现timetable（课程表）、grade（成绩查询）、test（考试时间）、me（我的）、login（登录）这五个用例的头部布局，布局的文件如下图：

![](https://github.com/linguangyu1996/StudentInfoManagementSystem/raw/master/images/titleUI.PNG)

先导入title实现所需的图片资源，在通过在RelativeLayout中嵌套其他布局或控件实现，如me（我的）的title实现细节如下：

```
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:background="@color/colorBlue"
    android:layout_width="match_parent"
    android:layout_height="40dp">

    <ImageView
        android:id="@+id/image_about"
        android:src="@mipmap/about"
        android:layout_centerVertical="true"
        android:layout_marginLeft="10dp"
        android:layout_width="22dp"
        android:layout_height="wrap_content" />

    <TextView
        android:id="@+id/text_about"
        android:layout_centerVertical="true"
        android:layout_toRightOf="@+id/image_about"
        android:textColor="@color/colorWhite"
        android:text="关于"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />

    <TextView
        android:id="@+id/text_me"
        android:layout_centerInParent="true"
        android:textColor="@color/colorWhite"
        android:textSize="16dp"
        android:text="我的"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />

    <ImageView
        android:id="@+id/image_login"
        android:src="@mipmap/login"
        android:layout_alignParentRight="true"
        android:layout_centerVertical="true"
        android:layout_marginRight="10dp"
        android:layout_width="22dp"
        android:layout_height="wrap_content" />

    <TextView
        android:id="@+id/text_login"
        android:layout_toLeftOf="@+id/image_login"
        android:layout_centerVertical="true"
        android:textColor="@color/colorWhite"
        android:text="登录"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />

</RelativeLayout>
```

### 3.3.2.实现之后的布局

![](https://github.com/linguangyu1996/StudentInfoManagementSystem/raw/master/images/titleLogin.PNG)

![](https://github.com/linguangyu1996/StudentInfoManagementSystem/raw/master/images/titleTimetable.PNG)

![](https://github.com/linguangyu1996/StudentInfoManagementSystem/raw/master/images/titleGrade.PNG)

![](https://github.com/linguangyu1996/StudentInfoManagementSystem/raw/master/images/titleTest.PNG)

![](https://github.com/linguangyu1996/StudentInfoManagementSystem/raw/master/images/titleMe.PNG)

### 3.3.3.布局关联到Activity的实现

在MainActivity中初始化控件之后隐藏所有的title：

```
/**
 * 隐藏所有的title
 */
public void goneAllTitle(){
    titleTimetable.setVisibility(View.GONE);
    titleGrade.setVisibility(View.GONE);
    titleTest.setVisibility(View.GONE);
    titleMe.setVisibility(View.GONE);
}
```

在控件的onClick()方法中准确的执行title显示的方法：

```
titleTimetable.setVisibility(View.VISIBLE);
```

实现效果如下：

![](https://github.com/linguangyu1996/StudentInfoManagementSystem/raw/master/images/gradeUI.PNG)

## 3.4.实现先登录再进入教务查询界面

### 3.4.1.实现未登录时显示的LoginFragment.java

在MainActivity中添加判断的标志：

```
public static int panduan = 0;//判断是否登录，若为0，还未登录，若为1，已登录
```

当panduan为0时将显示LoginFragment，实现如下：

```
/**
 * 添加显示LoginFragment
 */
public void addLoginFragment(){
    if (loginFragment == null){
        loginFragment = new LoginFragment();
        fragmentTransaction.add(R.id.layout_content,loginFragment);//将Fragment添加到布局中
    }else{
        fragmentTransaction.show(loginFragment);
    }
}
```

实现效果如下：

![](https://github.com/linguangyu1996/StudentInfoManagementSystem/raw/master/images/loginFragment.PNG)

### 3.4.2.实现登录之后进入教务查询界面

在LoginFragment中实现登录的控件点击事件，采用startActivityForResult()方法启动LoginActivity之后返回接收回调的数据，实现如下：

```
imagePleaseLogin.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        getActivity().startActivityForResult(intent,1000);//启动活动，传递请求码为1000
    }
});
```

在LoginActivity中返回resultCode为RESULT_OK：

```
Intent intent = new Intent();
setResult(RESULT_OK,intent);
finish();
```

在MainActivity中重写onActivityResult(),因为LoginActivity()被销毁后会回调该方法，给判断赋值为1，即已经实现登录，实现如下：

```
/**
 * 处理Login登录成功返回的数据
 * @param requestCode
 * @param resultCode
 * @param data
 */
@Override
public void onActivityResult(int requestCode, int resultCode, Intent data) {
    switch (requestCode) {
        case 1000:
            if (resultCode == RESULT_OK) {
                panduan = 1;
            }
            break;
        default:
    }
}
```

即在登录界面填写正确的学号和密码之后进入学生教务查询界面。