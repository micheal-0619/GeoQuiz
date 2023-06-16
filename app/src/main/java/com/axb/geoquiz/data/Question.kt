package com.axb.geoquiz.data

import androidx.annotation.StringRes


    /* @StringRes注解可以不加， 但最好加上， 原因有两个。 首先， Android Studio内置有Lint代码检查器，
     * 有了该注解， 它在编译时就知道构造函数会提供有效的资源ID。 这样一来， 构造函数使用无效资源ID的情
     * 况（比如提供的资源ID指向非String类型资源） 就能避免， 从而阻止了应用的运行时崩溃。 其次， 注解可以
     * 方便其他开发人员阅读和理解你的代码。
     */

    /*
     * 一个构造器
     * 两个成员变量 textResId 用来保存地理知识问题字符串的资源ID
     *            answer   问题文本和问题答案（true或false）
     * */

data class Question(@StringRes val textResId: Int, val answer: Boolean)