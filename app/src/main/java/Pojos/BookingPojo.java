package Pojos;

public class BookingPojo {

    private String name,phone,time;
    private Long slot;

    public BookingPojo() {
    }

    public BookingPojo(String name, String phone, String time, Long slot) {
        this.name = name;
        this.phone = phone;
        this.time = time;
        this.slot = slot;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Long getSlot() {
        return slot;
    }

    public void setSlot(Long slot) {
        this.slot = slot;
    }
}
