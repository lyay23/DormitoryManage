<template>
    <el-card style="margin: 15px; min-height: calc(100vh - 80px)">
        <!--    头部数据-->
        <div>
            <el-row :gutter="20" class="topInfo">
                <el-col :span="6">
                    <div id="stuNumDiv" class="el-colDiv">
                        <!-- <div id="ssv1-main-text" class="nowDiv">实时</div> -->
                        <span class="title">学生统计</span><br />
                        <span class="digital">{{ this.studentNum }}</span><br />
                        <span class="last-span">当前分类总记录数</span>
                    </div>
                </el-col>
                <el-col :span="6">
                    <div id="haveRoomDiv" class="el-colDiv">
                        <!-- <div id="ssv2-main-text" class="nowDiv">实时</div> -->
                        <span class="title">住宿人数</span><br />
                        <span class="digital">{{ this.haveRoomStudentNum }}</span><br />
                        <span class="last-span">当前分类总记录数</span>
                    </div>
                </el-col>
                <el-col :span="6">
                    <div id="repairNum" class="el-colDiv">
                        <!-- <div id="ssv3-main-text" class="nowDiv">实时</div> -->
                        <span class="title">报修统计</span><br />
                        <span class="digital">{{ this.repairOrderNum }}</span><br />
                        <span class="last-span">当前分类总记录数</span>
                    </div>
                </el-col>
                <el-col :span="6">
                    <div id="emptyRoom" class="el-colDiv">
                        <!-- <div id="ssv4-main-text" class="nowDiv">实时</div> -->
                        <span class="title">空宿舍统计</span><br />
                        <span class="digital">{{ this.noFullRoomNum }}</span><br />
                        <span class="last-span">当前分类总记录数</span>
                    </div>
                </el-col>
            </el-row>
        </div>
        <!-- 下部-->
        <div style="display: flex;width: 100%;margin-top: 40px;align-items: center;justify-content: center;">
            <!--   左侧 宿舍通告-->
            <div style="margin-right: 5%; width: 350px;">
                <el-card shadow="hover" style="border-radius: 12px;">
                    <div
                        style="font-size: 22px; font-weight: bold; text-align: center; margin-bottom: 18px; letter-spacing: 2px;">
                        宿舍通告
                    </div>
                    <el-timeline>
                        <el-timeline-item v-for="(activity, index) in activities.slice(0, 8)" :key="index"
                            :timestamp="activity.releaseTime" placement="top" color="#409EFF"
                            style="padding-bottom: 10px;">
                            <div style="font-size: 16px; font-weight: 500; color: #222; margin-bottom: 2px;">
                                {{ activity.title }}
                            </div>
                            <div v-if="activity.content"
                                style="font-size: 13px; color: #666; margin-bottom: 2px; line-height: 1.5;"
                                v-html="activity.content"></div>
                            
                        </el-timeline-item>
                        <div v-if="activities.length === 0" style="text-align: center; color: #bbb; margin-top: 30px;">
                            暂无公告
                        </div>
                    </el-timeline>
                </el-card>
            </div>
            <div style="height: 588px">
                <span style="
            font-size: 22px;
            display: block;
            margin-bottom: 30px;
            margin-left: 10px;
          ">宿舍学生人数分布</span>
                <home_echarts />
            </div>
            <!--  右侧-->
            <div style="margin-left: 5%">

            </div>
        </div>
    </el-card>
</template>

<script src="@/assets/js/Home.js"></script>
<style scoped>
@import '../assets/css/Home.css';
</style>