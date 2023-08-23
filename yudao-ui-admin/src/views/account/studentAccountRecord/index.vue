<template>
  <div class="app-container">

    <!-- 搜索工作栏 -->
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="姓名" prop="name">
        <el-input v-model="queryParams.name" placeholder="请输入姓名" clearable size="small" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="学生账户id" prop="studentAccountId">
        <el-input v-model="queryParams.studentAccountId" placeholder="请输入学生账户id" clearable size="small" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="客户id" prop="customerId">
        <el-input v-model="queryParams.customerId" placeholder="请输入客户id" clearable size="small" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="学生id" prop="studentId">
        <el-input v-model="queryParams.studentId" placeholder="请输入学生id" clearable size="small" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="来源" prop="sourceFrom">
        <el-input v-model="queryParams.sourceFrom" placeholder="请输入来源" clearable size="small" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="来源老师" prop="teacherFrom">
        <el-input v-model="queryParams.teacherFrom" placeholder="请输入来源老师" clearable size="small" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="来源销售" prop="saleFrom">
        <el-input v-model="queryParams.saleFrom" placeholder="请输入来源销售" clearable size="small" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="课程编码" prop="classCode">
        <el-input v-model="queryParams.classCode" placeholder="请输入课程编码" clearable size="small" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="购买课程数" prop="buyClassAmount">
        <el-input v-model="queryParams.buyClassAmount" placeholder="请输入购买课程数" clearable size="small" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="剩余课程数" prop="haveClassAmount">
        <el-input v-model="queryParams.haveClassAmount" placeholder="请输入剩余课程数" clearable size="small" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="消课课程数" prop="costClassAmount">
        <el-input v-model="queryParams.costClassAmount" placeholder="请输入消课课程数" clearable size="small" @keyup.enter.native="handleQuery"/>
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
                   v-hasPermi="['zhh:student-account-record:create']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
                   v-hasPermi="['zhh:student-account-record:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 列表 -->
    <el-table v-loading="loading" :data="list">
      <el-table-column label="" align="center" prop="id" />
      <el-table-column label="姓名" align="center" prop="name" />
      <el-table-column label="学生账户id" align="center" prop="studentAccountId" />
      <el-table-column label="客户id" align="center" prop="customerId" />
      <el-table-column label="学生id" align="center" prop="studentId" />
      <el-table-column label="来源" align="center" prop="sourceFrom" />
      <el-table-column label="来源老师" align="center" prop="teacherFrom" />
      <el-table-column label="来源销售" align="center" prop="saleFrom" />
      <el-table-column label="课程编码" align="center" prop="classCode" />
      <el-table-column label="购买课程数" align="center" prop="buyClassAmount" />
      <el-table-column label="剩余课程数" align="center" prop="haveClassAmount" />
      <el-table-column label="消课课程数" align="center" prop="costClassAmount" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
                     v-hasPermi="['zhh:student-account-record:update']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
                     v-hasPermi="['zhh:student-account-record:delete']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNo" :limit.sync="queryParams.pageSize"
                @pagination="getList"/>

    <!-- 对话框(添加 / 修改) -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="姓名" prop="name">
          <el-input v-model="form.name" placeholder="请输入姓名" />
        </el-form-item>
        <el-form-item label="学生账户id" prop="studentAccountId">
          <el-input v-model="form.studentAccountId" placeholder="请输入学生账户id" />
        </el-form-item>
        <el-form-item label="客户id" prop="customerId">
          <el-input v-model="form.customerId" placeholder="请输入客户id" />
        </el-form-item>
        <el-form-item label="学生id" prop="studentId">
          <el-input v-model="form.studentId" placeholder="请输入学生id" />
        </el-form-item>
        <el-form-item label="来源" prop="sourceFrom">
          <el-input v-model="form.sourceFrom" placeholder="请输入来源" />
        </el-form-item>
        <el-form-item label="来源老师" prop="teacherFrom">
          <el-input v-model="form.teacherFrom" placeholder="请输入来源老师" />
        </el-form-item>
        <el-form-item label="来源销售" prop="saleFrom">
          <el-input v-model="form.saleFrom" placeholder="请输入来源销售" />
        </el-form-item>
        <el-form-item label="课程编码" prop="classCode">
          <el-input v-model="form.classCode" placeholder="请输入课程编码" />
        </el-form-item>
        <el-form-item label="购买课程数" prop="buyClassAmount">
          <el-input v-model="form.buyClassAmount" placeholder="请输入购买课程数" />
        </el-form-item>
        <el-form-item label="剩余课程数" prop="haveClassAmount">
          <el-input v-model="form.haveClassAmount" placeholder="请输入剩余课程数" />
        </el-form-item>
        <el-form-item label="消课课程数" prop="costClassAmount">
          <el-input v-model="form.costClassAmount" placeholder="请输入消课课程数" />
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
import { createStudentAccountRecord, updateStudentAccountRecord, deleteStudentAccountRecord, getStudentAccountRecord, getStudentAccountRecordPage, exportStudentAccountRecordExcel } from "@/api/account/studentAccountRecord";

export default {
  name: "StudentAccountRecord",
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
      // 学生账户列表
      list: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      dateRangeCreateTime: [],
      // 查询参数
      queryParams: {
        pageNo: 1,
        pageSize: 10,
        name: null,
        studentAccountId: null,
        customerId: null,
        studentId: null,
        sourceFrom: null,
        teacherFrom: null,
        saleFrom: null,
        classCode: null,
        buyClassAmount: null,
        haveClassAmount: null,
        costClassAmount: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询列表 */
    getList() {
      this.loading = true;
      // 处理查询参数
      let params = {...this.queryParams};
      this.addBeginAndEndTime(params, this.dateRangeCreateTime, 'createTime');
      // 执行查询
      getStudentAccountRecordPage(params).then(response => {
        this.list = response.data.list;
        this.total = response.data.total;
        this.loading = false;
      });
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
        name: undefined,
        studentAccountId: undefined,
        customerId: undefined,
        studentId: undefined,
        sourceFrom: undefined,
        teacherFrom: undefined,
        saleFrom: undefined,
        classCode: undefined,
        buyClassAmount: undefined,
        haveClassAmount: undefined,
        costClassAmount: undefined,
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
      this.dateRangeCreateTime = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加学生账户";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id;
      getStudentAccountRecord(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改学生账户";
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
          updateStudentAccountRecord(this.form).then(response => {
            this.msgSuccess("修改成功");
            this.open = false;
            this.getList();
          });
          return;
        }
        // 添加的提交
        createStudentAccountRecord(this.form).then(response => {
          this.msgSuccess("新增成功");
          this.open = false;
          this.getList();
        });
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const id = row.id;
      this.$confirm('是否确认删除学生账户编号为"' + id + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return deleteStudentAccountRecord(id);
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
      this.addBeginAndEndTime(params, this.dateRangeCreateTime, 'createTime');
      // 执行导出
      this.$confirm('是否确认导出所有学生账户数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportStudentAccountRecordExcel(params);
        }).then(response => {
          this.downloadExcel(response, '学生账户.xls');
        })
    }
  }
};
</script>
