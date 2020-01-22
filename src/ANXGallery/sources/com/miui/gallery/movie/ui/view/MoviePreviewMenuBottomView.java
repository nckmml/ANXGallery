package com.miui.gallery.movie.ui.view;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.miui.gallery.movie.R;
import com.miui.gallery.util.Log;

public class MoviePreviewMenuBottomView extends LinearLayout implements View.OnClickListener {
    private TextView mEditView;
    private IMenuBottomViewClickListener mListener;
    private View mPlayArea;
    private ImageView mPlayView;
    private View mPreViewBottomView;
    private TextView mSaveView;

    public interface IMenuBottomViewClickListener {
        void onEditBtnClick();

        void onPlayAreaClick();

        void onSaveBtnClick();
    }

    public MoviePreviewMenuBottomView(Context context) {
        super(context);
        init(context);
    }

    public MoviePreviewMenuBottomView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.movie_fragment_preview_bottom, this);
    }

    public void onClick(View view) {
        if (this.mListener == null) {
            Log.d("MoviePreviewMenuBottomView", "the listener is null . ");
            return;
        }
        int id = view.getId();
        if (id == R.id.tv_movie_save) {
            this.mListener.onSaveBtnClick();
        } else if (id == R.id.tv_movie_editor) {
            this.mListener.onEditBtnClick();
        } else if (id == R.id.fl_preview_play_area) {
            this.mListener.onPlayAreaClick();
        }
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.mPreViewBottomView = findViewById(R.id.ll_movie_preview_bottom_area);
        this.mPlayArea = findViewById(R.id.fl_preview_play_area);
        this.mSaveView = (TextView) findViewById(R.id.tv_movie_save);
        this.mEditView = (TextView) findViewById(R.id.tv_movie_editor);
        this.mPlayView = (ImageView) findViewById(R.id.iv_movie_play);
        this.mPreViewBottomView.setOnClickListener(this);
        this.mSaveView.setOnClickListener(this);
        this.mEditView.setOnClickListener(this);
        this.mPlayArea.setOnClickListener(this);
    }

    public void removeListener() {
        this.mListener = null;
    }

    public void setIMenuBottomViewClickListener(IMenuBottomViewClickListener iMenuBottomViewClickListener) {
        this.mListener = iMenuBottomViewClickListener;
    }

    public void updatePlayBtnState(boolean z) {
        Resources resources;
        int i;
        this.mPlayView.setSelected(z);
        ImageView imageView = this.mPlayView;
        if (z) {
            resources = getResources();
            i = R.string.movie_content_describe_pause;
        } else {
            resources = getResources();
            i = R.string.movie_content_describe_play;
        }
        imageView.setContentDescription(resources.getString(i));
    }
}
