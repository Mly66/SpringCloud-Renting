package com.nbmly.renting.pojo;

// 房子详情
public class HousingManagement {
    private Long id; // 主键
    private String cityName; // 城市
    private String housingTitle; // 房屋标题
    private String address; // 详细地址
    private String groupName; // 图片组名
    private String fileName; // 图片名称
    private Double rent; // 租金
    private Integer state; // 状态:0(未租),1(已租)
    private Long housingModeId; // 出租方式ID
    private Integer room; // 室
    private Integer hall; // 厅
    private Long see; // 浏览人数

    // 出租方式
    private String mode;

    public HousingManagement() {
    }

    public HousingManagement(Long id, String cityName, String housingTitle, String address, String groupName,
            String fileName, Double rent, Integer state, Long housingModeId, Integer room, Integer hall, Long see,
            String mode) {
        this.id = id;
        this.cityName = cityName;
        this.housingTitle = housingTitle;
        this.address = address;
        this.groupName = groupName;
        this.fileName = fileName;
        this.rent = rent;
        this.state = state;
        this.housingModeId = housingModeId;
        this.room = room;
        this.hall = hall;
        this.see = see;
        this.mode = mode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getHousingTitle() {
        return housingTitle;
    }

    public void setHousingTitle(String housingTitle) {
        this.housingTitle = housingTitle;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Double getRent() {
        return rent;
    }

    public void setRent(Double rent) {
        this.rent = rent;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Long getHousingModeId() {
        return housingModeId;
    }

    public void setHousingModeId(Long housingModeId) {
        this.housingModeId = housingModeId;
    }

    public Integer getRoom() {
        return room;
    }

    public void setRoom(Integer room) {
        this.room = room;
    }

    public Integer getHall() {
        return hall;
    }

    public void setHall(Integer hall) {
        this.hall = hall;
    }

    public Long getSee() {
        return see;
    }

    public void setSee(Long see) {
        this.see = see;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    @Override
    public String toString() {
        return "HousingManagement{" +
                "id=" + id +
                ", cityName='" + cityName + '\'' +
                ", housingTitle='" + housingTitle + '\'' +
                ", address='" + address + '\'' +
                ", groupName='" + groupName + '\'' +
                ", fileName='" + fileName + '\'' +
                ", rent=" + rent +
                ", state=" + state +
                ", housingModeId=" + housingModeId +
                ", room=" + room +
                ", hall=" + hall +
                ", see=" + see +
                ", mode='" + mode + '\'' +
                '}';
    }
}
