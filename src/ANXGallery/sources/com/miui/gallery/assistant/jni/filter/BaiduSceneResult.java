package com.miui.gallery.assistant.jni.filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BaiduSceneResult implements ISceneResult {
    public static final int ACCOUNT_BOOK = 129;
    public static final int AIRCRAFT_AIRPORT = 122;
    public static final int AMUSEMENT_PARK = 54;
    public static final int ANCIENT_CHINESE_ARCHITECTURE = 108;
    public static final int ANIMAL_OTHER = 45;
    public static final int APPAREL = 136;
    public static final int AQUARIUM = 63;
    public static final int BABY = 0;
    public static final int BADMINTON = 87;
    public static final int BANK_CARD = 127;
    public static final int BAR = 57;
    public static final int BARBECUE = 10;
    public static final int BASKETBALL = 85;
    public static final int BEACH = 25;
    public static final int BED = 67;
    public static final int BICYCLE = 119;
    public static final int BILLIARDS = 90;
    public static final int BIRDS = 42;
    public static final int BLACK_WHITE = 143;
    public static final int BOWLING = 89;
    public static final int BOXING = 100;
    public static final int BREAD = 8;
    public static final int BRIDGE = 114;
    public static final int BUILDING_OTHER = 115;
    public static final int BUSINESS_CARD = 128;
    public static final int CABINET = 71;
    public static final int CANYON = 36;
    public static final int CAR = 116;
    public static final int CARTOON = 140;
    public static final int CASSEROLE_CASSEROLE = 20;
    public static final int CASTLE = 110;
    public static final int CAT = 40;
    public static final int CHURCH = 109;
    public static final int CINEMA = 55;
    public static final int COSMETICS = 138;
    public static final int DANCE = 96;
    public static final int DECORATION_RELATED = 75;
    public static final int DESERT = 33;
    public static final int DESSERT = 6;
    public static final int DESSERT_OTHER = 7;
    public static final int DIGITAL_PRODUCT = 142;
    public static final int DOG = 41;
    public static final int DRINK = 13;
    public static final int ENTERTAINMENT_OTHER = 65;
    public static final int EXPRESS_ORDER = 133;
    public static final int FASHION_OTHER = 139;
    public static final int FERRY = 117;
    public static final int FILE_OTHER = 135;
    public static final int FISH = 44;
    public static final int FITNESS = 82;
    public static final int FLOWER = 46;
    public static final int FOOD_OTHER = 22;
    public static final int FOOTBALL = 84;
    public static final int FOREST = 26;
    public static final int FOUNTAIN = 113;
    public static final int FRUIT = 12;
    public static final int FRYING = 21;
    public static final int GAME = 141;
    public static final int GAMING_ROOM = 58;
    public static final int GARDEN = 107;
    public static final int GOLF = 98;
    public static final int GRASS = 47;
    public static final int GRASSLAND = 31;
    public static final int GROUP_PHOTO = 2;
    public static final int HOT_POT = 9;
    public static final int HOUSEHOLD_APPLIANCES = 73;
    public static final int HOUSE_PLANT = 74;
    public static final int ICE_CREAM = 4;
    public static final int ID_CARD = 126;
    public static final int INDOOR_OTHER = 79;
    public static final int INSECT = 43;
    public static final int INTERIOR_DOOR = 69;
    public static final int INVOICE = 131;
    public static final int JAPANESE_CUISINE = 11;
    public static final int JEWELRY = 137;
    public static final int KTV = 56;
    public static final int LAKES_RIVERS = 27;
    public static final int LIGHT = 72;
    public static final int MALL = 53;
    public static final int MEETING_ROOM = 76;
    public static final int MOTORCYCLE = 120;
    public static final int MOUNTAIN = 35;
    public static final int MOUNTAINEERING = 103;
    public static final int NATURAL_SCENERY = 39;
    public static final int NIGHT_VIEW = 23;
    public static final int NOODLES_OTHER = 18;
    public static final int OCEAN = 24;
    public static final int PALACE = 106;
    public static final int PARACHUTE = 94;
    public static final int PARK = 38;
    public static final int PASTA = 17;
    public static final int PERSON = 3;
    public static final int PIER = 124;
    public static final int PINGPONG = 88;
    public static final int PLANT_OTHER = 49;
    public static final int PORRIDGE = 19;
    public static final int REPORT_HALL = 78;
    public static final int RESTAURANT = 61;
    public static final int RIDING = 91;
    public static final int RUN = 83;
    public static final int SCREEN_TEXT = 134;
    public static final int SEAFOOD = 14;
    public static final int SELFIE = 1;
    public static final int SHOOTING = 101;
    public static final int SHOW = 52;
    public static final int SKATEBOARD = 97;
    public static final int SKI = 80;
    public static final int SKY = 29;
    public static final int SNACKS = 5;
    public static final int SNORKELING_DIVE = 93;
    public static final int SNOW = 28;
    public static final int SOFA = 66;
    public static final int SPA = 64;
    public static final int SPORTS_OTHER = 104;
    public static final int STARRY_SKY = 32;
    public static final int STATION = 121;
    public static final int STREET_VIEW = 112;
    public static final int SUBWAY = 118;
    public static final int SUNRISE_SUNSET = 30;
    public static final int SUPERMARKET = 62;
    public static final int SURF = 92;
    public static final int SWIM = 81;
    public static final int SWIMMING_POOL = 60;
    public static final int TABLE = 70;
    public static final int TAEKWONDO = 102;
    public static final int TAG_COUNT = 144;
    public static final int TEMPLE = 105;
    public static final int TENNIS = 86;
    public static final int TOGETHER = 51;
    public static final int TRAIN = 123;
    public static final int TRAVEL_OTHER = 125;
    public static final int TREES_LEAVES = 48;
    public static final int UNDERWATER = 37;
    public static final int VARIOUS_TICKETS = 132;
    public static final int VISA = 130;
    public static final int VOLLEYBALL = 99;
    public static final int WATERFALL = 34;
    public static final int WEDDING = 50;
    public static final int WESTERN_ARCHITECTURE = 111;
    public static final int WESTERN_FOOD = 15;
    public static final int WHEATEN_FOOD = 16;
    public static final int WINDOW = 68;
    public static final int WORKPLACE = 77;
    public static final int YOGA = 95;
    public static final int ZOO = 59;
    public final int classNum;
    public final float[] classProb;
    public float[] tagThresholds;

    public BaiduSceneResult(int i, float[] fArr) {
        this.classNum = i;
        this.classProb = fArr;
        initThresholds();
    }

    private int[] getTopKIndex(float[] fArr, int i) {
        float[] copyOf = Arrays.copyOf(fArr, fArr.length);
        int[] iArr = new int[copyOf.length];
        for (int i2 = 0; i2 < iArr.length; i2++) {
            iArr[i2] = i2;
        }
        for (int i3 = 0; i3 < i; i3++) {
            int i4 = i3;
            int i5 = i4;
            while (i4 < copyOf.length) {
                if (copyOf[i5] < copyOf[i4]) {
                    i5 = i4;
                }
                i4++;
            }
            float f = copyOf[i3];
            copyOf[i3] = copyOf[i5];
            copyOf[i5] = f;
            int i6 = iArr[i3];
            iArr[i3] = iArr[i5];
            iArr[i5] = i6;
        }
        int[] iArr2 = new int[i];
        System.arraycopy(iArr, 0, iArr2, 0, i);
        return iArr2;
    }

    private void initThresholds() {
        this.tagThresholds = new float[TAG_COUNT];
        int i = 0;
        while (true) {
            float[] fArr = this.tagThresholds;
            if (i < fArr.length) {
                fArr[i] = 0.0f;
                i++;
            } else {
                fArr[4] = 0.8f;
                fArr[5] = 0.1f;
                fArr[6] = 0.1f;
                fArr[7] = 0.1f;
                fArr[8] = 0.1f;
                fArr[9] = 0.5f;
                fArr[10] = 0.5f;
                fArr[11] = 0.6f;
                fArr[12] = 0.8f;
                fArr[13] = 0.8f;
                fArr[14] = 0.6f;
                fArr[15] = 0.6f;
                fArr[16] = 0.6f;
                fArr[17] = 0.6f;
                fArr[18] = 0.8f;
                fArr[19] = 0.8f;
                fArr[20] = 0.8f;
                fArr[21] = 0.8f;
                fArr[22] = 0.8f;
                fArr[40] = 0.85f;
                fArr[41] = 0.85f;
                fArr[2] = 0.1f;
                fArr[51] = 0.3f;
                fArr[52] = 0.6f;
                fArr[56] = 0.07f;
                fArr[57] = 0.04f;
                fArr[80] = 0.5f;
                fArr[81] = 0.5f;
                fArr[82] = 0.2f;
                fArr[84] = 0.2f;
                fArr[85] = 0.2f;
                fArr[86] = 0.2f;
                fArr[87] = 0.2f;
                fArr[88] = 0.2f;
                fArr[90] = 0.5f;
                fArr[92] = 0.5f;
                fArr[99] = 0.03f;
                fArr[103] = 0.5f;
                fArr[126] = 0.001f;
                fArr[127] = 0.001f;
                fArr[128] = 0.001f;
                fArr[129] = 0.001f;
                fArr[130] = 0.001f;
                fArr[131] = 0.001f;
                fArr[132] = 0.001f;
                fArr[133] = 0.001f;
                fArr[134] = 0.001f;
                fArr[135] = 0.001f;
                return;
            }
        }
    }

    public int getClassNum() {
        return this.classNum;
    }

    public float[] getClassProb() {
        return this.classProb;
    }

    public List<Integer> getTags(int i) {
        if (i <= 0) {
            i = 3;
        }
        return getValidTopKTag(i);
    }

    public List<Integer> getValidTopKTag(int i) {
        int i2;
        ArrayList arrayList = new ArrayList(i);
        float[] fArr = this.classProb;
        if (fArr != null && fArr.length == (i2 = this.classNum) && i > 0 && i < i2) {
            float[] copyOf = Arrays.copyOf(fArr, fArr.length);
            for (int i3 = 0; i3 < copyOf.length; i3++) {
                if (copyOf[i3] < this.tagThresholds[i3]) {
                    copyOf[i3] = 0.0f;
                }
            }
            for (int i4 : getTopKIndex(copyOf, i)) {
                if (copyOf[i4] > 0.0f) {
                    arrayList.add(Integer.valueOf(i4));
                }
            }
        }
        return arrayList;
    }
}
