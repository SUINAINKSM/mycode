package com.example.materialviewpagerdemo;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.holder.BadgeStyle;
import com.mikepenz.materialdrawer.interfaces.OnCheckedChangeListener;
import com.mikepenz.materialdrawer.model.ExpandableBadgeDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.SwitchDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;

public class MainActivity extends AppCompatActivity {
    private MaterialViewPager mViewPager;
    private Drawer mainDrawer = null;
    static final int TAPS = 7;
    private IProfile profile = null;//登录用户信息
    private IProfile profile1 = null;//登录用户信息
    private IProfile profile2 = null;//登录用户信息
    private AccountHeader headerResult = null;//head头布局
    private PrimaryDrawerItem primaryItem = null;//菜单item
    private SectionDrawerItem sectionItem = null;//分组的item类似于group标签，无点击效果
    private ExpandableBadgeDrawerItem expandableItem = null;//伸缩式item
    private SecondaryDrawerItem secondItem = null;//子item
    private SwitchDrawerItem switchItem1 = null;//带有switch开关的item1
    private SwitchDrawerItem switchItem2 = null;//带有switch开关的item2
    private Drawer result = null;//嵌套抽屉

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = findViewById(R.id.materialViewPager);
        //添加监听
        mViewPager.setMaterialViewPagerListener(new MaterialViewPager.Listener() {
            @Override
            public HeaderDesign getHeaderDesign(int page) {
                switch (page) {
                    case 0:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.blue,
                                "http://cdn1.tnwcdn.com/wp-content/blogs.dir/1/files/2014/06/wallpaper_51.jpg");
                    case 1:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.green,
                                "https://fs01.androidpit.info/a/63/0e/android-l-wallpapers-630ea6-h900.jpg");
                    case 2:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.cyan,
                                "http://www.droid-life.com/wp-content/uploads/2014/10/lollipop-wallpapers10.jpg");
                    case 3:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.red,
                                "http://www.tothemobile.com/wp-content/uploads/2014/07/original.jpg");
                }

                //execute others actions if needed (ex : modify your header logo)

                return null;
            }
        });
        //设置Toolbar
        Toolbar toolbar = mViewPager.getToolbar();

        if (toolbar != null) {
            setSupportActionBar(toolbar);

            ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setDisplayUseLogoEnabled(false);
            actionBar.setHomeButtonEnabled(true);
        }

        //设置Adapter
        mViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                switch (position % TAPS) {
                    case 0:
                        return RecyclerViewFragment.newInstance();

                    default:
                        return RecyclerViewFragment.newInstance();
                }
            }

            @Override
            public int getCount() {
                return TAPS;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position % TAPS) {
                    case 0:
                        return "星期一";
                    case 1:
                        return "星期二";
                    case 2:
                        return "星期三";
                    case 3:
                        return "星期四";
                    case 4:
                        return "星期五";
                    case 5:
                        return "星期六";
                    case 6:
                        return "星期天";
                    default:
                        return "TAPN";
                }
            }
        });
        //设置setViewPager
        mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());
       /*new DrawerBuilder()
                .withActivity(this)
                //.withAccountHeader(headerResult)
                .addDrawerItems(
                        new PrimaryDrawerItem()
                                .withName("User")
                                .withDescription("This is a user")
                                //.withIcon(R.drawable.ic_account_circle_black_24dp)
                                .withIdentifier(1)
                                .withSelectable(false),
                        new SectionDrawerItem().withName("menu组"),  //分组item，类似于group标签，无点击效果
                        new ExpandableBadgeDrawerItem() //伸缩式item
                                .withName("个人中心")
                                // .withIcon(R.drawable.ic_android_black_24dp)
                                .withIdentifier(2)
                                .withBadge("3")  //设置圆角气泡中的数字
                                .withSubItems(
                                        new SecondaryDrawerItem().withName("我的信息").withIdentifier(3),  //添加子item
                                        new SecondaryDrawerItem().withName("我的预约").withIdentifier(4), //添加子item
                                        new SecondaryDrawerItem().withName("我的违约").withIdentifier(5)  //添加子item
                                ),
                        new SwitchDrawerItem()  //添加带有switch开关的item
                                .withName("开关item1")
                                // .withIcon(R.drawable.ic_android_black_24dp)
                                .withIdentifier(6)
                                .withCheckable(false)
                                .withOnCheckedChangeListener(checkedChangeListener),
                        new SwitchDrawerItem()
                                .withName("开关item2")
                                //.withIcon(R.drawable.ic_android_black_24dp)
                                .withIdentifier(7)
                                .withOnCheckedChangeListener(checkedChangeListener)
                                .withChecked(true)  //设置默认为ON状态
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        //监听方法实现
                        if(position==0)
                            Toast.makeText(MainActivity.this,"1",Toast.LENGTH_SHORT).show();
                        else if(position==1)
                            Toast.makeText(MainActivity.this,"2",Toast.LENGTH_SHORT).show();
                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .withShowDrawerOnFirstLaunch(true)  //设置为默认启动抽屉菜单
                .build();*/
       setProfile();
        //创建和NavigationView相似的Headerlayout
        setHeadLayout(savedInstanceState);//方法中不能直接设置此属性，所以oncreat中传参
        result = new DrawerBuilder()
                .withActivity(this)
                .addDrawerItems(setPrimaryItem())
                .build();
        //最后创建抽屉，将配置好的布局属性加进去
        new DrawerBuilder().withActivity(this)
                .withAccountHeader(headerResult)
                .withToolbar(toolbar)//和toolbar关联
                .withActionBarDrawerToggle(true) //启用toolbar的ActionBarDrawerToggle动画
                .addDrawerItems(setPrimaryItem(), setSectionItem(), setExpandableItem(),
                        setSwitchItem1(), setSwitchItem2(),setSectionItem())//给抽屉添加item布局
                .withShowDrawerOnFirstLaunch(false) //默认开启抽屉
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        //监听方法实现
                        Toast.makeText(MainActivity.this, drawerItem.getIdentifier() + "is clicked", Toast.LENGTH_SHORT).show();
                        if(drawerItem.getIdentifier()==4) {
                            Intent  intent=new Intent(MainActivity.this,user_information.class);
                            startActivity(intent);
                        }
                       if(6 == drawerItem.getIdentifier()){
                            Intent intent=new Intent(MainActivity.this,UserMessage.class);
                            startActivity(intent);
                        }

                        return false;
                    }
                }) //抽屉中item的监听事件
                .withDrawerGravity(Gravity.LEFT) //设置抽屉打开方向默认从左，end从右侧打开
//                .append(result);
                .build();

    }


    /**
     * ①IProfile创建登录用户对象
     */
    private void setProfile() {
        profile = new ProfileDrawerItem()
                .withName("ksm")
                .withEmail("626289512@qq.com")
                .withIcon("http://www.tothemobile.com/wp-content/uploads/2014/07/original.jpg")
                .withIdentifier(100);//标识符，当设置监听事件时可以根据这个来区别对象
        profile1 = new ProfileDrawerItem()
                .withName("lc")
                .withEmail("411887055@qq.com")
                .withIcon("http://www.tothemobile.com/wp-content/uploads/2014/07/original.jpg")
                .withIdentifier(101);//标识符，当设置监听事件时可以根据这个来区别对象
        profile2 = new ProfileDrawerItem()
                .withName("ctc")
                .withEmail("411887055@qq.com")
                .withIcon("http://www.tothemobile.com/wp-content/uploads/2014/07/original.jpg")
                .withIdentifier(102);//标识符，当设置监听事件时可以根据这个来区别对象
    }

    /**
     * ②创建head布局，显示概要，将创建的profile加入布局中
     * withOnAccountHeaderListener
     * 获得标识符进行判断点击事件是来自哪个用户对象而执行相应的响应方法。
     * 这里获取标识符的方法getIdentifier方法的返回值是long型的，
     * 而switch是不接受long变量的，所以这里需要转一下变量类型。
     * 监听事件的方法是返回值的，一般是按照原来给出的返回false就可以了，
     * 表示当执行完点击事件后，关闭抽屉菜单；
     * withTranslucentStatusBar(true) 设置是否启用沉浸式状态栏
     * addProfiles(profile) 于添加用户对象，可以添加多个，使用逗号进行隔开；
     * withSaveIntstance方法就是将意外被杀掉的Activity的状态设置回来。
     * 之后我们使用build就可以获得一个类似Headerlayout的对象。
     * 把新创建的AccountHeader对象添加进去的方法：
     * new DrawerBuilder()
     * .withActivity(this)
     * .withAccountHeader(headerResult)
     * .build();
     */
    private void setHeadLayout(Bundle savedInstanceState) {
        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.ic_launcher_background)
                .withTranslucentStatusBar(true) //半透明效果
                .addProfiles(profile,profile1,profile2)
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean current) {
                        switch ((int) profile.getIdentifier()) {
                            case 100://根据不同标识符监听不同对象
                                Toast.makeText(MainActivity.this, "头像被点击", Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                break;
                        }
                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .build();//至此头布局head构建完成
    }

    /**
     * ③创建自定义菜单布局
     *创建自己的带有图标和任何字体DrawerItems
     * 添加依赖：
     *  compile 'com.mikepenz:google-material-typeface:2.2.0.1@aar'
     *  compile 'com.mikepenz:fontawesome-typeface:4.6.0.3@aar'
     * @return
     */
    private PrimaryDrawerItem setPrimaryItem() {
        primaryItem = new PrimaryDrawerItem()
                .withName("Hunter_LC")
                .withDescription("This is a test.")
                .withIcon(GoogleMaterial.Icon.gmd_accounts_list_alt)
                .withIdentifier(1)
                .withSelectable(false);
        return primaryItem;
    }

    /**
     * ④分组item，类似于group标签，无点击效果
     *
     * @return
     */
    private SectionDrawerItem setSectionItem() {
        sectionItem = new SectionDrawerItem()
                .withName("menu组")
                .withDivider(true)
                .withTextColor(R.color.md_deep_orange_500)
                .withIdentifier(2);
        return sectionItem;
    }

    /**
     * ⑤伸缩式item包含内部子item SecondaryDrawerItem
     * @return
     */
    private ExpandableBadgeDrawerItem setExpandableItem() {
        expandableItem = new ExpandableBadgeDrawerItem()
                .withName("个人中心")
                .withIcon(FontAwesome.Icon.faw_user_secret)
                .withIdentifier(3)
                .withBadge("10") //设置圆角气泡中的数字
                .withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE).withColorRes(R.color.md_light_blue_A700))
                .withSubItems(
                        new SecondaryDrawerItem().withName("我的信息").withIdentifier(4),
                        new SecondaryDrawerItem().withName("我的预约").withIdentifier(5),
                        new SecondaryDrawerItem().withName("消息").withIdentifier(6)
                ); //内部item
        return expandableItem;
    }
    private SwitchDrawerItem setSwitchItem1(){
        switchItem1=new SwitchDrawerItem()
                // .withIcon(R.drawable.profile3)
                .withIdentifier(6)
                .withCheckable(false)
                .withOnCheckedChangeListener(checkedChangeListener)
                .withName("开关1");
        return switchItem1;
    }
    private SwitchDrawerItem setSwitchItem2(){
        switchItem2=new SwitchDrawerItem()
                //.withIcon(R.drawable.profile4)
                .withIdentifier(7)
                .withCheckable(true)
                .withOnCheckedChangeListener(checkedChangeListener)
                .withName("开关2");
        return switchItem2;
    }
    private OnCheckedChangeListener checkedChangeListener = new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(IDrawerItem drawerItem, CompoundButton buttonView, boolean isChecked) {
            if(drawerItem instanceof Nameable) {
                Toast.makeText(MainActivity.this,((Nameable)drawerItem).getName() + "'s check is" + isChecked,Toast.LENGTH_SHORT).show();
            }

        }
    };

}


