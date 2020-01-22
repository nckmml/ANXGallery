package com.miui.gallery.backup;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

public final class GalleryBackupProtos {

    public static final class BackupMessage extends GeneratedMessageLite<BackupMessage, Builder> implements BackupMessageOrBuilder {
        public static final int ALBUMPROFILES_FIELD_NUMBER = 2;
        /* access modifiers changed from: private */
        public static final BackupMessage DEFAULT_INSTANCE = new BackupMessage();
        private static volatile Parser<BackupMessage> PARSER = null;
        public static final int SETTINGS_FIELD_NUMBER = 1;
        private Internal.ProtobufList<AlbumProfile> albumProfiles_ = emptyProtobufList();
        private Settings settings_;

        public static final class AlbumProfile extends GeneratedMessageLite<AlbumProfile, Builder> implements AlbumProfileOrBuilder {
            public static final int ATTRIBUTES_FIELD_NUMBER = 3;
            /* access modifiers changed from: private */
            public static final AlbumProfile DEFAULT_INSTANCE = new AlbumProfile();
            public static final int NAME_FIELD_NUMBER = 2;
            private static volatile Parser<AlbumProfile> PARSER = null;
            public static final int PATH_FIELD_NUMBER = 1;
            private long attributes_;
            private String name_ = "";
            private String path_ = "";

            public static final class Builder extends GeneratedMessageLite.Builder<AlbumProfile, Builder> implements AlbumProfileOrBuilder {
                private Builder() {
                    super(AlbumProfile.DEFAULT_INSTANCE);
                }

                public Builder clearAttributes() {
                    copyOnWrite();
                    ((AlbumProfile) this.instance).clearAttributes();
                    return this;
                }

                public Builder clearName() {
                    copyOnWrite();
                    ((AlbumProfile) this.instance).clearName();
                    return this;
                }

                public Builder clearPath() {
                    copyOnWrite();
                    ((AlbumProfile) this.instance).clearPath();
                    return this;
                }

                public long getAttributes() {
                    return ((AlbumProfile) this.instance).getAttributes();
                }

                public String getName() {
                    return ((AlbumProfile) this.instance).getName();
                }

                public ByteString getNameBytes() {
                    return ((AlbumProfile) this.instance).getNameBytes();
                }

                public String getPath() {
                    return ((AlbumProfile) this.instance).getPath();
                }

                public ByteString getPathBytes() {
                    return ((AlbumProfile) this.instance).getPathBytes();
                }

                public Builder setAttributes(long j) {
                    copyOnWrite();
                    ((AlbumProfile) this.instance).setAttributes(j);
                    return this;
                }

                public Builder setName(String str) {
                    copyOnWrite();
                    ((AlbumProfile) this.instance).setName(str);
                    return this;
                }

                public Builder setNameBytes(ByteString byteString) {
                    copyOnWrite();
                    ((AlbumProfile) this.instance).setNameBytes(byteString);
                    return this;
                }

                public Builder setPath(String str) {
                    copyOnWrite();
                    ((AlbumProfile) this.instance).setPath(str);
                    return this;
                }

                public Builder setPathBytes(ByteString byteString) {
                    copyOnWrite();
                    ((AlbumProfile) this.instance).setPathBytes(byteString);
                    return this;
                }
            }

            static {
                GeneratedMessageLite.registerDefaultInstance(AlbumProfile.class, DEFAULT_INSTANCE);
            }

            private AlbumProfile() {
            }

            /* access modifiers changed from: private */
            public void clearAttributes() {
                this.attributes_ = 0;
            }

            /* access modifiers changed from: private */
            public void clearName() {
                this.name_ = getDefaultInstance().getName();
            }

            /* access modifiers changed from: private */
            public void clearPath() {
                this.path_ = getDefaultInstance().getPath();
            }

            public static AlbumProfile getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.createBuilder();
            }

            public static Builder newBuilder(AlbumProfile albumProfile) {
                return (Builder) DEFAULT_INSTANCE.createBuilder(albumProfile);
            }

            public static AlbumProfile parseDelimitedFrom(InputStream inputStream) throws IOException {
                return (AlbumProfile) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static AlbumProfile parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (AlbumProfile) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
            }

            public static AlbumProfile parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                return (AlbumProfile) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
            }

            public static AlbumProfile parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (AlbumProfile) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
            }

            public static AlbumProfile parseFrom(CodedInputStream codedInputStream) throws IOException {
                return (AlbumProfile) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
            }

            public static AlbumProfile parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (AlbumProfile) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
            }

            public static AlbumProfile parseFrom(InputStream inputStream) throws IOException {
                return (AlbumProfile) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static AlbumProfile parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (AlbumProfile) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
            }

            public static AlbumProfile parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                return (AlbumProfile) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
            }

            public static AlbumProfile parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (AlbumProfile) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
            }

            public static AlbumProfile parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                return (AlbumProfile) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
            }

            public static AlbumProfile parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (AlbumProfile) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
            }

            public static Parser<AlbumProfile> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }

            /* access modifiers changed from: private */
            public void setAttributes(long j) {
                this.attributes_ = j;
            }

            /* access modifiers changed from: private */
            public void setName(String str) {
                if (str != null) {
                    this.name_ = str;
                    return;
                }
                throw new NullPointerException();
            }

            /* access modifiers changed from: private */
            public void setNameBytes(ByteString byteString) {
                if (byteString != null) {
                    checkByteStringIsUtf8(byteString);
                    this.name_ = byteString.toStringUtf8();
                    return;
                }
                throw new NullPointerException();
            }

            /* access modifiers changed from: private */
            public void setPath(String str) {
                if (str != null) {
                    this.path_ = str;
                    return;
                }
                throw new NullPointerException();
            }

            /* access modifiers changed from: private */
            public void setPathBytes(ByteString byteString) {
                if (byteString != null) {
                    checkByteStringIsUtf8(byteString);
                    this.path_ = byteString.toStringUtf8();
                    return;
                }
                throw new NullPointerException();
            }

            /* access modifiers changed from: protected */
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
                switch (methodToInvoke) {
                    case NEW_MUTABLE_INSTANCE:
                        return new AlbumProfile();
                    case NEW_BUILDER:
                        return new Builder();
                    case BUILD_MESSAGE_INFO:
                        return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ\u0003\u0002", new Object[]{"path_", "name_", "attributes_"});
                    case GET_DEFAULT_INSTANCE:
                        return DEFAULT_INSTANCE;
                    case GET_PARSER:
                        Parser<AlbumProfile> parser = PARSER;
                        if (parser == null) {
                            synchronized (AlbumProfile.class) {
                                parser = PARSER;
                                if (parser == null) {
                                    parser = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                                    PARSER = parser;
                                }
                            }
                        }
                        return parser;
                    case GET_MEMOIZED_IS_INITIALIZED:
                        return (byte) 1;
                    case SET_MEMOIZED_IS_INITIALIZED:
                        return null;
                    default:
                        throw new UnsupportedOperationException();
                }
            }

            public long getAttributes() {
                return this.attributes_;
            }

            public String getName() {
                return this.name_;
            }

            public ByteString getNameBytes() {
                return ByteString.copyFromUtf8(this.name_);
            }

            public String getPath() {
                return this.path_;
            }

            public ByteString getPathBytes() {
                return ByteString.copyFromUtf8(this.path_);
            }
        }

        public interface AlbumProfileOrBuilder extends MessageLiteOrBuilder {
            long getAttributes();

            String getName();

            ByteString getNameBytes();

            String getPath();

            ByteString getPathBytes();
        }

        public static final class Builder extends GeneratedMessageLite.Builder<BackupMessage, Builder> implements BackupMessageOrBuilder {
            private Builder() {
                super(BackupMessage.DEFAULT_INSTANCE);
            }

            public Builder addAlbumProfiles(int i, AlbumProfile.Builder builder) {
                copyOnWrite();
                ((BackupMessage) this.instance).addAlbumProfiles(i, builder);
                return this;
            }

            public Builder addAlbumProfiles(int i, AlbumProfile albumProfile) {
                copyOnWrite();
                ((BackupMessage) this.instance).addAlbumProfiles(i, albumProfile);
                return this;
            }

            public Builder addAlbumProfiles(AlbumProfile.Builder builder) {
                copyOnWrite();
                ((BackupMessage) this.instance).addAlbumProfiles(builder);
                return this;
            }

            public Builder addAlbumProfiles(AlbumProfile albumProfile) {
                copyOnWrite();
                ((BackupMessage) this.instance).addAlbumProfiles(albumProfile);
                return this;
            }

            public Builder addAllAlbumProfiles(Iterable<? extends AlbumProfile> iterable) {
                copyOnWrite();
                ((BackupMessage) this.instance).addAllAlbumProfiles(iterable);
                return this;
            }

            public Builder clearAlbumProfiles() {
                copyOnWrite();
                ((BackupMessage) this.instance).clearAlbumProfiles();
                return this;
            }

            public Builder clearSettings() {
                copyOnWrite();
                ((BackupMessage) this.instance).clearSettings();
                return this;
            }

            public AlbumProfile getAlbumProfiles(int i) {
                return ((BackupMessage) this.instance).getAlbumProfiles(i);
            }

            public int getAlbumProfilesCount() {
                return ((BackupMessage) this.instance).getAlbumProfilesCount();
            }

            public List<AlbumProfile> getAlbumProfilesList() {
                return Collections.unmodifiableList(((BackupMessage) this.instance).getAlbumProfilesList());
            }

            public Settings getSettings() {
                return ((BackupMessage) this.instance).getSettings();
            }

            public boolean hasSettings() {
                return ((BackupMessage) this.instance).hasSettings();
            }

            public Builder mergeSettings(Settings settings) {
                copyOnWrite();
                ((BackupMessage) this.instance).mergeSettings(settings);
                return this;
            }

            public Builder removeAlbumProfiles(int i) {
                copyOnWrite();
                ((BackupMessage) this.instance).removeAlbumProfiles(i);
                return this;
            }

            public Builder setAlbumProfiles(int i, AlbumProfile.Builder builder) {
                copyOnWrite();
                ((BackupMessage) this.instance).setAlbumProfiles(i, builder);
                return this;
            }

            public Builder setAlbumProfiles(int i, AlbumProfile albumProfile) {
                copyOnWrite();
                ((BackupMessage) this.instance).setAlbumProfiles(i, albumProfile);
                return this;
            }

            public Builder setSettings(Settings.Builder builder) {
                copyOnWrite();
                ((BackupMessage) this.instance).setSettings(builder);
                return this;
            }

            public Builder setSettings(Settings settings) {
                copyOnWrite();
                ((BackupMessage) this.instance).setSettings(settings);
                return this;
            }
        }

        public static final class Settings extends GeneratedMessageLite<Settings, Builder> implements SettingsOrBuilder {
            /* access modifiers changed from: private */
            public static final Settings DEFAULT_INSTANCE = new Settings();
            public static final int ONLYSHOWLOCALPHOTO_FIELD_NUMBER = 1;
            private static volatile Parser<Settings> PARSER = null;
            public static final int REMINDCONNECTNETWORKEVERYTIME_FIELD_NUMBER = 4;
            public static final int SHOWHIDDENALBUM_FIELD_NUMBER = 2;
            public static final int SLIDESHOWINTERVAL_FIELD_NUMBER = 3;
            private boolean onlyShowLocalPhoto_;
            private boolean remindConnectNetworkEveryTime_;
            private boolean showHiddenAlbum_;
            private int slideshowInterval_;

            public static final class Builder extends GeneratedMessageLite.Builder<Settings, Builder> implements SettingsOrBuilder {
                private Builder() {
                    super(Settings.DEFAULT_INSTANCE);
                }

                public Builder clearOnlyShowLocalPhoto() {
                    copyOnWrite();
                    ((Settings) this.instance).clearOnlyShowLocalPhoto();
                    return this;
                }

                @Deprecated
                public Builder clearRemindConnectNetworkEveryTime() {
                    copyOnWrite();
                    ((Settings) this.instance).clearRemindConnectNetworkEveryTime();
                    return this;
                }

                public Builder clearShowHiddenAlbum() {
                    copyOnWrite();
                    ((Settings) this.instance).clearShowHiddenAlbum();
                    return this;
                }

                public Builder clearSlideshowInterval() {
                    copyOnWrite();
                    ((Settings) this.instance).clearSlideshowInterval();
                    return this;
                }

                public boolean getOnlyShowLocalPhoto() {
                    return ((Settings) this.instance).getOnlyShowLocalPhoto();
                }

                @Deprecated
                public boolean getRemindConnectNetworkEveryTime() {
                    return ((Settings) this.instance).getRemindConnectNetworkEveryTime();
                }

                public boolean getShowHiddenAlbum() {
                    return ((Settings) this.instance).getShowHiddenAlbum();
                }

                public int getSlideshowInterval() {
                    return ((Settings) this.instance).getSlideshowInterval();
                }

                public Builder setOnlyShowLocalPhoto(boolean z) {
                    copyOnWrite();
                    ((Settings) this.instance).setOnlyShowLocalPhoto(z);
                    return this;
                }

                @Deprecated
                public Builder setRemindConnectNetworkEveryTime(boolean z) {
                    copyOnWrite();
                    ((Settings) this.instance).setRemindConnectNetworkEveryTime(z);
                    return this;
                }

                public Builder setShowHiddenAlbum(boolean z) {
                    copyOnWrite();
                    ((Settings) this.instance).setShowHiddenAlbum(z);
                    return this;
                }

                public Builder setSlideshowInterval(int i) {
                    copyOnWrite();
                    ((Settings) this.instance).setSlideshowInterval(i);
                    return this;
                }
            }

            static {
                GeneratedMessageLite.registerDefaultInstance(Settings.class, DEFAULT_INSTANCE);
            }

            private Settings() {
            }

            /* access modifiers changed from: private */
            public void clearOnlyShowLocalPhoto() {
                this.onlyShowLocalPhoto_ = false;
            }

            /* access modifiers changed from: private */
            public void clearRemindConnectNetworkEveryTime() {
                this.remindConnectNetworkEveryTime_ = false;
            }

            /* access modifiers changed from: private */
            public void clearShowHiddenAlbum() {
                this.showHiddenAlbum_ = false;
            }

            /* access modifiers changed from: private */
            public void clearSlideshowInterval() {
                this.slideshowInterval_ = 0;
            }

            public static Settings getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.createBuilder();
            }

            public static Builder newBuilder(Settings settings) {
                return (Builder) DEFAULT_INSTANCE.createBuilder(settings);
            }

            public static Settings parseDelimitedFrom(InputStream inputStream) throws IOException {
                return (Settings) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static Settings parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (Settings) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
            }

            public static Settings parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                return (Settings) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
            }

            public static Settings parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (Settings) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
            }

            public static Settings parseFrom(CodedInputStream codedInputStream) throws IOException {
                return (Settings) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
            }

            public static Settings parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (Settings) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
            }

            public static Settings parseFrom(InputStream inputStream) throws IOException {
                return (Settings) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static Settings parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (Settings) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
            }

            public static Settings parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                return (Settings) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
            }

            public static Settings parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (Settings) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
            }

            public static Settings parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                return (Settings) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
            }

            public static Settings parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (Settings) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
            }

            public static Parser<Settings> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }

            /* access modifiers changed from: private */
            public void setOnlyShowLocalPhoto(boolean z) {
                this.onlyShowLocalPhoto_ = z;
            }

            /* access modifiers changed from: private */
            public void setRemindConnectNetworkEveryTime(boolean z) {
                this.remindConnectNetworkEveryTime_ = z;
            }

            /* access modifiers changed from: private */
            public void setShowHiddenAlbum(boolean z) {
                this.showHiddenAlbum_ = z;
            }

            /* access modifiers changed from: private */
            public void setSlideshowInterval(int i) {
                this.slideshowInterval_ = i;
            }

            /* access modifiers changed from: protected */
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
                switch (methodToInvoke) {
                    case NEW_MUTABLE_INSTANCE:
                        return new Settings();
                    case NEW_BUILDER:
                        return new Builder();
                    case BUILD_MESSAGE_INFO:
                        return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001\u0007\u0002\u0007\u0003\u0004\u0004\u0007", new Object[]{"onlyShowLocalPhoto_", "showHiddenAlbum_", "slideshowInterval_", "remindConnectNetworkEveryTime_"});
                    case GET_DEFAULT_INSTANCE:
                        return DEFAULT_INSTANCE;
                    case GET_PARSER:
                        Parser<Settings> parser = PARSER;
                        if (parser == null) {
                            synchronized (Settings.class) {
                                parser = PARSER;
                                if (parser == null) {
                                    parser = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                                    PARSER = parser;
                                }
                            }
                        }
                        return parser;
                    case GET_MEMOIZED_IS_INITIALIZED:
                        return (byte) 1;
                    case SET_MEMOIZED_IS_INITIALIZED:
                        return null;
                    default:
                        throw new UnsupportedOperationException();
                }
            }

            public boolean getOnlyShowLocalPhoto() {
                return this.onlyShowLocalPhoto_;
            }

            @Deprecated
            public boolean getRemindConnectNetworkEveryTime() {
                return this.remindConnectNetworkEveryTime_;
            }

            public boolean getShowHiddenAlbum() {
                return this.showHiddenAlbum_;
            }

            public int getSlideshowInterval() {
                return this.slideshowInterval_;
            }
        }

        public interface SettingsOrBuilder extends MessageLiteOrBuilder {
            boolean getOnlyShowLocalPhoto();

            @Deprecated
            boolean getRemindConnectNetworkEveryTime();

            boolean getShowHiddenAlbum();

            int getSlideshowInterval();
        }

        static {
            GeneratedMessageLite.registerDefaultInstance(BackupMessage.class, DEFAULT_INSTANCE);
        }

        private BackupMessage() {
        }

        /* access modifiers changed from: private */
        public void addAlbumProfiles(int i, AlbumProfile.Builder builder) {
            ensureAlbumProfilesIsMutable();
            this.albumProfiles_.add(i, builder.build());
        }

        /* access modifiers changed from: private */
        public void addAlbumProfiles(int i, AlbumProfile albumProfile) {
            if (albumProfile != null) {
                ensureAlbumProfilesIsMutable();
                this.albumProfiles_.add(i, albumProfile);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void addAlbumProfiles(AlbumProfile.Builder builder) {
            ensureAlbumProfilesIsMutable();
            this.albumProfiles_.add(builder.build());
        }

        /* access modifiers changed from: private */
        public void addAlbumProfiles(AlbumProfile albumProfile) {
            if (albumProfile != null) {
                ensureAlbumProfilesIsMutable();
                this.albumProfiles_.add(albumProfile);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void addAllAlbumProfiles(Iterable<? extends AlbumProfile> iterable) {
            ensureAlbumProfilesIsMutable();
            AbstractMessageLite.addAll(iterable, this.albumProfiles_);
        }

        /* access modifiers changed from: private */
        public void clearAlbumProfiles() {
            this.albumProfiles_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        public void clearSettings() {
            this.settings_ = null;
        }

        private void ensureAlbumProfilesIsMutable() {
            if (!this.albumProfiles_.isModifiable()) {
                this.albumProfiles_ = GeneratedMessageLite.mutableCopy(this.albumProfiles_);
            }
        }

        public static BackupMessage getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* access modifiers changed from: private */
        public void mergeSettings(Settings settings) {
            if (settings != null) {
                Settings settings2 = this.settings_;
                if (settings2 == null || settings2 == Settings.getDefaultInstance()) {
                    this.settings_ = settings;
                } else {
                    this.settings_ = (Settings) ((Settings.Builder) Settings.newBuilder(this.settings_).mergeFrom(settings)).buildPartial();
                }
            } else {
                throw new NullPointerException();
            }
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(BackupMessage backupMessage) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(backupMessage);
        }

        public static BackupMessage parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (BackupMessage) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static BackupMessage parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (BackupMessage) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static BackupMessage parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (BackupMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static BackupMessage parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (BackupMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static BackupMessage parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (BackupMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static BackupMessage parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (BackupMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static BackupMessage parseFrom(InputStream inputStream) throws IOException {
            return (BackupMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static BackupMessage parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (BackupMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static BackupMessage parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (BackupMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static BackupMessage parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (BackupMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static BackupMessage parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (BackupMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static BackupMessage parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (BackupMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static Parser<BackupMessage> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* access modifiers changed from: private */
        public void removeAlbumProfiles(int i) {
            ensureAlbumProfilesIsMutable();
            this.albumProfiles_.remove(i);
        }

        /* access modifiers changed from: private */
        public void setAlbumProfiles(int i, AlbumProfile.Builder builder) {
            ensureAlbumProfilesIsMutable();
            this.albumProfiles_.set(i, builder.build());
        }

        /* access modifiers changed from: private */
        public void setAlbumProfiles(int i, AlbumProfile albumProfile) {
            if (albumProfile != null) {
                ensureAlbumProfilesIsMutable();
                this.albumProfiles_.set(i, albumProfile);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void setSettings(Settings.Builder builder) {
            this.settings_ = (Settings) builder.build();
        }

        /* access modifiers changed from: private */
        public void setSettings(Settings settings) {
            if (settings != null) {
                this.settings_ = settings;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new BackupMessage();
                case NEW_BUILDER:
                    return new Builder();
                case BUILD_MESSAGE_INFO:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001\t\u0002\u001b", new Object[]{"settings_", "albumProfiles_", AlbumProfile.class});
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser<BackupMessage> parser = PARSER;
                    if (parser == null) {
                        synchronized (BackupMessage.class) {
                            parser = PARSER;
                            if (parser == null) {
                                parser = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                                PARSER = parser;
                            }
                        }
                    }
                    return parser;
                case GET_MEMOIZED_IS_INITIALIZED:
                    return (byte) 1;
                case SET_MEMOIZED_IS_INITIALIZED:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public AlbumProfile getAlbumProfiles(int i) {
            return (AlbumProfile) this.albumProfiles_.get(i);
        }

        public int getAlbumProfilesCount() {
            return this.albumProfiles_.size();
        }

        public List<AlbumProfile> getAlbumProfilesList() {
            return this.albumProfiles_;
        }

        public AlbumProfileOrBuilder getAlbumProfilesOrBuilder(int i) {
            return (AlbumProfileOrBuilder) this.albumProfiles_.get(i);
        }

        public List<? extends AlbumProfileOrBuilder> getAlbumProfilesOrBuilderList() {
            return this.albumProfiles_;
        }

        public Settings getSettings() {
            Settings settings = this.settings_;
            return settings == null ? Settings.getDefaultInstance() : settings;
        }

        public boolean hasSettings() {
            return this.settings_ != null;
        }
    }

    public interface BackupMessageOrBuilder extends MessageLiteOrBuilder {
        BackupMessage.AlbumProfile getAlbumProfiles(int i);

        int getAlbumProfilesCount();

        List<BackupMessage.AlbumProfile> getAlbumProfilesList();

        BackupMessage.Settings getSettings();

        boolean hasSettings();
    }

    private GalleryBackupProtos() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }
}
