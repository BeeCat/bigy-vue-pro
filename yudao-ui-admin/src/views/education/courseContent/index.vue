<template>
  <div class="app-container">

    <!-- 搜索工作栏 -->
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="课程" prop="courseName">
         <el-select v-model="queryParams.courseCode" placeholder="请选课程" clearable style="width: 100%">
           <el-option v-for="dict in courseList" :key="dict.courseCode" :label="dict.courseName" :value="dict.courseCode"/>
         </el-select>
      </el-form-item>
      <el-form-item label="课程主题" prop="courseSubject">
        <el-input v-model="queryParams.courseSubject" placeholder="请输入课程主题" clearable size="small" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="消课时长">
        <el-date-picker v-model="dateRangeCourseTime" size="small" style="width: 240px" value-format="yyyy-MM-dd"
                        type="daterange" range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期" />
      </el-form-item>
      <el-form-item label="创建时间">
        <el-date-picker v-model="dateRangeCreateTime" size="small" style="width: 240px" value-format="yyyy-MM-dd"
                        type="daterange" range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- 操作工具栏 -->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
                   v-hasPermi="['zhh:course-content:create']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
                   v-hasPermi="['zhh:course-content:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 列表 -->
    <el-table v-loading="loading" :data="list">
      <el-table-column label="" align="center" prop="id" />
      <el-table-column label="课程名称" align="center" prop="courseName" />
      <el-table-column label="课程主题" align="center" prop="courseSubject" />
      <el-table-column label="消课时长" align="center" prop="courseTime" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
                     v-hasPermi="['zhh:course-content:update']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
                     v-hasPermi="['zhh:course-content:delete']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNo" :limit.sync="queryParams.pageSize"
                @pagination="getList"/>

    <!-- 对话框(添加 / 修改) -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="课程名称" prop="courseName">
           <el-select v-model="form.courseCode" placeholder="请选课程" clearable @change="courseChange" style="width: 100%">
               <el-option v-for="dict in courseList" :key="dict.courseCode" :label="dict.courseName" :value="dict.courseCode"/>
             </el-select>
        </el-form-item>
        <el-form-item label="课程时长" prop="courseTime">
           <el-select v-model="form.courseTime" placeholder="请选择课程时长" clearable style="width: 100%" >
            <el-option v-for="dict in classTimeList" :key="dict.value" :label="dict.label" :value="dict.value"/>
          </el-select>
        </el-form-item>
        <el-form-item label="课程主题" prop="courseSubject">
          <el-input v-model="form.courseSubject" placeholder="请输入课程主题" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { createCourseContent, updateCourseContent, deleteCourseContent, getCourseContent, getCourseContentPage, exportCourseContentExcel } from "@/api/education/courseContent";
import {  getCoursePage } from "@/api/education/course";
import { getDictDataLabel, getDictDatas, DICT_TYPE } from '@/utils/dict'

export default {
  name: "CourseContent",
  components: {
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 课程体系列表
      list: [],
      courseList: [],
      classTimeList:[],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      dateRangeCourseTime: [],
      dateRangeCreateTime: [],
      // 查询参数
      queryParams: {
        pageNo: 1,
        pageSize: 10,
        courseName: null,
        courseCode: null,
        courseSubject: null,
      },
      // 表单参数
      form: {},
      courseMap:[],
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    getCoursePage({pageSize: 100}).then(response => {
      this.courseList = response.data.list;
      for(var index in this.courseList ) {
        this.courseMap[this.courseList[index].courseCode] = this.courseList[index]
      }
    });
    this.getList();
  },
  methods: {
    /** 查询列表 */
    getList() {
      this.loading = true;
      // 处理查询参数
      let params = {...this.queryParams};
      this.addBeginAndEndTime(params, this.dateRangeCourseTime, 'courseTime');
      this.addBeginAndEndTime(params, this.dateRangeCreateTime, 'createTime');
      // 执行查询
      getCourseContentPage(params).then(response => {
        this.list = response.data.list;

        this.total = response.data.total;
        this.loading = false;
      });
    },
    courseChange() {
      if (this.courseMap[this.form.courseCode].courseTime === "120") {
        this.classTimeList = getDictDatas(DICT_TYPE.ASSIGN_CLASS_TIME_120)
      } else if (this.courseMap[this.form.courseCode].courseTime === "60") {
         this.classTimeList = getDictDatas(DICT_TYPE.ASSIGN_CLASS_TIME_60)
      } else {
        this.classTimeList = getDictDatas(DICT_TYPE.ASSIGN_CLASS_TIME_90)
      }
    },
    /** 取消按钮 */
    cancel() {
      this.open = false;
      this.reset();
    },
    /** 表单重置 */
    reset() {
      this.form = {
        id: undefined,
        courseName: undefined,
        courseCode: undefined,
        courseSubject: undefined,
        courseTime: undefined,
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNo = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.dateRangeCourseTime = [];
      this.dateRangeCreateTime = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加课程体系";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id;
      getCourseContent(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改课程体系";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (!valid) {
          return;
        }
        // 修改的提交
        if (this.form.id != null) {
          updateCourseContent(this.form).then(response => {
            this.msgSuccess("修改成功");
            this.open = false;
            this.getList();
          });
          return;
        }
        // 添加的提交
        createCourseContent(this.form).then(response => {
          this.msgSuccess("新增成功");
          this.open = false;
          this.getList();
        });
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const id = row.id;
      this.$confirm('是否确认删除课程体系编号为"' + id + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return deleteCourseContent(id);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      // 处理查询参数
      let params = {...this.queryParams};
      params.pageNo = undefined;
      params.pageSize = undefined;
      this.addBeginAndEndTime(params, this.dateRangeCourseTime, 'courseTime');
      this.addBeginAndEndTime(params, this.dateRangeCreateTime, 'createTime');
      // 执行导出
      this.$confirm('是否确认导出所有课程体系数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportCourseContentExcel(params);
        }).then(response => {
          this.downloadExcel(response, '课程体系.xls');
        })
    }
  }
};
</script>
