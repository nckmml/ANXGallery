package com.xiaomi.measite.smack;

import com.xiaomi.channel.commonutils.android.SystemUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.slim.Blob;
import com.xiaomi.smack.Connection;
import com.xiaomi.smack.ConnectionListener;
import com.xiaomi.smack.PacketListener;
import com.xiaomi.smack.debugger.SmackDebugger;
import com.xiaomi.smack.filter.PacketFilter;
import com.xiaomi.smack.packet.Packet;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AndroidDebugger implements SmackDebugger {
    public static boolean printInterpreted;
    private final String TAG = "[Slim] ";
    private ConnectionListener connListener = null;
    /* access modifiers changed from: private */
    public Connection connection = null;
    /* access modifiers changed from: private */
    public SimpleDateFormat dateFormatter = new SimpleDateFormat("hh:mm:ss aaa");
    private Listener readListener = null;
    private Listener writeListener = null;

    class Listener implements PacketListener, PacketFilter {
        String rcvOrSent;

        Listener(boolean z) {
            this.rcvOrSent = z ? " RCV " : " Sent ";
        }

        public boolean accept(Packet packet) {
            return true;
        }

        public void process(Blob blob) {
            if (AndroidDebugger.printInterpreted) {
                MyLog.v("[Slim] " + AndroidDebugger.this.dateFormatter.format(new Date()) + this.rcvOrSent + blob.toString());
                return;
            }
            MyLog.v("[Slim] " + AndroidDebugger.this.dateFormatter.format(new Date()) + this.rcvOrSent + " Blob [" + blob.getCmd() + "," + blob.getChannelId() + "," + blob.getPacketID() + "]");
        }

        public void processPacket(Packet packet) {
            if (AndroidDebugger.printInterpreted) {
                MyLog.v("[Slim] " + AndroidDebugger.this.dateFormatter.format(new Date()) + this.rcvOrSent + " PKT " + packet.toXML());
                return;
            }
            MyLog.v("[Slim] " + AndroidDebugger.this.dateFormatter.format(new Date()) + this.rcvOrSent + " PKT [" + packet.getChannelId() + "," + packet.getPacketID() + "]");
        }
    }

    static {
        boolean z = true;
        if (SystemUtils.getMIUIType() != 1) {
            z = false;
        }
        printInterpreted = z;
    }

    public AndroidDebugger(Connection connection2) {
        this.connection = connection2;
        createDebug();
    }

    private void createDebug() {
        this.readListener = new Listener(true);
        this.writeListener = new Listener(false);
        Connection connection2 = this.connection;
        Listener listener = this.readListener;
        connection2.addPacketListener(listener, listener);
        Connection connection3 = this.connection;
        Listener listener2 = this.writeListener;
        connection3.addPacketSendingListener(listener2, listener2);
        this.connListener = new ConnectionListener() {
            public void connectionClosed(Connection connection, int i, Exception exc) {
                MyLog.v("[Slim] " + AndroidDebugger.this.dateFormatter.format(new Date()) + " Connection closed (" + AndroidDebugger.this.connection.hashCode() + ")");
            }

            public void connectionStarted(Connection connection) {
                MyLog.v("[Slim] " + AndroidDebugger.this.dateFormatter.format(new Date()) + " Connection started (" + AndroidDebugger.this.connection.hashCode() + ")");
            }

            public void reconnectionFailed(Connection connection, Exception exc) {
                MyLog.v("[Slim] " + AndroidDebugger.this.dateFormatter.format(new Date()) + " Reconnection failed due to an exception (" + AndroidDebugger.this.connection.hashCode() + ")");
                exc.printStackTrace();
            }

            public void reconnectionSuccessful(Connection connection) {
                MyLog.v("[Slim] " + AndroidDebugger.this.dateFormatter.format(new Date()) + " Connection reconnected (" + AndroidDebugger.this.connection.hashCode() + ")");
            }
        };
    }
}
