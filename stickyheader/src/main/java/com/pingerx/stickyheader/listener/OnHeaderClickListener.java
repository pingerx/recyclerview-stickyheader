package com.pingerx.stickyheader.listener;

import android.view.View;

/**
 * @author Pinger
 * @since 2019/2/13 11:33
 */
public interface OnHeaderClickListener {

    void onHeaderClick(View header, int position, long headerId);


    void onChildClick(View child, int position, long headerId);

}
