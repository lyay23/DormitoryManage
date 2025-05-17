// import weather from "@/components/weather";
// import Calender from "@/components/Calendar";
import request from "@/utils/request";
import home_echarts from "@/components/home_echarts";

export default {
  name: "Home",
  components: {
    // weather,
    // Calender,
    home_echarts,
  },
  data() {
    return {
      studentNum: "",
      haveRoomStudentNum: "",
      detailDialog: false,
      repairOrderNum: "",
      noFullRoomNum: "",
      activities: [],
    };
  },
  created() {
    this.getHomePageNotice();
    this.getStuNum();
    this.getHaveRoomNum();
    this.getOrderNum();
    this.getNoFullRoom();
  },
  methods: {
    async getStuNum() {
      request.get("/stu/stuNum").then((res) => {
        if (res.code === "0") {
          this.studentNum = res.data;
        } else {
          ElMessage({
            message: res.msg,
            type: "error",
          });
        }
      });
    },
    async getHaveRoomNum() {
      request.get("/room/selectHaveRoomStuNum").then((res) => {
        if (res.code === "0") {
          this.haveRoomStudentNum = res.data;
        } else {
          ElMessage({
            message: res.msg,
            type: "error",
          });
        }
      });
    },
    async getOrderNum() {
      request.get("/repair/count").then((res) => {
        if (res.code === "0") {
          this.repairOrderNum = res.data;
        } else {
          ElMessage({
            message: res.msg,
            type: "error",
          });
        }
      });
    },
    async getNoFullRoom() {
      request.get("/room/noFullRoom").then((res) => {
        if (res.code === "0") {
          this.noFullRoomNum = res.data;
        } else {
          ElMessage({
            message: res.msg,
            type: "error",
          });
        }
      });
    },
    async getHomePageNotice() {
      request.get("/notice/homePageNotices").then((res) => {
        if (res.code === "0") {
          // 兼容返回 Result.success(list) 或 Result.success(PageInfo)
          if (Array.isArray(res.data)) {
            this.activities = res.data;
          } else if (res.data && res.data.list) {
            this.activities = res.data.list;
          } else {
            this.activities = [];
          }
        } else {
          ElMessage({
            message: res.msg,
            type: "error",
          });
          this.activities = [];
        }
      });
    },
  },
};
