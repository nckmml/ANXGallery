package com.google.gson_nex.stream;

import com.google.gson_nex.internal.JsonReaderInternalAccess;
import com.google.gson_nex.internal.bind.JsonTreeReader;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;

public class JsonReader implements Closeable {
    private static final long MIN_INCOMPLETE_INTEGER = -922337203685477580L;
    private static final char[] NON_EXECUTE_PREFIX = ")]}'\n".toCharArray();
    private static final int NUMBER_CHAR_DECIMAL = 3;
    private static final int NUMBER_CHAR_DIGIT = 2;
    private static final int NUMBER_CHAR_EXP_DIGIT = 7;
    private static final int NUMBER_CHAR_EXP_E = 5;
    private static final int NUMBER_CHAR_EXP_SIGN = 6;
    private static final int NUMBER_CHAR_FRACTION_DIGIT = 4;
    private static final int NUMBER_CHAR_NONE = 0;
    private static final int NUMBER_CHAR_SIGN = 1;
    private static final int PEEKED_BEGIN_ARRAY = 3;
    private static final int PEEKED_BEGIN_OBJECT = 1;
    private static final int PEEKED_BUFFERED = 11;
    private static final int PEEKED_DOUBLE_QUOTED = 9;
    private static final int PEEKED_DOUBLE_QUOTED_NAME = 13;
    private static final int PEEKED_END_ARRAY = 4;
    private static final int PEEKED_END_OBJECT = 2;
    private static final int PEEKED_EOF = 17;
    private static final int PEEKED_FALSE = 6;
    private static final int PEEKED_LONG = 15;
    private static final int PEEKED_NONE = 0;
    private static final int PEEKED_NULL = 7;
    private static final int PEEKED_NUMBER = 16;
    private static final int PEEKED_SINGLE_QUOTED = 8;
    private static final int PEEKED_SINGLE_QUOTED_NAME = 12;
    private static final int PEEKED_TRUE = 5;
    private static final int PEEKED_UNQUOTED = 10;
    private static final int PEEKED_UNQUOTED_NAME = 14;
    private final char[] buffer = new char[1024];
    private final Reader in;
    private boolean lenient = false;
    private int limit = 0;
    private int lineNumber = 0;
    private int lineStart = 0;
    /* access modifiers changed from: private */
    public int peeked = 0;
    private long peekedLong;
    private int peekedNumberLength;
    private String peekedString;
    private int pos = 0;
    private int[] stack = new int[32];
    private int stackSize = 0;

    static {
        JsonReaderInternalAccess.INSTANCE = new JsonReaderInternalAccess() {
            public void promoteNameToValue(JsonReader jsonReader) throws IOException {
                if (jsonReader instanceof JsonTreeReader) {
                    ((JsonTreeReader) jsonReader).promoteNameToValue();
                    return;
                }
                int access$000 = jsonReader.peeked;
                if (access$000 == 0) {
                    access$000 = jsonReader.doPeek();
                }
                if (access$000 == 13) {
                    int unused = jsonReader.peeked = 9;
                } else if (access$000 == 12) {
                    int unused2 = jsonReader.peeked = 8;
                } else if (access$000 == 14) {
                    int unused3 = jsonReader.peeked = 10;
                } else {
                    throw new IllegalStateException("Expected a name but was " + jsonReader.peek() + "  at line " + jsonReader.getLineNumber() + " column " + jsonReader.getColumnNumber());
                }
            }
        };
    }

    public JsonReader(Reader reader) {
        int[] iArr = this.stack;
        int i = this.stackSize;
        this.stackSize = i + 1;
        iArr[i] = 6;
        if (reader != null) {
            this.in = reader;
            return;
        }
        throw new NullPointerException("in == null");
    }

    private void checkLenient() throws IOException {
        if (!this.lenient) {
            throw syntaxError("Use JsonReader.setLenient(true) to accept malformed JSON");
        }
    }

    private void consumeNonExecutePrefix() throws IOException {
        nextNonWhitespace(true);
        this.pos--;
        int i = this.pos;
        char[] cArr = NON_EXECUTE_PREFIX;
        if (i + cArr.length <= this.limit || fillBuffer(cArr.length)) {
            int i2 = 0;
            while (true) {
                char[] cArr2 = NON_EXECUTE_PREFIX;
                if (i2 >= cArr2.length) {
                    this.pos += cArr2.length;
                    return;
                } else if (this.buffer[this.pos + i2] == cArr2[i2]) {
                    i2++;
                } else {
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public int doPeek() throws IOException {
        int nextNonWhitespace;
        int[] iArr = this.stack;
        int i = this.stackSize;
        int i2 = iArr[i - 1];
        if (i2 == 1) {
            iArr[i - 1] = 2;
        } else if (i2 == 2) {
            int nextNonWhitespace2 = nextNonWhitespace(true);
            if (nextNonWhitespace2 != 44) {
                if (nextNonWhitespace2 == 59) {
                    checkLenient();
                } else if (nextNonWhitespace2 == 93) {
                    this.peeked = 4;
                    return 4;
                } else {
                    throw syntaxError("Unterminated array");
                }
            }
        } else if (i2 == 3 || i2 == 5) {
            this.stack[this.stackSize - 1] = 4;
            if (i2 == 5 && (nextNonWhitespace = nextNonWhitespace(true)) != 44) {
                if (nextNonWhitespace == 59) {
                    checkLenient();
                } else if (nextNonWhitespace == 125) {
                    this.peeked = 2;
                    return 2;
                } else {
                    throw syntaxError("Unterminated object");
                }
            }
            int nextNonWhitespace3 = nextNonWhitespace(true);
            if (nextNonWhitespace3 == 34) {
                this.peeked = 13;
                return 13;
            } else if (nextNonWhitespace3 == 39) {
                checkLenient();
                this.peeked = 12;
                return 12;
            } else if (nextNonWhitespace3 != 125) {
                checkLenient();
                this.pos--;
                if (isLiteral((char) nextNonWhitespace3)) {
                    this.peeked = 14;
                    return 14;
                }
                throw syntaxError("Expected name");
            } else if (i2 != 5) {
                this.peeked = 2;
                return 2;
            } else {
                throw syntaxError("Expected name");
            }
        } else if (i2 == 4) {
            iArr[i - 1] = 5;
            int nextNonWhitespace4 = nextNonWhitespace(true);
            if (nextNonWhitespace4 != 58) {
                if (nextNonWhitespace4 == 61) {
                    checkLenient();
                    if (this.pos < this.limit || fillBuffer(1)) {
                        char[] cArr = this.buffer;
                        int i3 = this.pos;
                        if (cArr[i3] == '>') {
                            this.pos = i3 + 1;
                        }
                    }
                } else {
                    throw syntaxError("Expected ':'");
                }
            }
        } else if (i2 == 6) {
            if (this.lenient) {
                consumeNonExecutePrefix();
            }
            this.stack[this.stackSize - 1] = 7;
        } else if (i2 == 7) {
            if (nextNonWhitespace(false) == -1) {
                this.peeked = 17;
                return 17;
            }
            checkLenient();
            this.pos--;
        } else if (i2 == 8) {
            throw new IllegalStateException("JsonReader is closed");
        }
        int nextNonWhitespace5 = nextNonWhitespace(true);
        if (nextNonWhitespace5 == 34) {
            if (this.stackSize == 1) {
                checkLenient();
            }
            this.peeked = 9;
            return 9;
        } else if (nextNonWhitespace5 != 39) {
            if (!(nextNonWhitespace5 == 44 || nextNonWhitespace5 == 59)) {
                if (nextNonWhitespace5 == 91) {
                    this.peeked = 3;
                    return 3;
                } else if (nextNonWhitespace5 != 93) {
                    if (nextNonWhitespace5 != 123) {
                        this.pos--;
                        if (this.stackSize == 1) {
                            checkLenient();
                        }
                        int peekKeyword = peekKeyword();
                        if (peekKeyword != 0) {
                            return peekKeyword;
                        }
                        int peekNumber = peekNumber();
                        if (peekNumber != 0) {
                            return peekNumber;
                        }
                        if (isLiteral(this.buffer[this.pos])) {
                            checkLenient();
                            this.peeked = 10;
                            return 10;
                        }
                        throw syntaxError("Expected value");
                    }
                    this.peeked = 1;
                    return 1;
                } else if (i2 == 1) {
                    this.peeked = 4;
                    return 4;
                }
            }
            if (i2 == 1 || i2 == 2) {
                checkLenient();
                this.pos--;
                this.peeked = 7;
                return 7;
            }
            throw syntaxError("Unexpected value");
        } else {
            checkLenient();
            this.peeked = 8;
            return 8;
        }
    }

    private boolean fillBuffer(int i) throws IOException {
        int i2;
        char[] cArr = this.buffer;
        int i3 = this.lineStart;
        int i4 = this.pos;
        this.lineStart = i3 - i4;
        int i5 = this.limit;
        if (i5 != i4) {
            this.limit = i5 - i4;
            System.arraycopy(cArr, i4, cArr, 0, this.limit);
        } else {
            this.limit = 0;
        }
        this.pos = 0;
        do {
            Reader reader = this.in;
            int i6 = this.limit;
            int read = reader.read(cArr, i6, cArr.length - i6);
            if (read == -1) {
                return false;
            }
            this.limit += read;
            if (this.lineNumber == 0 && (i2 = this.lineStart) == 0 && this.limit > 0 && cArr[0] == 65279) {
                this.pos++;
                this.lineStart = i2 + 1;
                i++;
            }
        } while (this.limit < i);
        return true;
    }

    /* access modifiers changed from: private */
    public int getColumnNumber() {
        return (this.pos - this.lineStart) + 1;
    }

    /* access modifiers changed from: private */
    public int getLineNumber() {
        return this.lineNumber + 1;
    }

    private boolean isLiteral(char c) throws IOException {
        if (c == 9 || c == 10 || c == 12 || c == 13 || c == ' ') {
            return false;
        }
        if (c != '#') {
            if (c == ',') {
                return false;
            }
            if (!(c == '/' || c == '=')) {
                if (c == '{' || c == '}' || c == ':') {
                    return false;
                }
                if (c != ';') {
                    switch (c) {
                        case '[':
                        case ']':
                            return false;
                        case '\\':
                            break;
                        default:
                            return true;
                    }
                }
            }
        }
        checkLenient();
        return false;
    }

    private int nextNonWhitespace(boolean z) throws IOException {
        char[] cArr = this.buffer;
        int i = this.pos;
        int i2 = this.limit;
        while (true) {
            if (i == i2) {
                this.pos = i;
                if (fillBuffer(1)) {
                    i = this.pos;
                    i2 = this.limit;
                } else if (!z) {
                    return -1;
                } else {
                    throw new EOFException("End of input at line " + getLineNumber() + " column " + getColumnNumber());
                }
            }
            int i3 = i + 1;
            char c = cArr[i];
            if (c == 10) {
                this.lineNumber++;
                this.lineStart = i3;
            } else if (!(c == ' ' || c == 13 || c == 9)) {
                if (c == '/') {
                    this.pos = i3;
                    if (i3 == i2) {
                        this.pos--;
                        boolean fillBuffer = fillBuffer(2);
                        this.pos++;
                        if (!fillBuffer) {
                            return c;
                        }
                    }
                    checkLenient();
                    int i4 = this.pos;
                    char c2 = cArr[i4];
                    if (c2 == '*') {
                        this.pos = i4 + 1;
                        if (skipTo("*/")) {
                            i = this.pos + 2;
                            i2 = this.limit;
                        } else {
                            throw syntaxError("Unterminated comment");
                        }
                    } else if (c2 != '/') {
                        return c;
                    } else {
                        this.pos = i4 + 1;
                        skipToEndOfLine();
                        i = this.pos;
                        i2 = this.limit;
                    }
                } else if (c == '#') {
                    this.pos = i3;
                    checkLenient();
                    skipToEndOfLine();
                    i = this.pos;
                    i2 = this.limit;
                } else {
                    this.pos = i3;
                    return c;
                }
            }
            i = i3;
        }
    }

    private String nextQuotedValue(char c) throws IOException {
        char[] cArr = this.buffer;
        StringBuilder sb = new StringBuilder();
        do {
            int i = this.pos;
            int i2 = this.limit;
            while (true) {
                int i3 = i;
                while (i < i2) {
                    int i4 = i + 1;
                    char c2 = cArr[i];
                    if (c2 == c) {
                        this.pos = i4;
                        sb.append(cArr, i3, (i4 - i3) - 1);
                        return sb.toString();
                    } else if (c2 == '\\') {
                        this.pos = i4;
                        sb.append(cArr, i3, (i4 - i3) - 1);
                        sb.append(readEscapeCharacter());
                        i = this.pos;
                        i2 = this.limit;
                    } else {
                        if (c2 == 10) {
                            this.lineNumber++;
                            this.lineStart = i4;
                        }
                        i = i4;
                    }
                }
                sb.append(cArr, i3, i - i3);
                this.pos = i;
            }
        } while (fillBuffer(1));
        throw syntaxError("Unterminated string");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:32:0x004b, code lost:
        checkLenient();
     */
    private String nextUnquotedValue() throws IOException {
        int i;
        String str;
        int i2 = 0;
        StringBuilder sb = null;
        while (true) {
            i = 0;
            while (true) {
                int i3 = this.pos;
                if (i3 + i < this.limit) {
                    char c = this.buffer[i3 + i];
                    if (!(c == 9 || c == 10 || c == 12 || c == 13 || c == ' ')) {
                        if (c != '#') {
                            if (c != ',') {
                                if (!(c == '/' || c == '=')) {
                                    if (!(c == '{' || c == '}' || c == ':')) {
                                        if (c != ';') {
                                            switch (c) {
                                                case '[':
                                                case ']':
                                                    break;
                                                case '\\':
                                                    break;
                                                default:
                                                    i++;
                                                    break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else if (i >= this.buffer.length) {
                    if (sb == null) {
                        sb = new StringBuilder();
                    }
                    sb.append(this.buffer, this.pos, i);
                    this.pos += i;
                    if (!fillBuffer(1)) {
                    }
                } else if (fillBuffer(i + 1)) {
                }
            }
        }
        i2 = i;
        if (sb == null) {
            str = new String(this.buffer, this.pos, i2);
        } else {
            sb.append(this.buffer, this.pos, i2);
            str = sb.toString();
        }
        this.pos += i2;
        return str;
    }

    private int peekKeyword() throws IOException {
        String str;
        String str2;
        int i;
        char c = this.buffer[this.pos];
        if (c == 't' || c == 'T') {
            i = 5;
            str2 = "true";
            str = "TRUE";
        } else if (c == 'f' || c == 'F') {
            i = 6;
            str2 = "false";
            str = "FALSE";
        } else if (c != 'n' && c != 'N') {
            return 0;
        } else {
            i = 7;
            str2 = "null";
            str = "NULL";
        }
        int length = str2.length();
        for (int i2 = 1; i2 < length; i2++) {
            if (this.pos + i2 >= this.limit && !fillBuffer(i2 + 1)) {
                return 0;
            }
            char c2 = this.buffer[this.pos + i2];
            if (c2 != str2.charAt(i2) && c2 != str.charAt(i2)) {
                return 0;
            }
        }
        if ((this.pos + length < this.limit || fillBuffer(length + 1)) && isLiteral(this.buffer[this.pos + length])) {
            return 0;
        }
        this.pos += length;
        this.peeked = i;
        return i;
    }

    private int peekNumber() throws IOException {
        char c;
        char[] cArr = this.buffer;
        int i = this.pos;
        int i2 = 0;
        int i3 = this.limit;
        boolean z = true;
        int i4 = 0;
        char c2 = 0;
        boolean z2 = false;
        long j = 0;
        while (true) {
            if (i + i4 == i3) {
                if (i4 == cArr.length) {
                    return i2;
                }
                if (!fillBuffer(i4 + 1)) {
                    break;
                }
                i = this.pos;
                i3 = this.limit;
            }
            c = cArr[i + i4];
            char c3 = 3;
            if (c == '+') {
                c3 = 6;
                i2 = 0;
                if (c2 != 5) {
                    return 0;
                }
            } else if (c == 'E' || c == 'e') {
                i2 = 0;
                if (c2 != 2 && c2 != 4) {
                    return 0;
                }
                c2 = 5;
                i4++;
            } else if (c == '-') {
                c3 = 6;
                i2 = 0;
                if (c2 == 0) {
                    c2 = 1;
                    z2 = true;
                    i4++;
                } else if (c2 != 5) {
                    return 0;
                }
            } else if (c == '.') {
                i2 = 0;
                if (c2 != 2) {
                    return 0;
                }
            } else if (c >= '0' && c <= '9') {
                if (c2 == 1 || c2 == 0) {
                    j = (long) (-(c - '0'));
                    c2 = 2;
                } else if (c2 != 2) {
                    if (c2 == 3) {
                        i2 = 0;
                        c2 = 4;
                    } else if (c2 == 5 || c2 == 6) {
                        i2 = 0;
                        c2 = 7;
                    }
                    i4++;
                } else if (j == 0) {
                    return 0;
                } else {
                    long j2 = (10 * j) - ((long) (c - '0'));
                    int i5 = (j > MIN_INCOMPLETE_INTEGER ? 1 : (j == MIN_INCOMPLETE_INTEGER ? 0 : -1));
                    boolean z3 = i5 > 0 || (i5 == 0 && j2 < j);
                    j = j2;
                    z = z3 & z;
                }
                i2 = 0;
                i4++;
            }
            c2 = c3;
            i4++;
        }
        if (isLiteral(c)) {
            return 0;
        }
        if (c2 == 2 && z && (j != Long.MIN_VALUE || z2)) {
            if (!z2) {
                j = -j;
            }
            this.peekedLong = j;
            this.pos += i4;
            this.peeked = 15;
            return 15;
        } else if (c2 != 2 && c2 != 4 && c2 != 7) {
            return 0;
        } else {
            this.peekedNumberLength = i4;
            this.peeked = 16;
            return 16;
        }
    }

    private void push(int i) {
        int i2 = this.stackSize;
        int[] iArr = this.stack;
        if (i2 == iArr.length) {
            int[] iArr2 = new int[(i2 * 2)];
            System.arraycopy(iArr, 0, iArr2, 0, i2);
            this.stack = iArr2;
        }
        int[] iArr3 = this.stack;
        int i3 = this.stackSize;
        this.stackSize = i3 + 1;
        iArr3[i3] = i;
    }

    private char readEscapeCharacter() throws IOException {
        int i;
        int i2;
        if (this.pos != this.limit || fillBuffer(1)) {
            char[] cArr = this.buffer;
            int i3 = this.pos;
            this.pos = i3 + 1;
            char c = cArr[i3];
            if (c == 10) {
                this.lineNumber++;
                this.lineStart = this.pos;
            } else if (c == 'b') {
                return 8;
            } else {
                if (c == 'f') {
                    return 12;
                }
                if (c == 'n') {
                    return 10;
                }
                if (c == 'r') {
                    return 13;
                }
                if (c == 't') {
                    return 9;
                }
                if (c == 'u') {
                    if (this.pos + 4 <= this.limit || fillBuffer(4)) {
                        char c2 = 0;
                        int i4 = this.pos;
                        int i5 = i4 + 4;
                        while (i4 < i5) {
                            char c3 = this.buffer[i4];
                            char c4 = (char) (c2 << 4);
                            if (c3 < '0' || c3 > '9') {
                                if (c3 >= 'a' && c3 <= 'f') {
                                    i = c3 - 'a';
                                } else if (c3 < 'A' || c3 > 'F') {
                                    throw new NumberFormatException("\\u" + new String(this.buffer, this.pos, 4));
                                } else {
                                    i = c3 - 'A';
                                }
                                i2 = i + 10;
                            } else {
                                i2 = c3 - '0';
                            }
                            c2 = (char) (c4 + i2);
                            i4++;
                        }
                        this.pos += 4;
                        return c2;
                    }
                    throw syntaxError("Unterminated escape sequence");
                }
            }
            return c;
        }
        throw syntaxError("Unterminated escape sequence");
    }

    private void skipQuotedValue(char c) throws IOException {
        char[] cArr = this.buffer;
        do {
            int i = this.pos;
            int i2 = this.limit;
            while (i < i2) {
                int i3 = i + 1;
                char c2 = cArr[i];
                if (c2 == c) {
                    this.pos = i3;
                    return;
                } else if (c2 == '\\') {
                    this.pos = i3;
                    readEscapeCharacter();
                    i = this.pos;
                    i2 = this.limit;
                } else {
                    if (c2 == 10) {
                        this.lineNumber++;
                        this.lineStart = i3;
                    }
                    i = i3;
                }
            }
            this.pos = i;
        } while (fillBuffer(1));
        throw syntaxError("Unterminated string");
    }

    private boolean skipTo(String str) throws IOException {
        while (true) {
            int i = 0;
            if (this.pos + str.length() > this.limit && !fillBuffer(str.length())) {
                return false;
            }
            char[] cArr = this.buffer;
            int i2 = this.pos;
            if (cArr[i2] == 10) {
                this.lineNumber++;
                this.lineStart = i2 + 1;
            } else {
                while (i < str.length()) {
                    if (this.buffer[this.pos + i] == str.charAt(i)) {
                        i++;
                    }
                }
                return true;
            }
            this.pos++;
        }
    }

    private void skipToEndOfLine() throws IOException {
        char c;
        do {
            if (this.pos < this.limit || fillBuffer(1)) {
                char[] cArr = this.buffer;
                int i = this.pos;
                this.pos = i + 1;
                c = cArr[i];
                if (c == 10) {
                    this.lineNumber++;
                    this.lineStart = this.pos;
                    return;
                }
            } else {
                return;
            }
        } while (c != 13);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0048, code lost:
        checkLenient();
     */
    private void skipUnquotedValue() throws IOException {
        do {
            int i = 0;
            while (true) {
                int i2 = this.pos;
                if (i2 + i < this.limit) {
                    char c = this.buffer[i2 + i];
                    if (!(c == 9 || c == 10 || c == 12 || c == 13 || c == ' ')) {
                        if (c != '#') {
                            if (c != ',') {
                                if (!(c == '/' || c == '=')) {
                                    if (!(c == '{' || c == '}' || c == ':')) {
                                        if (c != ';') {
                                            switch (c) {
                                                case '[':
                                                case ']':
                                                    break;
                                                case '\\':
                                                    break;
                                                default:
                                                    i++;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {
                    this.pos = i2 + i;
                }
            }
            this.pos += i;
            return;
        } while (fillBuffer(1));
    }

    private IOException syntaxError(String str) throws IOException {
        throw new MalformedJsonException(str + " at line " + getLineNumber() + " column " + getColumnNumber());
    }

    public void beginArray() throws IOException {
        int i = this.peeked;
        if (i == 0) {
            i = doPeek();
        }
        if (i == 3) {
            push(1);
            this.peeked = 0;
            return;
        }
        throw new IllegalStateException("Expected BEGIN_ARRAY but was " + peek() + " at line " + getLineNumber() + " column " + getColumnNumber());
    }

    public void beginObject() throws IOException {
        int i = this.peeked;
        if (i == 0) {
            i = doPeek();
        }
        if (i == 1) {
            push(3);
            this.peeked = 0;
            return;
        }
        throw new IllegalStateException("Expected BEGIN_OBJECT but was " + peek() + " at line " + getLineNumber() + " column " + getColumnNumber());
    }

    public void close() throws IOException {
        this.peeked = 0;
        this.stack[0] = 8;
        this.stackSize = 1;
        this.in.close();
    }

    public void endArray() throws IOException {
        int i = this.peeked;
        if (i == 0) {
            i = doPeek();
        }
        if (i == 4) {
            this.stackSize--;
            this.peeked = 0;
            return;
        }
        throw new IllegalStateException("Expected END_ARRAY but was " + peek() + " at line " + getLineNumber() + " column " + getColumnNumber());
    }

    public void endObject() throws IOException {
        int i = this.peeked;
        if (i == 0) {
            i = doPeek();
        }
        if (i == 2) {
            this.stackSize--;
            this.peeked = 0;
            return;
        }
        throw new IllegalStateException("Expected END_OBJECT but was " + peek() + " at line " + getLineNumber() + " column " + getColumnNumber());
    }

    public boolean hasNext() throws IOException {
        int i = this.peeked;
        if (i == 0) {
            i = doPeek();
        }
        return (i == 2 || i == 4) ? false : true;
    }

    public final boolean isLenient() {
        return this.lenient;
    }

    public boolean nextBoolean() throws IOException {
        int i = this.peeked;
        if (i == 0) {
            i = doPeek();
        }
        if (i == 5) {
            this.peeked = 0;
            return true;
        } else if (i == 6) {
            this.peeked = 0;
            return false;
        } else {
            throw new IllegalStateException("Expected a boolean but was " + peek() + " at line " + getLineNumber() + " column " + getColumnNumber());
        }
    }

    public double nextDouble() throws IOException {
        int i = this.peeked;
        if (i == 0) {
            i = doPeek();
        }
        if (i == 15) {
            this.peeked = 0;
            return (double) this.peekedLong;
        }
        if (i == 16) {
            this.peekedString = new String(this.buffer, this.pos, this.peekedNumberLength);
            this.pos += this.peekedNumberLength;
        } else if (i == 8 || i == 9) {
            this.peekedString = nextQuotedValue(i == 8 ? '\'' : '\"');
        } else if (i == 10) {
            this.peekedString = nextUnquotedValue();
        } else if (i != 11) {
            throw new IllegalStateException("Expected a double but was " + peek() + " at line " + getLineNumber() + " column " + getColumnNumber());
        }
        this.peeked = 11;
        double parseDouble = Double.parseDouble(this.peekedString);
        if (this.lenient || (!Double.isNaN(parseDouble) && !Double.isInfinite(parseDouble))) {
            this.peekedString = null;
            this.peeked = 0;
            return parseDouble;
        }
        throw new MalformedJsonException("JSON forbids NaN and infinities: " + parseDouble + " at line " + getLineNumber() + " column " + getColumnNumber());
    }

    public int nextInt() throws IOException {
        int i = this.peeked;
        if (i == 0) {
            i = doPeek();
        }
        if (i == 15) {
            long j = this.peekedLong;
            int i2 = (int) j;
            if (j == ((long) i2)) {
                this.peeked = 0;
                return i2;
            }
            throw new NumberFormatException("Expected an int but was " + this.peekedLong + " at line " + getLineNumber() + " column " + getColumnNumber());
        }
        if (i == 16) {
            this.peekedString = new String(this.buffer, this.pos, this.peekedNumberLength);
            this.pos += this.peekedNumberLength;
        } else if (i == 8 || i == 9) {
            this.peekedString = nextQuotedValue(i == 8 ? '\'' : '\"');
            try {
                int parseInt = Integer.parseInt(this.peekedString);
                this.peeked = 0;
                return parseInt;
            } catch (NumberFormatException unused) {
            }
        } else {
            throw new IllegalStateException("Expected an int but was " + peek() + " at line " + getLineNumber() + " column " + getColumnNumber());
        }
        this.peeked = 11;
        double parseDouble = Double.parseDouble(this.peekedString);
        int i3 = (int) parseDouble;
        if (((double) i3) == parseDouble) {
            this.peekedString = null;
            this.peeked = 0;
            return i3;
        }
        throw new NumberFormatException("Expected an int but was " + this.peekedString + " at line " + getLineNumber() + " column " + getColumnNumber());
    }

    public long nextLong() throws IOException {
        int i = this.peeked;
        if (i == 0) {
            i = doPeek();
        }
        if (i == 15) {
            this.peeked = 0;
            return this.peekedLong;
        }
        if (i == 16) {
            this.peekedString = new String(this.buffer, this.pos, this.peekedNumberLength);
            this.pos += this.peekedNumberLength;
        } else if (i == 8 || i == 9) {
            this.peekedString = nextQuotedValue(i == 8 ? '\'' : '\"');
            try {
                long parseLong = Long.parseLong(this.peekedString);
                this.peeked = 0;
                return parseLong;
            } catch (NumberFormatException unused) {
            }
        } else {
            throw new IllegalStateException("Expected a long but was " + peek() + " at line " + getLineNumber() + " column " + getColumnNumber());
        }
        this.peeked = 11;
        double parseDouble = Double.parseDouble(this.peekedString);
        long j = (long) parseDouble;
        if (((double) j) == parseDouble) {
            this.peekedString = null;
            this.peeked = 0;
            return j;
        }
        throw new NumberFormatException("Expected a long but was " + this.peekedString + " at line " + getLineNumber() + " column " + getColumnNumber());
    }

    public String nextName() throws IOException {
        String str;
        int i = this.peeked;
        if (i == 0) {
            i = doPeek();
        }
        if (i == 14) {
            str = nextUnquotedValue();
        } else if (i == 12) {
            str = nextQuotedValue('\'');
        } else if (i == 13) {
            str = nextQuotedValue('\"');
        } else {
            throw new IllegalStateException("Expected a name but was " + peek() + " at line " + getLineNumber() + " column " + getColumnNumber());
        }
        this.peeked = 0;
        return str;
    }

    public void nextNull() throws IOException {
        int i = this.peeked;
        if (i == 0) {
            i = doPeek();
        }
        if (i == 7) {
            this.peeked = 0;
            return;
        }
        throw new IllegalStateException("Expected null but was " + peek() + " at line " + getLineNumber() + " column " + getColumnNumber());
    }

    public String nextString() throws IOException {
        String str;
        int i = this.peeked;
        if (i == 0) {
            i = doPeek();
        }
        if (i == 10) {
            str = nextUnquotedValue();
        } else if (i == 8) {
            str = nextQuotedValue('\'');
        } else if (i == 9) {
            str = nextQuotedValue('\"');
        } else if (i == 11) {
            str = this.peekedString;
            this.peekedString = null;
        } else if (i == 15) {
            str = Long.toString(this.peekedLong);
        } else if (i == 16) {
            str = new String(this.buffer, this.pos, this.peekedNumberLength);
            this.pos += this.peekedNumberLength;
        } else {
            throw new IllegalStateException("Expected a string but was " + peek() + " at line " + getLineNumber() + " column " + getColumnNumber());
        }
        this.peeked = 0;
        return str;
    }

    public JsonToken peek() throws IOException {
        int i = this.peeked;
        if (i == 0) {
            i = doPeek();
        }
        switch (i) {
            case 1:
                return JsonToken.BEGIN_OBJECT;
            case 2:
                return JsonToken.END_OBJECT;
            case 3:
                return JsonToken.BEGIN_ARRAY;
            case 4:
                return JsonToken.END_ARRAY;
            case 5:
            case 6:
                return JsonToken.BOOLEAN;
            case 7:
                return JsonToken.NULL;
            case 8:
            case 9:
            case 10:
            case 11:
                return JsonToken.STRING;
            case 12:
            case 13:
            case 14:
                return JsonToken.NAME;
            case 15:
            case 16:
                return JsonToken.NUMBER;
            case 17:
                return JsonToken.END_DOCUMENT;
            default:
                throw new AssertionError();
        }
    }

    public final void setLenient(boolean z) {
        this.lenient = z;
    }

    public void skipValue() throws IOException {
        int i = 0;
        do {
            int i2 = this.peeked;
            if (i2 == 0) {
                i2 = doPeek();
            }
            if (i2 == 3) {
                push(1);
            } else if (i2 == 1) {
                push(3);
            } else {
                if (i2 == 4) {
                    this.stackSize--;
                } else if (i2 == 2) {
                    this.stackSize--;
                } else if (i2 == 14 || i2 == 10) {
                    skipUnquotedValue();
                    this.peeked = 0;
                } else if (i2 == 8 || i2 == 12) {
                    skipQuotedValue('\'');
                    this.peeked = 0;
                } else if (i2 == 9 || i2 == 13) {
                    skipQuotedValue('\"');
                    this.peeked = 0;
                } else {
                    if (i2 == 16) {
                        this.pos += this.peekedNumberLength;
                    }
                    this.peeked = 0;
                }
                i--;
                this.peeked = 0;
            }
            i++;
            this.peeked = 0;
        } while (i != 0);
    }

    public String toString() {
        return getClass().getSimpleName() + " at line " + getLineNumber() + " column " + getColumnNumber();
    }
}
