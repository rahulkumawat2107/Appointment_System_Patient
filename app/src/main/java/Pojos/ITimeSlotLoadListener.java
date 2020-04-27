package Pojos;

import java.util.List;

public interface ITimeSlotLoadListener {

    void onTimeSlotLoadSuccess(List<TimeSlot> timeSlotMorningList);
    void onTimeSlotLoadFailure(String message);
    void onTimeSlotLoadEmpty();
}
