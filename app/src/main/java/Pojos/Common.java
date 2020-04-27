package Pojos;

import java.util.Calendar;

public class Common {
    public static final int TIME_SLOT_TOTAL = 16;
    public static final Object DISABLE_TAG = "DISABLE";
    public static final String KEY_TIME_SLOT = "TIME_SLOT";
    public static final String KEY_STEP = "STEP";
    public static final String KEY_ENABLE_BUTTON_NEXT = "ENABLE_BUTTON_NEXT";
    public static final String KEY_CONFIRM_BOOKING = "CONFIRM_BOOKING";
    public static int step = 0;
    public static int currentTimeSlot =-1;
    public static Calendar bookingDate=Calendar.getInstance();

    public static String convertTimeSlotToString(int slot) {
        switch (slot)
        {
            case 0: return "9:00 AM";
            case 1: return "9:15 AM";
            case 2: return "9:30 AM";
            case 3: return "9:45 AM";
            case 4: return "10:00 AM";
            case 5: return "10:15 AM";
            case 6: return "10:30 AM";
            case 7: return "10:45 AM";
            case 8: return "11:00 AM";
            case 9: return "11:15 AM";
            case 10: return "11:30 AM";
            case 11: return "11:45 AM";
            case 12: return "12:00 AM";
            case 13: return "12:15 AM";
            case 14: return "12:30 AM";
            case 15: return "12:45 AM";
            default: return "Closed";
        }
    }
}
