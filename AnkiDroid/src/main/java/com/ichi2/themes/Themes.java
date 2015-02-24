/***************************************************************************************
 * Copyright (c) 2011 Norbert Nagold <norbert.nagold@gmail.com>                         *
 *                                                                                      *
 * This program is free software; you can redistribute it and/or modify it under        *
 * the terms of the GNU General Public License as published by the Free Software        *
 * Foundation; either version 3 of the License, or (at your option) any later           *
 * version.                                                                             *
 *                                                                                      *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY      *
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A      *
 * PARTICULAR PURPOSE. See the GNU General Public License for more details.             *
 *                                                                                      *
 * You should have received a copy of the GNU General Public License along with         *
 * this program.  If not, see <http://www.gnu.org/licenses/>.                           *
 ****************************************************************************************/

package com.ichi2.themes;

import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ichi2.anki.AnkiDroidApp;
import com.ichi2.anki.R;

import timber.log.Timber;

public class Themes {

    public final static String themeNames[] = {"Android Dark", "Android Light", "Blue", "White", "Flat", "Deep Black"};
    public final static int THEME_ANDROID_DARK = 0;
    public final static int THEME_ANDROID_LIGHT = 1;
    public final static int THEME_BLUE = 2;
    public final static int THEME_WHITE = 3;
    public final static int THEME_FLAT = 4;
    public final static int THEME_DEEPBLACK = 5;

    public final static int THEME_NO_THEME = 100;

    public final static int CALLER_STUDYOPTIONS = 1;
    public final static int CALLER_DECKPICKER_DECK = 3;
    public final static int CALLER_REVIEWER = 4;
    public final static int CALLER_FEEDBACK = 5;
    public final static int CALLER_DOWNLOAD_DECK = 6;
    public final static int CALLER_DECKPICKER = 7;
    public final static int CALLER_CARDBROWSER = 8;
    public final static int CALLER_CARDEDITOR_INTENTDIALOG = 9;
    public final static int CALLER_CARD_EDITOR = 10;

    private static int mCurrentTheme = -1;

    // TODO Use mBackgroundColor, mReviewerBackgroundColor as color value or as ID?

    private static int mProgressbarsBackgroundColorID;
    private static int mProgressbarsFrameColorID;
    private static int mProgressbarsMatureColorID;
    private static int mProgressbarsYoungColorID;
    private static int mProgressbarsDeckpickerYoungColorID;
    private static int mReviewerBackgroundID = 0;
    private static int mReviewerProgressbarColorID = 0;
    private static int mFlashcardBorder = 0;
    private static int mDeckpickerItemBorder = 0;
    private static int mTitleStyle = 0;
    private static int mTitleTextColor;
    private static int mTextColor;
    private static int mForegroundColor;
    private static int mTextViewStyle = 0;
    private static int mWallpaper = 0;
    private static int mBackgroundColor;
    private static int mBackgroundDarkColor = 0;
    private static int mDialogBackgroundColor = 0;
    private static int mToastBackground = 0;
    private static int[] mCardbrowserItemBorder;
    private static int[] mChartColors;
    private static int mPopupTopDark;
    private static int mPopupTopMedium;
    private static int mPopupTopBright;
    private static int mPopupCenterDark;
    private static int mPopupCenterBright;
    private static int mPopupCenterMedium;
    private static int mPopupBottomDark;
    private static int mPopupBottomBright;
    private static int mPopupBottomMedium;
    private static int mPopupFullDark;
    private static int mPopupFullMedium;
    private static int mPopupFullBright;
    private static int mDividerHorizontalBright;
    private static Typeface mLightFont;
    private static Typeface mRegularFont;
    private static Typeface mBoldFont;
    private static int mProgressDialogFontColor;
    private static int mNightModeButton;

    private static Context mContext;


    public static void applyTheme(Context context) {
          applyTheme(context, mCurrentTheme);
//        applyTheme(context, -1);
    }


    public static void applyTheme(Context context, int theme) {
        Log.e("JS", "applyTheme");
        mContext = context;
        if (mCurrentTheme == -1) {
            loadTheme();
        }



        switch (theme == -1 ? mCurrentTheme : theme) {
            case THEME_ANDROID_DARK:
                context.setTheme(android.R.style.Theme_Black);
//                Timber.d("Set theme: dark");
                break;
            case THEME_ANDROID_LIGHT:
                context.setTheme(android.R.style.Theme_Light);
//                Timber.d("Set theme: light");
                break;
            case THEME_BLUE:
                context.setTheme(R.style.Theme_Blue);
//                Timber.d("Set theme: blue");
                break;
            case THEME_FLAT:
                context.setTheme(R.style.Theme_Flat);
//                Timber.d("Set theme: flat");
                break;
            case THEME_WHITE:
                context.setTheme(R.style.Theme_White);
//                Timber.d("Set theme: white");
                break;
            case THEME_DEEPBLACK:
                context.setTheme(R.style.Theme_DeepBlack);


            case -1:
                break;
        }
        Timber.d("Set theme: "+themeNames[mCurrentTheme]);
        Log.e("JS", "theme: " + themeNames[mCurrentTheme]);  // js


    }

//    public static void recursivelyTheme(ViewGroup vg) {
//        Log.e("JS", " -- ");
//        for (int i = 0; i < vg.getChildCount(); i++) {
//            View child = vg.getChildAt(i);
//            if (child instanceof ViewGroup) {
//                recursivelyTheme((ViewGroup) child);
//            } else {
//                child.setBackgroundColor(mBackgroundColor);
//                if (child instanceof TextView) {
//                    ((TextView) child).setTextColor(mTitleTextColor);   // TODO different text color?
//                }
//            }
//        }
//    }


    public static void setContentStyle(View view, int caller) {
        setFont(view);
        switch (caller) {
            case CALLER_STUDYOPTIONS:
                // ((View)
                // view.findViewById(R.id.studyoptions_progressbar1_border)).setBackgroundResource(mProgressbarsFrameColor);
                // ((View)
                // view.findViewById(R.id.studyoptions_progressbar2_border)).setBackgroundResource(mProgressbarsFrameColor);
                // ((View)
                // view.findViewById(R.id.studyoptions_global_limit_bars)).setBackgroundResource(mProgressbarsFrameColor);
                // ((View)
                // view.findViewById(R.id.studyoptions_progressbar4_border)).setBackgroundResource(mProgressbarsFrameColor);
                //
                // ((View)
                // view.findViewById(R.id.studyoptions_bars_max)).setBackgroundResource(mProgressbarsBackgroundColor);
                // ((View)
                // view.findViewById(R.id.studyoptions_progressbar2_content)).setBackgroundResource(mProgressbarsBackgroundColor);
                // ((View)
                // view.findViewById(R.id.studyoptions_global_limit_bars_content)).setBackgroundResource(mProgressbarsBackgroundColor);
                // ((View)
                // view.findViewById(R.id.studyoptions_progressbar4_content)).setBackgroundResource(mProgressbarsBackgroundColor);
                //
                // ((View)
                // view.findViewById(R.id.studyoptions_global_mat_limit_bar)).setBackgroundResource(mProgressbarsMatureColor);
                // ((View)
                // view.findViewById(R.id.studyoptions_global_mat_bar)).setBackgroundResource(mProgressbarsMatureColor);
                //
                // ((View)
                // view.findViewById(R.id.studyoptions_global_limit_bar)).setBackgroundResource(mProgressbarsYoungColor);
                // ((View)
                // view.findViewById(R.id.studyoptions_global_bar)).setBackgroundResource(mProgressbarsYoungColor);


// JS TODO duplicate this for deep black theme
                if (mCurrentTheme == THEME_WHITE) {
                    setMargins(view.findViewById(R.id.studyoptions_deck_name), LayoutParams.WRAP_CONTENT,
                            LayoutParams.WRAP_CONTENT, 0, 6f, 0, 2f);
                    // setMargins(view.findViewById(R.id.studyoptions_statistic_field), LayoutParams.WRAP_CONTENT,
                    // LayoutParams.WRAP_CONTENT, 0, 2f, 0, 12f);
                    ((View) view.findViewById(R.id.studyoptions_scrollcontainer))
                            .setBackgroundResource(R.drawable.white_textview);
                    // ((View)
                    // view.findViewById(R.id.studyoptions_statistic_field)).setBackgroundResource(R.color.transparent);
                    ((View) view.findViewById(R.id.studyoptions_deck_name)).setVisibility(View.VISIBLE);
                    // ((View)
                    // view.findViewById(R.id.studyoptions_deckinformation)).setBackgroundResource(mTextViewStyle);
                    ((View) view.findViewById(R.id.studyoptions_main))
                            .setBackgroundResource(R.drawable.white_wallpaper);

                } else {
                    // ((View)
                    // view.findViewById(R.id.studyoptions_statistic_field)).setBackgroundResource(mTextViewStyle);
                    ((View) view.findViewById(R.id.studyoptions_main)).setBackgroundResource(mWallpaper);
                }
                break;

            case CALLER_DECKPICKER:
                ListView lv = (ListView) view.findViewById(R.id.files);


                lv.setVerticalScrollBarEnabled(false);
                lv.setFadingEdgeLength(15);
                lv.setDividerHeight(0);
                AnkiDroidApp.getCompat().setOverScrollModeNever(lv);

                switch (mCurrentTheme) {

                    case THEME_BLUE:
                        lv.setSelector(R.drawable.blue_deckpicker_list_selector);
                        break;
                    case THEME_FLAT:
                        lv.setSelector(R.drawable.blue_deckpicker_list_selector);
                        break;
                    case THEME_WHITE:
                        lv.setSelector(R.drawable.white_deckpicker_list_selector);
                        lv.setBackgroundResource(R.drawable.white_deckpicker_lv_background);
                        view.setBackgroundResource(mWallpaper);
                        // lv.setDivider(mContext.getResources().getDrawable(R.drawable.white_listdivider));
                        // setMargins(view, LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT, 4f, 4f, 4f, 4f);
                        break;
                    case THEME_DEEPBLACK:
                        lv.setSelector(R.drawable.deepblack_deckpicker_list_selector);
                        lv.setBackgroundResource(R.drawable.deepblack_deckpicker_lv_background);
//                        recursivelyTheme(lv);
                        break;
                    default:
                        break;
                }
                break;

            case CALLER_CARDBROWSER:
                ListView lv2 = (ListView) view.findViewById(R.id.card_browser_list);
                AnkiDroidApp.getCompat().setOverScrollModeNever(lv2);
                lv2.setFadingEdgeLength(15);
                lv2.setDividerHeight(0);
                setFont(view);
                setWallpaper(view);


                switch (mCurrentTheme) {
                    case THEME_BLUE:
                        lv2.setSelector(R.drawable.blue_cardbrowser_list_selector);
                        lv2.setDividerHeight(0);
                        break;
                    case THEME_FLAT:
                        lv2.setSelector(R.drawable.blue_cardbrowser_list_selector);
                        lv2.setDividerHeight(0);
                        break;
                    case THEME_WHITE:
                        lv2.setBackgroundResource(R.drawable.white_textview);
                        lv2.setSelector(R.drawable.white_deckpicker_list_selector);

                        lv2.setSelector(R.drawable.white_deckpicker_list_selector);
                        lv2.setDivider(mContext.getResources().getDrawable(R.drawable.white_listdivider));
                        break;
                    default:
                        break;
                }
                break;

            case CALLER_CARDEDITOR_INTENTDIALOG:
                ListView lv3 = (ListView) view;
                switch (mCurrentTheme) {
                    case THEME_BLUE:
                        lv3.setSelector(R.drawable.blue_cardbrowser_list_selector);
                        lv3.setDividerHeight(0);
                        break;
                    case THEME_FLAT:
                        lv3.setSelector(R.drawable.blue_cardbrowser_list_selector);
                        lv3.setDividerHeight(0);
                        break;
                    case THEME_WHITE:
                        lv3.setSelector(R.drawable.blue_cardbrowser_list_selector);
                        lv3.setDividerHeight(0);
                        break;
                    default:
                        break;
                }
                break;

            case CALLER_DECKPICKER_DECK:
                // if (view.getId() == R.id.deckpicker_bar_mat) {
                // // view.setBackgroundResource(mProgressbarsFrameColor);
                // } else if (view.getId() == R.id.deckpicker_bar_all) {
                // // view.setBackgroundResource(mProgressbarsDeckpickerYoungColor);
                // } else
                if (view.getId() == R.id.deckpicker_deck) {
                    view.setBackgroundResource(mDeckpickerItemBorder);
                }
                break;

            case CALLER_REVIEWER:
                ((View) view.findViewById(R.id.main_layout)).setBackgroundResource(mReviewerBackgroundID);
                ((View) view.findViewById(R.id.flashcard_border)).setBackgroundResource(mFlashcardBorder);
                switch (mCurrentTheme) {
                    case THEME_ANDROID_DARK:
                    case THEME_ANDROID_LIGHT:
                        ((View) view.findViewById(R.id.flashcard_frame)).setBackgroundResource(AnkiDroidApp
                                .getSharedPrefs(mContext).getBoolean("invertedColors", false) ? R.color.black
                                : R.color.white);
                        break;
                    case THEME_BLUE:
                        ((View) view.findViewById(R.id.flashcard_frame))
                                .setBackgroundResource(AnkiDroidApp.getSharedPrefs(mContext).getBoolean(
                                        "invertedColors", false) ? R.color.reviewer_night_card_background
                                        : R.color.white);
                        break;
                    case THEME_FLAT:
                        ((View) view.findViewById(R.id.flashcard_frame))
                                .setBackgroundResource(AnkiDroidApp.getSharedPrefs(mContext).getBoolean(
                                        "invertedColors", false) ? R.color.reviewer_night_card_background
                                        : R.color.white);
                        break;
                    case THEME_WHITE:
                        ((View) view.findViewById(R.id.flashcard_frame)).setBackgroundResource(AnkiDroidApp
                                .getSharedPrefs(mContext).getBoolean("invertedColors", false) ? R.color.black
                                : R.color.white);

                        setMargins(view.findViewById(R.id.main_layout), LayoutParams.FILL_PARENT,
                                LayoutParams.FILL_PARENT, 4f, 0, 4f, 4f);

                        // ((View)view.findViewById(R.id.nextTime1)).setBackgroundResource(R.drawable.white_next_time_separator);
                        // ((View)view.findViewById(R.id.nextTime2)).setBackgroundResource(R.drawable.white_next_time_separator);
                        // ((View)view.findViewById(R.id.nextTime3)).setBackgroundResource(R.drawable.white_next_time_separator);
                        // ((View)view.findViewById(R.id.nextTime4)).setBackgroundResource(R.drawable.white_next_time_separator);
                        break;
                }
                break;

            case CALLER_FEEDBACK:
                ((TextView) view).setTextColor(mForegroundColor);
                break;

            case CALLER_CARD_EDITOR:
                view.findViewById(R.id.CardEditorEditFieldsLayout).setBackgroundResource(mTextViewStyle);
                // int padding = (int) (4 * mContext.getResources().getDisplayMetrics().density);
                // view.findViewById(R.id.CardEditorScroll).setPadding(padding, padding, padding, padding);
                break;

            case CALLER_DOWNLOAD_DECK:
                view.setBackgroundResource(mDeckpickerItemBorder);
                break;
        }
    }

    // temporary hack js
    public static void forceIterateTheme() {
        mCurrentTheme = (mCurrentTheme > 4) ? 2 : (mCurrentTheme + 1);  // Hack js
    }

    public static void loadTheme() {
        Log.e("JS", "loadTheme");
        // SharedPreferences preferences = PrefSettings.getSharedPrefs(mContext);
        //mCurrentTheme = Integer.parseInt(preferences.getString("theme", "3"));

        // set theme always to "white" until theming is properly reimplemented
        if (mCurrentTheme == -1) {  // temporary hack js
            mCurrentTheme = 3;
        }

        switch (mCurrentTheme) {
            case THEME_ANDROID_DARK:
                mDialogBackgroundColor = mContext.getResources().getColor(R.color.card_browser_background);
                mProgressbarsBackgroundColorID = R.color.studyoptions_progressbar_background_default;
                mProgressbarsFrameColorID = R.color.studyoptions_progressbar_frame_default;
                mProgressbarsMatureColorID = R.color.studyoptions_progressbar_mature_default;
                mProgressbarsYoungColorID = R.color.studyoptions_progressbar_young_default;
                mProgressbarsDeckpickerYoungColorID = R.color.deckpicker_progressbar_young_dark;
                mReviewerBackgroundID = 0;
                mFlashcardBorder = 0;
                mDeckpickerItemBorder = 0;
                mTitleStyle = 0;
                mTitleTextColor = mContext.getResources().getColor(R.color.white);
                mTextViewStyle = 0;
                mWallpaper = 0;
                mToastBackground = 0;
                mBackgroundDarkColor = 0;
                mReviewerProgressbarColorID = 0;
                mCardbrowserItemBorder = new int[] { 0, R.color.card_browser_marked, R.color.card_browser_suspended,
                        R.color.card_browser_marked };
                mChartColors = new int[] { Color.WHITE, Color.BLACK };
                mPopupTopBright = R.drawable.popup_top_bright;
                mPopupTopMedium = R.drawable.popup_top_bright;
                mPopupTopDark = R.drawable.popup_top_dark;
                mPopupCenterDark = R.drawable.popup_center_dark;
                mPopupCenterBright = R.drawable.popup_center_bright;
                mPopupCenterMedium = R.drawable.popup_center_medium;
                mPopupBottomDark = R.drawable.popup_bottom_dark;
                mPopupBottomBright = R.drawable.popup_bottom_bright;
                mPopupBottomMedium = R.drawable.popup_bottom_medium;
                mPopupFullBright = R.drawable.popup_full_bright;
                mPopupFullDark = R.drawable.popup_full_dark;
                mPopupFullMedium = R.drawable.popup_full_bright;
                mDividerHorizontalBright = R.drawable.blue_divider_horizontal_bright;
                mBackgroundColor = mContext.getResources().getColor(R.color.white);

                mProgressDialogFontColor = mContext.getResources().getColor(R.color.white);
                mNightModeButton = R.drawable.btn_keyboard_key_fulltrans_normal;
                break;

            case THEME_ANDROID_LIGHT:
                mProgressbarsBackgroundColorID = R.color.studyoptions_progressbar_background_light;
                mProgressbarsFrameColorID = R.color.studyoptions_progressbar_frame_light;
                mProgressbarsMatureColorID = R.color.studyoptions_progressbar_mature_light;
                mProgressbarsYoungColorID = R.color.studyoptions_progressbar_young_light;
                mProgressbarsDeckpickerYoungColorID = R.color.deckpicker_progressbar_young_light;
                mReviewerBackgroundID = 0;
                mFlashcardBorder = 0;
                mDeckpickerItemBorder = 0;
                mTitleStyle = 0;
                mTitleTextColor = mContext.getResources().getColor(R.color.black);
                mTextViewStyle = 0;
                mWallpaper = 0;
                mToastBackground = 0;
                mBackgroundDarkColor = 0;
                mDialogBackgroundColor = mContext.getResources().getColor(R.color.card_browser_background);
                // Fix mContext.getResources().getColor() for this
                mCardbrowserItemBorder = new int[] { 0, R.color.card_browser_marked, R.color.card_browser_suspended,
                        R.color.card_browser_marked };
                mReviewerProgressbarColorID = mProgressbarsYoungColorID;
                mChartColors = new int[] { Color.BLACK, Color.WHITE };
                mPopupTopDark = R.drawable.popup_top_dark;
                mPopupTopBright = R.drawable.popup_top_bright;
                mPopupTopMedium = R.drawable.popup_top_bright;
                mPopupCenterDark = R.drawable.popup_center_dark;
                mPopupCenterBright = R.drawable.popup_center_bright;
                mPopupCenterMedium = R.drawable.popup_center_medium;
                mPopupBottomDark = R.drawable.popup_bottom_dark;
                mPopupBottomBright = R.drawable.popup_bottom_bright;
                mPopupBottomMedium = R.drawable.popup_bottom_medium;
                mPopupFullBright = R.drawable.popup_full_bright;
                mPopupFullMedium = R.drawable.popup_full_bright;
                mPopupFullDark = R.drawable.popup_full_dark;
                mDividerHorizontalBright = R.drawable.blue_divider_horizontal_bright;
                mBackgroundColor = mContext.getResources().getColor(R.color.white);
                mProgressDialogFontColor = mContext.getResources().getColor(R.color.white);
                mNightModeButton = R.drawable.btn_keyboard_key_fulltrans_normal;
                break;

            case THEME_BLUE:
                mProgressbarsBackgroundColorID = R.color.studyoptions_progressbar_background_blue;
                mProgressbarsFrameColorID = R.color.studyoptions_progressbar_frame_light;
                mProgressbarsMatureColorID = R.color.studyoptions_progressbar_mature_light;
                mProgressbarsYoungColorID = R.color.studyoptions_progressbar_young_blue;
                mProgressbarsDeckpickerYoungColorID = R.color.deckpicker_progressbar_young_light;
                mReviewerBackgroundID = R.color.reviewer_background;
                mFlashcardBorder = R.drawable.blue_bg_webview;
                mDeckpickerItemBorder = R.drawable.blue_bg_deckpicker;
                mTitleStyle = R.drawable.blue_title;
                mTitleTextColor = mContext.getResources().getColor(R.color.black);
                mTextViewStyle = R.drawable.blue_textview;
                mWallpaper = R.drawable.blue_background;
                mBackgroundColor = mContext.getResources().getColor(R.color.background_blue);
                mToastBackground = R.drawable.blue_toast_frame;
                mDialogBackgroundColor = mContext.getResources().getColor(R.color.background_dialog_blue);
                mBackgroundDarkColor = mContext.getResources().getColor(R.color.background_dark_blue);
                mReviewerProgressbarColorID = R.color.reviewer_progressbar_session_blue;
                mCardbrowserItemBorder = new int[] { R.drawable.blue_bg_cardbrowser,
                        R.drawable.blue_bg_cardbrowser_marked, R.drawable.blue_bg_cardbrowser_suspended,
                        R.drawable.blue_bg_cardbrowser_marked_suspended };
                mChartColors = new int[] { Color.BLACK, Color.WHITE };
                mPopupTopDark = R.drawable.blue_popup_top_dark;
                mPopupTopBright = R.drawable.blue_popup_top_bright;
                mPopupTopMedium = R.drawable.blue_popup_top_medium;
                mPopupCenterDark = R.drawable.blue_popup_center_dark;
                mPopupCenterBright = R.drawable.blue_popup_center_bright;
                mPopupCenterMedium = R.drawable.blue_popup_center_medium;
                mPopupBottomDark = R.drawable.blue_popup_bottom_dark;
                mPopupBottomBright = R.drawable.blue_popup_bottom_bright;
                mPopupBottomMedium = R.drawable.blue_popup_bottom_medium;
                mPopupFullBright = R.drawable.blue_popup_full_bright;
                mPopupFullMedium = R.drawable.blue_popup_full_medium;
                mPopupFullDark = R.drawable.blue_popup_full_dark;
                mDividerHorizontalBright = R.drawable.blue_divider_horizontal_bright;
                mProgressDialogFontColor = mContext.getResources().getColor(R.color.white);
                mNightModeButton = R.drawable.blue_btn_night;
                break;

            case THEME_FLAT:
                mProgressbarsBackgroundColorID = R.color.studyoptions_progressbar_background_blue;
                mProgressbarsFrameColorID = R.color.studyoptions_progressbar_frame_light;
                mProgressbarsMatureColorID = R.color.studyoptions_progressbar_mature_light;
                mProgressbarsYoungColorID = R.color.studyoptions_progressbar_young_blue;
                mProgressbarsDeckpickerYoungColorID = R.color.deckpicker_progressbar_young_light;
                mReviewerBackgroundID = R.color.reviewer_background;
                mFlashcardBorder = R.drawable.blue_bg_webview;
                mDeckpickerItemBorder = R.drawable.blue_bg_deckpicker;
                mTitleStyle = R.drawable.flat_title;
                mTitleTextColor = mContext.getResources().getColor(R.color.flat_title_color);
                mTextViewStyle = R.drawable.flat_textview;
                mWallpaper = R.drawable.flat_background;
                mBackgroundColor = mContext.getResources().getColor(R.color.background_blue);
                mToastBackground = R.drawable.blue_toast_frame;
                mDialogBackgroundColor = mContext.getResources().getColor(R.color.background_dialog_blue);
                mBackgroundDarkColor = mContext.getResources().getColor(R.color.background_dark_blue);
                mReviewerProgressbarColorID = R.color.reviewer_progressbar_session_blue;
                mCardbrowserItemBorder = new int[] { R.drawable.blue_bg_cardbrowser,
                        R.drawable.blue_bg_cardbrowser_marked, R.drawable.blue_bg_cardbrowser_suspended,
                        R.drawable.blue_bg_cardbrowser_marked_suspended };
                mChartColors = new int[] { Color.BLACK, Color.WHITE };
                mPopupTopDark = R.drawable.blue_popup_top_dark;
                mPopupTopBright = R.drawable.blue_popup_top_bright;
                mPopupTopMedium = R.drawable.blue_popup_top_medium;
                mPopupCenterDark = R.drawable.blue_popup_center_dark;
                mPopupCenterBright = R.drawable.blue_popup_center_bright;
                mPopupCenterMedium = R.drawable.blue_popup_center_medium;
                mPopupBottomDark = R.drawable.blue_popup_bottom_dark;
                mPopupBottomBright = R.drawable.blue_popup_bottom_bright;
                mPopupBottomMedium = R.drawable.blue_popup_bottom_medium;
                mPopupFullBright = R.drawable.blue_popup_full_bright;
                mPopupFullMedium = R.drawable.blue_popup_full_medium;
                mPopupFullDark = R.drawable.blue_popup_full_dark;
                mDividerHorizontalBright = R.drawable.blue_divider_horizontal_bright;
                mLightFont = Typeface.createFromAsset(mContext.getAssets(), "fonts/OpenSans-Light.ttf");
                mRegularFont = Typeface.createFromAsset(mContext.getAssets(), "fonts/OpenSans-Regular.ttf");
                mBoldFont = Typeface.createFromAsset(mContext.getAssets(), "fonts/OpenSans-Bold.ttf");
                mProgressDialogFontColor = mContext.getResources().getColor(R.color.white);
                mNightModeButton = R.drawable.blue_btn_night;
                break;

            case THEME_WHITE:
                mProgressbarsBackgroundColorID = R.color.studyoptions_progressbar_background_blue;
                mProgressbarsFrameColorID = R.color.studyoptions_progressbar_frame_light;  // Some previous uses suggest expecting color, not colorID
                mProgressbarsMatureColorID = R.color.studyoptions_progressbar_mature_light;
                mProgressbarsYoungColorID = R.color.studyoptions_progressbar_young_blue;
                mProgressbarsDeckpickerYoungColorID = R.color.deckpicker_progressbar_young_light;
                mReviewerBackgroundID = R.color.white_background;
                mFlashcardBorder = R.drawable.white_bg_webview;
                mTitleStyle = R.drawable.white_btn_default_normal;
                mTitleTextColor = mContext.getResources().getColor(R.color.black);
                mTextColor = mContext.getResources().getColor(R.color.white);
                mTextViewStyle = R.drawable.white_textview_padding;
                mWallpaper = R.drawable.white_wallpaper;
                mBackgroundColor = mContext.getResources().getColor(R.color.white_background);
                mForegroundColor = mContext.getResources().getColor(android.R.color.black); // R.color.white_foreground;
                mToastBackground = R.drawable.white_toast_frame;
                mDialogBackgroundColor = mContext.getResources().getColor(R.color.white);
                mBackgroundDarkColor = mContext.getResources().getColor(R.color.background_dark_blue);
                mReviewerProgressbarColorID = R.color.reviewer_progressbar_session_blue;
                mCardbrowserItemBorder = new int[] { R.drawable.white_bg_cardbrowser,
                        R.drawable.white_bg_cardbrowser_marked, R.drawable.white_bg_cardbrowser_suspended,
                        R.drawable.white_bg_cardbrowser_marked_suspended };
                mChartColors = new int[] { Color.BLACK, Color.WHITE };
                mPopupTopBright = R.drawable.white_popup_top_bright;
                mPopupTopMedium = R.drawable.white_popup_top_medium;
                mPopupTopDark = mPopupTopMedium;
                mPopupCenterDark = R.drawable.white_popup_center_bright;
                mPopupCenterBright = R.drawable.white_popup_center_bright;
                mPopupCenterMedium = R.drawable.white_popup_center_medium;
                mPopupBottomBright = R.drawable.white_popup_bottom_bright;
                mPopupBottomDark = mPopupBottomBright;
                mPopupBottomMedium = R.drawable.white_popup_bottom_medium;
                mPopupFullBright = R.drawable.white_popup_full_bright;
                mPopupFullMedium = R.drawable.white_popup_full_medium;
                mPopupFullDark = mPopupFullBright;
                mDividerHorizontalBright = R.drawable.white_dialog_divider;
                mLightFont = Typeface.createFromAsset(mContext.getAssets(), "fonts/OpenSans-Light.ttf");
                mRegularFont = Typeface.createFromAsset(mContext.getAssets(), "fonts/OpenSans-Regular.ttf");
                mBoldFont = Typeface.createFromAsset(mContext.getAssets(), "fonts/OpenSans-Bold.ttf");
                mProgressDialogFontColor = mContext.getResources().getColor(R.color.black);
                mNightModeButton = R.drawable.white_btn_night;
                break;

            case THEME_DEEPBLACK:
                mProgressbarsBackgroundColorID = R.color.studyoptions_progressbar_background_deepblack;
                mProgressbarsFrameColorID = R.color.studyoptions_progressbar_frame_deepblack;
                mProgressbarsMatureColorID = R.color.studyoptions_progressbar_mature_deepblack;
                mProgressbarsYoungColorID = R.color.studyoptions_progressbar_young_deepblack;
                mProgressbarsDeckpickerYoungColorID = R.color.deckpicker_progressbar_young_deepblack;
                mReviewerBackgroundID = R.color.deepblack_background;
                mFlashcardBorder = R.drawable.deepblack_bg_webview;
                mTitleStyle = R.drawable.deepblack_btn_default_normal;  // For dialogs?
                mTitleTextColor = mContext.getResources().getColor(R.color.deepblack_textcolor);
                mTextColor = mContext.getResources().getColor(R.color.deepblack_textcolor);
                mTextViewStyle = R.drawable.deepblack_textview_padding;
                mWallpaper = R.drawable.deepblack_wallpaper;
                mBackgroundColor = mContext.getResources().getColor(R.color.deepblack_background);
                mForegroundColor = mContext.getResources().getColor(android.R.color.white); //R.color.deepblack_foreground;
                mToastBackground = R.drawable.deepblack_toast_frame;
                mDialogBackgroundColor = mContext.getResources().getColor(R.color.deepblack_background);
                mBackgroundDarkColor = mContext.getResources().getColor(R.color.background_dark_blue);  // TODO tweak
                mReviewerProgressbarColorID = R.color.reviewer_progressbar_session_blue;
                mCardbrowserItemBorder = new int[] { R.drawable.deepblack_bg_cardbrowser,
                        R.drawable.deepblack_bg_cardbrowser_marked, R.drawable.deepblack_bg_cardbrowser_suspended,
                        R.drawable.deepblack_bg_cardbrowser_marked_suspended };
                mChartColors = new int[] { Color.WHITE, Color.BLACK };  // TODO tweak this
                mPopupTopBright = R.drawable.deepblack_popup_top_bright;
                mPopupTopMedium = R.drawable.deepblack_popup_top_medium;
                mPopupTopDark = mPopupTopMedium;  // TODO not sure correct color for deepblack theme
                mPopupCenterDark = R.drawable.deepblack_popup_center_bright;
                mPopupCenterBright = R.drawable.deepblack_popup_center_bright;
                mPopupCenterMedium = R.drawable.deepblack_popup_center_medium;
                mPopupBottomBright = R.drawable.deepblack_popup_bottom_bright;
                mPopupBottomDark = mPopupBottomBright; // TODO not sure correct color for deepblack theme
                mPopupBottomMedium = R.drawable.deepblack_popup_bottom_medium;
                mPopupFullBright = R.drawable.deepblack_popup_full_bright;
                mPopupFullMedium = R.drawable.deepblack_popup_full_medium;
                mPopupFullDark = mPopupFullBright; // TODO not sure correct color for deepblack theme
                mDividerHorizontalBright = R.drawable.deepblack_dialog_divider;
                mLightFont = Typeface.createFromAsset(mContext.getAssets(), "fonts/OpenSans-Light.ttf");
                mRegularFont = Typeface.createFromAsset(mContext.getAssets(), "fonts/OpenSans-Regular.ttf");
                mBoldFont = Typeface.createFromAsset(mContext.getAssets(), "fonts/OpenSans-Bold.ttf");
                mProgressDialogFontColor = mContext.getResources().getColor(R.color.black);
                mNightModeButton = R.drawable.deepblack_btn_night;
                break;

        }
    }


    public static void setLightFont(TextView view) {
        if (mLightFont != null) {
            view.setTypeface(mLightFont);
        }
    }


    public static void setRegularFont(TextView view) {
        if (mRegularFont != null) {
            view.setTypeface(mRegularFont);
        }
    }


    public static void setBoldFont(TextView view) {
        if (mBoldFont != null) {
            view.setTypeface(mBoldFont);
        }
    }


    public static void setFont(View view) {
        if (view instanceof ViewGroup) {
            ViewGroup vg = (ViewGroup) view;
            for (int i = 0; i < vg.getChildCount(); i++) {
                View child = vg.getChildAt(i);
                if (child instanceof TextView) {
                    TextView tv = (TextView) child;
                    if (tv.getTypeface() != null && tv.getTypeface().getStyle() == Typeface.BOLD) {
                        setBoldFont((TextView) child);
                    } else {
                        setRegularFont((TextView) child);
                    }
                }
                setFont(child);
            }
        }
    }


    public static String getReviewerFontName() {
        switch (mCurrentTheme) {
            case THEME_WHITE:
                return "OpenSans";
            default:
                return null;
        }
    }

    // Might not be useful, as so many views contained multi-colored text
    public static void setTextColor(View v) {
        Log.e("JS", "setTextColor(v)");
        setTextColor(v, mTextColor);

    }


    public static void setTextColor(View view, int color) {
        Log.e("JS", "setTextColor");
        if (view instanceof ViewGroup) {
            ViewGroup vg = (ViewGroup) view;
            for (int i = 0; i < vg.getChildCount(); i++) {
                View child = vg.getChildAt(i);
                if (child instanceof TextView) {
                    TextView tv = (TextView) child;
                    tv.setTextColor(color);
                }
                setTextColor(child, color);
            }
        }
    }


    public static void setWallpaper(View view) {
        setWallpaper(view, false);
    }


    public static void setWallpaper(View view, boolean solid) {
        if (solid) {
            view.setBackgroundResource(mBackgroundDarkColor);
        } else {
            try {
                view.setBackgroundResource(mWallpaper);
            } catch (OutOfMemoryError e) {
                mWallpaper = mBackgroundColor;
                view.setBackgroundResource(mWallpaper);
                Timber.e(e, "Themes: setWallpaper: OutOfMemoryError");
            }
        }
    }


    public static void setTextViewStyle(View view) {
        view.setBackgroundResource(mTextViewStyle);
    }


    public static void setTitleStyle(View view) {
        loadTheme();
        view.setBackgroundResource(mTitleStyle);
        if (view instanceof TextView) {
            TextView tv = (TextView) view;
            tv.setTextColor(mTitleTextColor);
            if (mCurrentTheme == THEME_FLAT) {
                tv.setMinLines(1);
                tv.setMaxLines(2);
                int height = (int) (tv.getLineHeight() / 2);
                LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,
                        LayoutParams.WRAP_CONTENT);
                MarginLayoutParams mlp = (MarginLayoutParams) tv.getLayoutParams();
                height += mlp.bottomMargin;
                llp.setMargins(0, height, 0, height);
                tv.setLayoutParams(llp);
                setBoldFont(tv);
            }
        }
    }


    public static void setMargins(View view, int width, int height, float dipLeft, float dipTop, float dipRight,
            float dipBottom) {
        View parent = (View) view.getParent();
//        parent.setBackgroundResource(mBackgroundColor);
        parent.setBackgroundColor(mBackgroundColor);
        Class<?> c = view.getParent().getClass();
        float factor = mContext.getResources().getDisplayMetrics().density;
        if (c == LinearLayout.class) {
            LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(width, height);
            llp.setMargins((int) (dipLeft * factor), (int) (dipTop * factor), (int) (dipRight * factor),
                    (int) (dipBottom * factor));
            view.setLayoutParams(llp);
        } else if (c == FrameLayout.class) {
            FrameLayout.LayoutParams llp = new FrameLayout.LayoutParams(width, height);
            llp.setMargins((int) (dipLeft * factor), (int) (dipTop * factor), (int) (dipRight * factor),
                    (int) (dipBottom * factor));
            llp.gravity = Gravity.CENTER_HORIZONTAL;
            view.setLayoutParams(llp);
        } else if (c == RelativeLayout.class) {
            RelativeLayout.LayoutParams llp = new RelativeLayout.LayoutParams(width, height);
            llp.setMargins((int) (dipLeft * factor), (int) (dipTop * factor), (int) (dipRight * factor),
                    (int) (dipBottom * factor));
            view.setLayoutParams(llp);
        }
    }


    public static int getForegroundColor() {

//        if (mCurrentTheme == THEME_DEEPBLACK) {
//        mForegroundColor = mContext.getResources().getColor(android.R.color.white); //R.color.deepblack_foreground;
//            return 0x88888888;
//        }
        return mForegroundColor;
    }


    public static int getBackgroundColor() {
        return mBackgroundColor;
    }


    public static int getDialogBackgroundColor() {
        return mDialogBackgroundColor;
    }







    public static int getTheme() {
        return mCurrentTheme;
    }

    public static String getThemeName() {return themeNames[mCurrentTheme];}


    public static int[] getCardBrowserBackground() {
        return mCardbrowserItemBorder;
    }


    public static void setTextViewBackground(View view) {
        view.setBackgroundResource(mTextViewStyle);
    }


    public static void showThemedToast(Context context, String text, boolean shortLength) {
        Toast result = Toast.makeText(context, text, shortLength ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG);
        try {
            if (mCurrentTheme >= THEME_BLUE) {
                TextView tv = new TextView(context);
                tv.setBackgroundResource(mToastBackground);
                tv.setTextColor(mProgressDialogFontColor);
                tv.setText(text);
                result.setView(tv);
            }
            result.show();
        } catch (OutOfMemoryError e) {
            Timber.e(e, "showThemedToast - OutOfMemoryError occured");
            result.getView().setBackgroundResource(R.color.black);
            result.show();
        }
    }


    public static StyledDialog htmlOkDialog(Context context, String title, String text) {
        return htmlOkDialog(context, title, text, null, null);
    }


    public static StyledDialog htmlOkDialog(Context context, String title, String text, OnClickListener okListener,
            OnCancelListener cancelListener) {
        return htmlOkDialog(context, title, text, null, null, false);
    }


    public static StyledDialog htmlOkDialog(Context context, String title, String text, OnClickListener okListener,
            OnCancelListener cancelListener, boolean includeBody) {
        StyledDialog.Builder builder = new StyledDialog.Builder(context);
        builder.setTitle(title);
        WebView view = new WebView(context);
        view.setBackgroundColor(context.getResources().getColor(mDialogBackgroundColor));
        if (includeBody) {
            text = "<html><body text=\"#FFFFFF\" link=\"#E37068\" alink=\"#E37068\" vlink=\"#E37068\">" + text
                    + "</body></html>";
        }
        view.loadDataWithBaseURL("", text, "text/html", "UTF-8", "");
        builder.setView(view, true);
        builder.setPositiveButton(context.getResources().getString(R.string.dialog_ok), okListener);
        builder.setCancelable(true);
        builder.setOnCancelListener(cancelListener);
        return builder.create();
    }


    public static void setStyledProgressDialogDialogBackgrounds(View main) {
        View topPanel = ((View) main.findViewById(R.id.topPanel));
        View contentPanel = ((View) main.findViewById(R.id.contentPanel));
        if (topPanel.getVisibility() == View.VISIBLE) {
            try {
                topPanel.setBackgroundResource(mPopupTopDark);
                ((View) main.findViewById(R.id.titleDivider)).setBackgroundResource(mDividerHorizontalBright);
                contentPanel.setBackgroundResource(mPopupBottomBright);
            } catch (OutOfMemoryError e) {
                Timber.e(e, "setStyledDialogBackgrounds - OutOfMemoryError occured");
                topPanel.setBackgroundResource(R.color.black);
                contentPanel.setBackgroundResource(R.color.white);
            }
        } else {
            try {
                contentPanel.setBackgroundResource(mPopupFullMedium);
            } catch (OutOfMemoryError e) {
                Timber.e(e, "setStyledDialogBackgrounds - OutOfMemoryError occured");
                contentPanel.setBackgroundResource(R.color.white);
            }
        }
        ((TextView) main.findViewById(R.id.alertTitle)).setTextColor(mProgressDialogFontColor);
        ((TextView) main.findViewById(R.id.message)).setTextColor(mProgressDialogFontColor);
    }


    public static void setStyledDialogBackgrounds(View main) {
        int buttonCount = 0;
        for (int id : new int[] { R.id.positive_button, R.id.negative_button, R.id.neutral_button }) {
            if (main.findViewById(id).getVisibility() == View.VISIBLE) {
                buttonCount++;
            }
        }
        setStyledDialogBackgrounds(main, buttonCount);
    }


    public static void setStyledDialogBackgrounds(View main, int buttonNumbers) {
        setStyledDialogBackgrounds(main, buttonNumbers, false);
    }


    public static void setStyledDialogBackgrounds(View main, int buttonNumbers, boolean brightCustomPanelBackground) {
        setFont(main);
        if (mCurrentTheme == THEME_WHITE) {
            setTextColor(main, mContext.getResources().getColor(R.color.black));
        }
        // order of styled dialog elements:
        // 1. top panel (title)
        // 2. content panel
        // 3. listview panel
        // 4. custom view panel
        // 5. button panel
        View topPanel = ((View) main.findViewById(R.id.topPanel));
        boolean[] visibility = new boolean[5];

        if (topPanel.getVisibility() == View.VISIBLE) {
            try {
                topPanel.setBackgroundResource(mPopupTopDark);
                ((View) main.findViewById(R.id.titleDivider)).setBackgroundResource(mDividerHorizontalBright);
            } catch (OutOfMemoryError e) {
                Timber.e(e, "setStyledDialogBackgrounds - OutOfMemoryError occured");
                topPanel.setBackgroundResource(R.color.black);
            }
            visibility[0] = true;
        }
        View contentPanel = ((View) main.findViewById(R.id.contentPanel));
        if (contentPanel.getVisibility() == View.VISIBLE) {
            visibility[1] = true;
        }
        View listViewPanel = ((View) main.findViewById(R.id.listViewPanel));
        if (listViewPanel.getVisibility() == View.VISIBLE) {
            visibility[2] = true;
        }
        View customPanel = ((View) main.findViewById(R.id.customPanel));
        if (customPanel.getVisibility() == View.VISIBLE) {
            visibility[3] = true;
        }
        if (buttonNumbers > 0) {
            LinearLayout buttonPanel = (LinearLayout) main.findViewById(R.id.buttonPanel);
            try {
                buttonPanel.setBackgroundResource(mPopupBottomMedium);
            } catch (OutOfMemoryError e) {
                Timber.e(e, "setStyledDialogBackgrounds - OutOfMemoryError occured");
                buttonPanel.setBackgroundResource(R.color.white);
            }
            if (buttonNumbers > 1 || AnkiDroidApp.SDK_VERSION > 13) {
                // Starting at API 14, the dialog looks quite different, and these spacers are in the way.
                main.findViewById(R.id.rightSpacer).setVisibility(View.GONE);
                main.findViewById(R.id.leftSpacer).setVisibility(View.GONE);
            }
            visibility[4] = true;
        }

        int first = -1;
        int last = -1;
        for (int i = 0; i < 5; i++) {
            if (first == -1 && visibility[i]) {
                first = i;
            }
            if (visibility[i]) {
                last = i;
            }
        }

        int res = mPopupCenterDark;
        if (first == 1) {
            res = mPopupTopDark;
        }
        if (last == 1) {
            res = mPopupBottomDark;
            if (first == 1) {
                res = mPopupFullDark;
            }
        }
        try {
            contentPanel.setBackgroundResource(res);
        } catch (OutOfMemoryError e) {
            Timber.e(e, "setStyledDialogBackgrounds - OutOfMemoryError occured");
            contentPanel.setBackgroundResource(R.color.black);
        }

        res = mPopupCenterBright;
        if (first == 2) {
            res = mPopupTopBright;
        }
        if (last == 2) {
            res = mPopupBottomBright;
            if (first == 2) {
                res = mPopupFullBright;
            }
        }
        try {
            listViewPanel.setBackgroundResource(res);
        } catch (OutOfMemoryError e) {
            Timber.e(e, "setStyledDialogBackgrounds - OutOfMemoryError occured");
            listViewPanel.setBackgroundResource(R.color.white);
        }

        res = brightCustomPanelBackground ? mPopupCenterMedium : mPopupCenterDark;
        if (first == 3) {
            res = brightCustomPanelBackground ? mPopupTopMedium : mPopupTopDark;
            ;
        }
        if (last == 3) {
            res = brightCustomPanelBackground ? mPopupBottomMedium : mPopupBottomDark;
            ;
            if (first == 3) {
                res = brightCustomPanelBackground ? mPopupFullMedium : mPopupFullDark;
                ;
            }
        }
        try {
            customPanel.setBackgroundResource(res);
        } catch (OutOfMemoryError e) {
            Timber.e(e, "setStyledDialogBackgrounds - OutOfMemoryError occured");
            customPanel.setBackgroundResource(brightCustomPanelBackground ? R.color.white : R.color.black);
        }

        // set divider
        if (visibility[3] && brightCustomPanelBackground) {
            ((View) main.findViewById(R.id.bottomDivider)).setBackgroundResource(mDividerHorizontalBright);
            ((View) main.findViewById(R.id.bottomDivider)).setVisibility(View.VISIBLE);
        } else if (visibility[4]) {
            ((View) main.findViewById(R.id.bottomButtonDivider)).setBackgroundResource(mDividerHorizontalBright);
            ((View) main.findViewById(R.id.bottomButtonDivider)).setVisibility(View.VISIBLE);
        }
    }


    public static int[] getChartColors() {
        return mChartColors;
    }


    public static int getNightModeCardBackground(Context context) {
        switch (mCurrentTheme) {
            case THEME_BLUE:
                return context.getResources().getColor(R.color.reviewer_night_card_background);
            case THEME_FLAT:
                return context.getResources().getColor(R.color.reviewer_night_card_background);
            case THEME_WHITE:
            default:
                return context.getResources().getColor(R.color.black);
        }
    }


    public static int[] setNightMode(Context context, View view, boolean nightMode) {
        Resources res = context.getResources();
        View flipCard = view.findViewById(R.id.flashcard_layout_flip);
        View ease1 = view.findViewById(R.id.flashcard_layout_ease1);
        View ease2 = view.findViewById(R.id.flashcard_layout_ease2);
        View ease3 = view.findViewById(R.id.flashcard_layout_ease3);
        View ease4 = view.findViewById(R.id.flashcard_layout_ease4);
        View border = view.findViewById(R.id.flashcard_border);
        View mAnswerField = view.findViewById(R.id.answer_field);
        final Drawable[] defaultButtons = new Drawable[] { flipCard.getBackground(), ease1.getBackground(),
                ease2.getBackground(), ease3.getBackground(), ease4.getBackground() };

        int foregroundColor;
        int nextTimeRecommendedColor;

        if (nightMode) {
            flipCard.setBackgroundResource(mNightModeButton);
            ease1.setBackgroundResource(mNightModeButton);
            ease2.setBackgroundResource(mNightModeButton);
            ease3.setBackgroundResource(mNightModeButton);
            ease4.setBackgroundResource(mNightModeButton);
            mAnswerField.setBackgroundResource(mNightModeButton);

            foregroundColor = Color.WHITE;
            nextTimeRecommendedColor = res.getColor(R.color.next_time_recommended_color_inv);

            switch (mCurrentTheme) {
                case THEME_BLUE:
                    border.setBackgroundResource(R.drawable.blue_bg_webview_night);
                    view.setBackgroundColor(res.getColor(R.color.background_dark_blue));
                    break;
                case THEME_WHITE:
                    border.setBackgroundResource(R.drawable.white_bg_webview_night);
                    view.setBackgroundColor(res.getColor(R.color.white_background_night));
                    ((View) view.getParent()).setBackgroundColor(res.getColor(R.color.white_background_night));
                    break;
                case THEME_FLAT:
                default:
                    view.setBackgroundColor(res.getColor(R.color.black));
                    break;
            }
        } else {
            foregroundColor = Color.BLACK;
            nextTimeRecommendedColor = res.getColor(R.color.next_time_recommended_color);
            flipCard.setBackgroundDrawable(defaultButtons[0]);
            ease1.setBackgroundDrawable(defaultButtons[1]);
            ease2.setBackgroundDrawable(defaultButtons[2]);
            ease3.setBackgroundDrawable(defaultButtons[3]);
            ease4.setBackgroundDrawable(defaultButtons[4]);
            border.setBackgroundResource(mFlashcardBorder);
        }

        return new int[] { foregroundColor, nextTimeRecommendedColor };
    }

    public static int getDeckpickerListElementBackground(String  text) {
//        Log.e("JS", "getDeckPicker...");
        // There is surely a smarter, cleaner way to do all of this
        if (text.equals("top")) {
            switch (mCurrentTheme) {
                case THEME_DEEPBLACK:
                    return R.drawable.deepblack_deckpicker_top;
                case THEME_WHITE:
                    return R.drawable.white_deckpicker_top;
                default:
                    return R.drawable.white_deckpicker_top;
            }
//            return res.getDrawable(R.drawable.deepblack_deckpicker_top);
        } else if (text.equals("bot")) {
            switch (mCurrentTheme) {
                case THEME_DEEPBLACK:
                    return R.drawable.deepblack_deckpicker_bottom;
                case THEME_WHITE:
                    return R.drawable.white_deckpicker_bottom;
                default:
                    return R.drawable.white_deckpicker_bottom;
            }

        } else if (text.equals("ful")) {
            switch (mCurrentTheme) {
                case THEME_DEEPBLACK:
                    return R.drawable.deepblack_deckpicker_full;
                case THEME_WHITE:
                    return R.drawable.white_deckpicker_full;
                default:
                    return R.drawable.white_deckpicker_full;
            }
        } else if (text.equals("cen")) {
            switch (mCurrentTheme) {
                case THEME_DEEPBLACK:
                    return R.drawable.deepblack_deckpicker_center;
                case THEME_WHITE:
                    return R.drawable.white_deckpicker_center;
                default:
                    return R.drawable.white_deckpicker_center;
            }
        }


        // Should never reach here:
        return R.drawable.white_deckpicker_center;

    }

    public static TypedArray getNavigationImages(Resources resources) {
        if (mCurrentTheme != THEME_DEEPBLACK) {
            return resources.obtainTypedArray(R.array.drawer_images);
        }else {
            return resources.obtainTypedArray(R.array.drawer_images_deepblack);
        }
    }

    public static Spannable getSpannableForegroundColor(CharSequence mTitle) {
        SpannableString span = new SpannableString(mTitle);
        span.setSpan(new ForegroundColorSpan(mForegroundColor), 0, mTitle.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        return span;
    }
}
