package com.example.hotspotalk.view.customview

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import com.naver.maps.map.MapView
import com.naver.maps.map.NaverMapOptions


class CustomMapView : MapView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, options: NaverMapOptions) : super(context, options)

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        // 해당 뷰의 터치 이벤트 발생시 ViewParent 의 터치 이벤트 비활성화 시키기
        parent.requestDisallowInterceptTouchEvent(true)
        return super.dispatchTouchEvent(ev)
    }
}
