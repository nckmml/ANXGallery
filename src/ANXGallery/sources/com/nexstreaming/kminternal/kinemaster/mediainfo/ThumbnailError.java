package com.nexstreaming.kminternal.kinemaster.mediainfo;

import android.content.Context;
import com.nexstreaming.app.common.task.Task;

enum ThumbnailError implements Task.TaskError {
    RawFileNotFound,
    RawFileTooSmall,
    NoThumbailsFound,
    UnknownFormat,
    ParameterError;

    public Exception getException() {
        return null;
    }

    public String getLocalizedMessage(Context context) {
        return name();
    }

    public String getMessage() {
        return name();
    }
}
