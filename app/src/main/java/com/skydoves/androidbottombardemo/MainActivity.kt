/*
 * Designed and developed by 2020 skydoves (Jaewoong Eum)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.skydoves.androidbottombardemo

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.skydoves.androidbottombar.BottomMenuItem
import com.skydoves.androidbottombar.OnMenuItemSelectedListener
import com.skydoves.androidbottombar.animations.BadgeAnimation
import com.skydoves.androidbottombar.forms.badgeForm
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    with(viewpager) {
      adapter = MainPagerAdapter(supportFragmentManager)
      offscreenPageLimit = 3
    }

    val badgeForm = badgeForm(this) {
      setBadgeTextSize(9f)
      setBadgePaddingLeft(6)
      setBadgePaddingRight(6)
      setBadgeDuration(550)
    }

    androidBottomBar.addBottomMenuItems(mutableListOf(
      BottomMenuItem(this)
        .setTitle("Movie")
        .setTitleActiveColorRes(R.color.md_blue_200)
        .setBadgeForm(badgeForm)
        .setBadgeColorRes(R.color.md_blue_200)
        .setBadgeAnimation(BadgeAnimation.FADE)
        .setBadgeText("New!")
        .setIcon(R.drawable.ic_movie)
        .build(),

      BottomMenuItem(this)
        .setTitle("Tv")
        .setTitleActiveColorRes(R.color.md_blue_200)
        .setIcon(R.drawable.ic_tv)
        .build(),

      BottomMenuItem(this)
        .setTitle("star")
        .setTitleActiveColorRes(R.color.md_blue_200)
        .setBadgeForm(badgeForm)
        .setBadgeText("⭐⭐⭐")
        .setBadgeColorRes(R.color.white)
        .setBadgeTextColorRes(R.color.black)
        .setBadgeAnimation(BadgeAnimation.SCALE)
        .setIcon(R.drawable.ic_star)
        .build(),

      BottomMenuItem(this)
        .setTitle("Social")
        .setTitleActiveColorRes(R.color.md_blue_200)
        .setBadgeForm(badgeForm)
        .setBadgeColorRes(R.color.md_red_900)
        .setBadgeAnimation(BadgeAnimation.SCALE)
        .setBadgeText("999+")
        .setIcon(R.drawable.ic_social)
        .build())
    )

    androidBottomBar.onMenuItemSelectedListener = object : OnMenuItemSelectedListener {
      override fun onMenuItemSelected(index: Int, bottomMenuItem: BottomMenuItem, fromUser: Boolean) {
        viewpager.currentItem = index
        androidBottomBar.dismissBadge(index)
      }
    }

    androidBottomBar.setOnBottomMenuInitializedListener {
      androidBottomBar.bindViewPager(viewpager)

      // show badges after 1500 milliseconds.
      Handler().postDelayed({
        androidBottomBar.showBadge(index = 0)
        androidBottomBar.showBadge(2)
        androidBottomBar.showBadge(3)
      }, 1500L)
    }
  }
}
