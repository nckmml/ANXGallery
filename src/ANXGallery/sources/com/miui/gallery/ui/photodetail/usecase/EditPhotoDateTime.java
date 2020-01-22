package com.miui.gallery.ui.photodetail.usecase;

import com.miui.gallery.app.base.BaseUseCase;
import com.miui.gallery.model.repository.datesource.photo.IPhotoRepository;
import io.reactivex.Flowable;

public class EditPhotoDateTime extends BaseUseCase<Boolean, RequestBean> {
    protected IPhotoRepository mRepository;

    public static class RequestBean {
        private long id;
        private boolean isFavorite;
        private long newTime;
        private String path;

        public RequestBean(long j, long j2) {
            this.newTime = j;
            this.id = j2;
        }

        public long getId() {
            return this.id;
        }

        public long getNewTime() {
            return this.newTime;
        }

        public String getPath() {
            return this.path;
        }

        public boolean isFavorite() {
            return this.isFavorite;
        }

        public void setFavorite(boolean z) {
            this.isFavorite = z;
        }
    }

    public EditPhotoDateTime(IPhotoRepository iPhotoRepository) {
        this.mRepository = iPhotoRepository;
    }

    /* access modifiers changed from: protected */
    public Flowable<Boolean> buildUseCaseFlowable(RequestBean requestBean) {
        return requestBean == null ? Flowable.empty() : this.mRepository.editPhotoDateTimeDetailInfo(requestBean);
    }
}
