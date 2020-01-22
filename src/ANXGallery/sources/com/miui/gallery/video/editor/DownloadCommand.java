package com.miui.gallery.video.editor;

import com.miui.gallery.video.editor.factory.VideoEditorModuleFactory;
import com.miui.gallery.video.editor.model.VideoEditorBaseModel;

public class DownloadCommand {
    private VideoEditorBaseModel mBaseModel;
    private long mId;
    private boolean mIsTemplate;
    private int mPosition;
    private String mUnzipPath;
    private String mZipPath;

    public DownloadCommand(VideoEditorBaseModel videoEditorBaseModel, int i, VideoEditorModuleFactory videoEditorModuleFactory) {
        this.mBaseModel = videoEditorBaseModel;
        this.mId = videoEditorBaseModel.getId();
        this.mPosition = i;
        this.mIsTemplate = videoEditorBaseModel.isTemplate();
        this.mZipPath = videoEditorModuleFactory.getTemplateDir(videoEditorBaseModel.getId()) + ".zip";
        this.mUnzipPath = this.mIsTemplate ? videoEditorModuleFactory.getUnzipPath() : "";
    }

    public boolean equals(Object obj) {
        return obj != null && (obj instanceof DownloadCommand) && ((DownloadCommand) obj).getId() == this.mId;
    }

    public VideoEditorBaseModel getData() {
        return this.mBaseModel;
    }

    public long getId() {
        return this.mId;
    }

    public int getPosition() {
        return this.mPosition;
    }

    public String getUnzipPath() {
        return this.mUnzipPath;
    }

    public String getZipPath() {
        return this.mZipPath;
    }

    public int hashCode() {
        return String.valueOf(this.mId).hashCode();
    }

    public boolean isTemplate() {
        return this.mIsTemplate;
    }
}
