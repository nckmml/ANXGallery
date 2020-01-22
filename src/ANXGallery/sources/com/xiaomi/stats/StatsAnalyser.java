package com.xiaomi.stats;

import com.xiaomi.push.thrift.ChannelStatsType;
import com.xiaomi.smack.ConnectionHelper;
import com.xiaomi.smack.XMPPException;
import java.net.UnknownHostException;

final class StatsAnalyser {

    static class TypeWraper {
        String annotation;
        ChannelStatsType type;

        TypeWraper() {
        }
    }

    private static void checkNull(Exception exc) {
        if (exc == null) {
            throw new NullPointerException();
        }
    }

    static TypeWraper fromBind(Exception exc) {
        checkNull(exc);
        boolean z = exc instanceof XMPPException;
        Throwable th = exc;
        if (z) {
            XMPPException xMPPException = (XMPPException) exc;
            th = exc;
            if (xMPPException.getWrappedThrowable() != null) {
                th = xMPPException.getWrappedThrowable();
            }
        }
        TypeWraper typeWraper = new TypeWraper();
        String message = th.getMessage();
        if (th.getCause() != null) {
            message = th.getCause().getMessage();
        }
        int asErrorCode = ConnectionHelper.asErrorCode(th);
        String str = th.getClass().getSimpleName() + ":" + message;
        if (asErrorCode == 105) {
            typeWraper.type = ChannelStatsType.BIND_TCP_READ_TIMEOUT;
        } else if (asErrorCode == 199) {
            typeWraper.type = ChannelStatsType.BIND_TCP_ERR;
        } else if (asErrorCode == 499) {
            typeWraper.type = ChannelStatsType.BIND_BOSH_ERR;
            if (message.startsWith("Terminal binding condition encountered: item-not-found")) {
                typeWraper.type = ChannelStatsType.BIND_BOSH_ITEM_NOT_FOUND;
            }
        } else if (asErrorCode == 109) {
            typeWraper.type = ChannelStatsType.BIND_TCP_CONNRESET;
        } else if (asErrorCode != 110) {
            typeWraper.type = ChannelStatsType.BIND_XMPP_ERR;
        } else {
            typeWraper.type = ChannelStatsType.BIND_TCP_BROKEN_PIPE;
        }
        if (typeWraper.type == ChannelStatsType.BIND_TCP_ERR || typeWraper.type == ChannelStatsType.BIND_XMPP_ERR || typeWraper.type == ChannelStatsType.BIND_BOSH_ERR) {
            typeWraper.annotation = str;
        }
        return typeWraper;
    }

    static TypeWraper fromConnectionException(Exception exc) {
        Throwable cause;
        checkNull(exc);
        boolean z = exc instanceof XMPPException;
        Throwable th = exc;
        if (z) {
            XMPPException xMPPException = (XMPPException) exc;
            th = exc;
            if (xMPPException.getWrappedThrowable() != null) {
                th = xMPPException.getWrappedThrowable();
            }
        }
        TypeWraper typeWraper = new TypeWraper();
        String message = th.getMessage();
        if (th.getCause() != null) {
            message = th.getCause().getMessage();
        }
        int asErrorCode = ConnectionHelper.asErrorCode(th);
        String str = th.getClass().getSimpleName() + ":" + message;
        if (asErrorCode != 0) {
            typeWraper.type = ChannelStatsType.findByValue(ChannelStatsType.CONN_SUCCESS.getValue() + asErrorCode);
            if (typeWraper.type == ChannelStatsType.CONN_BOSH_ERR && (cause = th.getCause()) != null && (cause instanceof UnknownHostException)) {
                typeWraper.type = ChannelStatsType.CONN_BOSH_UNKNOWNHOST;
            }
        } else {
            typeWraper.type = ChannelStatsType.CONN_XMPP_ERR;
        }
        if (typeWraper.type == ChannelStatsType.CONN_TCP_ERR_OTHER || typeWraper.type == ChannelStatsType.CONN_XMPP_ERR || typeWraper.type == ChannelStatsType.CONN_BOSH_ERR) {
            typeWraper.annotation = str;
        }
        return typeWraper;
    }

    static TypeWraper fromDisconnectEx(Exception exc) {
        checkNull(exc);
        boolean z = exc instanceof XMPPException;
        Throwable th = exc;
        if (z) {
            XMPPException xMPPException = (XMPPException) exc;
            th = exc;
            if (xMPPException.getWrappedThrowable() != null) {
                th = xMPPException.getWrappedThrowable();
            }
        }
        TypeWraper typeWraper = new TypeWraper();
        String message = th.getMessage();
        int asErrorCode = ConnectionHelper.asErrorCode(th);
        String str = th.getClass().getSimpleName() + ":" + message;
        if (asErrorCode == 105) {
            typeWraper.type = ChannelStatsType.CHANNEL_TCP_READTIMEOUT;
        } else if (asErrorCode == 199) {
            typeWraper.type = ChannelStatsType.CHANNEL_TCP_ERR;
        } else if (asErrorCode == 499) {
            typeWraper.type = ChannelStatsType.CHANNEL_BOSH_EXCEPTION;
            if (message.startsWith("Terminal binding condition encountered: item-not-found")) {
                typeWraper.type = ChannelStatsType.CHANNEL_BOSH_ITEMNOTFIND;
            }
        } else if (asErrorCode == 109) {
            typeWraper.type = ChannelStatsType.CHANNEL_TCP_CONNRESET;
        } else if (asErrorCode != 110) {
            typeWraper.type = ChannelStatsType.CHANNEL_XMPPEXCEPTION;
        } else {
            typeWraper.type = ChannelStatsType.CHANNEL_TCP_BROKEN_PIPE;
        }
        if (typeWraper.type == ChannelStatsType.CHANNEL_TCP_ERR || typeWraper.type == ChannelStatsType.CHANNEL_XMPPEXCEPTION || typeWraper.type == ChannelStatsType.CHANNEL_BOSH_EXCEPTION) {
            typeWraper.annotation = str;
        }
        return typeWraper;
    }

    static TypeWraper fromGslbException(Exception exc) {
        checkNull(exc);
        boolean z = exc instanceof XMPPException;
        Throwable th = exc;
        if (z) {
            XMPPException xMPPException = (XMPPException) exc;
            th = exc;
            if (xMPPException.getWrappedThrowable() != null) {
                th = xMPPException.getWrappedThrowable();
            }
        }
        TypeWraper typeWraper = new TypeWraper();
        String message = th.getMessage();
        if (th.getCause() != null) {
            message = th.getCause().getMessage();
        }
        String str = th.getClass().getSimpleName() + ":" + message;
        int asErrorCode = ConnectionHelper.asErrorCode(th);
        if (asErrorCode != 0) {
            typeWraper.type = ChannelStatsType.findByValue(ChannelStatsType.GSLB_REQUEST_SUCCESS.getValue() + asErrorCode);
        }
        if (typeWraper.type == null) {
            typeWraper.type = ChannelStatsType.GSLB_TCP_ERR_OTHER;
        }
        if (typeWraper.type == ChannelStatsType.GSLB_TCP_ERR_OTHER) {
            typeWraper.annotation = str;
        }
        return typeWraper;
    }
}
